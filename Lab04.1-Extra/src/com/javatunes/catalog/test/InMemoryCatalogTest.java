/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */

package com.javatunes.catalog.test;

import com.javatunes.catalog.InMemoryCatalog;
import com.javatunes.catalog.MusicCategory;
import com.javatunes.catalog.MusicItem;

import java.util.Collection;

class InMemoryCatalogTest {

  /*
   * One by one, complete each test method below, and then "activate" it by
   * uncommenting the call to that method in main().
   *
   * Once you see that the test method verifies the corresponding business method
   * works correctly, you can comment out that call in main() and proceed to the next one.
   */
  public static void main(String[] args) {
    // testFindById();
    // testFindByKeyword();
    // testFindByCategory();
    // testSize();
    // testGetAll();
    // testFindSelfTitled();
    // testFindCheapRock();
    // testGenreCount();
    // testAveragePrice();
    // testFindCheapestAlbumInGenre();
    // testFindAveragePriceInGenre();
  }

  private static void testFindAveragePriceInGenre() {
    InMemoryCatalog catalog = new InMemoryCatalog();

    System.out.println("Should be 16.23");
    System.out.println(catalog.findAveragePriceInGenre(MusicCategory.POP));
  }

  private static void testFindCheapestAlbum() {
    InMemoryCatalog catalog = new InMemoryCatalog();

    System.out.println("Should be 'Diva'");
    System.out.println(catalog.findCheapestAlbumInGenre(MusicCategory.POP));
  }

  private static void testAveragePrice() {
    InMemoryCatalog catalog = new InMemoryCatalog();

    System.out.println("Should be 15.31");
    System.out.printf("$%,.2f", catalog.averagePrice());
  }

  private static void testGenreCount() {
    InMemoryCatalog catalog = new InMemoryCatalog();

    System.out.println("Should return 4");
    System.out.println(catalog.genreCount(MusicCategory.POP));
  }

  private static void testFindCheapRock() {
    InMemoryCatalog catalog = new InMemoryCatalog();

    System.out.println("Should return 3 items");
    dump(catalog.findCheapRock(13.50));
  }

  private static void testFindSelfTitled() {
    InMemoryCatalog catalog = new InMemoryCatalog();

    System.out.println("Should return items 'Seal' & 'Ian Moore'");
    dump(catalog.findSelfTitled());
  }

  private static void testFindById() {
    InMemoryCatalog catalog = new InMemoryCatalog();

    System.out.println("Should be 'Seal'");
    MusicItem found = catalog.findById(6L); // primitive long autoboxed into Long object
    System.out.println(found); // toString() automatically called

    System.out.println();

    System.out.println("Should be 'null'");
    MusicItem notFound = catalog.findById(101L);
    System.out.println(notFound);
  }

  private static void testFindByKeyword() {
    InMemoryCatalog catalog = new InMemoryCatalog();


  }

  private static void testFindByCategory() {
    InMemoryCatalog catalog = new InMemoryCatalog();

    System.out.println("Should return 4 items, all POP");
    Collection<MusicItem> popItems = catalog.findByCategory(MusicCategory.POP);
    dump(popItems);

    System.out.println();

    System.out.println("Should return an empty collection");
    Collection<MusicItem> jazzItems = catalog.findByCategory(MusicCategory.JAZZ);
    dump(jazzItems);
  }

  private static void testSize() {
    InMemoryCatalog catalog = new InMemoryCatalog();

    System.out.println("Should return 18");
    System.out.println(catalog.size());
  }

  private static void testGetAll() {
    InMemoryCatalog catalog = new InMemoryCatalog();

    dump(catalog.getAll());
  }

  private static void dump(Collection<MusicItem> items) {
    for (MusicItem item : items) {
      System.out.println(item);
    }
  }
}
