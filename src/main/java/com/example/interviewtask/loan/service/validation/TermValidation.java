package com.example.interviewtask.loan.service.validation;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TermValidation implements LoanApplicationValidation {

    @Value("${loan.term.max:365}")
    private int MAX_TERM;

    @Value("${loan.term.min:30}")
    private int MIN_TERM;

    @Override
    public boolean isValid(LoanApplicationDto loanApplicationDto) {
        return isMaxLimitValid(loanApplicationDto) && isMinLimitValid(loanApplicationDto);
    }

    private boolean isMaxLimitValid(LoanApplicationDto loanApplicationDto) {
        return loanApplicationDto.getTerm().compareTo(MAX_TERM) <= 0;
    }

    private boolean isMinLimitValid(LoanApplicationDto loanApplicationDto) {
        return loanApplicationDto.getTerm().compareTo(MIN_TERM) >= 0;
    }
}
