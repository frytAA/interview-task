package com.example.interviewtask.loan.service;

import com.example.interviewtask.loan.entity.LoanEntity;
import com.example.interviewtask.loan.entity.LoanEntityBuilder;
import com.example.interviewtask.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    @Test
    public void saveLoan() {
        // given
        LocalDate dueDate = LocalDate.now();
        LoanEntity loanEntity = LoanEntityBuilder
                .aLoanEntity()
                .withAmmount(BigDecimal.TEN)
                .withDueDate(dueDate)
                .build();
        when(loanRepository.save(loanEntity)).thenReturn(loanEntity);

        // when
        LoanEntity saved = loanService.save(loanEntity);

        // then
        assertThat(saved.getDueDate()).isEqualTo(dueDate);
        assertThat(saved.getAmmount()).isEqualTo(BigDecimal.TEN);
        verify(loanRepository).save(loanEntity);
    }
}
