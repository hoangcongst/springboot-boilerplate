package com.hoangcongst.sbboilerplate.auth.service;

import com.hoangcongst.sbboilerplate.auth.model.User;
import com.hoangcongst.sbboilerplate.auth.model.UserSecretInfo;
import com.hoangcongst.sbboilerplate.auth.vo.AdminIndexRequest;
import com.hoangcongst.sbboilerplate.auth.vo.UserCreateRequest;
import com.hoangcongst.sbboilerplate.auth.vo.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User create(UserCreateRequest userCreateRequest);
    UserSecretInfo show(long userId) throws Exception;
    Page<User> list(AdminIndexRequest adminIndexRequest, Pageable pageable);
    UserSecretInfo update(long userId, UserUpdateRequest userCreateRequest) throws Exception;
}
