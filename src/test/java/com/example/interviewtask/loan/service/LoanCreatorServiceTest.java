package com.example.interviewtask.loan.service;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import com.example.interviewtask.loan.dto.LoanApplicationDtoBuilder;
import com.example.interviewtask.loan.entity.LoanEntity;
import com.example.interviewtask.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanCreatorServiceTest {

    private static final BigDecimal AMOUNT = BigDecimal.valueOf(1000);
    private static final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2022, 6, 12, 12, 0, 1);
    private static final int TERM = 30;
    private final static BigDecimal RATE = BigDecimal.valueOf(1.1);

    @InjectMocks
    private LoanCreatorService loanCreatorService;

    @Mock
    private LoanRepository loanRepository;

    @Test
    void createLoan() {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withAmount(AMOUNT)
                .withTerm(TERM)
                .withCreationDate(APPLICATION_DATE)
                .build();

        when(loanRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        // when
        LoanEntity loan = loanCreatorService.createLoan(loanApplicationDto);

        // then
        assertThat(loan.getDueDate()).isEqualTo(APPLICATION_DATE.plusDays(TERM));
        assertThat(loan.getTerm()).isEqualTo(TERM);
        assertThat(loan.getAmount()).isEqualTo(AMOUNT.multiply(RATE));
    }
}
