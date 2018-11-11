/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.FooProject.controllers;

import hu.elte.FooProject.entities.User;
import hu.elte.FooProject.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rebeka
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    //összes felhasználó
    @GetMapping("")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Iterable<User>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }
    
    //adott ID-jú felhasználó, ha van ilyen
    @GetMapping("/{id}")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<User> get(@PathVariable Integer id) {
        Optional<User> oUser = userRepository.findById(id);
        if (!oUser.isPresent()) {
            return ResponseEntity.notFound().build();   
        }
        
        return ResponseEntity.ok(oUser.get());
    }
    
    //adott ID-jú felhasználó törlése, ha van ilyen
    @DeleteMapping("/{id}")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<User> oUser = userRepository.findById(id);
        if (!oUser.isPresent()) {
            return ResponseEntity.notFound().build();   
        }
            
        userRepository.delete(oUser.get());
        return ResponseEntity.ok().build();
    }
    
    //adott ID-jú felhasználó adminná tétele, ha van ilyen
    @PutMapping("/{id}")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<User> putToAdmin(@PathVariable Integer id, @RequestBody User user) {
        Optional<User> oUser = userRepository.findById(id);
        if (!oUser.isPresent()) {
            System.out.println("asd");
            return ResponseEntity.notFound().build();
        }
        
        user.setId(id);
        user.setUsername(oUser.get().getUsername());
        user.setPassword(oUser.get().getPassword());
        user.setHighscore(oUser.get().getHighscore());
        user.setScores(oUser.get().getScores());
        user.setMessages(oUser.get().getMessages());
        user.setVehicles(oUser.get().getVehicles());
        user.setRole(User.Role.ROLE_ADMIN);
        return ResponseEntity.ok(userRepository.save(user));
    }
    
    //regisztrálja a felhasználót ha még nincs ilyen
    @PostMapping("/register")
    public ResponseEntity<User> post(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByUsername(user.getUsername());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setId(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.ROLE_USER);
        user.setHighscore(0);
        return ResponseEntity.ok(userRepository.save(user));
    }
    
    //bejelentkezés
    @PostMapping("login")
    public ResponseEntity login(@RequestBody User user) {
        return ResponseEntity.ok().build();
    } 
    
}
