package com.duckrace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

class DuckRacer implements Serializable {

  // static properties/fields

  // properties
  private final int id;
  private String name;
  private final Collection<Reward> rewards = new ArrayList<>();

  // constructors
  public DuckRacer(int id, String name) {
    this.id = id;
    setName(name);
  }

  // business methods
  public void win(Reward reward) {
    rewards.add(reward);
  }

  // accessor methods
  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getWins() {
    return rewards.size();
  }

  // NOTE: return a read-only view of the 'rewards' collection (client can't modify it!!)
  public Collection<Reward> getRewards() {
    return Collections.unmodifiableCollection(rewards);
  }

  // toString()
  @Override
  public String toString() {
    return String.format(
        "%s: id=%s, name=%s, wins=%s, rewards=%s",
        getClass().getSimpleName(), getId(), getName(), getWins(), getRewards());
  }
}
