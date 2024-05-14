package com.example.gestao_fluxos_trabalho.DTO;

import com.example.gestao_fluxos_trabalho.model.workflow_type_step.Workflow_type_step;

public class WorkflowTypeStepDTO {

    private int id;
    private int workflowTypeId;
    private String description;
    private UsersDTO user;

    // Constructors
    public WorkflowTypeStepDTO() {
    }

    public WorkflowTypeStepDTO(Workflow_type_step step) {
        this.id = step.getId();
        this.workflowTypeId = step.getWorkflowType().getId();
        this.description = step.getDescription();
        this.user = new UsersDTO(step.getUser());
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

    public UsersDTO getUserId() {
        return user;
    }

    public void setUserId(UsersDTO user) {
        this.user = user;
    }
}
