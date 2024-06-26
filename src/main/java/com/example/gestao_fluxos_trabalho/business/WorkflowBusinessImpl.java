package com.example.gestao_fluxos_trabalho.business;

import com.example.gestao_fluxos_trabalho.DAO.UserDAO;
import com.example.gestao_fluxos_trabalho.DAO.WorkflowDAO;
import com.example.gestao_fluxos_trabalho.DAO.WorkflowTypeDAO;
import com.example.gestao_fluxos_trabalho.DTO.*;
import com.example.gestao_fluxos_trabalho.model.worfklow_step.Workflow_step;
import com.example.gestao_fluxos_trabalho.model.workflow.Workflow;
import com.example.gestao_fluxos_trabalho.model.workflow_type.Workflow_type;
import com.example.gestao_fluxos_trabalho.model.workflow_type_step.Workflow_type_step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkflowBusinessImpl implements WorkflowBusiness {

    @Autowired
    private WorkflowTypeDAO workflowTypeDAO;

    @Autowired
    private WorkflowDAO workflowDAO;

    @Autowired
    private UserDAO userDAO;

    @Transactional
    @Override
    public void createWorkflow(WorkflowDTO workflowDTO, Long loggedUserId) {
        Workflow workflow = new Workflow();
        Workflow_type workflowType = new Workflow_type();
        workflowType = workflowTypeDAO.findById(workflowDTO.getWorkflowTypeId());
        workflow.setWorkflowType(workflowType);
        workflow.setDescription(workflowDTO.getDescription());
        workflow.setCreatedBy(userDAO.findById(loggedUserId));
        workflow.setCreatedOn(new Date());

        Workflow workflowReturn = workflowDAO.save(workflow);

        if (!workflowType.getWorkflowTypeStepList().isEmpty()) {
            int count = 0;
            for (Workflow_type_step workflowTypeStep : workflowType.getWorkflowTypeStepList()) {
                Workflow_step workflowStep = new Workflow_step();
                workflowStep.setWorkflow(workflowReturn);
                workflowStep.setAssignedTo(workflowTypeStep.getUser());
                workflowStep.setWorkflowTypeStep(workflowTypeStep);
                workflowStep.setWorkflowTypeStepDescription(workflowTypeStep.getDescription());
                workflowStep.setIsApproved(0);
                if (count == 0) {
                    workflowStep.setIsCurrent(1);
                }
                count++;
                workflowDAO.saveStep(workflowStep);
            }
        }

    }

    @Transactional
    @Override
    public List<WorkflowDTO> listOpenWorkflowUser(Long userId) {
        List<Workflow> workflowList = workflowDAO.listOpenWorkflowUser(userId);
        return workflowList.stream().map(WorkflowDTO::new).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<WorkflowDTO> listClosedWorkflowUser(Long userId) {
        List<Workflow> workflowList = workflowDAO.listClosedWorkflowUser(userId);
        return workflowList.stream().map(WorkflowDTO::new).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<WorkflowMyListDTO> listMyWorkflows(Long loggedUserId) {
        return workflowDAO.listMyWorkflows(loggedUserId);

    }

    @Transactional
    @Override
    public List<WorkflowAssignedToMeDTO> listWorkflowAssignedToMe(Long loggedUserId) {
        return workflowDAO.listWorkflowsByAssignedUser(loggedUserId);
    }

    @Transactional
    @Override
    public List<WorkflowStepDTO> listWorkflowStepByWorkflowId(Long workflowId){
        List<Workflow_step> listStep = workflowDAO.listWorkflowStepByWorkflowId(workflowId);
        List<WorkflowStepDTO> dtoStepList = new ArrayList<WorkflowStepDTO>();
        for(Workflow_step step : listStep){
            dtoStepList.add(new WorkflowStepDTO(step,0));
        }
    return dtoStepList;
    }

    @Override
    public void aproveOrRejectWorklow(WorkflowStepAproveRejectDTO workflowData) {
        Workflow_step workflowStep = workflowDAO.getStepById(workflowData.getWorkflowStepId());
        Workflow workflow = workflowDAO.getWorkflowById(workflowData.getWorkflowId());
        if (workflowData.isAproved()) {
            workflowStep.setIsApproved(1);
            workflowStep.setIsCurrent(0);
        } else {
            workflowStep.setIsApproved(0);
            workflow.setCanceled(1);
        }
        workflowStep.setComment(workflowData.getComment());

        List<WorkflowStepDTO> stepList = listWorkflowStepByWorkflowId(workflowData.getWorkflowId());
        if (workflowData.isAproved()){
            for (int i = 0; i < stepList.size(); i++) {
                WorkflowStepDTO currentStep = stepList.get(i);

                if (currentStep.getId() == workflowData.getWorkflowStepId()){
                    if (i == stepList.size() - 1) {
                        workflow.setFinished(1);
                        workflowDAO.save(workflow);

                    } else {
                        // Não é a última iteração, pegar o valor da próxima iteração
                        WorkflowStepDTO nextStep = stepList.get(i + 1);
                        Workflow_step nextCurrentStep = workflowDAO.getStepById((long) nextStep.getId());
                        nextCurrentStep.setIsCurrent(1);
                        workflowDAO.saveStep(nextCurrentStep);
                    }
            }
            }
    }

    }
}
