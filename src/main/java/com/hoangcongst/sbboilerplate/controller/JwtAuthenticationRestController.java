package com.hoangcongst.sbboilerplate.controller;

import com.hoangcongst.sbboilerplate.response.JwtTokenResponse;
import com.hoangcongst.sbboilerplate.exception.AuthenticationException;
import com.hoangcongst.sbboilerplate.model.User;
import com.hoangcongst.sbboilerplate.util.JwtTokenUtil;
import com.hoangcongst.sbboilerplate.request.JwtTokenRequest;
import com.hoangcongst.sbboilerplate.service.jwt.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

@Validated
@RestController
@RequestMapping("/auth")
public class JwtAuthenticationRestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService jwtUserDetailsService;
    private final UserService userService;

    public JwtAuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                                           @Qualifier("jwtUserDetailsService") UserDetailsService jwtUserDetailsService,
                                           @Qualifier("userService") UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
            throws AuthenticationException {

        try {
            authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
            final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());

            final String token = jwtTokenUtil.generateToken(userDetails);
            Date expired = jwtTokenUtil.getExpirationDateFromToken(token);
            return ResponseEntity.ok(new JwtTokenResponse(JwtTokenResponse.SUCCESS, token, expired, (User) userDetails));
        } catch (Exception e) {
            this.logger.debug(e.getMessage());
            JwtTokenResponse res = new JwtTokenResponse(JwtTokenResponse.FAILED, null, null, null);
            res.setMessage("Wrong account or password!");
            return ResponseEntity.badRequest().body(res);
        }
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    @Operation(summary = "refreshToken", security = @SecurityRequirement(name = "Bearer"))
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User developer = (User) jwtUserDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtTokenResponse(JwtTokenResponse.SUCCESS, refreshedToken, jwtTokenUtil.getExpirationDateFromToken(refreshedToken), developer));
        } else {
            return ResponseEntity.badRequest().body(new JwtTokenResponse(JwtTokenResponse.FAILED, null, null, null));
        }
    }

//    @RequestMapping(value = "register", method = RequestMethod.POST)
//    public ResponseEntity<?> register(@ModelAttribute @Valid UserCreateRequest userCreateRequest) {
//        User user = this.userService.create(userCreateRequest);
//        final String token = jwtTokenUtil.generateToken(user);
//        Date expired = jwtTokenUtil.getExpirationDateFromToken(token);
//        return ResponseEntity.ok(new JwtTokenResponse(JwtTokenResponse.SUCCESS,
//                token, expired, user));
//    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }
    }
}
