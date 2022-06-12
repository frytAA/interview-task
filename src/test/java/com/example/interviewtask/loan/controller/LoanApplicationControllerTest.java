package com.example.interviewtask.loan.controller;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import com.example.interviewtask.loan.dto.LoanApplicationDtoBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoanApplicationControllerTest {
    private final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

    @Autowired
    private MockMvc mockMvc;

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
