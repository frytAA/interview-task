package com.example.interviewtask.loan.controller;

import com.example.interviewtask.loan.dto.LoanDto;
import com.example.interviewtask.loan.mapper.LoanMapper;
import com.example.interviewtask.loan.service.LoanExtensionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans/{loanId}")
public class LoanExtensionController {

    private final LoanExtensionService loanExtensionService;
    private final LoanMapper loanMapper;

    public LoanExtensionController(LoanExtensionService loanExtensionService, LoanMapper loanMapper) {
        this.loanExtensionService = loanExtensionService;
        this.loanMapper = loanMapper;
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/extend")
    public ResponseEntity<LoanDto> extendLoan(@PathVariable(name = "loanId") Long loanId) {
        return loanExtensionService
                .extendLoan(loanId)
                .map(loanMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
