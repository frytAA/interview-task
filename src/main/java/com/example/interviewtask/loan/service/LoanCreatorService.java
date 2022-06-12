package com.example.interviewtask.loan.service;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import com.example.interviewtask.loan.entity.LoanEntity;
import com.example.interviewtask.loan.entity.LoanEntityBuilder;
import com.example.interviewtask.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanCreatorService {

    private final LoanRepository loanRepository;

    public LoanCreatorService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public LoanEntity createLoan(LoanApplicationDto loanApplicationDto) {
        LoanEntity loanEntity = LoanEntityBuilder.aLoanEntity()
                .withAmmount(loanApplicationDto.getAmmount())
                .withDueDate(calculateDueDate(loanApplicationDto))
                .build();

        return loanRepository.save(loanEntity);
    }

    private LocalDate calculateDueDate(LoanApplicationDto loanApplicationDto) {
        return loanApplicationDto.getCreationDate().plusDays(loanApplicationDto.getTerm());
    }
}
