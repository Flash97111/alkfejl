/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.FooProject.controllers;
import hu.elte.FooProject.entities.Vehicles;
import hu.elte.FooProject.repositories.VehiclesRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/vehicles")
public class VehiclesController {
    @Autowired
    private VehiclesRepository vehiclesRepository;
    
    //Járművek
    @GetMapping("")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Iterable<Vehicles>> getAll() {
        return ResponseEntity.ok(vehiclesRepository.findAll());
    }
    
    //Adott ID-jú jármű törlése, ha van ilyen
    @DeleteMapping("/{id}")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Vehicles> oVehicles = vehiclesRepository.findById(id);
        if (!oVehicles.isPresent()) {
            return ResponseEntity.notFound().build();   
        }
            
        vehiclesRepository.delete(oVehicles.get());
        return ResponseEntity.ok().build();
    }
    
    //Új jármű létrehozása, ha még nincs ilyen
    @PostMapping("")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Vehicles> post(@RequestBody Vehicles vehicle) {
        Optional<Vehicles> oVehicles = vehiclesRepository.findByName(vehicle.getName());
        if (oVehicles.isPresent()) {
            return ResponseEntity.badRequest().build();   
        }
        vehicle.setId(null);
        return ResponseEntity.ok(vehiclesRepository.save(vehicle));
    }
}

