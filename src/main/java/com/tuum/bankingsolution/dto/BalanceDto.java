package com.tuum.bankingsolution.dto;

import com.tuum.bankingsolution.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {

    private long id;

    private long accountId;

    private Currency currency;

    private double balance;
}
