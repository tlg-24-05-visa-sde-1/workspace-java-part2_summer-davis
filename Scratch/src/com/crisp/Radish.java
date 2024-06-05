package com.crisp;

/*
 * Natural order is defined by 'size' (double).
 * 'size' is called the "sort key".
 */
class Radish implements Comparable<Radish> {
  // static properties and methods

  // properties
  private String color;
  private double size;
  private double tailLength;
  private int guysOnTop;

  // constructors
  public Radish(String color, double size, double tailLength, int guysOnTop) {
    setColor(color);
    setSize(size);
    setTailLength(tailLength);
    setGuysOnTop(guysOnTop);
  }

  // business methods

  // accessor methods
  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public double getSize() {
    return size;
  }

  public void setSize(double size) {
    this.size = size;
  }

  public double getTailLength() {
    return tailLength;
  }

  public void setTailLength(double tailLength) {
    this.tailLength = tailLength;
  }

  public int getGuysOnTop() {
    return guysOnTop;
  }

  public void setGuysOnTop(int guysOnTop) {
    this.guysOnTop = guysOnTop;
  }

  // equals() and hashCode()

  // compareTo()
  // 'size' (double) is our sort key
  @Override // interface Comparable
  public int compareTo(Radish other) {
    System.out.println("compareTo() called");
    return Double.compare(this.getSize(), other.getSize());
  }

  // toString()
  @Override
  public String toString() {
    return String.format("%s: color=%s, size=%s, tailLength=%s, guysOnTop=%s",
      getClass().getSimpleName(), getColor(), getSize(), getTailLength(), getGuysOnTop());
  }
}
