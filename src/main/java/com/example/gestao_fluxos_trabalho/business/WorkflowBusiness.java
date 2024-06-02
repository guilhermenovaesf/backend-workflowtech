package com.example.gestao_fluxos_trabalho.business;


import com.example.gestao_fluxos_trabalho.DTO.WorkflowDTO;

import java.util.List;

public interface WorkflowBusiness {

    public void createWorkflow(WorkflowDTO workflowDTO, Long loggedUserId);
    public List<WorkflowDTO> listOpenWorkflowUser(Long userId);
    public List<WorkflowDTO> listClosedWorkflowUser(Long userId);
}
