package com.hoangcongst.sbboilerplate.service.jwt;

import com.hoangcongst.sbboilerplate.repository.UserRepository;
import com.hoangcongst.sbboilerplate.model.User;
import com.hoangcongst.sbboilerplate.model.UserSecretInfo;
import com.hoangcongst.sbboilerplate.request.AdminIndexRequest;
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
import com.hoangcongst.sbboilerplate.request.UserCreateRequest;
import com.hoangcongst.sbboilerplate.request.UserUpdateRequest;

@Service("jwtUserService")
@Qualifier("jwtUserDetailsService")
public class JwtUserDetailsServiceImpl implements UserDetailsService, UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;

    public JwtUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User mUser = this.userRepository.findUserByEmail(MysqlSHAUtil.encrypt(email));
        if (mUser == null) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", email));
        }
        return mUser;
    }

    @Override
    public User create(UserCreateRequest userCreateRequest) {
        return null;
    }

    @Override
    public UserSecretInfo show(long userId) throws Exception {
        return null;
    }

    @Override
    public Page<User> list(AdminIndexRequest adminIndexRequest, Pageable pageable) {
        return null;
    }

    @Override
    public UserSecretInfo update(long userId, UserUpdateRequest userCreateRequest) throws Exception {
        return null;
    }
}
