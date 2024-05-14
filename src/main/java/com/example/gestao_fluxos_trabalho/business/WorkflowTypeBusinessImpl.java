package com.example.gestao_fluxos_trabalho.business;

import com.example.gestao_fluxos_trabalho.DAO.WorkflowTypeDAO;
import com.example.gestao_fluxos_trabalho.DTO.WorkflowTypeDTO;
import com.example.gestao_fluxos_trabalho.model.users.Users;
import com.example.gestao_fluxos_trabalho.model.workflow_type.Workflow_type;
import com.example.gestao_fluxos_trabalho.model.workflow_type_step.Workflow_type_step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkflowTypeBusinessImpl implements WorkflowTypeBusiness {

    @Autowired
    private WorkflowTypeDAO workflowTypeDAO;

    @Transactional
    @Override
    public void createWorkflow(WorkflowTypeDTO workflowTypeDTO) {
        Workflow_type workflowType = new Workflow_type();
        workflowType.setTitle(workflowTypeDTO.getTitle());
        workflowType.setDescription(workflowTypeDTO.getDescription());

        Users createdBy = new Users();
        createdBy.setId(workflowTypeDTO.getCreatedBy().getId());
        createdBy.setName(workflowTypeDTO.getCreatedBy().getName());
        createdBy.setPassword(workflowTypeDTO.getCreatedBy().getPassword());
        createdBy.setEmail(workflowTypeDTO.getCreatedBy().getEmail());
        createdBy.setAdmin(workflowTypeDTO.getCreatedBy().getAdmin());

        workflowType.setCreatedBy(createdBy);

        workflowType.setCreatedOn(workflowTypeDTO.getCreatedOn());

        List<Workflow_type_step> workflowTypeStepList = workflowTypeDTO.getWorkflowTypeStepTOList().stream()
                .map(dto -> {
                    Workflow_type_step step = new Workflow_type_step();
                    step.setId(dto.getId());
                    step.setDescription(dto.getDescription());

                    Users user = new Users();
                    user.setId(dto.getUserId().getId());
                    user.setName(dto.getUserId().getName());
                    user.setPassword(dto.getUserId().getPassword());
                    user.setEmail(dto.getUserId().getEmail());
                    user.setAdmin(dto.getUserId().getAdmin());

                    step.setUser(user);

                    return step;
                })
                .collect(Collectors.toList());

        workflowType.setWorkflowTypeStepList(workflowTypeStepList);

        workflowTypeDAO.save(workflowType);
    }
}
