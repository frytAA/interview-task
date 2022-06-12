package com.example.interviewtask.loan.mapper;

import com.example.interviewtask.loan.dto.LoanDto;
import com.example.interviewtask.loan.dto.LoanDtoBuilder;
import com.example.interviewtask.loan.entity.LoanEntity;
import com.example.interviewtask.loan.entity.LoanEntityBuilder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LoanMapperTest {

    private static final int TERM_DAYS = 30;
    private final LoanMapper loanMapper = new LoanMapperImpl();

    @Test
    public void toDto() {
        // given
        LocalDateTime dueDate = LocalDateTime.now();
        LoanEntity loanEntity = LoanEntityBuilder
                .aLoanEntity()
                .withAmmount(BigDecimal.TEN)
                .withDueDate(dueDate)
                .withTerm(TERM_DAYS)
                .build();

        // when
        LoanDto loanDto = loanMapper.toDto(loanEntity);

        // then
        assertThat(loanDto.getDueDate()).isEqualTo(dueDate);
        assertThat(loanDto.getAmmount()).isEqualTo(BigDecimal.TEN);
        assertThat(loanDto.getTerm()).isEqualTo(TERM_DAYS);
    }

    @Test
    public void toEntity() {
        // given
        LocalDateTime dueDate = LocalDateTime.now();
        LoanDto loanDto = LoanDtoBuilder
                .aLoanDto()
                .withAmmount(BigDecimal.TEN)
                .withDueDate(dueDate)
                .withTerm(TERM_DAYS)
                .build();

        // when
        LoanEntity loanEntity = loanMapper.toEntity(loanDto);

        // then
        assertThat(loanEntity.getDueDate()).isEqualTo(dueDate);
        assertThat(loanEntity.getAmmount()).isEqualTo(BigDecimal.TEN);
        assertThat(loanEntity.getTerm()).isEqualTo(TERM_DAYS);
    }
}
