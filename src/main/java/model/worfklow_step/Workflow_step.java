package model.worfklow_step;

import jakarta.persistence.*;
import model.users.Users;
import model.workflow.Workflow;
import model.workflow_type_step.Workflow_type_step;

@Entity
@Table(name = "workflow_step")
public class Workflow_step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "WORKFLOW_ID", referencedColumnName = "id")
    private Workflow workflow;

    @ManyToOne
    @JoinColumn(name = "WORKFLOW_TYPE_STEP_ID", referencedColumnName = "id")
    private Workflow_type_step workflowTypeStep;

    @Column(name = "WORKFLOW_TYPE_STEP_DESCRIPTION")
    private String workflowTypeStepDescription;

    @Column(name = "COMMENT", length = 255)
    private String comment;

    @Column(name = "IS_APPROVED", columnDefinition = "int default 0")
    private int isApproved;

    @ManyToOne
    @JoinColumn(name = "ASSIGNED_TO", referencedColumnName = "id")
    private Users assignedTo;

    @Column(name = "IS_CURRENT", columnDefinition = "int default 0")
    private int isCurrent;

    // Constructors, getters, and setters

    public Workflow_step() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    public Workflow_type_step getWorkflowTypeStep() {
        return workflowTypeStep;
    }

    public void setWorkflowTypeStep(Workflow_type_step workflowTypeStep) {
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

    public Users getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Users assignedTo) {
        this.assignedTo = assignedTo;
    }

    public int getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(int isCurrent) {
        this.isCurrent = isCurrent;
    }
}
