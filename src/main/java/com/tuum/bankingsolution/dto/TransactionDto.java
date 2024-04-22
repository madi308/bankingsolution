package com.tuum.bankingsolution.dto;

import com.tuum.bankingsolution.enums.Currency;
import com.tuum.bankingsolution.enums.TransactionDirection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private long id;

    private long accountId;

    private double amount;

    private Currency currency;

    private TransactionDirection transactionDirection;

    private String description;
}
