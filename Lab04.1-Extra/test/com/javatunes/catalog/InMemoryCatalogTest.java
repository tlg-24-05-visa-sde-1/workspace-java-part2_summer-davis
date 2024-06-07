package com.javatunes.catalog;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class InMemoryCatalogTest {

  //fixture
  private InMemoryCatalog catalog;

  @Before
  public void setUp() {
    catalog = new InMemoryCatalog();
  }

  // findCheapRock()

  @Test
  public void findCheapRock_() {}

  // findSelfTitled()
  @Test
  public void findBySelfTitled_shouldReturnCollection() {
    Collection<MusicItem> selfTitledItems = catalog.findSelfTitled();

    assertEquals(2, selfTitledItems.size());

    for (MusicItem musicItem : selfTitledItems) {
      assertEquals(musicItem.getTitle(), musicItem.getArtist());
    }
  }

  // findByCategory()
  @Test
  public void findByCategory_shouldReturnPopulatedCollection_categoryNotFound() {
    Collection<MusicItem> jazzItems = catalog.findByCategory(MusicCategory.JAZZ);
    assertNotNull(jazzItems);
    assertTrue(jazzItems.isEmpty());
    assertEquals(0, jazzItems.size());
  }

  @Test
  public void findByCategory_shouldReturnPopulatedCollection_categoryFound() {
    Collection<MusicItem> popItems = catalog.findByCategory(MusicCategory.POP);
    assertEquals(4, popItems.size());

    for (MusicItem item : popItems) {
      assertEquals(MusicCategory.POP, item.getMusicCategory());
    }
  }

  // findById()
  @Test
  public void findById_shouldReturnNull_idNotFound() {
    // make the business call, null should come back
    MusicItem item = catalog.findById(101L);
    assertNull(item);
  }

  @Test
  public void findById_shouldReturnMusicItem_idFound() {
    // make the business call, a non-null MusicItem should come back
    MusicItem item = catalog.findById(6L);
    assertNotNull(item);
    assertEquals(6L, item.getId().longValue()); // long, long
  }
}
