package com.example.gestao_fluxos_trabalho.DAO;

import com.example.gestao_fluxos_trabalho.model.workflow_type.Workflow_type;
import com.example.gestao_fluxos_trabalho.model.workflow_type_step.Workflow_type_step;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public Workflow_type findById(int id) {
        return entityManager.find(Workflow_type.class, id);
    }

    @Override
    public List<Workflow_type> findAll() {
        return entityManager.createQuery("SELECT wt FROM Workflow_type wt", Workflow_type.class).getResultList();
    }
}
