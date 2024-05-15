package com.example.gestao_fluxos_trabalho.DAO;

import com.example.gestao_fluxos_trabalho.model.workflow_type.Workflow_type;
import com.example.gestao_fluxos_trabalho.model.workflow_type_step.Workflow_type_step;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class WorkflowTypeDAOImpl implements WorkflowTypeDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Workflow_type save(Workflow_type workflowType) {
        entityManager.persist(workflowType);
        entityManager.flush(); // Garante que as alterações sejam enviadas para o banco de dados
        return workflowType;
    }

    @Override
    public void saveWorkflowTypeStep(Workflow_type_step workflowTypeStep) {
        entityManager.persist(workflowTypeStep);

    }
}
