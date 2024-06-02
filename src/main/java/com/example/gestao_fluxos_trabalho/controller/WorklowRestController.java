package com.example.gestao_fluxos_trabalho.controller;

import com.example.gestao_fluxos_trabalho.DTO.WorkflowAssignedToMeDTO;
import com.example.gestao_fluxos_trabalho.DTO.WorkflowDTO;
import com.example.gestao_fluxos_trabalho.DTO.WorkflowMyListDTO;
import com.example.gestao_fluxos_trabalho.DTO.WorkflowTypeDTO;
import com.example.gestao_fluxos_trabalho.business.WorkflowBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workflow")
@CrossOrigin(origins = "http://localhost:4200")
public class WorklowRestController {

    @Autowired
    private WorkflowBusiness workflowBusiness;

    @PostMapping("/create")
    public ResponseEntity<String> createWorkflow(@RequestBody WorkflowDTO workflowDTO, @RequestHeader("userId") Long loggedUserId) {
            workflowBusiness.createWorkflow(workflowDTO,loggedUserId);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/listOpenUser/{id}")
    public ResponseEntity<List<WorkflowDTO>> getWorkflowType(@PathVariable Long id) {
        List<WorkflowDTO> workflow = workflowBusiness.listOpenWorkflowUser(id);
        if (workflow != null) {
            return new ResponseEntity<>(workflow, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listCloseUser/{id}")
    public ResponseEntity<List<WorkflowDTO>> getWorkflowTypeClosed(@PathVariable Long id) {
        List<WorkflowDTO> workflow = workflowBusiness.listClosedWorkflowUser(id);
        if (workflow != null) {
            return new ResponseEntity<>(workflow, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listMyWorkflows/{id}")
    public ResponseEntity<List<WorkflowMyListDTO>> listmyWorkflows(@PathVariable Long id) {
        List<WorkflowMyListDTO> workflow = workflowBusiness.listMyWorkflows(id);
        if (workflow != null) {
            return new ResponseEntity<>(workflow, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listWorkflowsAssignedMe/{id}")
    public ResponseEntity<List<WorkflowAssignedToMeDTO>> listWorkflowsAssignedMe(@PathVariable Long id) {
        List<WorkflowAssignedToMeDTO> workflow = workflowBusiness.listWorkflowAssignedToMe(id);
        if (workflow != null) {
            return new ResponseEntity<>(workflow, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
