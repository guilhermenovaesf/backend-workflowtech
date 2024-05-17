package com.example.gestao_fluxos_trabalho.DTO;

import com.example.gestao_fluxos_trabalho.model.workflow.Workflow;

import java.util.Date;

public class WorkflowDTO {

    private int id;
    private WorkflowTypeDTO workflowType;
    private String description;
    private UsersDTO createdBy;
    private Date createdOn;
    private int finished;
    private int canceled;
    private int workflowTypeId;

    public WorkflowDTO() {
    }

    public WorkflowDTO(Workflow workflow) {
        this.id = workflow.getId();
        this.workflowType = new WorkflowTypeDTO(workflow.getWorkflowType());
        this.description = workflow.getDescription();
        this.createdBy = new UsersDTO(workflow.getCreatedBy());
        this.createdOn = workflow.getCreatedOn();
        this.finished = workflow.getFinished();
        this.canceled = workflow.getCanceled();
        this.workflowTypeId = workflow.getWorkflowType().getId();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WorkflowTypeDTO getWorkflowType() {
        return workflowType;
    }

    public void setWorkflowType(WorkflowTypeDTO workflowType) {
        this.workflowType = workflowType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public int getCanceled() {
        return canceled;
    }

    public void setCanceled(int canceled) {
        this.canceled = canceled;
    }

    public int getWorkflowTypeId() {
        return workflowTypeId;
    }

    public void setWorkflowTypeId(int workflowTypeId) {
        this.workflowTypeId = workflowTypeId;
    }

    public UsersDTO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UsersDTO createdBy) {
        this.createdBy = createdBy;
    }
}
