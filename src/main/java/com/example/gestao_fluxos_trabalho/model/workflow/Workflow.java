package com.example.gestao_fluxos_trabalho.model.workflow;

import com.example.gestao_fluxos_trabalho.model.users.Users;
import com.example.gestao_fluxos_trabalho.model.workflow_type.Workflow_type;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "workflow")
public class Workflow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity= Workflow_type.class)
    @JoinColumn(name="WORKFLOW_TYPE_ID")
    private Workflow_type workflowType;

    @Column(name = "DESCRIPTION", columnDefinition = "longtext", nullable = false)
    private String description;

    @ManyToOne(fetch=FetchType.EAGER, targetEntity= Users.class, optional=false)
    @JoinColumn(name="CREATED_BY")
    private Users createdBy;

    @Column(name = "CREATED_ON")
    private Date createdOn;

    @Column(name = "FINISHED", columnDefinition = "int default 0")
    private int finished;

    @Column(name = "CANCELED", columnDefinition = "int default 0")
    private int canceled;

    // Constructors, getters, and setters

    public Workflow() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Workflow_type getWorkflowType() {
        return workflowType;
    }

    public void setWorkflowType(Workflow_type workflowType) {
        this.workflowType = workflowType;
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
}
