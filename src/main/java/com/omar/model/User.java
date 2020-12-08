/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author omar.abdellah
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USERS")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAMENAME", length = 100, unique = true)
    private String username ;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    private UserType type;

    private LocalDate joinDate;
    
 public User(Long id, String username, UserType type, LocalDate joinDate) {
        this.id = id;
        this.username = username;
     
        this.type = type;
        this.joinDate = joinDate;
    }
}
