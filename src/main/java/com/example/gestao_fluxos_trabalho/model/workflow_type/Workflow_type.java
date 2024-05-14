package com.example.gestao_fluxos_trabalho.model.workflow_type;

import com.example.gestao_fluxos_trabalho.model.users.Users;
import com.example.gestao_fluxos_trabalho.model.workflow_type_step.Workflow_type_step;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "workflow_type")
public class Workflow_type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TITLE", nullable = false, length = 500)
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "longtext")
    private String description;

    @JoinColumn(name = "CREATED_BY", referencedColumnName = "id")
    private Users createdBy;

    @Column(name = "CREATED_ON", nullable = false)
    private Date createdOn;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Workflow_type_step.class)
    @Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    @JoinColumn(name = "WORKFLOW_TYPE_ID")
    private Collection<Workflow_type_step> workflowTypeStepList;

    // Constructors, getters, and setters

    public Workflow_type() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Collection<Workflow_type_step> getWorkflowTypeStepList() {
        return workflowTypeStepList;
    }

    public void setWorkflowTypeStepList(Collection<Workflow_type_step> workflowTypeStepList) {
        this.workflowTypeStepList = workflowTypeStepList;
    }
}