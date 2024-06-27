package com.jrostosk.stats;

import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.stats.AnalyzerConfigurationException;
import org.stats.RatingAnalyzer;

/*
 * https://www.calculator.net/mean-median-mode-range-calculator.html
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RatingAnalyzerImplTest {
    private RatingAnalyzer analyzer;

    private static final int[] ratings1 = { 5, 3, 5, 3, 4, 1, 3, 2, 4 };     // 9 values
    private static final int[] ratings2 = { 5, 3, 4, 3, 4, 3, 5, 2, 4, 1 };  // 10 values
    private static final int[] ratings3 = { 3, 3, 5, 3, 4, 5, 5, 5, 3, 5, 4, 5, 3, 4, 3, 4, 4, 4, 3, 3, 4 };  // 21 values
    private static final int[] ratings4 = { 4, 3, 4, 5, 5, 3, 4, 3, 3, 5, 3, 4, 4, 5, 3, 4, 4, 4, 3, 3 };     // 20 values

    private static final int[] singleValue = { 10 };
    private static final int[] singleOccurrenceEachModeValue = { 44, 55, 8 };

    /*
     * Per spec, the constructor MUST throw IllegalArgumentException upon null or
     * empty input ratings.  It's the RatingAnalyzer.newInstance() method that 
     * wraps the exception thrown from the constructor into an AnalyzerConfigurationException, 
     * and throws that back to the client that called newInstance().
     * It's the egg inside the egg!
     */
    @Test
    public void constructor_shouldThrowIllegalArgumentException_nullRatings() {
        try {
            int[] ratingsNull = null;
            RatingAnalyzer.newInstance(ratingsNull);
            fail("Constructor should have thrown IllegalArgumentException");
        }
        catch (AnalyzerConfigurationException e) {
            assertEquals(IllegalArgumentException.class, e.getCause().getClass());
        }
    }

    @Test
    public void constructor_shouldThrowIllegalArgumentException_emptyRatings() {
        try {
            int[] ratingsEmpty = { };
            RatingAnalyzer.newInstance(ratingsEmpty);
            fail("Constructor should have thrown IllegalArgumentException");
        }
        catch (AnalyzerConfigurationException e) {
            assertEquals(IllegalArgumentException.class, e.getCause().getClass());
        }
    }

    @Test
    public void mean_oddNumberRatings() {
        analyzer = RatingAnalyzer.newInstance(ratings1);
        assertEquals(3.333, analyzer.mean(), .001);

        analyzer = RatingAnalyzer.newInstance(ratings3);
        assertEquals(3.905, analyzer.mean(), .001);
    }

    @Test
    public void mean_evenNumberRatings() {
        analyzer = RatingAnalyzer.newInstance(ratings2);
        assertEquals(3.4, analyzer.mean(), .001);

        analyzer = RatingAnalyzer.newInstance(ratings4);
        assertEquals(3.8, analyzer.mean(), .001);
    }

    @Test
    public void median_oddNumberRatings() {
        analyzer = RatingAnalyzer.newInstance(ratings1);
        assertEquals(3.0, analyzer.median(), .001);

        analyzer = RatingAnalyzer.newInstance(ratings3);
        assertEquals(4.0, analyzer.median(), .001);
    }

    @Test
    public void median_evenNumberRatings() {
        analyzer = RatingAnalyzer.newInstance(ratings2);
        assertEquals(3.5, analyzer.median(), .001);

        analyzer = RatingAnalyzer.newInstance(ratings4);
        assertEquals(4.0, analyzer.median(), .001);
    }

    @Test
    public void mode_singleMode() {
        analyzer = RatingAnalyzer.newInstance(ratings1);
        assertArrayEquals(new int[] {3}, analyzer.mode());

        analyzer = RatingAnalyzer.newInstance(ratings3);
        assertArrayEquals(new int[] {3}, analyzer.mode());
    }

    @Test
    public void mode_multiMode() {
        analyzer = RatingAnalyzer.newInstance(ratings2);
        assertArrayEquals(new int[] {3, 4}, analyzer.mode());

        analyzer = RatingAnalyzer.newInstance(ratings4);
        assertArrayEquals(new int[] {3, 4}, analyzer.mode());
    }

    @Test
    public void mode_multiMode_singleOccurrenceEach() {
        analyzer = RatingAnalyzer.newInstance(singleOccurrenceEachModeValue);
        assertArrayEquals(new int[] {8, 44, 55}, analyzer.mode());
    }

    @Test
    public void mean_singleValue() {
        analyzer = RatingAnalyzer.newInstance(singleValue);
        assertEquals(10.0, analyzer.mean(), .001);
    }

    @Test
    public void median_singleValue() {
        analyzer = RatingAnalyzer.newInstance(singleValue);
        assertEquals(10.0, analyzer.median(), .001);
    }

    @Test
    public void mode_singleMode_singleValue() {
        analyzer = RatingAnalyzer.newInstance(singleValue);
        assertArrayEquals(new int[] {10}, analyzer.mode());
    }
}