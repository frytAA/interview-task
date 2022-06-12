package com.example.interviewtask.loan.service;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import com.example.interviewtask.loan.entity.LoanEntity;
import com.example.interviewtask.loan.entity.LoanEntityBuilder;
import com.example.interviewtask.loan.enumeration.ApplicationStatus;
import com.example.interviewtask.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanApplicationService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanApplicationService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public ApplicationStatus apply(LoanApplicationDto loanApplicationDto) {
        LoanEntity loanEntity = LoanEntityBuilder.aLoanEntity()
                .withAmmount(loanApplicationDto.getAmmount())
                .withDueDate(calculateDueDate(loanApplicationDto))
                .build();

        loanRepository.save(loanEntity);

        return ApplicationStatus.SUCCESS;
    }

    private LocalDate calculateDueDate(LoanApplicationDto loanApplicationDto) {
        return loanApplicationDto.getCreationDate().plusDays(loanApplicationDto.getTerm());
    }
}
