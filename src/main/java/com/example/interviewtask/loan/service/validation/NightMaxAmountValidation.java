package com.example.interviewtask.loan.service.validation;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
/*
    Denies max limit loans during night:
    if application is between 00:00 and 06:00 and max amount is asked then reject application
 */
public class NightMaxAmountValidation implements LoanApplicationValidation {

    @Value("${loan.amount.max:10000}")
    private int MAX_AMOUNT;

    @Value("${loan.nightHour.start:0}")
    private int NIGHT_START;

    @Value("${loan.nightHour.end:6}")
    private int NIGHT_END;

    @Override
    public boolean isValid(LoanApplicationDto loanApplicationDto) {
        return !isMaxLimitNightLoan(loanApplicationDto);
    }

    private boolean isMaxLimitNightLoan(LoanApplicationDto loanApplicationDto) {
        return isMaxLimitLoan(loanApplicationDto) && isCreatedAtNight(loanApplicationDto);
    }

    private boolean isMaxLimitLoan(LoanApplicationDto loanApplicationDto) {
        return loanApplicationDto.getAmount().compareTo(BigDecimal.valueOf(MAX_AMOUNT)) == 0;
    }

    private boolean isCreatedAtNight(LoanApplicationDto loanApplicationDto) {
        int hour = loanApplicationDto.getCreationDateTime().getHour();

        return hour > NIGHT_START && hour < NIGHT_END;
    }

}
