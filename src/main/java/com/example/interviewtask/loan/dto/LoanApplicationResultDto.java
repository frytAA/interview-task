package com.example.interviewtask.loan.dto;

import com.example.interviewtask.loan.enumeration.ApplicationStatus;

public class LoanApplicationResultDto {
    private ApplicationStatus applicationStatus;

    public LoanApplicationResultDto() {
    }

    public LoanApplicationResultDto(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
