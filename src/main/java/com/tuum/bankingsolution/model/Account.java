package com.tuum.bankingsolution.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private long id;

    private long customerId;

    private String country;

    List<Balance> balances;
}
