package com.example.gestao_fluxos_trabalho.DAO;

import com.example.gestao_fluxos_trabalho.model.workflow_type.Workflow_type;
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
    public void save(Workflow_type workflowType) {
        entityManager.persist(workflowType);
    }
}
