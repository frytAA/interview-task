package com.example.interviewtask.loan.service;

import com.example.interviewtask.loan.entity.LoanEntity;
import com.example.interviewtask.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class LoanExtensionService {

    @Value("${loan.rate:1.1}")
    private final static BigDecimal RATE = BigDecimal.valueOf(1.1);

    private final LoanRepository loanRepository;

    public LoanExtensionService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Optional<LoanEntity> extendLoan(Long id) {
        return loanRepository.findById(id).map(this::updateLoan);
    }

    private LoanEntity updateLoan(LoanEntity loanEntity) {
        loanEntity.setDueDate(loanEntity.getDueDate().plusDays(loanEntity.getTerm()));
        loanEntity.setAmount(loanEntity.getAmount().multiply(RATE));
        return loanRepository.save(loanEntity);
    }
}
