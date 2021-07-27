package com.hoangcongst.sbboilerplate.mongo.repository;

import com.hoangcongst.sbboilerplate.mongo.model.LogProject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogProjectRepository extends MongoRepository<LogProject, String> {
}
