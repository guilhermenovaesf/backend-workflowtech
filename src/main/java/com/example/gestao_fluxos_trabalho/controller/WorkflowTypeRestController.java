package com.example.gestao_fluxos_trabalho.controller;

import com.example.gestao_fluxos_trabalho.DTO.UsersDTO;
import com.example.gestao_fluxos_trabalho.DTO.WorkflowTypeDTO;
import com.example.gestao_fluxos_trabalho.business.WorkflowTypeBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workflowType")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkflowTypeRestController {

    @Autowired
    private WorkflowTypeBusiness workflowTypeBusiness;

    @PostMapping("/create")
    public ResponseEntity<String> createWorkflowType(@RequestBody WorkflowTypeDTO workflowTypeDTO) {
        try {
            workflowTypeBusiness.createWorkflowType(workflowTypeDTO);
            return new ResponseEntity<>("Workflow created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<WorkflowTypeDTO>> listWorkflowType() {
        List<WorkflowTypeDTO> workflowTypeList = workflowTypeBusiness.listWorkflowType();
        return new ResponseEntity<>(workflowTypeList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkflowTypeDTO> getWorkflowType(@PathVariable int id) {
        WorkflowTypeDTO workflowType = workflowTypeBusiness.getWorkflowType(id);
        if (workflowType != null) {
            return new ResponseEntity<>(workflowType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
