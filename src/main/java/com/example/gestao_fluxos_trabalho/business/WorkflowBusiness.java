package com.example.gestao_fluxos_trabalho.business;


import com.example.gestao_fluxos_trabalho.DTO.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WorkflowBusiness {

    public void createWorkflow(WorkflowDTO workflowDTO, Long loggedUserId);
    public List<WorkflowDTO> listOpenWorkflowUser(Long userId);
    public List<WorkflowDTO> listClosedWorkflowUser(Long userId);
    public List<WorkflowMyListDTO> listMyWorkflows(Long loggedUserId);
    public List<WorkflowAssignedToMeDTO> listWorkflowAssignedToMe(Long loggedUserId);
    public List<WorkflowStepDTO> listWorkflowStepByWorkflowId(Long workflowId);
    public void aproveOrRejectWorklow(WorkflowStepAproveRejectDTO workflowData);
}
