package com.example.interviewtask.loan.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanDto {
    private BigDecimal ammount;
    private LocalDate dueDate;

    public BigDecimal getAmmount() {
        return ammount;
    }

    public void setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
