package com.example.interviewtask.loan.service.validation;

import com.example.interviewtask.loan.dto.LoanApplicationDto;

public interface LoanApplicationValidation {
    boolean isValid(LoanApplicationDto loanApplicationDto);
}
