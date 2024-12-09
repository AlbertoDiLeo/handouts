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

package it.unimi.di.prog2.s19;

import static it.unimi.di.prog2.s19.IteratorUtils.*;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/** Ordered list of integers without duplicates. */
public class OrderedIntList {

  /** Indicates if the list is empty. */
  private boolean isEmpty;

  /** The value stored in this node of the list. */
  private int value;

  /** Two sublists, containing values respectively smaller and greater than {@link #value}. */
  private OrderedIntList left, right;

  /*-
   * RI: if isEmpty is false:
   *      - left and right are not null
   *      - the elements of left are smaller than value
   *      - the elements of right are greater than value
   *     if isEmpty is true:
   *      - left and right are null
   *
   * AF: if isEmpty is false: AF(left) + [value] + AF(right),
   *     if isEmpty is true: []
   *
   * Note that AF/RI, by structural induction, allow us to prove that
   * if the list has size n >= 1, then its elements are ordered in strictly
   * increasing order:
   *
   * - if n = 1 it is trivially true.
   * - if n > 1, then left and right contain fewer than n elements and by the induction hypothesis
   * they are strictly ordered; by the AF the list is given by AF(left) + [v] + AF(right), by
   * induction AF(left) is strictly increasing, by RI its maximum value is less than value,
   * again by RI value is less than the elements in AF(right) which is strictly increasing by
   * induction, therefore AF is strictly increasing.
   *
   */

  /** Constructs an empty list. */
  public OrderedIntList() {
    this.isEmpty = true;
    left = right = null;
  }

  /**
   * Returns the number of elements in the list.
   *
   * @return the number of elements in the list, 0 if it is empty.
   */
  public int size() {
    return isEmpty ? 0 : 1 + left.size() + right.size();
  }

  /**
   * Determines if the list is empty.
   *
   * @return true if the list is empty, false otherwise.
   */
  public boolean isEmpty() {
    return isEmpty;
  }

  /**
   * Consente di determinare se un valore appartiene alla lista.
   *
   * @param value il valore da cercare.
   * @return se appartiene alla lista.
   */
  public boolean contains(int value) {
    if (isEmpty) return false;
    if (value == this.value) return true;
    return value < this.value ? left.contains(value) : right.contains(value);
  }

  /**
   * Adds an element to the list.
   *
   * @param value the element to add.
   * @throws IllegalArgumentException if the element is already present.
   */
  public void add(int value) {
    if (value == this.value) throw new IllegalArgumentException("Duplicate value");
    if (isEmpty) {
      this.value = value;
      isEmpty = false;
      left = new OrderedIntList();
      right = new OrderedIntList();
    } else if (value < this.value) left.add(value);
    else right.add(value);
  }

  /**
   * Returns the minimum value in the list.
   *
   * @return the minimum value in the list.
   * @throws NoSuchElementException if the list is empty.
   */
  public int min() {
    if (isEmpty) throw new NoSuchElementException();
    if (left.isEmpty) return value;
    return left.min();
  }

  /**
   * Returns the maximum value in the list.
   *
   * @return the maximum value in the list.
   * @throws NoSuchElementException if the list is empty.
   */
  public int max() {
    if (isEmpty) throw new NoSuchElementException();
    if (right.isEmpty) return value;
    return right.max();
  }

  /**
   * Removes an element from the list.
   *
   * @param value the element to remove.
   * @return true if the element was removed, false otherwise.
   */
  public boolean remove(int value) {
    if (isEmpty) return false;
    if (value == this.value) {
      if (left.isEmpty && right.isEmpty) {
        isEmpty = true;
        left = right = null;
      } else if (left.isEmpty) {
        this.value = right.value;
        left = right.left;
        right = right.right;
      } else if (right.isEmpty) {
        this.value = left.value;
        right = left.right;
        left = left.left;
      } else {
        int min = right.min();
        this.value = min;
        right.remove(min);
      }
      return true;
    }
    return value < this.value ? left.remove(value) : right.remove(value);
  }

  /**
   * Iterator that lists the elements of the list in ascending order.
   *
   * @return the iterator.
   */
  public Iterator<Integer> smallToBig() {
    return isEmpty
        ? emptyIterator()
        : concatenate(
            concatenate(left.smallToBig(), List.of(value).iterator()), right.smallToBig());
  }

  /**
   * Iterator that lists the elements of the list in descending order.
   *
   * @return the iterator.
   */
  public Iterator<Integer> bigToSmall() {
    return isEmpty
        ? emptyIterator()
        : concatenate(
            concatenate(right.bigToSmall(), List.of(value).iterator()), left.bigToSmall());
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof OrderedIntList other)) return false;
    if (isEmpty != other.isEmpty) return false;
    if (isEmpty) return true;
    final Iterator<Integer> thisIterator = smallToBig(), otherIterator = other.smallToBig();
    while (thisIterator.hasNext() && otherIterator.hasNext())
      if (!thisIterator.next().equals(otherIterator.next())) return false;
    return !(thisIterator.hasNext() || otherIterator.hasNext());
  }

  @Override
  public int hashCode() {
    int result = 0;
    final Iterator<Integer> it = smallToBig();
    while (it.hasNext()) result = 31 * result + it.next().hashCode();
    return result;
  }
}
