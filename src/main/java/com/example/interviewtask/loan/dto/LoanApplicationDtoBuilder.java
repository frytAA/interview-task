package com.example.interviewtask.loan.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class LoanApplicationDtoBuilder {
    private LocalDateTime creationDate;
    private BigDecimal amount;
    private Integer term;

    private LoanApplicationDtoBuilder() {
    }

    public static LoanApplicationDtoBuilder aLoanApplicationDto() {
        return new LoanApplicationDtoBuilder();
    }

    public LoanApplicationDtoBuilder withCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public LoanApplicationDtoBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public LoanApplicationDtoBuilder withTerm(Integer term) {
        this.term = term;
        return this;
    }

    public LoanApplicationDto build() {
        LoanApplicationDto loanApplicationDto = new LoanApplicationDto();
        loanApplicationDto.setCreationDateTime(creationDate);
        loanApplicationDto.setAmount(amount);
        loanApplicationDto.setTerm(term);
        return loanApplicationDto;
    }
}
