package com.entertainment.client;

import com.entertainment.Television;

import java.util.HashSet;
import java.util.Set;

class TelevisionClient {

  public static void main(String[] args) {

    // examine == and the equals() method
    Television tvA = new Television("Sony", 50);
    Television tvB = new Television("Sony", 50);

    System.out.println(tvA.hashCode());
    System.out.println(tvB.hashCode());

    System.out.println("tvA == tvB: " + (tvA == tvB));
    System.out.println("tvA.equals(tvB): " + tvA.equals(tvB));
    System.out.println();

    Set<Television> tvs = new HashSet<>();
    tvs.add(tvA);
    tvs.add(tvB);

    System.out.println("The size of the set is: " + tvs.size());
  }
}
