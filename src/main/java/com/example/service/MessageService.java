package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    
    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    public void setMessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository =  messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message mes) {
        return messageRepository.save(mes);
    }

    public boolean checkIfAccountExists(Message mes) {
        if(accountRepository.findByAccountId(mes.getPosted_by()) != null) {
            return true;
        }
        return false;
    }

}
