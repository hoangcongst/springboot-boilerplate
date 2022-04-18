package com.hoangcongst.sbboilerplate.auth.service;

import com.hoangcongst.sbboilerplate.auth.UserRepository;
import com.hoangcongst.sbboilerplate.auth.exception.UserNotFoundException;
import com.hoangcongst.sbboilerplate.auth.model.User;
import com.hoangcongst.sbboilerplate.auth.model.UserSecretInfo;
import com.hoangcongst.sbboilerplate.auth.vo.AdminIndexRequest;
import com.hoangcongst.sbboilerplate.auth.UserSpecification;
import com.conght.common.requestcriteria.builder.GenericSpecificationsBuilder;
import com.conght.common.requestcriteria.util.CriteriaParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.hoangcongst.sbboilerplate.auth.vo.UserCreateRequest;
import com.hoangcongst.sbboilerplate.auth.vo.UserUpdateRequest;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository mUserRepository;

    public UserServiceImpl(UserRepository mUserRepository) {
        this.mUserRepository = mUserRepository;
    }

    @Override
    public User create(UserCreateRequest userCreateRequest) {
        return null;
    }

    @Override
    public UserSecretInfo show(long userId) throws Exception {
        Optional<UserSecretInfo> result = this.mUserRepository.findUserById(userId, UserSecretInfo.class);
        if (result.isPresent())
            return result.get();
        else throw new UserNotFoundException();
    }

    @Override
    public Page<User> list(AdminIndexRequest adminIndexRequest, Pageable pageable) {
        Specification<User> specs = resolveSpecificationFromInfixExpr(adminIndexRequest);
        return this.mUserRepository.findAll(specs, pageable);
    }

    protected Specification<User> resolveSpecificationFromInfixExpr(AdminIndexRequest adminIndexRequest) {
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<User> specBuilder = new GenericSpecificationsBuilder<>();
        specBuilder.with(parser.parse(adminIndexRequest));
        return specBuilder.build(UserSpecification::new);
    }

    @Override
    public UserSecretInfo update(long userId, UserUpdateRequest userUpdateRequest) throws Exception {
        Optional<User> result = this.mUserRepository.findUserById(userId, User.class);
        if (result.isPresent()) {
            User findUser = result.get();
            if (!findUser.getEmail().equals(userUpdateRequest.getEmail()))
                findUser.setEmail(userUpdateRequest.getEmail());

            if (!findUser.getName().equals(userUpdateRequest.getDisplay_name()))
                findUser.setName(userUpdateRequest.getDisplay_name());

            if (userUpdateRequest.getPassword() != null) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
                findUser.setPassword(bCryptPasswordEncoder.encode(userUpdateRequest.getPassword()));
            }

            User savedUser = this.mUserRepository.save(findUser);
            return this.show(savedUser.getId());
        } else
            throw new UserNotFoundException();
    }
}
