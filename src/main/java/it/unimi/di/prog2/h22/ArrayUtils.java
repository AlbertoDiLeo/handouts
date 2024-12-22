/*

Copyright 2024 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

package it.unimi.di.prog2.h22;

import java.util.List;
import java.util.Objects;

/** A class providing some utility methods for arrays. */
public class ArrayUtils {

  /** . */
  private ArrayUtils() {}

  /**
   * Counts the number of {@code null} elements in an array.
   *
   * @param <T> the type of the elements in the haystack.
   * @param vals the array.
   * @return the number of {@code null} elements in {@code vals}.
   * @throws NullPointerException if {@code vals} is {@code null}.
   */
  public static <T> int countNull(T[] vals) {
    int num = 0;
    for (T s : Objects.requireNonNull(vals)) if (s == null) num++;
    return num;
  }

  /**
   * Searches for a needle in a haystack.
   *
   * <p>Returns an index {@code i} such that {@code haystack[i] == needle}, or {@code -1} if the
   * needle does not appear in the haystack.
   *
   * @param <T> the type of the elements in the haystack.
   * @param haystack the haystack.
   * @param needle the needle.
   * @return an index {@code i} such that {@code haystack[i] == needle}, or {@code -1} if the needle
   *     does not appear in the haystack.
   * @throws NullPointerException if {@code haystack} or {@code needle} are {@code null}.
   */
  public static <T> int linearSearch(T[] haystack, T needle) {
    Objects.requireNonNull(needle);
    Objects.requireNonNull(haystack);
    for (int i = 0; i < haystack.length; i++) if (haystack[i].equals(needle)) return i;
    return -1;
  }

  /**
   * Counts the number of elements in an array that are smaller than a given element.
   *
   * @param <T> the type of the elements in the array.
   * @param array the array.
   * @param element the element to compare.
   * @return the number of elements in {@code array} that are smaller than {@code element}.
   * @throws NullPointerException if {@code array} is {@code null}.
   */
  public static <T extends Comparable<T>> int countSmaller(T[] array, T element) {
    Objects.requireNonNull(array);
    int num = 0;
    for (int i = 0; i < array.length; i++) if (array[i].compareTo(element) < 0) num++;
    return num;
  }

  /**
   * Pours the elements of a list into another list.
   *
   * @param <U> the type of the elements of the destination list.
   * @param <T> the type of the elements of the source list.
   * @param src the source list.
   * @param dst the destination list.
   * @throws NullPointerException if {@code src} or {@code dst} are {@code null}.
   */
  public static <U, T extends U> void pour(List<T> src, List<U> dst) {
    Objects.requireNonNull(src);
    Objects.requireNonNull(dst);
    for (T x : src) dst.add(x);
  }
}
