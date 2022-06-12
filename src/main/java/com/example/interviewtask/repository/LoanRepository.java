package com.example.interviewtask.repository;

import com.example.interviewtask.loan.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

}
