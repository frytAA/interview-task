package com.example.interviewtask.loan.service.validation;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import com.example.interviewtask.loan.dto.LoanApplicationDtoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TermValidationTest {

    private static final int MAX_LIMIT = 365;
    private static final int MIN_LIMIT = 30;

    TermValidation termValidation = new TermValidation();

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(termValidation, "MAX_TERM", MAX_LIMIT);
        ReflectionTestUtils.setField(termValidation, "MIN_TERM", MIN_LIMIT);
    }


    @Test
    public void validTerm() {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withTerm(60)
                .build();

        //when
        boolean result = termValidation.isValid(loanApplicationDto);

        // then
        assertTrue(result);
    }

    @Test
    public void minValidation() {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withTerm(29)
                .build();

        //when
        boolean result = termValidation.isValid(loanApplicationDto);

        // then
        assertFalse(result);
    }

    @Test
    public void maxValidation() {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withTerm(366)
                .build();

        //when
        boolean result = termValidation.isValid(loanApplicationDto);

        // then
        assertFalse(result);
    }

    @Test
    public void allowMinLimit() {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withTerm(MIN_LIMIT)
                .build();

        //when
        boolean result = termValidation.isValid(loanApplicationDto);

        // then
        assertTrue(result);
    }

    @Test
    public void allowMaxLimit() {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withTerm(MAX_LIMIT)
                .build();

        //when
        boolean result = termValidation.isValid(loanApplicationDto);

        // then
        assertTrue(result);
    }
}
