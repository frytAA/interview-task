package com.example.interviewtask.loan.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class LoanDtoBuilder {
    private BigDecimal ammount;
    private LocalDateTime dueDate;
    private Integer term;

    private LoanDtoBuilder() {
    }

    public static LoanDtoBuilder aLoanDto() {
        return new LoanDtoBuilder();
    }

    public LoanDtoBuilder withAmmount(BigDecimal ammount) {
        this.ammount = ammount;
        return this;
    }

    public LoanDtoBuilder withDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public LoanDtoBuilder withTerm(Integer term) {
        this.term = term;
        return this;
    }

    public LoanDto build() {
        LoanDto loanDto = new LoanDto();
        loanDto.setAmmount(ammount);
        loanDto.setDueDate(dueDate);
        loanDto.setTerm(term);
        return loanDto;
    }
}
