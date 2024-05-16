package com.example.gestao_fluxos_trabalho.business;

import com.example.gestao_fluxos_trabalho.DAO.WorkflowDAO;
import com.example.gestao_fluxos_trabalho.DAO.WorkflowTypeDAO;
import com.example.gestao_fluxos_trabalho.DTO.WorkflowDTO;
import com.example.gestao_fluxos_trabalho.model.workflow.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkflowBusinessImpl implements WorkflowBusiness {

    @Autowired
    private WorkflowTypeDAO workflowTypeDAO;

    @Autowired
    private WorkflowDAO workflowDAO;

    @Transactional
    @Override
    public void createWorkflow(WorkflowDTO workflowDTO) {
        Workflow workflow = new Workflow();
        workflow.setWorkflowType(workflowTypeDAO.findById(workflowDTO.getWorkflowTypeId()));
        workflow.setDescription(workflowDTO.getDescription());
        workflow.setCreatedBy(workflowDTO.getCreatedBy());
        workflow.setCreatedOn(workflowDTO.getCreatedOn());

        Workflow workflowReturn = this.workflowDAO.save(workflow);

    }
}
