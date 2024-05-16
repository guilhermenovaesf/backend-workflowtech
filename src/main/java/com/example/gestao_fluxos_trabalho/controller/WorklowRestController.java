package com.example.gestao_fluxos_trabalho.controller;

import com.example.gestao_fluxos_trabalho.DTO.WorkflowDTO;
import com.example.gestao_fluxos_trabalho.business.WorkflowBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflow")
public class WorklowRestController {

    @Autowired
    private WorkflowBusiness workflowBusiness;

    @PostMapping("/create")
    public ResponseEntity<String> createWorkflow(@RequestBody WorkflowDTO workflowDTO) {
        try {
            workflowBusiness.createWorkflow(workflowDTO);
            return new ResponseEntity<>("Workflow created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
