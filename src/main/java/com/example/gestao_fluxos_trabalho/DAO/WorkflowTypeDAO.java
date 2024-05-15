package com.example.gestao_fluxos_trabalho.DAO;

import com.example.gestao_fluxos_trabalho.model.workflow_type.Workflow_type;
import com.example.gestao_fluxos_trabalho.model.workflow_type_step.Workflow_type_step;

public interface WorkflowTypeDAO {

    public Workflow_type save(Workflow_type workflowType);
    public void saveWorkflowTypeStep(Workflow_type_step workflowTypeStep);
}
