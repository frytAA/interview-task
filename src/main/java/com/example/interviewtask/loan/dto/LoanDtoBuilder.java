package com.example.interviewtask.loan.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class LoanDtoBuilder {
    private BigDecimal amount;
    private LocalDateTime dueDate;
    private Integer term;

    private LoanDtoBuilder() {
    }

    public static LoanDtoBuilder aLoanDto() {
        return new LoanDtoBuilder();
    }

    public LoanDtoBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
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
        loanDto.setAmount(amount);
        loanDto.setDueDate(dueDate);
        loanDto.setTerm(term);
        return loanDto;
    }
}
