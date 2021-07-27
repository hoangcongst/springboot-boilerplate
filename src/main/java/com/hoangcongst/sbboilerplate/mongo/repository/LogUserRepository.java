package com.hoangcongst.sbboilerplate.mongo.repository;

import com.hoangcongst.sbboilerplate.mongo.model.LogUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogUserRepository extends MongoRepository<LogUser, String> {
}
