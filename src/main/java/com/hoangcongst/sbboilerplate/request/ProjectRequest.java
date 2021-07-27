package com.hoangcongst.sbboilerplate.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ProjectRequest {
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @Size(min = 1, max = 255)
    private String description;

    private List<Long> taskManagerIds;

    @NotNull(message = "Invalid project manager")
    private Long projectManagerId;

    @NotNull(message = "Invalid company")
    private Long companyId;

    public ProjectRequest(String name, String description, List<Long> taskManagerIds, long projectManagerId, long companyId) {
        this.name = name;
        this.description = description;
        this.taskManagerIds = taskManagerIds;
        this.projectManagerId = projectManagerId;
        this.companyId = companyId;
    }
}
