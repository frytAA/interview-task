package com.example.interviewtask.loan.service;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import com.example.interviewtask.loan.enumeration.ApplicationStatus;
import com.example.interviewtask.loan.service.validation.LoanApplicationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LoanApplicationService {

    private final LoanCreatorService loanCreatorService;
    private final Set<LoanApplicationValidation> validators;

    @Autowired
    public LoanApplicationService(LoanCreatorService loanCreatorService, Set<LoanApplicationValidation> validators) {
        this.loanCreatorService = loanCreatorService;
        this.validators = validators;
    }

    public ApplicationStatus apply(LoanApplicationDto loanApplicationDto) {
        boolean validationResult = validators.stream().map(validator -> validator.isValid(loanApplicationDto)).anyMatch(val -> !val);

        if (validationResult) {
            loanCreatorService.createLoan(loanApplicationDto);
            return ApplicationStatus.SUCCESS;
        } else {
            return ApplicationStatus.REJECTED;
        }
    }
}
