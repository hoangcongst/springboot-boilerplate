package com.hoangcongst.sbboilerplate.service.jwt;

import com.hoangcongst.sbboilerplate.model.User;
import com.hoangcongst.sbboilerplate.model.UserSecretInfo;
import com.hoangcongst.sbboilerplate.request.AdminIndexRequest;
import com.hoangcongst.sbboilerplate.request.UserCreateRequest;
import com.hoangcongst.sbboilerplate.request.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User create(UserCreateRequest userCreateRequest);
    UserSecretInfo show(long userId) throws Exception;
    Page<User> list(AdminIndexRequest adminIndexRequest, Pageable pageable);
    UserSecretInfo update(long userId, UserUpdateRequest userCreateRequest) throws Exception;
}
