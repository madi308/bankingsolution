package com.tuum.bankingsolution.service;

import com.tuum.bankingsolution.enums.TransactionDirection;
import com.tuum.bankingsolution.mapper.TransactionMapper;
import com.tuum.bankingsolution.model.Balance;
import com.tuum.bankingsolution.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionMapper transactionMapper;
    private final BalanceService balanceService;
    private final AccountService accountService;

    public List<Transaction> getTransactions(long accountId) {
        //This is here for checking if the account exists. If it doesn't, and exception gets thrown in the method called.
        accountService.getAccount(accountId);
        return transactionMapper.getTransactionsByAccount(accountId);
    }

    public Transaction addTransaction(Transaction transaction) {
        TransactionDirection transactionDirection = transaction.getTransactionDirection();
        if (transaction.getCurrency() == null)
            throw new RuntimeException("Invalid currency");
        if (transactionDirection == null) {
            throw new RuntimeException("Invalid direction");
        }
        if (accountService.getAccount(transaction.getAccountId()) == null)
            throw new RuntimeException("Account missing");
        String description = transaction.getDescription();
        if (description == null || description.isEmpty())
            throw new RuntimeException("Description missing");
        double amount = transaction.getAmount();
        if (amount <= 0)
            throw new RuntimeException("Invalid amount: " + amount);

        List<Balance> balances = balanceService.getBalancesByTransaction(transaction);
        if (balances.isEmpty())
            throw new RuntimeException("Currency not allowed for this account");
        Balance balance = balances.getFirst();

        double balanceBefore = balance.getBalance();

        if (transactionDirection == TransactionDirection.IN)
            balance.setBalance(balanceBefore + amount);
        else if (transactionDirection == TransactionDirection.OUT) {
            if (balanceBefore - amount < 0)
                throw new RuntimeException("Insufficient funds");
            balance.setBalance(balanceBefore - amount);
        }
        balanceService.updateBalance(balance);
        long id = transactionMapper.addTransaction(transaction);
        return transactionMapper.getTransaction(id).getFirst();
    }
}
