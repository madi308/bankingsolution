package com.tuum.bankingsolution.dtomapper;

import com.tuum.bankingsolution.dto.AccountDto;
import com.tuum.bankingsolution.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DtoAccountMapper {

    AccountDto entityToDto(Account entity);
}
