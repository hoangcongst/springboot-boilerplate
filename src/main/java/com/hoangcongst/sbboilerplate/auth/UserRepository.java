package com.hoangcongst.sbboilerplate.auth;

import com.hoangcongst.sbboilerplate.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findUserByEmail(String userName);

    <T> Optional<T> findUserById(long id, Class<T> type);
}