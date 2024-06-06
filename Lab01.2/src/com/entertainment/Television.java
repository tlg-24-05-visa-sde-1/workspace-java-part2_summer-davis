package com.entertainment;

import java.util.Objects;

/*
 * NOTE: to be consistent with equals, you must use the same sort key(s)
 * as you're using in your equals() and hashCode() methods.
 * Natural order is defined by brand (String), and then by volume (int).
 * when tied on "brand".
 */
public class Television implements Comparable<Television> {

  // static fields and properties

  // properties
  private String brand;
  private int volume;

  // Television HAS-A Tuner (for all things related to channel changing)
  private final Tuner tuner = new Tuner();  // instantiated internally when a Television is created

  // constructors
  public Television() {
    super();
  }

  public Television(String brand, int volume) {
    super();
    setBrand(brand);
    setVolume(volume);
  }

  // business methods
  public int getCurrentChannel() {
    return tuner.getChannel();     // delegate to contained Tuner object
  }

  public void changeChannel(int channel) {
    tuner.setChannel(channel);     // delegate to contained Tuner object
  }

  // accessor methods
  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }


  @Override
  public int hashCode() {
    // this is a poor hash function, because it easily yields "hash collisions"
    // a "hash collision" is when "different" objects happen to have the same hashcode by dumb luck.
    // return getBrand().length() + getVolume();

    // this is a "scientifically correct" hash function, i.e.,
    // it minimizes the probability of hash collisions
    return Objects.hash(getBrand(), getVolume());
  }

  @Override
  public boolean equals(Object obj) {
    boolean result = false;

    // check that 'obj' is really referring to a Television object
    // if (obj instanceof Television) { // IS-A check
    if (this.getClass() == obj.getClass()) { // are we the EXACT SAME TYPE?

      // downcast 'obj' to more specific reference type Television, for getName(), getAge()
      Television other = (Television) obj;

      // do the checks: business equality is defined as brand, volume
      result = Objects.equals(this.getBrand(), other.getBrand()) &&  // null-safe check
               this.getVolume() == other.getVolume();                // primitives can't be null
    }
    return result;
  }

  // toCompare()


  @Override
  public int compareTo(Television other) {
    int result = this.getBrand().compareTo(other.getBrand());

    if (result == 0) { // tied on brand, so break the tie based on volume
      result = Integer.compare(this.getVolume(), other.getVolume());
    }
    return result;
  }

  // toString()
  @Override
  public String toString() {
    return String.format("%s: brand=%s, volume=%s, currentChannel=%s",
      getClass().getSimpleName(), getBrand(), getVolume(), getCurrentChannel());
  }
}
