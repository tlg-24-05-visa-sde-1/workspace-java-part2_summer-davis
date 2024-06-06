package com.javatunes.personnel;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class HourlyEmployeeTest {

  // fixture
  HourlyEmployee emp;

  @Before
  public void setUp() {
    emp = new HourlyEmployee("Amilia", Date.valueOf("2010-10-10"), 25.0, 35.0);
  }

  @Test
  public void testPay() {
    assertEquals(875.0, emp.pay(), 0.001);  // rate * hours
  }

  @Test
  public void testPayTaxes() {
    assertEquals(218.75, emp.payTaxes(), .001);  // 0.25 * rate * hours
  }
}
