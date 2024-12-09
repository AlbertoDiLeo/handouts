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

package it.unimi.di.prog2.h18.refactored;

import java.util.Arrays;
import java.util.Iterator;

/**
 * A set of integer that can be preallocated to a given capacity.
 *
 * <p>An instance of this class is stored in a preallocated container of given initial
 * <em>capacity</em>. Once the size reaches the capacity, the contained is doubled in size.
 */
public class PreallocatedIntSet extends AbstractIntSet {

  /** The array of elements. */
  protected int[] elements;

  /*-
   * AF(size, elements) = { elements[i] | 0 <= i < size }
   *
   * RI:
   *
   *  - elements != null,
   *  - elements.length >= size,
   *  - for every 0 <= i != j < size, elements[i] < elements[j].
   */

  /** Creates an empty set. */
  public PreallocatedIntSet() {
    this(1);
  }

  /**
   * Creates an empty set with a given initial capacity.
   *
   * @param capacity the initial capacity of the set.
   * @throws IllegalArgumentException if capacity is negative.
   */
  public PreallocatedIntSet(final int capacity) {
    if (capacity < 0) throw new IllegalArgumentException("Capacity must be non-negative");
    elements = new int[capacity];
  }

  @Override
  public boolean isIn(int x) {
    return Arrays.binarySearch(elements, x) >= 0;
  }

  @Override
  public void insert(int x) {
    final int index = Arrays.binarySearch(elements, 0, size, x);
    if (index < 0) {
      if (size == elements.length) elements = Arrays.copyOf(elements, elements.length * 2);
      System.arraycopy(elements, -index - 1, elements, -index, size + index + 1);
      elements[-index - 1] = x;
      size++;
    }
  }

  @Override
  public void remove(int x) {
    final int index = Arrays.binarySearch(elements, 0, size, x);
    if (index >= 0) {
      System.arraycopy(elements, index + 1, elements, index, size - index - 1);
      size--;
    }
  }

  @Override
  public Iterator<Integer> iterator() {
    return new Iterator<Integer>() {
      private int i = 0;

      @Override
      public boolean hasNext() {
        return i < size;
      }

      @Override
      public Integer next() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
        return elements[i++];
      }
    };
  }
}
