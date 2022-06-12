package com.example.interviewtask.loan.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class LoanApplicationDtoBuilder {
    private LocalDateTime creationDate;
    private BigDecimal ammount;
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

    public LoanApplicationDtoBuilder withAmmount(BigDecimal ammount) {
        this.ammount = ammount;
        return this;
    }

    public LoanApplicationDtoBuilder withTerm(Integer term) {
        this.term = term;
        return this;
    }

    public LoanApplicationDto build() {
        LoanApplicationDto loanApplicationDto = new LoanApplicationDto();
        loanApplicationDto.setCreationDateTime(creationDate);
        loanApplicationDto.setAmmount(ammount);
        loanApplicationDto.setTerm(term);
        return loanApplicationDto;
    }
}
