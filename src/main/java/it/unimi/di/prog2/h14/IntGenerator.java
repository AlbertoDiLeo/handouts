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

package it.unimi.di.prog2.h14;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

// IntGenerator Implementa l'interfaccia Iterator<Integer>.
// Fornisce i metodi hasNext() e next() per iterare su una lista di interi.

/** Generator (in Liskov parlance) of the ints contained in a {@link List}. */ 
public class IntGenerator implements Iterator<Integer> {
  // Nella terminologia di Liskov, un generatore è un oggetto che produce una sequenza di valori, uno alla volta, su richiesta. 
  // Questo concetto è strettamente legato agli iteratori, che sono utilizzati per attraversare collezioni di dati.

  /** The list elements. */
  private final List<Integer> els;

  /** The position of the next element to return (if {@code idx < els.size()}. */
  private int idx;

  /**
   * Builds an iterator (partial constructor, used just in {@link IntSet}). parziale perchè solo in IntSet
   *
   * @param els the list of elements, must not be, or contain, {@code null}.
   */
  public IntGenerator(List<Integer> els) {
    this.els = els;
    this.idx = 0;
  }

  @Override
  public boolean hasNext() {
    return idx < els.size();
  }

  @Override
  public Integer next() {
    if (!hasNext()) throw new NoSuchElementException();
    return els.get(idx++);
  }
}
