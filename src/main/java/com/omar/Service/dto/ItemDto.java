/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omar.Service.dto;

import com.omar.model.*;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author omar.abdellah
 */
@Getter
@Setter
@NoArgsConstructor

public class ItemDto implements Serializable {

    private Long id;

    private Double price;

    private String name;
   private String ItemType ;
}
