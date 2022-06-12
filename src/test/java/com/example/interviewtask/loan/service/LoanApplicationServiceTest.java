package com.example.interviewtask.loan.service;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoanApplicationServiceTest {

    @InjectMocks
    private LoanApplicationService loanApplicationService;

    @Test
    public void test() {
        loanApplicationService.apply(new LoanApplicationDto());
    }
}
