package com.wuqihang.syblog.services;

import com.wuqihang.syblog.pojo.Account;
import com.wuqihang.syblog.pojo.User;

import java.util.List;

public interface AccountService {
    Account getAccount(String id);

    Account getAccountByUsername(String username);

    boolean insert(Account account);

    Account create(Account account);

    boolean update(Account account);

    void delete(Account account);

    void delete(String id);

    List<Account> getAllAccount();

    User getUser(Account account);

    Account copyNullUserAccount(Account account);
}
