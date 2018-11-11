/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.FooProject.entities;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author rebeka
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Messages implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    @NotNull
    private Integer sender_id;
    
    @Column
    @NotNull
    private Integer receiver_id;
    
    @Column
    @NotNull
    private String title;
    
    @Column
    @NotNull
    private String message;
    
    @Column
    @NotNull
    private boolean opened;
    
    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @JsonIgnore
    @JoinColumn
    @ManyToOne
    private User user;
}
