package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessage(Integer id) {
        return messageRepository.findByMessageId(id);
    }

    public int deleteMessage(Integer id) {
        if(messageRepository.findByMessageId(id) != null) {
            messageRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    public int updateMessage(Integer id, String newText) {
        if(messageRepository.findByMessageId(id) != null && newText.length()-20 > 0 && newText.length()-20 < 255) {
            messageRepository.findByMessageId(id).getMessage_text().equals(newText);
            return 1;
        }
        return 0;
    }

    public List<Message> getMessagesFromUser(Integer id) {
        return messageRepository.findAllByAccountId(id);
    }

}
