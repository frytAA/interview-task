package com.example.interviewtask.loan.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class LoanDtoBuilder {
    private BigDecimal ammount;
    private LocalDate dueDate;

    private LoanDtoBuilder() {
    }

    public static LoanDtoBuilder aLoanDto() {
        return new LoanDtoBuilder();
    }

    public LoanDtoBuilder withAmmount(BigDecimal ammount) {
        this.ammount = ammount;
        return this;
    }

    public LoanDtoBuilder withDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public LoanDto build() {
        LoanDto loanDto = new LoanDto();
        loanDto.setAmmount(ammount);
        loanDto.setDueDate(dueDate);
        return loanDto;
    }
}