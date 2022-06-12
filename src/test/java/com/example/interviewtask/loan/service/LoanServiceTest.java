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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

    private static final long ID = 1L;
    private static final BigDecimal AMOUNT = BigDecimal.valueOf(1000);

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    @Test
    public void saveLoan() {
        // given
        LocalDate dueDate = LocalDate.now();
        LoanEntity loanEntity = buildLoanEntity(dueDate);
        when(loanRepository.save(loanEntity)).thenReturn(loanEntity);

        // when
        LoanEntity saved = loanService.save(loanEntity);

        // then
        assertThat(saved.getDueDate()).isEqualTo(dueDate);
        assertThat(saved.getAmmount()).isEqualTo(AMOUNT);
        verify(loanRepository).save(loanEntity);
    }

    private LoanEntity buildLoanEntity(LocalDate dueDate) {
        return LoanEntityBuilder
                .aLoanEntity()
                .withAmmount(AMOUNT)
                .withDueDate(dueDate)
                .build();
    }


    @Test
    public void getLoan() {
        // given
        LocalDate dueDate = LocalDate.now();
        LoanEntity loanEntity = buildLoanEntity(dueDate);
        when(loanRepository.findById(ID)).thenReturn(Optional.of(loanEntity));

        // when
        Optional<LoanEntity> loan = loanService.getLoan(ID);

        // then
        assertThat(loan).isPresent();
        assertThat(loan.get().getDueDate()).isEqualTo(dueDate);
        assertThat(loan.get().getAmmount()).isEqualTo(AMOUNT);
    }
}
