/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omar.controller;

import com.omar.Service.DiscountService;
import com.omar.Service.dto.DiscountRequest;
import com.omar.Service.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author omar.abdellah
 */
@RestController
@RequestMapping("discounts")

@Scope(value = "request")

public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @RequestMapping(value = "/discountCalculation", method = RequestMethod.POST)
    public ResponseEntity<Response> discountCalculation(@RequestBody DiscountRequest discountRequest) {
        Response responses = new Response(true, " discountCalculation is Calculated  successfully");

        
        
                    discountService.discountCalculation(discountRequest);

        return new ResponseEntity<Response>(responses, HttpStatus.OK);
    }

}
