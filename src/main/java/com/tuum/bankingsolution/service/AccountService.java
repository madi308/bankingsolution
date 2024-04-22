package com.tuum.bankingsolution.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuum.bankingsolution.enums.Currency;
import com.tuum.bankingsolution.mapper.AccountMapper;
import com.tuum.bankingsolution.model.Account;
import com.tuum.bankingsolution.model.Balance;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountMapper accountMapper;

    private final BalanceService balanceService;

    private final RabbitTemplate rabbitTemplate;

    private final Queue queue;

    private final ObjectMapper objectMapper;

    public Account getAccount(long id) {
        Account account = accountMapper.getAccount(id);
        if (account == null)
            throw new RuntimeException("Account not found");
        account.setBalances(balanceService.getBalancesByAccount(account.getId()));
        return account;
    }

    public Account addAccount(Map<String, Object> payload) {
        Account account = getAccountFromPayload(payload);
        long accountId = accountMapper.addAccount(account);
        List<Currency> currencies = getCurrenciesFromPayload(payload);
        currencies.forEach(currency ->
            balanceService.addBalance(Balance.builder()
                    .accountId(accountId)
                    .currency(currency)
                    .balance(0)
                    .build()));
        Account addedAccount = getAccount(accountId);
        try {
            String json = objectMapper.writeValueAsString(addedAccount);
            rabbitTemplate.convertAndSend(queue.getName(), json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return addedAccount;
    }

    private Account getAccountFromPayload(Map<String, Object> payload) {
        Object object = payload.get("account");
        if (!(object instanceof Map)) {
            throw new RuntimeException();
        }
        long customerId = Long.parseLong((String) ((Map<?, ?>) object).get("customerId"));
        String country = (String) ((Map<?, ?>) object).get("country");
        if (country == null)
            throw new RuntimeException();
        return Account.builder()
                .customerId(customerId)
                .country(country)
                .build();
    }

    private List<Currency> getCurrenciesFromPayload(Map<String, Object> payload) {
        Object listObject = payload.get("currencies");
        if (!(listObject instanceof List))
            throw new RuntimeException();
        List<Currency> currencies = new ArrayList<>();
        ((List<?>) listObject).forEach(item -> {
            if (item instanceof String currencyString) {
                currencies.add(Currency.valueOf(currencyString));
            } else {
                throw new RuntimeException("Invalid currency");
            }
        });
        return currencies;
    }
}
