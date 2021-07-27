package com.hoangcongst.sbboilerplate.controller;

import com.hoangcongst.sbboilerplate.request.AdminIndexRequest;
import com.hoangcongst.sbboilerplate.response.BaseResponse;
import com.hoangcongst.sbboilerplate.service.jwt.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@SecurityRequirement(name = "Bearer")
public class AdminController {
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    public AdminController(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> index(@Valid AdminIndexRequest request) throws Exception {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(new BaseResponse(BaseResponse.SUCCESS, userService.list(request, pageable)));
    }
}
