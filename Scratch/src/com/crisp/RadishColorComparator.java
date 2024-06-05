package com.crisp;

import java.util.Comparator;

class RadishColorComparator implements Comparator<Radish> {

  @Override
  public int compare(Radish radish1, Radish radish2) {
    return radish1.getColor().compareTo(radish2.getColor());
  }
}
