package com.example.gestao_fluxos_trabalho.DAO;

import com.example.gestao_fluxos_trabalho.DTO.WorkflowAssignedToMeDTO;
import com.example.gestao_fluxos_trabalho.DTO.WorkflowMyListDTO;
import com.example.gestao_fluxos_trabalho.model.worfklow_step.Workflow_step;
import com.example.gestao_fluxos_trabalho.model.workflow.Workflow;

import java.util.List;

public interface WorkflowDAO {

    public Workflow save(Workflow workflow);

    public void saveStep(Workflow_step workflowStep);

    public List<Workflow> listOpenWorkflowUser(Long userId);

    public List<Workflow> listClosedWorkflowUser(Long userId);

    public List<WorkflowMyListDTO> listMyWorkflows(Long userId);

    public List<WorkflowAssignedToMeDTO> listWorkflowsByAssignedUser(Long userId);

    public List<Workflow_step> listWorkflowStepByWorkflowId(Long workflowId);

    public Workflow_step getStepById(Long id);

    public Workflow getWorkflowById(Long id);
}
