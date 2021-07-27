package com.hoangcongst.sbboilerplate.service.impl;

import com.hoangcongst.sbboilerplate.model.Project;
import com.hoangcongst.sbboilerplate.repository.ProjectRepository;
import com.hoangcongst.sbboilerplate.request.ProjectRequest;
import com.hoangcongst.sbboilerplate.service.ProjectService;
import com.conght.common.requestcriteria.util.RequestPropertiesUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Page<Project> list(String name, Pageable pageable) {
        return this.projectRepository.findAll(pageable);
    }

    public Optional<Project> show(long id) {
        return this.projectRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        Optional<Project> result = this.show(id);
        result.ifPresent(project -> project.setStatus(Project.STATUS_DELETED));
    }

    @Override
    public Project create(ProjectRequest projectRequest) {
        Project project = new Project(projectRequest.getName(), projectRequest.getDescription(),
                projectRequest.getTaskManagerIds(), projectRequest.getProjectManagerId());
        return this.projectRepository.save(project);
    }

    @Override
    public Project update(long id, ProjectRequest projectRequest) throws Exception {
        Optional<Project> result = this.show(id);
        if (result.isPresent()) {
            Project project = result.get();
            RequestPropertiesUtil.copyNonNullProperties(projectRequest, project);
            return this.projectRepository.save(project);
        }
        throw new Exception("Cannot update project " + id);
    }
}
