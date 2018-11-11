/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.FooProject.controllers;

import hu.elte.FooProject.entities.Scores;
import hu.elte.FooProject.repositories.ScoresRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rebeka
 */
@RestController
@RequestMapping("/scores")
public class ScoresController {
    @Autowired
    private ScoresRepository scoresRepository;
    
    //Játékosok játékai
    @GetMapping("")
    public ResponseEntity<Iterable<Scores>> getAll() {
        return ResponseEntity.ok(scoresRepository.findAll());
    }
    
    //Adott ID-jú játékos játékai, ha van ilyen
    @GetMapping("/{id}")
    public ResponseEntity<Scores> get(@PathVariable Integer id) {
        Optional<Scores> oScores = scoresRepository.findById(id);
        if (!oScores.isPresent()) {
            return ResponseEntity.notFound().build();   
        }
        
        return ResponseEntity.ok(oScores.get());
    }
}
