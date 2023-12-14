package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerUser(Account acc) {
        return accountRepository.save(acc);
    }

    public Account loginUser(Account acc) {
        return accountRepository.findByUsernameAndPassword(acc.getUsername(), acc.getPassword());
    }

    // public Account userExistsByAccountId(Account acc) {
    //     return accountRepository.findByAccountId(acc.getAccount_id());
    // }

    public boolean userExistsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    public boolean userExistsByUsernameAndPassword(String username, String password) {
        return accountRepository.existsByUsernameAndPassword(username, password);
    }

}
