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

  // CAMPI head e tail UTILI PER STABILIRE SE UNA CODA è PIENA OPPURE NO (non li avevo messi)
  /** The index in {@link #elements} of the first queue element (or -1 if the queue is empty). */
  private int head;

  /** The index of the first free position in {@link #elements} (if the queue is not full). */
  private int tail;

  // Constructors
  /**
   * Creates a new bounded queue with the given capacity.
   *
   * @param capacity the capacity of the queue.
   * @throws IllegalArgumentException if {@code capacity} is negative.
   */
  public BoundedIntQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity must be non-negative");
    }
    elements = new int[capacity];
    head = -1;
    tail = 0;
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
    if (head == -1) head = 0; // fatto dal prof
    // se la coda è vuota, significa che questo è il primo elemento che viene aggiunto. Lo head quindi diventa 0 per indicare l'inizio dells coda
    elements[tail] = x;
    tail = (tail + 1) % elements.length;
  }
  /*
   * L'indice tail viene incrementato di 1 e poi viene calcolato il modulo con la lunghezza dell'array (elements.length). 
   * Questo assicura che tail "avvolga" all'inizio dell'array quando raggiunge la fine, mantenendo il comportamento circolare del buffer.
   * Supponiamo che la lunghezza dell'array elements sia 5 e che tail sia 4.
   * Dopo aver aggiunto un elemento alla posizione 4, tail diventa (4 + 1) % 5 = 0.
   * Questo significa che il prossimo elemento verrà aggiunto alla posizione 0, avvolgendo l'array.
   */



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
    final int r = elements[head];
    head = (head + 1) % elements.length; // fatto dal prof
    if (head == tail) {
      head = -1;
      tail = 0;
    }
    return r;
  }


  /**
    * Checks if the queue is full.
    *
    * @return {@code true} if the queue is full, {@code false} otherwise.
    */
    public boolean isFull() {
      return tail == head;
    }


  /**
   * Checks if the queue is empty.
   *
   * @return {@code true} if the queue is empty, {@code false} otherwise.
   */
  public boolean isEmpty() {
    return head == -1;
  }


  /**
   * Returns the number of elements in the queue.
   * Restituisce il numero di elementi nella coda e non la lunghezza dell'array.
   * IMPORTANTE (non avevo messo il metodo size)
   *
   * @return the number of elements.
   */
  public int size() {
    if (isEmpty()) return 0;
    if (isFull()) return elements.length;
    return (tail - head + elements.length) % elements.length;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof BoundedIntQueue other)) return false;
    if (size() != other.size()) return false;
    int i = head, j = other.head, n = 0; // fatta dal prof
    while (n < size()) {
      if (elements[i] != other.elements[j]) return false;
      i = (i + 1) % elements.length;
      j = (j + 1) % other.elements.length;
      n += 1;
    }
    return true;
  }

  @Override
  public int hashCode() { // fatta dal prof
    int result = 0;
    int i = head, n = 0;
    while (n < size()) {
      result = 31 * result + Integer.hashCode(elements[i]);
      // Il fattore 31 è comunemente usato nelle funzioni hash per distribuire meglio i valori hash.
      i = (i + 1) % elements.length;
      n += 1;
    }
    return result;
  }


  @Override
  public String toString() {
    if (isEmpty()) return "BoundedIntQueue: []";
    final StringBuilder sb = new StringBuilder("BoundedIntQueue: [");
    int i = head, n = 0;
    while (n < size() - 1) {
      sb.append(elements[i] + ", ");
      i = (i + 1) % elements.length;
      n += 1;
    }
    sb.append(elements[i] + "]");
    return sb.toString();
  }











}
