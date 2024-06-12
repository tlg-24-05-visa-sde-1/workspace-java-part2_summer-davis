/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.product;

import com.javatunes.billing.Location;
import com.javatunes.billing.TaxCalculator;
import com.javatunes.billing.TaxCalculatorFactory;

import java.util.Collection;

public class Order {
    private final String id;
    private double cost;
    private final Location location;

    public Order(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    /*
     * We have three options to delegate to a TaxCalculator strategy:
     * 1. direct instantiation (NOT a good option here)
     *      if (getLocation() == Location.USA)
     *          new USATax(); ...
     *
     * ("pull")
     * 2. call out to a factory, e.g., TaxCalculatorFactory, and "ask" for it (passing indicator)
     *      This is the option we'll implement.
     *
     * 3. let some other party "inject" it into me, and I'll store it in another field
     *      private TaxCalculator calc;
     */
    public double getTax() { // USES
        TaxCalculator calc = TaxCalculatorFactory.getTaxCalculator(getLocation());
        return calc.taxAmount(getCost());
    }

    /**
     * DONE:
     * get the items from the cart and iterate over them, print each item's product code
     * get cart total and print
     */
    public void processCart(ShoppingCart<? extends Product> cart) {
        System.out.println("Order: " + getId() + " contains the following:");

        Collection<? extends Product> cartItems = cart.allItems();
        for (Product product : cartItems) {
            System.out.println(product.getCode());
        }
        this.cost = cart.total();
        System.out.println("Order Total: " + cart.total());
    }

    public String getId() {
        return id;
    }

    public double getCost() {
        return cost;
    }

    public Location getLocation() {
        return location;
    }
}