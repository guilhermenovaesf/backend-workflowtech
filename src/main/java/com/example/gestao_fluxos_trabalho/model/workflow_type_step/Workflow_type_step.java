package com.example.gestao_fluxos_trabalho.model.workflow_type_step;
import com.example.gestao_fluxos_trabalho.model.users.Users;
import com.example.gestao_fluxos_trabalho.model.workflow_type.Workflow_type;
import jakarta.persistence.*;

@Entity
@Table(name = "workflow_type_step")
public class Workflow_type_step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "WORKFLOW_TYPE_ID", referencedColumnName = "id")
    private Workflow_type workflowType;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private Users user;

    // Constructors, getters, and setters

    public Workflow_type_step() {
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}