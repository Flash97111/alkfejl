/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.FooProject.repositories;

import hu.elte.FooProject.entities.Vehicles;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author rebeka
 */
public interface VehiclesRepository extends CrudRepository<Vehicles, Integer> {
    public Optional<Vehicles> findByName(String name);
}
