package com.example.gestao_fluxos_trabalho.controller;

import com.example.gestao_fluxos_trabalho.DTO.WorkflowTypeDTO;
import com.example.gestao_fluxos_trabalho.business.WorkflowTypeBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflowType")
public class WorkflowTypeRestControlller {

    @Autowired
    private WorkflowTypeBusiness workflowTypeBusiness;

    @PostMapping("/create")
    public ResponseEntity<String> createWorkflow(@RequestBody WorkflowTypeDTO workflowTypeDTO) {
        try {
            workflowTypeBusiness.createWorkflow(workflowTypeDTO);
            return new ResponseEntity<>("Workflow created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
