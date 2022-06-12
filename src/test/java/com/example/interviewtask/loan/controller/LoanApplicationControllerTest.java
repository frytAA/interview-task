package com.example.interviewtask.loan.controller;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import com.example.interviewtask.loan.dto.LoanApplicationDtoBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class LoanApplicationControllerTest {
    private final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

    private MockMvc mockMvc;

    @InjectMocks
    private LoanApplicationController loanApplicationController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(loanApplicationController).build();
    }

    @Test
    public void applyForLoan() throws Exception {
        // given
        LoanApplicationDto value = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withTerm(40)
                .withAmmount(BigDecimal.TEN)
                .withCreationDate(LocalDate.now())
                .build();

        // when then
        this.mockMvc.perform(post("/api/application/apply")
                .content(objectMapper.writeValueAsString(value))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
