package com.example.interviewtask.loan.service;

import com.example.interviewtask.loan.entity.LoanEntity;
import com.example.interviewtask.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Optional<LoanEntity> getLoan(Long id) {
        LoanEntity loanEntity = new LoanEntity();

        loanEntity.setDueDate(LocalDate.now());
        loanEntity.setAmmount(BigDecimal.ONE);

        return Optional.of(loanEntity);
    }

    public LoanEntity save(LoanEntity loanEntity) {
        return loanRepository.save(loanEntity);
    }
}
