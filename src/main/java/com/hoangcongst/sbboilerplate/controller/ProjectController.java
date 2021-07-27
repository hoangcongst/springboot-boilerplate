package com.hoangcongst.sbboilerplate.controller;

import com.hoangcongst.sbboilerplate.model.Project;
import com.hoangcongst.sbboilerplate.request.ProjectRequest;
import com.hoangcongst.sbboilerplate.response.BaseResponse;
import com.hoangcongst.sbboilerplate.service.ProjectService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/project")
@SecurityRequirement(name = "Bearer")
public class ProjectController {
    private final ProjectService projectService;

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> index(@RequestParam(name = "name", required = false) String name,
                                   @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                   @RequestParam(name = "size", required = false, defaultValue = "15") Integer size) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(new BaseResponse(BaseResponse.SUCCESS, projectService.list(name, pageable)));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> show(@PathVariable("id") long id) throws Exception {
        Optional<Project> result = this.projectService.show(id);
        return result.map(post -> ResponseEntity.ok(new BaseResponse(BaseResponse.SUCCESS, post)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse(BaseResponse.FAILED)));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid ProjectRequest projectRequest) throws Exception {
        Project result = this.projectService.create(projectRequest);
        return ResponseEntity.ok(new BaseResponse(BaseResponse.SUCCESS, result));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("id") long id,
                                    @RequestBody @Valid ProjectRequest projectRequest) throws Exception {
        return ResponseEntity.ok(new BaseResponse(BaseResponse.SUCCESS, this.projectService.update(id, projectRequest)));
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") long id) throws Exception {
        this.projectService.delete(id);
        return ResponseEntity.ok(new BaseResponse(BaseResponse.SUCCESS));
    }
}
