package com.example.interviewtask.loan.controller;

import com.example.interviewtask.loan.dto.LoanDto;
import com.example.interviewtask.loan.entity.LoanEntity;
import com.example.interviewtask.loan.mapper.LoanMapper;
import com.example.interviewtask.loan.service.LoanService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class LoanControllerTest {

    MockMvc mockMvc;

    @Mock
    private LoanService loanService;

    @Mock
    private LoanMapper loanMapper;

    @InjectMocks
    private LoanController loanController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(loanController).build();
    }

    @Test
    public void statusOkWhenLoanExists() throws Exception {
        // given
        LoanEntity loanEntity = new LoanEntity();
        when(loanService.getLoan(1L)).thenReturn(Optional.of(loanEntity));
        when(loanMapper.toDto(loanEntity)).thenReturn(new LoanDto());

        // when then
        this.mockMvc.perform(get("/api/loans/1/get/"))
                .andExpect(status().isOk());
    }

    @Test
    public void noContentForNotExistingLoan() throws Exception {
        this.mockMvc.perform(get("/api/loans/1/get/"))
                .andExpect(status().isNoContent());
    }
}
