/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.FooProject.repositories;

import hu.elte.FooProject.entities.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author rebeka
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    public Optional<User> findByUsername(String name);
}
