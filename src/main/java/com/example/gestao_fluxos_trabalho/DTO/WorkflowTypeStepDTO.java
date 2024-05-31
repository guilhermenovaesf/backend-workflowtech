package com.example.gestao_fluxos_trabalho.DTO;

import com.example.gestao_fluxos_trabalho.model.workflow_type_step.Workflow_type_step;

public class WorkflowTypeStepDTO {

    private int id;
    private int workflowTypeId;
    private String description;
    private UsersDTO user;
    private long userId;

    // Constructors
    public WorkflowTypeStepDTO() {
    }

    public WorkflowTypeStepDTO(Workflow_type_step step) {
        this.id = step.getId();
        this.workflowTypeId = step.getWorkflowType().getId();
        this.description = step.getDescription();
        this.user = step.getUser() !=null ? new UsersDTO(step.getUser()) : null ;
        this.userId = step.getUser() !=null ? step.getUser().getId() : 0;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkflowTypeId() {
        return workflowTypeId;
    }

    public void setWorkflowTypeId(int workflowTypeId) {
        this.workflowTypeId = workflowTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
