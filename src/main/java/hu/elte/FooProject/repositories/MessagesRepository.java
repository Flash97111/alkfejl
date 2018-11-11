/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.FooProject.repositories;

import hu.elte.FooProject.entities.Messages;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author rebeka
 */
public interface MessagesRepository extends CrudRepository<Messages, Integer> {

}
