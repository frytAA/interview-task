package com.example.interviewtask.loan.controller;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import com.example.interviewtask.loan.dto.LoanApplicationResultDto;
import com.example.interviewtask.loan.enumeration.ApplicationStatus;
import com.example.interviewtask.loan.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/application")
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    @Autowired
    public LoanApplicationController(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    @PostMapping("/apply")
    public ResponseEntity<LoanApplicationResultDto> apply(@Valid @RequestBody LoanApplicationDto loanApplicationDto) {
        ApplicationStatus status = loanApplicationService.apply(loanApplicationDto);
        return ResponseEntity.ok(new LoanApplicationResultDto(status));
    }
}
