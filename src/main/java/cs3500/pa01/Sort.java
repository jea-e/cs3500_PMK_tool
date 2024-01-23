package cs3500.pa01;

import java.util.ArrayList;

/**
 * How to sort.
 */
public interface Sort {
  /**
   * Sort a list by a given tag.
   *
   * @param tag the tag to sort by
   * @param <T> the type of list
   * @return ArrayList the sorted list
   */
  <T> ArrayList<T> sortBy(String tag);
}
