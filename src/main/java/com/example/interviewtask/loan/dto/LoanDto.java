package com.example.interviewtask.loan.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanDto {
    private BigDecimal ammount;
    private LocalDateTime dueDate;

    public BigDecimal getAmmount() {
        return ammount;
    }

    public void setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
