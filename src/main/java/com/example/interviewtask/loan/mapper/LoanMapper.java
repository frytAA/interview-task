package com.example.interviewtask.loan.mapper;


import com.example.interviewtask.config.MapperConfig;
import com.example.interviewtask.loan.dto.LoanDto;
import com.example.interviewtask.loan.entity.LoanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface LoanMapper {

    LoanDto toDto(LoanEntity loanEntity);

    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    LoanEntity toEntity(LoanDto loanDto);
}
