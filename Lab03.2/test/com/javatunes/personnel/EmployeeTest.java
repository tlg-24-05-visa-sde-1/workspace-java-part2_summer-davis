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
    // create two instances of your named inner class
    //emp1 = new DummyEmployee("John", Date.valueOf("2000-01-01"));
    //emp2 = new DummyEmployee("John", Date.valueOf("2000-01-01"));

    // create instances of an anonymous class instead
    emp1 = getEmployee();
    emp2 = getEmployee();
    }

  private static Employee getEmployee() {
    return new Employee("John", Date.valueOf("2000-01-01")) {
      public double pay() { return 0; }
      public double payTaxes() { return 0; }
    };
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

  // NAMED MEMBER-LEVEL INNER CLASSES
  private class DummyEmployee extends Employee {

    public DummyEmployee() {}

    public DummyEmployee(String name, Date hireDate) {
      super(name, hireDate);
    }

    @Override
    public double pay() {
      return 0;
    }

    @Override
    public double payTaxes() {
      return 0;
    }
  }
}
