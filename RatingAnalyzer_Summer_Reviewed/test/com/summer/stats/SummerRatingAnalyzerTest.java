package com.summer.stats;

import org.junit.Assert;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.stats.AnalyzerConfigurationException;
import org.stats.RatingAnalyzer;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SummerRatingAnalyzerTest {

  // mean
  @Test
  public void mean_shouldReturnAverageOfAllValues_allPositiveValues() {
    int[] ratings = {1, 2, 3, 4};
    RatingAnalyzer analyzer = RatingAnalyzer.newInstance(ratings);

    assertEquals(2.5, analyzer.mean(), .001);
  }

  @Test
  public void mean_shouldReturnAverageOfAllValues_allNegativeValues() {
    int[] ratings = {-1, -2, -3, -4};
    RatingAnalyzer analyzer = RatingAnalyzer.newInstance(ratings);

    assertEquals(-2.5, analyzer.mean(), .001);
  }

  @Test
  public void mean_shouldReturnZero_allValuesZero() {
    int[] ratings = {0, 0, 0, 0};
    RatingAnalyzer analyzer = RatingAnalyzer.newInstance(ratings);

    assertEquals(0, analyzer.mean(), .001);
  }

  // median
  @Test
  public void median_shouldReturnAverageOfTwoMiddleValues_evenDataSet() {
    int[] ratings = {1, 2, 3, 4};
    RatingAnalyzer analyzer = RatingAnalyzer.newInstance(ratings);

    assertEquals(2.5, analyzer.median(), .001);
  }

  @Test
  public void median_shouldReturnMiddleValue_oddDataSet() {
    int[] ratings = {1, 2, 3, 4, 5};
    RatingAnalyzer analyzer = RatingAnalyzer.newInstance(ratings);

    assertEquals(3, analyzer.median(), .001);
  }

  @Test
  public void median_shouldReturnOneValue_OneValueInDataSet() {
    int[] ratings = {1};
    RatingAnalyzer analyzer = RatingAnalyzer.newInstance(ratings);

    assertEquals(1, analyzer.median(), .001);
  }

  // mode
  @Test
  public void mode_shouldReturnValueWithMostOccurrences_OneValueWithMostOccurrences() {
    int[] ratings = {1, 2, 3, 4, 4};
    int[] result = {4};
    RatingAnalyzer analyzer = RatingAnalyzer.newInstance(ratings);

    // JR: perfectly fine to do it this way, or use assertArrayEquals()
    assertEquals(Arrays.toString(result), Arrays.toString(analyzer.mode()));
  }

  @Test
  public void mode_shouldReturnOneMode_OneValueInDataSet() {
    int[] ratings = {1};
    RatingAnalyzer analyzer = RatingAnalyzer.newInstance(ratings);

    assertEquals(Arrays.toString(ratings), Arrays.toString(analyzer.mode()));
  }

  @Test
  public void mode_shouldReturnAllModesInAscendingOrder_multipleModes() {
    int[] ratings = {1, 1, 5, 5, 3, 4, 2, 2};
    int[] result = {1, 2, 5};
    RatingAnalyzer analyzer = RatingAnalyzer.newInstance(ratings);

    System.out.println(analyzer);

    assertEquals(Arrays.toString(result), Arrays.toString(analyzer.mode()));
  }

  // toString()
  @Test
  public void toString_shouldReturnMatchingDataSetReport_validInput() {
    int[] ratings = {1, 2, 3, 4};
    RatingAnalyzer analyzer = RatingAnalyzer.newInstance(ratings);

    assertEquals("SummerRatingAnalyzer: dataset=[1, 2, 3, 4], mean=2.5, median=2.5, mode(s)=[1, 2, 3, 4]",
                  analyzer.toString());
  }

  // exceptions

  /*
   * JR: to be 100% sure of correct behavior here, you need to check two things:
   *  1. AnalyzerConfigurationException is thrown from RatingAnalyzer.newInstance().
   *  2. The nested exception inside is an instance of IllegalArgumentException.
   * See first two test methods (prefixed with "constructor_") in my test class,
   * com.jrostosk.stats.RatingAnalyzerImplTest.
   *
   * Your exception tests are passing just by dumb luck.
   * Inside this exception object (the egg) is another exception object (the inner egg),
   * and that inside egg is a NullPointerException, due to the lack of checking for
   * null or empty input array in the ctor.  setDataSet() is throwing it, which is
   * called by the ctor.
   *
   * I've enhanced your test method to show this.
   */
  @Test(expected=AnalyzerConfigurationException.class)
  public void summerRatingAnalyzer_shouldThrowAnalyzerConfigurationException_nullArray() {
    int[] ratings = null;
    try {
      RatingAnalyzer analyzer = RatingAnalyzer.newInstance(ratings);
      Assert.fail("Should have thrown AnalyzerConfigurationException");
    }
    catch (AnalyzerConfigurationException e) {
      // JR: proof that we have a NPE here
      assertEquals(NullPointerException.class, e.getCause().getClass());
      throw e;  // JR: because of the 'expected' attribute above, you and I spoke of this in class
    }
  }

  /*
   * JR: empty input array should also throw an exception.
   */
  @Test
  public void summerRatingAnalyzer_shouldThrowAnalyzerConfigurationException_emptyArray() {
    int[] ratings = {};
    RatingAnalyzer analyzer = RatingAnalyzer.newInstance(ratings);
  }
}
