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

package it.unimi.di.prog2.h09;

import it.unimi.di.prog2.h08.impl.EmptyException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code IntSet}s are mutable, unbounded sets of integers. illimitato
 *
 * <p>A typical IntSet is \( S = \{x_1, \ldots, x_n \} \).
 */
public class IntSet {

  // Fields

  /** The {@link List} containing this set elements. */
  private final List<Integer> els;
  // Rendere il campo els privato garantisce che l'accesso diretto alla lista sia limitato alla classe IntSet
  // Dichiarare il campo els come final garantisce che il riferimento alla lista non possa essere cambiato dopo 
  // l'inizializzazione. Anche se la lista stessa è mutabile, il riferimento rimane costante, il che aiuta a prevenire errori accidentali dove il riferimento potrebbe essere riassegnato.
  // Anche se la lista è mutabile, mantenendo il campo privato, puoi fornire metodi pubblici che controllano come la lista viene modificata. Questo ti permette di aggiungere logica aggiuntiva, 
  // come evitare duplicati o mantenere l'ordine degli elementi.

  // Constructors

  /**
   * Initializes this set to be empty.
   *
   * <p>Builds the set \( S = \varnothing \).
   */
  public IntSet() {
    els = new ArrayList<>();
  }
  // Il costruttore è il luogo dove vengono inizializzati i campi di un oggetto. In questo caso, il costruttore IntSet assicura che els sia inizializzato come una nuova lista vuota ogni volta che viene creato un nuovo oggetto IntSet.

  // Methods

  /**
   * Looks for a given element in this set.
   *
   * @param x the element to look for.
   * @return the index where {@code x} appears in {@code els} if the element belongs to this set, or
   *     -1
   */
  private int getIndex(int x) {
    return els.indexOf(x);
  }

  /**
   * Adds the given element to this set.
   *
   * <p>This method modifies the object, that is: \( S' = S \cup \{ x \} \).
   *
   * @param x the element to be added.
   */
  public void insert(int x) {
    if (getIndex(x) < 0) els.add(x);
  }
  // modifica la lista
  // Questo controllo è necessario per garantire che l'insieme non contenga duplicati

  /**
   * Removes the given element from this set.
   *
   * <p>This method modifies the object, that is: \( S' = S \setminus \{ x \} \).
   *
   * @param x the element to be removed.
   */
  public void remove(int x) {
    int i = getIndex(x);
    if (i < 0) return;
    int last = els.size() - 1;
    els.set(i, els.get(last)); // sposto l'elemento da rimuovere alla fine. Questo è un modo efficiente per rimuovere un elemento senza dover spostare tutti gli elementi successivi di una posizione.
    els.remove(last);
  }
  // modifica la lista

  /**
   * Tells if the given element is in this set.
   *
   * <p>Answers the question \( x\in S \).
   *
   * @param x the element to look for.
   * @return whether the given element belongs to this set, or not.
   */
  public boolean isIn(int x) {
    return getIndex(x) != -1;
  }

  /**
   * Returns the cardinality of this set.
   *
   * <p>Responds with \( |S| \).
   *
   * @return the size of this set.
   */
  public int size() {
    return els.size();
  }

  /**
   * Returns an element from this set.
   *
   * @return an arbitrary element from this set. (l'elemento scelto è l'ultimo elemento della lista)
   * @throws EmptyException if this set is empty.
   */
  public int choose() throws EmptyException {
    if (els.isEmpty()) throw new EmptyException("Can't choose from an empty set");
    return els.get(els.size() - 1);
  }
}
