package com.example.interviewtask.loan.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanDto {
    private BigDecimal amount;
    private LocalDateTime dueDate;
    private Integer term;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }
}
