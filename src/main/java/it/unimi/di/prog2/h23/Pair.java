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

package it.unimi.di.prog2.h23;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * A class representing an immutable pair of objects.
 *
 * @param <T> the type of the first object.
 * @param <U> the type of the second object.
 */
public class Pair<T, U> {

  /** The first object of the pair. */
  private final T first;

  /** The second object of the pair. */
  private final U second;

  /**
   * Creates a new pair.
   *
   * @param first the first object.
   * @param second the second object.
   * @throws NullPointerException if {@code first} or {@code second} is {@code null}.
   */
  public Pair(final T first, final U second) {
    this.first = Objects.requireNonNull(first);
    this.second = Objects.requireNonNull(second);
  }

  /**
   * Returns the first object of the pair.
   *
   * @return the first object of the pair.
   */
  public T first() {
    return first;
  }

  /**
   * Returns the second object of the pair.
   *
   * @return the second object of the pair.
   */
  public U second() {
    return second;
  }

  @Override
  public String toString() {
    return "(" + first + ", " + second + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Pair<?, ?> other)) return false;
    return first.equals(other.first) && second.equals(other.second);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }

  /**
   * Returns a comparator that compares pairs by their first element.
   *
   * @param <T> the type of the first element.
   * @return a comparator that compares pairs by their first element.
   */
  public static <T extends Comparable<? super T>> Comparator<Pair<T, ?>> firstComparator() {
    return new Comparator<Pair<T, ?>>() {
      @Override
      public int compare(Pair<T, ?> p1, Pair<T, ?> p2) {
        return p1.first.compareTo(p2.first);
      }
    };
  }

  /**
   * Returns a comparator that compares pairs by their second element.
   *
   * @param <T> the type of the second element.
   * @return a comparator that compares pairs by their second element.
   */
  public static <T extends Comparable<? super T>> Comparator<Pair<?, T>> secondComparator() {
    return new Comparator<Pair<?, T>>() {
      @Override
      public int compare(Pair<?, T> p1, Pair<?, T> p2) {
        return p1.second.compareTo(p2.second);
      }
    };
  }

  /**
   * Swaps the elements of a pair.
   *
   * @param pair the pair to swap.
   * @param <X> the type of the first element.
   * @param <Y> the type of the second element.
   * @return a new pair with the elements of {@code pair} swapped.
   * @throws NullPointerException if {@code pair} is {@code null}.
   */
  public static <X, Y> Pair<Y, X> swap(Pair<X, Y> pair) {
    Objects.requireNonNull(pair);
    return new Pair<>(pair.second, pair.first);
  }

  /**
   * Checks if two pairs are equal.
   *
   * @param p1 the first pair.
   * @param p2 the second pair.
   * @return {@code true} if {@code p1} and {@code p2} are equal, {@code false} otherwise.
   * @throws NullPointerException if {@code p1} or {@code p2} is {@code null}.
   */
  public static boolean areEqual(Pair<?, ?> p1, Pair<?, ?> p2) {
    return Objects.requireNonNull(p1).first.equals(Objects.requireNonNull(p2).first)
        && p1.second.equals(p2.second);
  }

  /**
   * A method to test the class.
   *
   * @param args unused.
   */
  public static void main(String[] args) {
    List<Pair<Integer, String>> lst = Arrays.asList(new Pair<>(1, "uno"), new Pair<>(2, "due"));
    lst.sort(Pair.firstComparator());
    System.out.println(lst);
    lst.sort(Pair.secondComparator());
    System.out.println(lst);
  }
}
