package com.example.interviewtask.loan.service.validation;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import com.example.interviewtask.loan.dto.LoanApplicationDtoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AmountValidationTest {

    private static final int MAX_LIMIT = 10000;
    private static final int MIN_LIMIT = 100;

    AmountValidation amountValidation = new AmountValidation();

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(amountValidation, "MAX_AMOUNT", MAX_LIMIT);
        ReflectionTestUtils.setField(amountValidation, "MIN_AMOUNT", MIN_LIMIT);
    }

    @Test
    public void validAmount() {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withAmmount(BigDecimal.valueOf(5000))
                .build();

        //when
        boolean result = amountValidation.isValid(loanApplicationDto);

        // then
        assertTrue(result);
    }

    @Test
    public void minValidation() {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withAmmount(BigDecimal.valueOf(99))
                .build();

        //when
        boolean result = amountValidation.isValid(loanApplicationDto);

        // then
        assertFalse(result);
    }

    @Test
    public void maxValidation() {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withAmmount(BigDecimal.valueOf(10001))
                .build();

        //when
        boolean result = amountValidation.isValid(loanApplicationDto);

        // then
        assertFalse(result);
    }

    @Test
    public void allowMinLimit() {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withAmmount(BigDecimal.valueOf(MIN_LIMIT))
                .build();

        //when
        boolean result = amountValidation.isValid(loanApplicationDto);

        // then
        assertTrue(result);
    }

    @Test
    public void allowMaxLimit() {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withAmmount(BigDecimal.valueOf(MAX_LIMIT))
                .build();

        //when
        boolean result = amountValidation.isValid(loanApplicationDto);

        // then
        assertTrue(result);
    }
}
