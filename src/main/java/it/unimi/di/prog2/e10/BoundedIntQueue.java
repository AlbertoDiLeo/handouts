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

package it.unimi.di.prog2.e10;

import java.util.Arrays;

/**
 * A <em>queue</em> is a mutable data structure that provides access to its elements in
 * first-in/first-out order.
 *
 * <p>A <em>bounded</em> queue has an upper bound, established when a queue is created, on the
 * number of elements that can be stored in the queue.
 */

 /**
 * Una <em>coda</em> è una struttura dati mutabile che fornisce accesso ai suoi elementi in
 * ordine first-in/first-out (FIFO).
 *
 * <p>Una coda <em>limitata</em> ha un limite superiore, stabilito quando la coda viene creata,
 * sul numero di elementi che possono essere memorizzati nella coda.
 */

public class BoundedIntQueue {

  // EXERCISE: complete following the specification (with particular attention
  // to the eventual exceptions) and provide an implementation (including the
  // equals, hashCode, and toString methods); add methods that are adequate to
  // the specification.

  // ESERCIZIO: completare seguendo la specifica (con particolare attenzione alle
  // eventuali eccezioni) e fornire un'implementazione (inclusi i metodi equals,
  // hashCode e toString); aggiungere metodi che siano adeguati alla specifica.

  // Given the boundedness constraint, it is not allowed to use any Java
  // Collection Framework class. An array can be used to store the elements in a
  // circular buffer (see https://www.wikiwand.com/en/articles/Circular_buffer).

  // Dato il vincolo di limitatezza, non è consentito utilizzare alcuna classe del
  // Java Collection Framework. È possibile utilizzare un array per memorizzare gli
  // elementi in un buffer circolare (vedi https://www.wikiwand.com/en/articles/Circular_buffer).


  // Fields

  /** L'array che contiene gli elementi di questa coda. */
  private final int[] elements;

  // Constructors
  /**
   * Creates a new bounded queue with the given capacity.
   *
   * @param capacity the capacity of the queue.
   * @throws IllegalArgumentException if {@code capacity} is negative.
   */
  public BoundedIntQueue(int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("capacity must be non-negative");
    }
    elements = new int[capacity];
  }



  // Methods

  /**
   * Adds an element to the queue.
   *
   * @param x the element to add. (l'elemento da aggiungere alla fine della coda)
   * @throws IllegalStateException if the queue is full.
   */
  public void enqueue(int x) {
    if (isFull()) {
      throw new IllegalStateException("queue is full");
    }
    // Find the first empty slot
    int i = 0;
    while (elements[i] != 0) {
      i++;
    }
    elements[i] = x;
  }

  /**
   * Removes the element at the head of the queue. (rimuove l'elemento in testa alla coda, all'inizio della coda)
   *
   * @return the element at the head of the queue.
   * @throws IllegalStateException if the queue is empty.
   */
  public int dequeue() {
    if (isEmpty()) {
      throw new IllegalStateException("queue is empty");
    }
    int head = elements[0];
    // Shift all elements to the left
    for (int i = 0; i < elements.length - 1; i++) {
      elements[i] = elements[i + 1];
    }
    return head;
  }


  /**
    * Checks if the queue is full.
    *
    * @return {@code true} if the queue is full, {@code false} otherwise.
    */
  public boolean isFull() {
    // Assuming the queue is full when all elements are non-zero
    for (int element : elements) {
      if (element == 0) {
        return false;
      }
    }
      return true;
    }


  /**
   * Checks if the queue is empty.
   *
   * @return {@code true} if the queue is empty, {@code false} otherwise.
   */
  public boolean isEmpty() {
    if (elements.length == 0) return true;
    return false;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof BoundedIntQueue other)) return false;
    if (elements.length != other.elements.length) return false;
    for (int i = 0; i < elements.length; i++) {
      if (elements[i] != other.elements[i]) return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(elements);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("BoundedIntQueue: [");
    for (int i = 0; i < elements.length; i++) {
      if (elements[i] == 0) {
        break;
      }
      sb.append(elements[i]);
      if (elements[i + 1] != 0) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }











}
