package com.example.gestao_fluxos_trabalho.DTO;

import com.example.gestao_fluxos_trabalho.model.users.Users;
import com.example.gestao_fluxos_trabalho.model.workflow_type.Workflow_type;
import com.example.gestao_fluxos_trabalho.model.workflow_type_step.Workflow_type_step;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class WorkflowTypeDTO {
    private int id;
    private String title;
    private String description;
    private UsersDTO createdBy;
    private Date createdOn;
    private List<WorkflowTypeStepDTO> workflowTypeStepTOList;

    // Constructors
    public WorkflowTypeDTO() {
    }

    public WorkflowTypeDTO(Workflow_type workflowType) {
        this.id = workflowType.getId();
        this.title = workflowType.getTitle();
        this.description = workflowType.getDescription();
        this.createdBy = new UsersDTO(workflowType.getCreatedBy());
        this.createdOn = workflowType.getCreatedOn();
        this.workflowTypeStepTOList = workflowType.getWorkflowTypeStepList() != null ? workflowType.getWorkflowTypeStepList()
                        .stream()
                        .filter(workflowTypeStep -> workflowTypeStep != null)
                        .map(WorkflowTypeStepDTO::new)
                        .collect(Collectors.toList()) :
                new ArrayList<WorkflowTypeStepDTO>();
    }

    public WorkflowTypeDTO(Workflow_type workflowType,boolean workflowTypeStepPut) {
        this.id = workflowType.getId();
        this.title = workflowType.getTitle();
        this.description = workflowType.getDescription();
        this.createdBy = new UsersDTO(workflowType.getCreatedBy());
        this.createdOn = workflowType.getCreatedOn();
        if(workflowTypeStepPut) {
            this.workflowTypeStepTOList = workflowType.getWorkflowTypeStepList() != null ? workflowType.getWorkflowTypeStepList()
                    .stream()
                    .filter(workflowTypeStep -> workflowTypeStep != null)
                    .map(WorkflowTypeStepDTO::new)
                    .collect(Collectors.toList()) :
                    new ArrayList<WorkflowTypeStepDTO>();
        }
    }

    // Getters and Setters

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

    public UsersDTO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UsersDTO createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    public List<WorkflowTypeStepDTO> getWorkflowTypeStepTOList() {
        return workflowTypeStepTOList;
    }

    public void setWorkflowTypeStepTOList(List<WorkflowTypeStepDTO> workflowTypeStepTOList) {
        this.workflowTypeStepTOList = workflowTypeStepTOList;
    }
    public List<Workflow_type_step> toWorkflowTypeStepList(Users user) {
        return workflowTypeStepTOList.stream()
                .map(dto -> {
                    Workflow_type_step step = new Workflow_type_step();
                    step.setId(dto.getId());
                    step.setDescription(dto.getDescription());
                    step.setUser(user); // Configuração do usuário
                    return step;
                })
                .collect(Collectors.toList());
    }

}
