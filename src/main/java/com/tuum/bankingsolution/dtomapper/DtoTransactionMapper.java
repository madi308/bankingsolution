package com.tuum.bankingsolution.dtomapper;

import com.tuum.bankingsolution.dto.TransactionDto;
import com.tuum.bankingsolution.model.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DtoTransactionMapper {

    TransactionDto entityToDto(Transaction transaction);

    Transaction dtoToEntity(TransactionDto transactionDto);

    List<TransactionDto> entitiesToDtoList(List<Transaction> entities);
}
