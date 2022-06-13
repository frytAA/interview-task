package com.example.interviewtask.loan.service;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import com.example.interviewtask.loan.entity.LoanEntity;
import com.example.interviewtask.loan.entity.LoanEntityBuilder;
import com.example.interviewtask.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoanCreatorService {

    private final LoanRepository loanRepository;

    public LoanCreatorService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public LoanEntity createLoan(LoanApplicationDto loanApplicationDto) {
        LoanEntity loanEntity = LoanEntityBuilder.aLoanEntity()
                .withAmount(loanApplicationDto.getAmount())
                .withDueDate(calculateDueDate(loanApplicationDto))
                .withTerm(loanApplicationDto.getTerm())
                .build();

        return loanRepository.save(loanEntity);
    }

    private LocalDateTime calculateDueDate(LoanApplicationDto loanApplicationDto) {
        return loanApplicationDto.getCreationDateTime().plusDays(loanApplicationDto.getTerm());
    }
}
