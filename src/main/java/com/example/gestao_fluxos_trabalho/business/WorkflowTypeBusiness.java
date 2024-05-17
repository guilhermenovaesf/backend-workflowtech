package com.example.gestao_fluxos_trabalho.business;

import com.example.gestao_fluxos_trabalho.DTO.WorkflowTypeDTO;

import java.util.List;

public interface WorkflowTypeBusiness {
    public void createWorkflowType(WorkflowTypeDTO workflowTypeDTO);

    public WorkflowTypeDTO getWorkflowType(int id);

    public List<WorkflowTypeDTO> listWorkflowType();
}
