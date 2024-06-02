package com.example.gestao_fluxos_trabalho.DAO;

import com.example.gestao_fluxos_trabalho.DTO.WorkflowAssignedToMeDTO;
import com.example.gestao_fluxos_trabalho.DTO.WorkflowMyListDTO;
import com.example.gestao_fluxos_trabalho.model.worfklow_step.Workflow_step;
import com.example.gestao_fluxos_trabalho.model.workflow.Workflow;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<WorkflowMyListDTO> listMyWorkflows(Long userId) {
        List<Object[]> results = entityManager.createNativeQuery(
                        "SELECT W.ID, WT.TITLE, W.CANCELED, W.FINISHED, W.CREATED_ON " +
                                "FROM workflow_type WT, workflow W " +
                                "WHERE W.CREATED_BY = :userId " +
                                "AND WT.ID = W.WORKFLOW_TYPE_ID")
                .setParameter("userId", userId)
                .getResultList();

        return results.stream()
                .map(result -> new WorkflowMyListDTO(
                        ((Number) result[0]).longValue(),
                        (String) result[1],
                        ((Number) result[2]).intValue() == 1,
                        ((Number) result[3]).intValue() == 1,
                        (Date) result[4]
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkflowAssignedToMeDTO> listWorkflowsByAssignedUser(Long userId) {
        List<Object[]> results = entityManager.createNativeQuery(
                        "SELECT W.ID workflow_id, WS.ID workflow_step_id, WT.TITLE title, W.CREATED_ON createdOn, U.NAME userName " +
                                "FROM WORKFLOW_STEP WS, WORKFLOW W, WORKFLOW_TYPE WT, USERS U " +
                                "WHERE WS.ASSIGNED_TO = :userId " +
                                "AND WS.IS_CURRENT = 1 " +
                                "AND WS.IS_APPROVED = 0 " +
                                "AND W.FINISHED = 0 AND W.CANCELED = 0 "+
                                "AND WS.WORKFLOW_ID = W.ID " +
                                "AND U.ID = WS.ASSIGNED_TO " +
                                "GROUP BY WS.ID ORDER BY createdOn")
                .setParameter("userId", userId)
                .getResultList();

        return results.stream()
                .map(result -> {
                    WorkflowAssignedToMeDTO workflowDTO = new WorkflowAssignedToMeDTO();
                    workflowDTO.setWorkflowId(((Number) result[0]).longValue());
                    workflowDTO.setWorkflowStepId(((Number) result[1]).longValue());
                    workflowDTO.setTitle((String) result[2]);
                    workflowDTO.setCreatedOn(((Date) result[3]));
                    workflowDTO.setUserName((String) result[4]);
                    return workflowDTO;
                })
                .collect(Collectors.toList());
    }
}
