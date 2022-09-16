package com.wuqihang.syblog.services;

import com.wuqihang.syblog.mapper.AccountMapper;
import com.wuqihang.syblog.mapper.UserMapper;
import com.wuqihang.syblog.pojo.Account;
import com.wuqihang.syblog.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Wuqihang
 */
@Component
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final UserMapper userMapper;

    public AccountServiceImpl(AccountMapper accountMapper, UserMapper userMapper) {
        this.accountMapper = accountMapper;
        this.userMapper = userMapper;
    }


    @Override
    public Account getAccount(String id) {
        return accountMapper.getAccountById(id);
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountMapper.getAccountByUsername(username);
    }

    @Override
    public boolean insert(Account account) {
        if (account == null) return false;
        if (account.getUser() == null) {
            return false;
        }
        try {
            userMapper.insert(account.getUser());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            accountMapper.insertAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
            userMapper.delete(account.getUser().getUsername());
            return false;
        }
        return true;
    }

    @Override
    public Account create(Account account) {
        if (account.getUser() == null || account.getUser().getUsername() == null || account.getUser().getPassword() == null) {
            return null;
        }
        account.getUser().setId(String.valueOf(System.currentTimeMillis()));
        return account;
    }

    @Override
    public boolean update(Account account) {
        if (account.getUser() == null) return false;
        if (getUser(account) == null) return false;
        try {
            userMapper.update(account.getUser());
            accountMapper.updateAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void delete(Account account) {
        if (account.getUser() == null) {
            return;
        }
        delete(account.getUser().getId());
    }

    @Override
    public void delete(String id) {
        accountMapper.deleteAccount(id);
        userMapper.delete(id);
    }

    @Override
    public List<Account> getAllAccount() {
        return accountMapper.getAllAccount();
    }

    @Override
    public User getUser(Account account) {
        if (account.getUser() == null) return null;
        User user = userMapper.getUserByUsername(account.getUser().getUsername());
        account.setUser(user);
        return user;
    }
}
