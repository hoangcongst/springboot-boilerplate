package com.hoangcongst.sbboilerplate.model;

import com.conght.common.database.interceptor.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_project_info")
@Data
@Validated
@EntityListeners(AuditingEntityListener.class)
public class Project extends BaseModel {
    public static final byte STATUS_CREATED = 1;
    public static final byte STATUS_DELETED = 7;

    public Project() {}
    public Project(long id) {this.id = id;}

    public Project(String name, String description, List<Long> taskManagerIds, long projectManagerId) {
        this.name = name;
        this.description = description;
        this.status = STATUS_CREATED;
        List<User> taskManagers = new ArrayList<>();
        taskManagerIds.forEach(taskManagerId -> taskManagers.add(new User(taskManagerId)));
        this.taskManagers = taskManagers;
        this.projectManager = new User(projectManagerId);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="pm_admin_seq")
    private User projectManager;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_rel_admin_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id"))
    @JsonManagedReference
    private List<User> taskManagers;

    private byte status;
    private String name;
    private byte type;
    private String description;
    private Timestamp startDtm;
    private Timestamp endDtm;

    @ManyToOne
    @JoinColumn(name = "ins_admin_id")
    @CreatedBy
    private User insAdmin;

    @CreatedDate
    private Timestamp insDtm;
}
