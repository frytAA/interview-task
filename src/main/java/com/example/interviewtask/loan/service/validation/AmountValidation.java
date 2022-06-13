package com.example.interviewtask.loan.service.validation;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AmountValidation implements LoanApplicationValidation {

    @Value("${loan.amount.max:10000}")
    private int MAX_AMOUNT;

    @Value("${loan.amount.min:100}")
    private int MIN_AMOUNT;

    @Override
    public boolean isValid(LoanApplicationDto loanApplicationDto) {
        return isMaxLimitValid(loanApplicationDto) && isMinLimitValid(loanApplicationDto);
    }

    private boolean isMaxLimitValid(LoanApplicationDto loanApplicationDto) {
        return loanApplicationDto.getAmount().compareTo(BigDecimal.valueOf(MAX_AMOUNT)) <= 0;
    }

    private boolean isMinLimitValid(LoanApplicationDto loanApplicationDto) {
        return loanApplicationDto.getAmount().compareTo(BigDecimal.valueOf(MIN_AMOUNT)) >= 0;
    }
}
