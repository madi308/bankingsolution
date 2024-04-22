package com.tuum.bankingsolution.mapper;

import com.tuum.bankingsolution.model.Balance;
import com.tuum.bankingsolution.model.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BalanceMapper {

    @Select("SELECT * FROM BALANCES WHERE account_id = #{accountId}")
    List<Balance> getBalancesByAccount(long accountId);

    @Select("SELECT * FROM BALANCES WHERE account_id = #{accountId} AND currency = #{currency}")
    List<Balance> getBalancesByTransaction(Transaction transaction);

    @Insert("INSERT INTO BALANCES (account_id, currency, balance) " +
            "VALUES (#{accountId}, #{currency}, #{balance})")
    void addBalance(Balance balance);

    @Update("UPDATE BALANCES SET balance = #{balance} WHERE id = #{id}")
    void updateBalance(Balance balance);
}
