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

import java.util.List;
import java.util.Objects;

/** A class providing some utility methods for lists. */
public class ListUtils {
  /** . */
  private ListUtils() {}

  /**
   * Returns the maximum element in a list.
   *
   * <p>This implementation has an issue with subclasses.
   *
   * @param <T> the type of the elements in the list.
   * @param lst the list.
   * @return the maximum element in {@code lst}.
   * @throws NullPointerException if {@code lst} is {@code null}.
   */
  public static <T extends Comparable<T>> T almostRightMax(List<T> lst) {
    Objects.requireNonNull(lst);
    T max = null;
    for (T t : lst) if (max == null || t.compareTo(max) > 0) max = t;
    return max;
  }

  /**
   * Returns the maximum element in a list.
   *
   * @param <T> the type of the elements in the list.
   * @param lst the list.
   * @return the maximum element in {@code lst}.
   * @throws NullPointerException if {@code lst} is {@code null}.
   */
  public static <T extends Comparable<? super T>> T max(List<T> lst) {
    Objects.requireNonNull(lst);
    T max = null;
    for (T t : lst) if (max == null || t.compareTo(max) > 0) max = t;
    return max;
  }

  /** A box containing a positive integer. */
  public static class PositiveIntBox implements Comparable<PositiveIntBox> {

    /** The value in the box. */
    private final int value;

    /**
     * Creates a new box containing a positive integer.
     *
     * @param value the value.
     * @throws IllegalArgumentException if {@code value} is not positive.
     */
    public PositiveIntBox(final int value) {
      if (value <= 0) throw new IllegalArgumentException("value must be positive");
      this.value = value;
    }

    /**
     * Returns the value in the box.
     *
     * @return the value in the box.
     */
    public int value() {
      return value;
    }

    @Override
    public int compareTo(PositiveIntBox other) {
      return Integer.compare(value, other.value);
    }

    @Override
    public String toString() {
      return Integer.toString(value);
    }
  }

  /** A subtype of {@link ListUtils.PositiveIntBox} that can be created from negative ints. */
  public static class AbsoluteValueIntBox extends PositiveIntBox {
    /**
     * Creates a new box containing the absolute value of an integer.
     *
     * @param value the value.
     */
    public AbsoluteValueIntBox(final int value) {
      super(Math.abs(value));
    }
  }

  /**
   * A method to test the code in this class
   *
   * @param args unused.
   */
  public static void main(String[] args) {
    List<PositiveIntBox> pos =
        List.of(new PositiveIntBox(1), new PositiveIntBox(3), new PositiveIntBox(2));
    List<AbsoluteValueIntBox> abs =
        List.of(
            new AbsoluteValueIntBox(1), new AbsoluteValueIntBox(-3), new AbsoluteValueIntBox(2));
    System.out.println(almostRightMax(pos));
    System.out.println(max(abs));
  }
}
