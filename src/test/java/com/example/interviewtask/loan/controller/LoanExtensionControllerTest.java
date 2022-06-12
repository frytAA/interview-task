package com.example.interviewtask.loan.controller;

import com.example.interviewtask.loan.dto.LoanDto;
import com.example.interviewtask.loan.entity.LoanEntity;
import com.example.interviewtask.loan.mapper.LoanMapper;
import com.example.interviewtask.loan.service.LoanExtensionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LoanExtensionControllerTest {

    MockMvc mockMvc;

    @Mock
    private LoanExtensionService loanExtensionService;

    @Mock
    private LoanMapper loanMapper;

    @InjectMocks
    private LoanExtensionController loanExtensionController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(loanExtensionController)
                .build();
    }

    @Test
    public void statusOkWhenLoanExists() throws Exception {
        // given
        LoanEntity loanEntity = new LoanEntity();
        when(loanExtensionService.extendLoan(1L)).thenReturn(Optional.of(loanEntity));
        when(loanMapper.toDto(loanEntity)).thenReturn(new LoanDto());

        // when then
        this.mockMvc.perform(put("/api/loans/1/extend/"))
                .andExpect(status().isOk());
    }

    @Test
    public void noContentForNotExistingLoan() throws Exception {
        this.mockMvc.perform(put("/api/loans/1/extend/"))
                .andExpect(status().isNoContent());
    }
}
