package com.crisp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class RadishSortTest {

  public static void main(String[] args){
    List<Radish> radishes = new ArrayList<>();

    // color, size, tailLength, guysOnTop
    radishes.add(new Radish("red", 1.5, 2.5, 5));
    radishes.add(new Radish("black", 1.0, 0.0, 0));
    radishes.add(new Radish("red", 2.7, 5.5, 0));
    radishes.add(new Radish("pink", 0.9, 0.0, 6));


    System.out.println("original list");
    dump(radishes);

    System.out.println("sort by natural order (size)");
    radishes.sort(null); // passing null means natural order
    dump(radishes);

    System.out.println("sort by color via RadishColorComparator");
    //radishes.sort(new RadishColorComparator());
    radishes.sort(new Comparator<Radish>() {
      @Override
      public int compare(Radish radish1, Radish radish2) {
        return radish1.getColor().compareTo(radish2.getColor());
      }
    });
    dump(radishes);

    System.out.println("sort by guysOnTop via RadishGuysOnTopComparator");
    //radishes.sort(new RadishGuysOnTopComparator());
    radishes.sort(new Comparator<Radish>() {
      @Override
      public int compare(Radish radish1, Radish radish2) {
        return Integer.compare(radish1.getGuysOnTop(), radish2.getGuysOnTop());
      }
    });
    dump(radishes);

    System.out.println("sort by tailLength via anonymous class");
    radishes.sort(new Comparator<Radish>() {
      @Override
      public int compare(Radish radish1, Radish radish2) {
        return Double.compare(radish1.getTailLength(), radish2.getTailLength());
      }
    });
    dump(radishes);
  }

  private static void dump(List<Radish> radishList) {
    for (Radish radish : radishList) {
      System.out.println(radish); // toString() automatically called
    }
    System.out.println();
  }
}
