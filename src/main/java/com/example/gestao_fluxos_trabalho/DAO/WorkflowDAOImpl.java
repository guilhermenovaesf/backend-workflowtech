package com.example.gestao_fluxos_trabalho.DAO;

import com.example.gestao_fluxos_trabalho.model.worfklow_step.Workflow_step;
import com.example.gestao_fluxos_trabalho.model.workflow.Workflow;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class WorkflowDAOImpl implements WorkflowDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Workflow save(Workflow workflow) {
        entityManager.persist(workflow);
        entityManager.flush(); // Garante que as alterações sejam enviadas para o banco de dados
        return workflow;
    }

    @Override
    public void saveStep(Workflow_step workflowStep) {
        entityManager.persist(workflowStep);
    }

    @Override
    public List<Workflow> listOpenWorkflowUser(Long userId) {
        return entityManager.createQuery("SELECT w FROM Workflow w WHERE w.createdBy.id = :userId AND w.finished = 0 AND w.canceled = 0", Workflow.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Workflow> listClosedWorkflowUser(Long userId) {
        return entityManager.createQuery("SELECT w FROM Workflow w WHERE w.createdBy.id = :userId AND w.finished = 1 OR w.canceled = 1", Workflow.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
