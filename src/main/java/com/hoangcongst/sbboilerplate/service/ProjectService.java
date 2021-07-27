package com.hoangcongst.sbboilerplate.service;

import com.hoangcongst.sbboilerplate.model.Project;
import com.hoangcongst.sbboilerplate.request.ProjectRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProjectService {
    Page<Project> list(String search, Pageable pageable);
    Project create(ProjectRequest projectRequest);
    Project update(long id, ProjectRequest projectRequest) throws Exception;
    Optional<Project> show(long id);
    void delete(long id);
}
