package com.example.gestao_fluxos_trabalho.DAO;

import com.example.gestao_fluxos_trabalho.model.workflow.Workflow;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
