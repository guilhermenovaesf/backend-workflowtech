package com.example.gestao_fluxos_trabalho.business;

import com.example.gestao_fluxos_trabalho.DAO.UserDAO;
import com.example.gestao_fluxos_trabalho.DAO.WorkflowTypeDAO;
import com.example.gestao_fluxos_trabalho.DTO.WorkflowTypeDTO;
import com.example.gestao_fluxos_trabalho.DTO.WorkflowTypeStepDTO;
import com.example.gestao_fluxos_trabalho.model.users.Users;
import com.example.gestao_fluxos_trabalho.model.workflow_type.Workflow_type;
import com.example.gestao_fluxos_trabalho.model.workflow_type_step.Workflow_type_step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public void createWorkflowType(WorkflowTypeDTO workflowTypeDTO, Long loggedUserId) {
        Workflow_type workflowType = new Workflow_type();
        workflowType.setTitle(workflowTypeDTO.getTitle());
        workflowType.setDescription(workflowTypeDTO.getDescription());

        Users createdBy = new Users();
        createdBy =userDAO.findById(loggedUserId);

        workflowType.setCreatedBy(createdBy);

        workflowType.setCreatedOn(new Date());

        Workflow_type returnType = workflowTypeDAO.save(workflowType);

        for (WorkflowTypeStepDTO stepTO : workflowTypeDTO.getWorkflowTypeStepTOList()) {
            Workflow_type_step workflowStep = new Workflow_type_step();
            workflowStep.setDescription(stepTO.getDescription());
            workflowStep.setUser(userDAO.findById(stepTO.getUserId()));
            workflowStep.setWorkflowType(returnType);
            workflowTypeDAO.saveWorkflowTypeStep(workflowStep);
        }
    }

    @Transactional
    @Override
    public WorkflowTypeDTO getWorkflowType(int id) {
        Workflow_type workflowType = workflowTypeDAO.findById(id);
        return new WorkflowTypeDTO(workflowType,false);

    }

    @Transactional
    @Override
    public List<WorkflowTypeDTO> listWorkflowType() {
        List<Workflow_type> workflowTypeList = workflowTypeDAO.findAll();
        return workflowTypeList.stream()
                .map(workflowType -> new WorkflowTypeDTO(workflowType, false))
                .collect(Collectors.toList());
    }
}
