/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.billing;

/**
 * European orders are taxed as follows:
 * VAT is 17% of taxable amount.
 * Luxury tax is an additional 25% on amount over $100.
 * 
 * implement this algorithm.
 */
public class EuropeTax implements TaxCalculator {

  static private final double VAT_RATE = 0.17;
  static private final double LUXURY_FREE_AMOUNT = 100.0;
  static private final double LUXURY_RATE = 0.25;

  public EuropeTax() {

  }

  @Override
  public double taxAmount(double taxable) {
    double result = 0.0;
    double vat = taxable * VAT_RATE;

    if (taxable > LUXURY_FREE_AMOUNT) {
      result = vat + ((taxable - LUXURY_FREE_AMOUNT) * LUXURY_RATE);
    }
    else {
      result = vat;
    }
    return result;
  }
}