/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.FooProject.controllers;

import hu.elte.FooProject.entities.Messages;
import hu.elte.FooProject.entities.User;
import hu.elte.FooProject.repositories.MessagesRepository;
import hu.elte.FooProject.repositories.UserRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rebeka
 */
@RestController
@RequestMapping("/messages")
public class MessagesController {
    @Autowired
    private MessagesRepository messagesRepository;
    @Autowired
    private UserRepository userRepository;
    
    //Üzenetek
    @GetMapping("")
    public ResponseEntity<Iterable<Messages>> getAll() {
        return ResponseEntity.ok(messagesRepository.findAll());
    }
    
    //Adott ID-jú üzenet törlése, ha van ilyen
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Messages> oMessages = messagesRepository.findById(id);
        if (!oMessages.isPresent()) {
            return ResponseEntity.notFound().build();   
        }
            
        messagesRepository.delete(oMessages.get());
        return ResponseEntity.ok().build();
    }
    
    //Üzenet küldése adott felhasználónak, ha van ilyen
    @PostMapping("")
    public ResponseEntity<Messages> post(@RequestBody Messages message) {
        Optional<User> oUser = userRepository.findById(message.getReceiver_id());
        if (!oUser.isPresent()) {
            return ResponseEntity.notFound().build();   
        }
        message.setId(null);
        message.setOpened(false);
        Date date = new Date();
        message.setDate(date);
        return ResponseEntity.ok(messagesRepository.save(message));
    }
}
