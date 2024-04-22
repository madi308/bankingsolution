package com.tuum.bankingsolution.mapper;

import com.tuum.bankingsolution.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {


    @Select("SELECT * FROM ACCOUNTS WHERE id = #{id}")
    Account getAccount(long id);

    @Select("INSERT INTO ACCOUNTS (customer_id, country) VALUES (#{customerId}, #{country}) RETURNING id")
    long addAccount(Account account);
}
