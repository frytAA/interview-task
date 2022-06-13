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
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanExtensionServiceTest {

    private static final long ID = 1L;
    private static final BigDecimal AMOUNT = BigDecimal.valueOf(1000);
    private final static BigDecimal RATE = BigDecimal.valueOf(1.1);

    @Mock
    private LoanRepository loanRepository;


    @InjectMocks
    private LoanExtensionService loanExtensionService;

    private static final LocalDateTime LOAN_DUE_DATE = LocalDateTime.of(2022, 6, 12, 12, 0);
    private static final Integer TERM = 30;

    @Test
    public void extendLoan() {
        // given
        LoanEntity loanEntity = buildLoanEntity();
        when(loanRepository.save(loanEntity)).thenReturn(loanEntity);
        when(loanRepository.findById(ID)).thenReturn(Optional.of(loanEntity));

        // when
        Optional<LoanEntity> result = loanExtensionService.extendLoan(ID);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getDueDate()).isEqualTo(LOAN_DUE_DATE.plusDays(TERM));
        assertThat(result.get().getAmount()).isEqualTo(AMOUNT.multiply(RATE));
        verify(loanRepository).save(loanEntity);
    }

    @Test
    public void noExtensionForMissingLoan() {
        // when
        Optional<LoanEntity> result = loanExtensionService.extendLoan(ID);

        // then
        assertThat(result).isEmpty();
        verify(loanRepository, never()).save(any());
    }


    private LoanEntity buildLoanEntity() {
        return LoanEntityBuilder
                .aLoanEntity()
                .withDueDate(LOAN_DUE_DATE)
                .withTerm(TERM)
                .withAmount(AMOUNT)
                .build();
    }

}
