package com.example.interviewtask.loan.controller;

import com.example.interviewtask.loan.dto.LoanDto;
import com.example.interviewtask.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans/{loanId}")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/get")
    public ResponseEntity<LoanDto> getLoan(@PathVariable(name = "loanId") Long loanId) {
        return ResponseEntity.ok(new LoanDto());
    }

}
