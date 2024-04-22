package com.tuum.bankingsolution.service;

import com.tuum.bankingsolution.mapper.BalanceMapper;
import com.tuum.bankingsolution.model.Balance;
import com.tuum.bankingsolution.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceMapper balanceMapper;

    public List<Balance> getBalancesByAccount(long accountId) {
        return balanceMapper.getBalancesByAccount(accountId);
    }

    public List<Balance> getBalancesByTransaction(Transaction transaction) {
        return balanceMapper.getBalancesByTransaction(transaction);
    }

    public void addBalance(Balance balance) {
        balanceMapper.addBalance(balance);
    }

    public void updateBalance(Balance balance) {
        balanceMapper.updateBalance(balance);
    }
}
