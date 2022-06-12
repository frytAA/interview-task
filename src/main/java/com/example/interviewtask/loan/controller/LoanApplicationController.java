package com.example.interviewtask.loan.controller;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import com.example.interviewtask.loan.dto.LoanApplicationResultDto;
import com.example.interviewtask.loan.enumeration.ApplicationStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/application")
public class LoanApplicationController {


    @PostMapping("/apply")
    public ResponseEntity<LoanApplicationResultDto> apply(@Valid @RequestBody LoanApplicationDto loanApplicationDto) {
        LoanApplicationResultDto result = new LoanApplicationResultDto();
        result.setApplicationStatus(ApplicationStatus.SUCCESS);
        return ResponseEntity.ok(result);
    }
}
