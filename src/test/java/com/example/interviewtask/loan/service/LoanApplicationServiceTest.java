package com.example.interviewtask.loan.service;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import com.example.interviewtask.loan.enumeration.ApplicationStatus;
import com.example.interviewtask.loan.service.validation.AmountValidation;
import com.example.interviewtask.loan.service.validation.TermValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LoanApplicationServiceTest {

    @Mock
    private LoanCreatorService loanCreatorService;

    @Mock
    private TermValidation termValidation;

    @Mock
    private AmountValidation amountValidation;

    private LoanApplicationService loanApplicationService;

    @Test
    public void loanRejectedWhenValidationFails() {
        // given
        loanApplicationService = new LoanApplicationService(loanCreatorService, Set.of(termValidation, amountValidation));
        LoanApplicationDto loanApplicationDto = new LoanApplicationDto();
        when(termValidation.isValid(any(LoanApplicationDto.class))).thenReturn(false);
        when(amountValidation.isValid(any(LoanApplicationDto.class))).thenReturn(false);

        //when
        ApplicationStatus apply = loanApplicationService.apply(loanApplicationDto);

        // then
        assertThat(apply).isEqualTo(ApplicationStatus.REJECTED);
    }

    @Test
    public void loanRejectedWhenFirstValidationFails() {
        // given
        loanApplicationService = new LoanApplicationService(loanCreatorService, Set.of(termValidation, amountValidation));
        LoanApplicationDto loanApplicationDto = new LoanApplicationDto();
        when(termValidation.isValid(any(LoanApplicationDto.class))).thenReturn(false);
        when(amountValidation.isValid(any(LoanApplicationDto.class))).thenReturn(true);

        //when
        ApplicationStatus apply = loanApplicationService.apply(loanApplicationDto);

        // then
        assertThat(apply).isEqualTo(ApplicationStatus.REJECTED);
    }

    @Test
    public void loanRejectedWhenSecondValidationFails() {
        // given
        loanApplicationService = new LoanApplicationService(loanCreatorService, Set.of(termValidation, amountValidation));
        LoanApplicationDto loanApplicationDto = new LoanApplicationDto();
        when(termValidation.isValid(any(LoanApplicationDto.class))).thenReturn(true);
        when(amountValidation.isValid(any(LoanApplicationDto.class))).thenReturn(false);

        //when
        ApplicationStatus apply = loanApplicationService.apply(loanApplicationDto);

        // then
        assertThat(apply).isEqualTo(ApplicationStatus.REJECTED);
    }

    @Test
    public void loanCreatedWhenOneValidationFails() {
        // given
        loanApplicationService = new LoanApplicationService(loanCreatorService, Set.of(termValidation, amountValidation));
        LoanApplicationDto loanApplicationDto = new LoanApplicationDto();
        when(termValidation.isValid(any(LoanApplicationDto.class))).thenReturn(true);
        when(amountValidation.isValid(any(LoanApplicationDto.class))).thenReturn(true);

        //when
        ApplicationStatus apply = loanApplicationService.apply(loanApplicationDto);

        // then
        assertThat(apply).isEqualTo(ApplicationStatus.SUCCESS);
    }
}
