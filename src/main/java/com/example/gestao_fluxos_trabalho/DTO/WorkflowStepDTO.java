package com.example.gestao_fluxos_trabalho.DTO;

import com.example.gestao_fluxos_trabalho.model.worfklow_step.Workflow_step;

public class WorkflowStepDTO {
    private int id;
    private WorkflowDTO workflow;
    private WorkflowTypeStepDTO workflowTypeStep;
    private String workflowTypeStepDescription;
    private String comment;
    private int isApproved;
    private UsersDTO assignedTo;
    private int isCurrent;

    // Constructors
    public WorkflowStepDTO() {
    }

    public WorkflowStepDTO(Workflow_step workflowStep) {
        this.id = workflowStep.getId();
        this.workflow = new WorkflowDTO(workflowStep.getWorkflow());
        this.workflowTypeStep = new WorkflowTypeStepDTO(workflowStep.getWorkflowTypeStep());
        this.workflowTypeStepDescription = workflowStep.getWorkflowTypeStepDescription();
        this.comment = workflowStep.getComment();
        this.isApproved = workflowStep.getIsApproved();
        this.assignedTo = new UsersDTO(workflowStep.getAssignedTo());
        this.isCurrent = workflowStep.getIsCurrent();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WorkflowDTO getWorkflow() {
        return workflow;
    }

    public void setWorkflow(WorkflowDTO workflow) {
        this.workflow = workflow;
    }

    public WorkflowTypeStepDTO getWorkflowTypeStep() {
        return workflowTypeStep;
    }

    public void setWorkflowTypeStep(WorkflowTypeStepDTO workflowTypeStep) {
        this.workflowTypeStep = workflowTypeStep;
    }

    public String getWorkflowTypeStepDescription() {
        return workflowTypeStepDescription;
    }

    public void setWorkflowTypeStepDescription(String workflowTypeStepDescription) {
        this.workflowTypeStepDescription = workflowTypeStepDescription;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

    public UsersDTO getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(UsersDTO assignedTo) {
        this.assignedTo = assignedTo;
    }

    public int getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(int isCurrent) {
        this.isCurrent = isCurrent;
    }
}
