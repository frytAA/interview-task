package com.example.interviewtask.loan.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public final class LoanEntityBuilder {
    private LocalDateTime creationDate = LocalDateTime.now(ZoneOffset.UTC);
    private LocalDateTime updateDate = null;
    private BigDecimal amount;
    private LocalDateTime dueDate;
    private Integer term;

    private LoanEntityBuilder() {
    }

    public static LoanEntityBuilder aLoanEntity() {
        return new LoanEntityBuilder();
    }

    public LoanEntityBuilder withCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public LoanEntityBuilder withUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public LoanEntityBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public LoanEntityBuilder withDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public LoanEntityBuilder withTerm(Integer term) {
        this.term = term;
        return this;
    }

    public LoanEntity build() {
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setCreationDate(creationDate);
        loanEntity.setUpdateDate(updateDate);
        loanEntity.setAmount(amount);
        loanEntity.setDueDate(dueDate);
        loanEntity.setTerm(term);
        return loanEntity;
    }
}
