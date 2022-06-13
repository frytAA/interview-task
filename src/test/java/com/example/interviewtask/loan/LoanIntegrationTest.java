package com.example.interviewtask.loan;

import com.example.interviewtask.loan.dto.LoanApplicationDto;
import com.example.interviewtask.loan.dto.LoanApplicationDtoBuilder;
import com.example.interviewtask.loan.entity.LoanEntity;
import com.example.interviewtask.loan.service.LoanService;
import com.example.interviewtask.repository.LoanRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class LoanIntegrationTest {
    private static final int TERM = 100;
    private static final BigDecimal AMOUNT = BigDecimal.valueOf(1000);
    public static final BigDecimal AMOUNT_AFTER_EXTENSION = BigDecimal.valueOf(1210.0);
    private final static BigDecimal RATE = BigDecimal.valueOf(1.1);
    private static final LocalDateTime LOAN_DUE_DATE = LocalDateTime.of(2022, 6, 12, 12, 0, 1);
    private static final LocalDateTime EXPECTED_DATE_AFTER_EXTENSION = LOAN_DUE_DATE.plusDays(TERM).plusDays(TERM);

    private final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

    @Autowired
    public LoanRepository loanRepository;

    @Autowired
    private LoanService loanService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void applyForLoan() throws Exception {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withTerm(40)
                .withAmount(AMOUNT)
                .withCreationDate(LOAN_DUE_DATE)
                .withTerm(TERM)
                .build();

        // when
        this.mockMvc.perform(post("/api/application/apply")
                .content(objectMapper.writeValueAsString(loanApplicationDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // then
        List<LoanEntity> loans = loanRepository.findAll();
        assertThat(loans).hasSize(1);
        assertThat(loans.get(0).getTerm()).isEqualTo(TERM);
        assertThat(loans.get(0).getAmount()).isEqualTo(AMOUNT.multiply(RATE));
        assertThat(loans.get(0).getDueDate()).isEqualTo(LOAN_DUE_DATE.plusDays(TERM));
    }


    @Test
    public void createExtendAndFetchLoan() throws Exception {
        // given
        LoanApplicationDto loanApplicationDto = LoanApplicationDtoBuilder
                .aLoanApplicationDto()
                .withTerm(40)
                .withAmount(AMOUNT)
                .withCreationDate(LOAN_DUE_DATE)
                .withTerm(TERM)
                .build();

        // create
        this.mockMvc.perform(post("/api/application/apply")
                .content(objectMapper.writeValueAsString(loanApplicationDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // extend
        this.mockMvc.perform(put("/api/loans/1/extend/"))
                .andExpect(status().isOk());

        // fetch
        this.mockMvc.perform(get("/api/loans/1/get/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.term").value(TERM))
                .andExpect(jsonPath("$.amount").value(AMOUNT_AFTER_EXTENSION))
                .andExpect(jsonPath("$.dueDate", is(EXPECTED_DATE_AFTER_EXTENSION.toString())));

        // then verify database state
        List<LoanEntity> loans = loanRepository.findAll();
        assertThat(loans).hasSize(1);
        assertThat(loans.get(0).getTerm()).isEqualTo(TERM);
        assertThat(loans.get(0).getAmount()).isEqualTo(AMOUNT.multiply(RATE).multiply(RATE));
        assertThat(loans.get(0).getDueDate()).isEqualTo(LOAN_DUE_DATE.plusDays(TERM).plusDays(TERM).toString());
    }
}
