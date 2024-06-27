package com.summer.stats;

import org.stats.RatingAnalyzer;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.Collection;
import java.util.Set;
import java.util.Arrays;

/**
 * JR: Very good result overall.
 *  - Constructor does not throw correct exception upon null or empty input ratings -
 *    it blows up with NullPointerException.
 *  - median() flawed for odd-numbered dataset.
 *
 * Also check for comments below and in test class.
 */
public class SummerRatingAnalyzer implements RatingAnalyzer {

  // properties
  // JR: only use reference type LinkedList if employing the additional methods
  // it provides beyond what's specified in the List interface.
  private final LinkedList<Integer> dataset = new LinkedList<>();

  // constructors

  /*
   * JR: per spec:
   *  The constructor must throw IllegalArgumentException if the array is null or empty.
   *
   * if (data == null || data.length == 0)
   *   throw new IllegalArgumentException("Input array cannot be null or empty");
   */
  public SummerRatingAnalyzer(int[] data) throws IllegalArgumentException {
    // JR: see comments above this method
    setDataSet(data);
  }

  // business methods
  // mean
  public double mean() {
    double result = 0.0;

    for (Integer number : dataset) {
      result += number;
    }
    return result / dataset.size();
  }

  // median
  public double median() {
    double result = 0.0;

    if (dataset.size() % 2 != 0) {  // if dataset contains an odd number of values,
      int median = dataset.get(dataset.size()/2); // divide size by 2 and assign the element at that index to result
      result = (double) median;
    }
    else { // if dataset contains an even number of values,
      double middle1 = (double) (dataset.get((dataset.size()/2)-1)); // identify the middle 2 values,
      double middle2 = (double) (dataset.get(dataset.size()/2));
      double median = (middle1 + middle2) / 2;                       // divide by 2 to find their average
      result = median;
    }
    return result;
  }

  // mode
  public int[] mode() {
    // create a treemap of the dataset

    // JR: reference as Map, not TreeMap, and TreeMap not needed, HashMap would be fine
    TreeMap<Integer, Integer> map = convertLinkedListToTreeMap(dataset);

    // find the largest value(s) in the treemap and add their keys to a treeset so that the results
    // contain no duplicates and are sorted in natural order (ascending)

    // JR: the logic is sound for using TreeSet, but reference as Set on the left.
    TreeSet<Integer> resultKeys = getResultKeys(map);

    //  convert resultKeys to int[], the expected return type
    int[] result = convertTreeSetToIntArray(resultKeys);

    return result;
  }

  // extracted method to support mode(): create a treeset of the result keys
  // (all keys from the treemap with a value matching the largest number of occurrences)

  // JR: sure, use a TreeSet, I understand why you did, but reference as Set, not TreeSet,
  // and return type should be Set, not TreeSet.
  //  First Principle: "Code to the interface, not the implementation"
  private static TreeSet<Integer> getResultKeys(TreeMap<Integer, Integer> map) {
    Set<Map.Entry<Integer, Integer>> entries = map.entrySet();

    int largestValue = 0;
    TreeSet<Integer> resultKeys = new TreeSet<>();

    // for each entry, check if its value is equal to or greater than largestValue
    for (Map.Entry<Integer, Integer> entry : entries) {
      if (entry.getValue() >= largestValue) { // if it is, assign that value to largestValue
        largestValue = entry.getValue();
      }
    }

    // iterate over treemap again and put all keys with the largestValue in resultKeys
    for (Map.Entry<Integer, Integer> entry: entries) {
      if (entry.getValue() == largestValue) {
        resultKeys.add(entry.getKey());
      }
    }
    return resultKeys;
  }

  // extracted method to support mode(): convert treeset to int[]
  private static int[] convertTreeSetToIntArray(TreeSet<Integer> resultKeys) {
    int[] result = new int[resultKeys.size()];

    // JR: for (int i = 0; i < result.length; i++)

    // No need for you to define and increment your own 'index' variable,
    // the "classic" / old style for loop does that via the 'i' variable.

    int index = 0;
    for (Integer key : resultKeys) {
      result[index] = key;
      index++;
    }

    return result;
  }

  // extracted method to support mode(): convert dataset to treemap
  private static TreeMap<Integer, Integer> convertLinkedListToTreeMap(LinkedList<Integer> dataset) {
    TreeMap<Integer, Integer> map = new TreeMap<>();

    for (Integer number : dataset) {
      // if the number is not already a map key, add it to map and set value to 1
      if (!(map.containsKey(number))) {
        map.put(number, 1);
      }
      else { // otherwise, increment that key's value
        map.put(number, (map.get(number) + 1));
      }
    }
    return map;
  }

  // accessor methods
  public Collection<Integer> getDataSet() {
    return dataset;
  }

  // JR: this blows up with NullPointerException if 'data' is null
  private void setDataSet(int[] data) {
    for (int value : data) {
      dataset.add(value);
    }
  }

  // toString()
  @Override
  public String toString() {
    return String.format("%s: dataset=%s, mean=%s, median=%s, mode(s)=%s",
      getClass().getSimpleName(), getDataSet(), mean(), median(), Arrays.toString(mode()));
  }
}
