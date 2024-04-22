package com.tuum.bankingsolution.mapper;

import com.tuum.bankingsolution.model.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransactionMapper {

    @Select("SELECT * FROM TRANSACTIONS WHERE account_id = #{accountId}")
    List<Transaction> getTransactionsByAccount(long accountId);

    @Select("SELECT * FROM TRANSACTIONS WHERE id = #{transactionId}")
    List<Transaction> getTransaction(long transactionId);

    @Select("INSERT INTO TRANSACTIONS (account_id, amount, currency, transaction_direction, description) " +
            "VALUES (#{accountId}, #{amount}, #{currency}, #{transactionDirection}, #{description}) RETURNING id")
    long addTransaction(Transaction transaction);
}
