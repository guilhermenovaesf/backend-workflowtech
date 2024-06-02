package com.example.gestao_fluxos_trabalho.DTO;

import java.util.Date;

public class WorkflowAssignedToMeDTO {
    private Long workflowId;
    private Long workflowStepId;
    private String title;
    private Date createdOn;
    private String userName;

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public Long getWorkflowStepId() {
        return workflowStepId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWorkflowStepId(Long workflowStepId) {
        this.workflowStepId = workflowStepId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
