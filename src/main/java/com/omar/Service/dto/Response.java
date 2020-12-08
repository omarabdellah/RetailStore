/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omar.Service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author omar.abdellah
 */

@Getter
@Setter
public class Response {

      Object object;
    boolean successful;
    String message;
    String data;

    public Response(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    public Response(boolean successful, Object object, String message, String data) {
        this.successful = successful;
        this.object = object;
        this.message = message;
        this.data = data;
    }
    
    
}
