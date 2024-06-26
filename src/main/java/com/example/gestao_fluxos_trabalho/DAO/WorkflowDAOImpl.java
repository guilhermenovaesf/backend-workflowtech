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
                        "SELECT W.ID, WT.TITLE, W.CANCELED, W.FINISHED, W.CREATED_ON, W.DESCRIPTION " +
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
                        (Date) result[4],
                        (String) result[5]
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkflowAssignedToMeDTO> listWorkflowsByAssignedUser(Long userId) {
        List<Object[]> results = entityManager.createNativeQuery(
                        " SELECT W.ID AS workflow_id,  " +
                                "    WS.ID AS workflow_step_id,  " +
                                "    WT.TITLE AS title,  " +
                                "    W.CREATED_ON AS createdOn,  " +
                                "    W.DESCRIPTION AS descriptionWorkflow,  " +
                                "    CU.NAME AS userName " +
                                "FROM  " +
                                "    WORKFLOW_STEP WS " +
                                "JOIN  " +
                                "    WORKFLOW W ON WS.WORKFLOW_ID = W.ID " +
                                "JOIN  " +
                                "    WORKFLOW_TYPE WT ON WT.ID = W.WORKFLOW_TYPE_ID " +
                                "JOIN  " +
                                "    USERS U ON U.ID = WS.ASSIGNED_TO " +
                                "JOIN  " +
                                "    USERS CU ON CU.ID = W.CREATED_BY " +
                                "WHERE  " +
                                "    WS.ASSIGNED_TO = :userId " +
                                "    AND WS.IS_CURRENT = 1  " +
                                "    AND WS.IS_APPROVED = 0  " +
                                "    AND W.FINISHED = 0  " +
                                "    AND W.CANCELED = 0  " +
                                "GROUP BY  " +
                                "    WS.ID, W.ID, WT.TITLE, W.CREATED_ON, W.DESCRIPTION, U.NAME, CU.NAME " +
                                "ORDER BY  " +
                                "    createdOn")
                .setParameter("userId", userId)
                .getResultList();

        return results.stream()
                .map(result -> {
                    WorkflowAssignedToMeDTO workflowDTO = new WorkflowAssignedToMeDTO();
                    workflowDTO.setWorkflowId(((Number) result[0]).longValue());
                    workflowDTO.setWorkflowStepId(((Number) result[1]).longValue());
                    workflowDTO.setTitle((String) result[2]);
                    workflowDTO.setCreatedOn(((Date) result[3]));
                    workflowDTO.setWorkflowDescription((String) result[4]);
                    workflowDTO.setUserName((String) result[5]);
                    return workflowDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Workflow_step> listWorkflowStepByWorkflowId(Long workflowId) {
        return entityManager.createQuery("SELECT ws FROM Workflow_step ws WHERE ws.workflow.id = :workflowId", Workflow_step.class)
                .setParameter("workflowId", workflowId)
                .getResultList();
    }

    @Override
    public Workflow_step getStepById(Long id) {
        return entityManager.find(Workflow_step.class, id);
    }

    @Override
    public Workflow getWorkflowById(Long id) {
        return entityManager.find(Workflow.class, id);
    }
}
