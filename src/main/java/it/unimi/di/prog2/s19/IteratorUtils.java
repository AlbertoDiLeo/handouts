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

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

/** Utility methods for iterators. */
public class IteratorUtils {
  /** . */
  private IteratorUtils() {}

  /**
   * Concatenates two iterators.
   *
   * @param <T> the type of the elements in the iterators.
   * @param a the first iterator.
   * @param b the second iterator.
   * @return an iterator that first returns all elements of {@code a} and then all elements of
   *     {@code b}.
   * @throws NullPointerException if {@code a} or {@code b} is {@code null}.
   */
  public static <T> Iterator<T> concatenate(Iterator<? extends T> a, Iterator<? extends T> b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    return new Iterator<T>() {
      @Override
      public boolean hasNext() {
        return a.hasNext() || b.hasNext();
      }

      @Override
      public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        return a.hasNext() ? a.next() : b.next();
      }
    };
  }

  /**
   * An empty iterator.
   *
   * @param <T> the type of the elements in the iterator.
   * @return the iterator.
   */
  public static <T> Iterator<T> emptyIterator() {
    return new Iterator<>() {
      @Override
      public boolean hasNext() {
        return false;
      }

      @Override
      public T next() {
        throw new NoSuchElementException();
      }
    };
  }

  /**
   * Joins the elements of an iterator using a separator.
   *
   * @param <T> the type of the elements in the iterator.
   * @param iterator the iterator.
   * @param separator the separator.
   * @return the string obtained by concatenating the string representation of the elements of the
   *     iterator, separated by the separator.
   * @throws NullPointerException if {@code iterator} or {@code separator} is {@code null}.
   */
  public static <T> String join(Iterator<T> iterator, String separator) {
    Objects.requireNonNull(iterator);
    StringJoiner sj = new StringJoiner(separator);
    while (iterator.hasNext()) sj.add(iterator.next().toString());
    return sj.toString();
  }
}
