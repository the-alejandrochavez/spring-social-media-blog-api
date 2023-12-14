package com.example.controller;

import org.springframework.stereotype.Controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@Controller
public class SocialMediaController {
    
    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account acc) {
        if(accountService.userExistsByUsername(acc.getUsername())) {
            return ResponseEntity.status(409).build();
        }
        if(acc.getPassword().length() < 4 || acc.getUsername().length() == 0) {
            return ResponseEntity.status(400).build();
        }

        accountService.registerUser(acc);
        return ResponseEntity.status(200).body(acc);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginUser(@RequestBody Account acc) {
        if(accountService.userExistsByUsernameAndPassword(acc.getUsername(), acc.getPassword())) {
            Account loggedIn = accountService.loginUser(acc);
            return ResponseEntity.status(200).body(loggedIn);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> newMessage(@RequestBody Message mes) {
        if(mes.getMessage_text().length() > 0 && mes.getMessage_text().length() <= 255 && messageService.checkIfAccountExists(mes)) {
            messageService.createMessage(mes);
            return ResponseEntity.status(200).body(mes);
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/messages")
    public @ResponseBody List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{message_id}")
    public @ResponseBody Message getMessageById(@PathVariable Integer message_id) {
        return messageService.getMessage(message_id);
    }

    @DeleteMapping("/messages/{message_id}")
    public @ResponseBody ResponseEntity<Integer> deleteMessageById(@PathVariable Integer message_id) {
        int val = messageService.deleteMessage(message_id);

        if(val == 0) {
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(200).body(val);
    }

}
