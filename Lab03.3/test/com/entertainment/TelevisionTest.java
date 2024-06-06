package com.entertainment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*
 * BVT:
 *  volume
 *   -1,0  and  100,101
 *  channel
 *   0,1  and  999,1000
 */
public class TelevisionTest {

  //fixture
  private Television tv;

  @Before
  public void setUp() {
    tv = new Television();
  }

  @Test
  public void hashCode_shouldReturnFalse_sameBrand_sameVolume_differentDisplay() {
    tv.setDisplay(DisplayType.OLED);
    Television otherTv = new Television();

    assertNotEquals(tv.hashCode(), otherTv.hashCode());
  }

  @Test
  public void hashCode_shouldReturnFalse_sameBrand_differentVolume() {
    tv.setBrand("Sony");
    Television other = new Television("Sony", 10);

    assertNotEquals(tv.hashCode(), other.hashCode());
  }

  @Test
  public void hashCode_shouldReturnFalse_differentBrand_sameVolume() {
    tv.setBrand("Samsung");
    Television other = new Television();

    assertNotEquals(tv.hashCode(), other.hashCode());
  }

  @Test
  public void hashCode_shouldReturnTrue_allPropertiesSame() {
    Television other = new Television();

    assertEquals(tv.hashCode(), other.hashCode());
  }

  @Test
  public void equals_shouldReturnFalse_sameBrand_sameVolume_differentDisplay() {
    tv.setDisplay(DisplayType.OLED);
    Television otherTv = new Television();

    assertNotEquals(tv, otherTv);
  }

  @Test
  public void equals_shouldReturnFalse_sameBrand_differentVolume_sameDisplay() {
    tv.setBrand("Sony");
    Television other = new Television("Sony", 10);

    assertNotEquals(tv, other);
  }

  @Test
  public void equals_shouldReturnFalse_differentBrand_sameVolume_sameDisplay() {
    tv.setBrand("Samsung");
    Television other = new Television();

    assertNotEquals(tv, other);
  }

  @Test
  public void equals_shouldReturnTrue_allPropertiesSame() {
    Television other = new Television();

    assertEquals(tv, other);
  }

  @Test
  public void compareTo_shouldReturnNegativeNumber_when1stBrandLessThan2ndBrand() {
    tv.setBrand("A_lessthan");
    Television other = new Television("B_greaterThan");

    assertTrue(tv.compareTo(other) < 0);
  }

  @Test
  public void compareTo_shouldReturnPositiveNumber_when1stBrandGreaterThan2ndBrand() {
    tv.setBrand("B_greaterThan");
    Television other = new Television("A_lessThan");

    assertTrue(tv.compareTo(other) > 0);
  }

  @Test
  public void compareTo_shouldReturnZero_sameBrands() {
    tv.setBrand("Sony");
    Television other = new Television("Sony");

    assertTrue(tv.compareTo(other) == 0);
  }

  @Test
  public void changeChannel_shouldStoreValue_validInput_upperBound()
  throws InvalidChannelException{
    tv.changeChannel(999);
    assertEquals(999, tv.getCurrentChannel());
  }

  @Test
  public void changeChannel_shouldStoreValue_validInput_lowerBound()
  throws InvalidChannelException{
    tv.changeChannel(1);
    assertEquals(1, tv.getCurrentChannel());
  }

  @Test(expected = InvalidChannelException.class)
  public void changeChannel_shouldThrowInvalidChannelException_invalidInput_lowerBound()
  throws InvalidChannelException{
    try {
      tv.changeChannel(0);
      fail("Should have thrown InvalidChannelException");
    } catch (InvalidChannelException e) {
      assertEquals("Invalid channel: 0. Allowed range: [1,999].", e.getMessage());
      throw e;
    }
  }

  @Test(expected=IllegalArgumentException.class)
  public void setVolume_shouldThrowIllegalArgumentException_invalidInput_lowerBound() {
    tv.setVolume(-1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void setVolume_shouldThrowIllegalArgumentException_invalidInput_upperBound() {
    tv.setVolume(101);
  }

  @Test
  public void setVolume_shouldStoreValue_validInput_lowerBound() {
    tv.setVolume(0);
    assertEquals(0, tv.getVolume());
  }

  @Test
  public void setVolume_shouldStoreValue_validInput_upperBound() {
    tv.setVolume(100);
    assertEquals(100, tv.getVolume());
  }
}
