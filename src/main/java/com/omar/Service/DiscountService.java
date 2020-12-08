/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omar.Service;

import com.omar.Service.dto.DiscountRequest;
import com.omar.Service.dto.ItemDto;
import com.omar.dao.ItemRepo;
import com.omar.dao.UserRepo;
import com.omar.model.Item;
import com.omar.model.ItemType;
import com.omar.model.User;
import com.omar.model.UserType;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author omar.abdellah
 */
@Service
public class DiscountService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private UserRepo userRepo;

    public Double discountCalculation(DiscountRequest discountRequest) {
        User user = userRepo.findByUsername(discountRequest.getUsername());

        double userDiscount = getUserSpecificDiscount(user);

        double groceryAmount = calculateTotal(discountRequest.getItem(), ItemType.GROCERY);
        double totalAmount = calculateTotal(discountRequest.getItem(), null);
        double nonGroceryAmount = totalAmount - groceryAmount;

        double billsDiscount = calculateBillsDiscount(totalAmount, 100, 5);
        if (nonGroceryAmount > 0) {
            nonGroceryAmount = calculateDiscount(nonGroceryAmount, userDiscount);
        }
        double finalAmount = (groceryAmount + nonGroceryAmount) - billsDiscount;
        return Double.valueOf(finalAmount);
    }

    public final double getUserSpecificDiscount(User user) throws NullPointerException {
        if (user == null) {
            throw new NullPointerException("user cannot be null");
        }

        UserType type = user.getType();
        double discount = 0;

        switch (type) {
            case AFFILIATE: {
                discount = 0.1;
                break;
            }

            case CUSTOMER: {
                if (isCustomerSince(user.getJoinDate(), 2)) {
                    discount = 0.05;
                }
                break;
            }

            case EMPLOYEE: {
                discount = 0.3;
                break;
            }

            default: {
                break;
            }
        }

        return discount;
    }

    public final boolean isCustomerSince(LocalDate joinDate, long years) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(joinDate, now);
        int diff = period.getYears();
        return diff >= years;
    }

    public final double calculateTotal(List<ItemDto> items, ItemType type) throws NullPointerException {
        if (items == null) {
            throw new NullPointerException("items cannot be null");
        }

        double total = 0.0;
        for (ItemDto item : items) {
            if (type == null) {
                total = total + item.getPrice().doubleValue();
            } else {
                if (type.equals(item.getItemType())) {
                    total = total + item.getPrice().doubleValue();
                }
            }
        }

        return total;
    }

    public final double calculateDiscount(double amount, double discount) throws IllegalArgumentException {
        if (discount > 1.0) {
            throw new IllegalArgumentException("discount cannot be more than 1.0");
        }

        double x = amount * discount;
        return amount - x;
    }

    public final double calculateBillsDiscount(double totalAmount, double amount, double discountAmount) {
        int count = (int) (totalAmount / amount);
        return discountAmount * count;
    }

}
