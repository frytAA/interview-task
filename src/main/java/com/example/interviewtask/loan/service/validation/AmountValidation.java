package com.example.interviewtask.loan.service.validation;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import org.springframework.stereotype.Component;

@Component
public class AmountValidation implements LoanApplicationValidation {

    @Override
    public boolean isValid(LoanApplicationDto loanApplicationDto) {
        return true;
    }

}
