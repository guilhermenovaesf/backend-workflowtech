package com.example.gestao_fluxos_trabalho.DTO;

public class WorkflowStepAproveRejectDTO {
        private Long workflowId;
        private Long workflowStepId;
        private String comment;
        private boolean aproved;

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public Long getWorkflowStepId() {
        return workflowStepId;
    }

    public void setWorkflowStepId(Long workflowStepId) {
        this.workflowStepId = workflowStepId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isAproved() {
        return aproved;
    }

    public void setAproved(boolean aproved) {
        this.aproved = aproved;
    }

    // Getters and setters
    }