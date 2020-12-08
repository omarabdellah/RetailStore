/*
 * To change this license headiscountServicer, choose License HeadiscountServicers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omar.service;


import com.omar.model.Item;
import com.omar.model.ItemType;
import com.omar.model.User;
import com.omar.model.UserType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.InjectMocks;


/**
 *
 * @author omar.abdiscountServicellah
 */
public class DiscountserviceTest {

    @InjectMocks
    DiscountService discountService;

    @Test
    public void testCalculateTotal_GroceriesOnly() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(Double.valueOf(100.0), "Apple", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Banana", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Kiwi", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Pear", ItemType.GROCERY));

        double total = discountService.calculateTotal(items, ItemType.GROCERY);
        assertEquals(400.00, total, 0);
    }

    @Test
    public void testCalculateTotal_NonGroceriesOnly() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(Double.valueOf(100.0), "Pencil", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Eraser", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Stapler", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Glue", ItemType.STATIONARY));

        double total = discountService.calculateTotal(items, ItemType.STATIONARY);
        assertEquals(400.00, total, 0);
    }

    @Test
    public void testCalculateTotal_GroceriesMix() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(Double.valueOf(100.0), "Pencil", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Eraser", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Banana", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Kiwi", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Pear", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Stapler", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Glue", ItemType.STATIONARY));

     
        double total = discountService.calculateTotal(items, ItemType.GROCERY);
        assertEquals(300.00, total, 0);
    }

    @Test
    public void testCalculateTotal_NonGroceriesMix() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(Double.valueOf(100.0), "Pencil", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Eraser", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Banana", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Kiwi", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Pear", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Stapler", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Glue", ItemType.STATIONARY));

        double total = discountService.calculateTotal(items, ItemType.STATIONARY);
        assertEquals(400.00, total, 0);
    }

    @Test
    public void testCalculateTotal_AllMix() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(Double.valueOf(100.0), "Pencil", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Eraser", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Banana", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Kiwi", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Pear", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Stapler", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Glue", ItemType.STATIONARY));

 
        double total = discountService.calculateTotal(items, null);
        assertEquals(700.00, total, 0);
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateTotal_error() {
 
        discountService.calculateTotal(null, ItemType.CLEANING);
    }

    @Test
    public void testCalculateDiscount_10pct() {
 
        double total = discountService.calculateDiscount(1000, 0.1);
        assertEquals(900.00, total, 0);
    }

    @Test
    public void testCalculateDiscount_50pct() {
 
        double total = discountService.calculateDiscount(1000, 0.5);
        assertEquals(500.00, total, 0);
    }

    @Test
    public void testCalculateDiscount_0pct() {
 
        double total = discountService.calculateDiscount(1000, 0.0);
        assertEquals(1000.00, total, 0);
    }

    @Test
    public void testCalculateDiscount_100pct() {
 
        double total = discountService.calculateDiscount(1000, 1.0);
        assertEquals(0.0, total, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateDiscount_error() {
 
        discountService.calculateDiscount(1000, 2.0);
    }

    @Test
    public void testGetUserSpecificDiscount_affiliate() {
        User user = new User(Long.valueOf(100), "omar", UserType.AFFILIATE, LocalDate.now());
 
        double discount = discountService.getUserSpecificDiscount(user);
        assertEquals(0.1, discount, 0);
    }

    @Test
    public void testGetUserSpecificDiscount_employee() {
        User user = new User(Long.valueOf(100), "omar", UserType.EMPLOYEE, LocalDate.now());
 
        double discount = discountService.getUserSpecificDiscount(user);
        assertEquals(0.3, discount, 0);
    }

    @Test
    public void testGetUserSpecificDiscount_customer_old() {
        LocalDate joinDate = LocalDate.of(2016, 2, 23);
        User user = new User(Long.valueOf(100), "omar", UserType.CUSTOMER, joinDate);
 
        double discount = discountService.getUserSpecificDiscount(user);
        assertEquals(0.05, discount, 0);
    }

    @Test
    public void testGetUserSpecificDiscount_customer_new() {
        User user = new User(Long.valueOf(100), "omar", UserType.CUSTOMER, LocalDate.now());
 
        double discount = discountService.getUserSpecificDiscount(user);
        assertEquals(0.0, discount, 0);
    }

    @Test(expected = NullPointerException.class)
    public void testGetUserSpecificDiscount_customer_null_user() {
 
        discountService.getUserSpecificDiscount(null);
    }

    @Test
    public void testIsCustomerSince() {
 
        LocalDate joinDate = LocalDate.now();
        boolean isTwoYearsJoined = discountService.isCustomerSince(joinDate, 2);
        assertFalse(isTwoYearsJoined);
    }

    @Test
    public void testIsCustomerSince_1year() {
 
        LocalDate joinDate = LocalDate.now().minusYears(1);
        boolean isTwoYearsJoined = discountService.isCustomerSince(joinDate, 2);
        assertFalse(isTwoYearsJoined);
    }

    @Test
    public void testIsCustomerSince_2years() {
 
        LocalDate joinDate = LocalDate.now().minusYears(2);
        boolean isTwoYearsJoined = discountService.isCustomerSince(joinDate, 2);
        assertTrue(isTwoYearsJoined);
    }

    @Test
    public void testIsCustomerSince_3years() {
 
        LocalDate joinDate = LocalDate.now().minusYears(3);
        boolean isTwoYearsJoined = discountService.isCustomerSince(joinDate, 2);
        assertTrue(isTwoYearsJoined);
    }

    @Test
    public void testCalculateBillsDiscount() {
 
        double amount = discountService.calculateBillsDiscount(1000, 100, 5);
        assertEquals(50, amount, 0);
    }

    @Test
    public void testCalculateBillsDiscount_2() {
 
        double amount = discountService.calculateBillsDiscount(1000, 50, 5);
        assertEquals(100, amount, 0);
    }

    @Test
    public void testCalculateBillsDiscount_3() {
 
        double amount = discountService.calculateBillsDiscount(5632, 100, 5);
        assertEquals(280, amount, 0);
    }
}
