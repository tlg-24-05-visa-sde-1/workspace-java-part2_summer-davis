package com.javatunes.personnel;

import org.junit.Before;
import org.junit.Test;
import java.sql.Date;
import static org.junit.Assert.*;

public class EmployeeTest {

  // fixture
  private Employee emp1;
  private Employee emp2;

  @Before
  public void setUp() {
    emp1 = new SalariedEmployee("John", Date.valueOf("2000-01-01"));
    emp2 = new SalariedEmployee("John", Date.valueOf("2000-01-01"));
  }

  @Test
  public void equals_shouldReturnFalse_differentName_sameHireDate() {
    emp2.setName("Mary");
    assertNotEquals(emp1, emp2);
    assertFalse(emp1.equals(emp2));
  }

  @Test
  public void equals_shouldReturnFalse_sameName_differentHireDate() {
    emp2.setHireDate(Date.valueOf("2000-02-02"));
    assertNotEquals(emp1, emp2);   // this does a !.equals() check
    assertFalse(emp1.equals(emp2));
  }

  @Test
  public void equals_shouldReturnTrue_allPropertiesSame() {
    assertEquals(emp1, emp2);      // this does a .equals() check
    assertTrue(emp1.equals(emp2)); // alternative assertion
  }
}
