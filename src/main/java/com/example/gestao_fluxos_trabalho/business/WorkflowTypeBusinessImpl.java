package com.example.gestao_fluxos_trabalho.business;

import com.example.gestao_fluxos_trabalho.DAO.UserDAO;
import com.example.gestao_fluxos_trabalho.DAO.WorkflowTypeDAO;
import com.example.gestao_fluxos_trabalho.DAO.WorkflowTypeDAOImpl;
import com.example.gestao_fluxos_trabalho.DTO.WorkflowTypeDTO;
import com.example.gestao_fluxos_trabalho.DTO.WorkflowTypeStepDTO;
import com.example.gestao_fluxos_trabalho.model.users.Users;
import com.example.gestao_fluxos_trabalho.model.workflow_type.Workflow_type;
import com.example.gestao_fluxos_trabalho.model.workflow_type_step.Workflow_type_step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkflowTypeBusinessImpl implements WorkflowTypeBusiness {

    @Autowired
    private WorkflowTypeDAO workflowTypeDAO;

    @Autowired
    private UserDAO userDAO;

    @Transactional
    @Override
    public void createWorkflow(WorkflowTypeDTO workflowTypeDTO) {
        Workflow_type workflowType = new Workflow_type();
        workflowType.setTitle(workflowTypeDTO.getTitle());
        workflowType.setDescription(workflowTypeDTO.getDescription());

        Users createdBy = new Users();
        createdBy =userDAO.findById(workflowTypeDTO.getCreatedBy().getId());

        workflowType.setCreatedBy(createdBy);

        workflowType.setCreatedOn(workflowTypeDTO.getCreatedOn());

        Workflow_type returnType = workflowTypeDAO.save(workflowType);

        for (WorkflowTypeStepDTO stepTO : workflowTypeDTO.getWorkflowTypeStepTOList()) {
            Workflow_type_step workflowStep = new Workflow_type_step();
            workflowStep.setDescription(stepTO.getDescription());
            workflowStep.setUser(userDAO.findById(stepTO.getUserId().getId()));
            workflowStep.setWorkflowType(returnType);
            workflowTypeDAO.saveWorkflowTypeStep(workflowStep);
        }
    }
}
