package com.example.interviewtask.loan.service.validation;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import com.example.interviewtask.loan.dto.LoanApplicationDtoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NightMaxAmountValidationTest {

    private static final int MAX_LIMIT = 10000;
    private static final int NIGHT_START = 0;
    private static final int NIGHT_END = 6;
    private static final int VALID_AMOUNT = 5000;
    private static final LocalDateTime VALID_DATE_TIME = LocalDateTime.of(2022, 6, 12, 12, 0);
    private static final LocalDateTime NIGHT_DATE_TIME = LocalDateTime.of(2022, 6, 12, 3, 0);


    NightMaxAmountValidation nightMaxAmountValidation = new NightMaxAmountValidation();

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(nightMaxAmountValidation, "MAX_AMOUNT", MAX_LIMIT);
        ReflectionTestUtils.setField(nightMaxAmountValidation, "NIGHT_START", NIGHT_START);
        ReflectionTestUtils.setField(nightMaxAmountValidation, "NIGHT_END", NIGHT_END);
    }

    @Test
    public void validLoan() {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withAmmount(BigDecimal.valueOf(VALID_AMOUNT))
                .withCreationDate(VALID_DATE_TIME)
                .build();

        //when
        boolean result = nightMaxAmountValidation.isValid(loanApplicationDto);

        // then
        assertTrue(result);
    }


    @Test
    public void allowSmallLoanAtNight() {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withAmmount(BigDecimal.valueOf(VALID_AMOUNT))
                .withCreationDate(VALID_DATE_TIME)
                .build();

        //when
        boolean result = nightMaxAmountValidation.isValid(loanApplicationDto);

        // then
        assertTrue(result);
    }

    @Test
    public void allowMaxLimitDuringDay() {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withAmmount(BigDecimal.valueOf(MAX_LIMIT))
                .withCreationDate(VALID_DATE_TIME)
                .build();

        //when
        boolean result = nightMaxAmountValidation.isValid(loanApplicationDto);

        // then
        assertTrue(result);
    }

    @Test
    public void validationFailForNightMaxLoan() {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withAmmount(BigDecimal.valueOf(MAX_LIMIT))
                .withCreationDate(NIGHT_DATE_TIME)
                .build();

        //when
        boolean result = nightMaxAmountValidation.isValid(loanApplicationDto);

        // then
        assertFalse(result);
    }

}
