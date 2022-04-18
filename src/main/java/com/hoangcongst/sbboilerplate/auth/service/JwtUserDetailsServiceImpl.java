package com.hoangcongst.sbboilerplate.auth.service;

import com.hoangcongst.sbboilerplate.auth.UserRepository;
import com.hoangcongst.sbboilerplate.auth.model.User;
import com.hoangcongst.sbboilerplate.auth.model.UserSecretInfo;
import com.hoangcongst.sbboilerplate.auth.vo.AdminIndexRequest;
import com.hoangcongst.sbboilerplate.util.MysqlSHAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.hoangcongst.sbboilerplate.auth.vo.UserCreateRequest;
import com.hoangcongst.sbboilerplate.auth.vo.UserUpdateRequest;

@Service("jwtUserService")
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;

    public JwtUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User mUser = this.userRepository.findUserByEmail(email);
        if (mUser == null) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", email));
        }
        return mUser;
    }
}
