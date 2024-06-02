package com.example.gestao_fluxos_trabalho.controller;

import com.example.gestao_fluxos_trabalho.DTO.WorkflowDTO;
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
        try {
            workflowBusiness.createWorkflow(workflowDTO,loggedUserId);
            return new ResponseEntity<>("Workflow created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
}
