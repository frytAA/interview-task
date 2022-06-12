package com.example.interviewtask.loan.service;

import com.example.interviewtask.loan.entity.LoanEntity;
import com.example.interviewtask.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanExtensionService {
    private final LoanRepository loanRepository;

    public LoanExtensionService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Optional<LoanEntity> extendLoan(Long id) {
        return loanRepository.findById(id).map(this::updateDueDate);
    }

    private LoanEntity updateDueDate(LoanEntity loanEntity) {
        loanEntity.setDueDate(loanEntity.getDueDate().plusDays(loanEntity.getTerm()));
        return loanRepository.save(loanEntity);
    }
}
