package com.tuum.bankingsolution.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private long id;

    private long customerId;

    private String country;

    private List<BalanceDto> balances;
}
