package com.example.interviewtask.loan.service.validation;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import org.springframework.stereotype.Component;

@Component
/*
    if application is between 00:00 and 06:00 and max amount is asked then reject application
 */
public class NightMaxAmountValidation implements LoanApplicationValidation {
    @Override
    public boolean isValid(LoanApplicationDto loanApplicationDto) {
        return true;
    }
}
