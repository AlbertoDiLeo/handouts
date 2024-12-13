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

package it.unimi.di.prog2.h18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/** A concrete set of integer. */
public class IntSet extends AbstractIntSet {

  /** The set elements. */
  private final List<Integer> elements;

  /*-
   * AF(elements, size) = { elements.get(0), elements.get(1), ..., elements.get(size - 1) }
   * RI:
   *   - super.RI
   *   - elements != null and does not contain nulls.
   */

  /** Creates an empty set. */
  public IntSet() {
    this.elements = new ArrayList<>();
  }

  @Override
  public Iterator<Integer> iterator() {
    return Collections.unmodifiableCollection(elements).iterator();
  }
  // Utilizza un iteratore standard su una collezione non modificabile. no utilizza un iteratore personalizzato

  /*
   * Collections.unmodifiableCollection(elements) restituisce una vista non modificabile della collezione elements.
   * Questo significa che l'iteratore restituito non permette di modificare la collezione sottostante durante l'iterazione.
   * È una buona pratica restituire una collezione non modificabile per evitare modifiche accidentali o non autorizzate agli elementi dell'insieme durante l'iterazione.
   */

  @Override
  public void insert(int x) {
    // the use of isIn(x) instead of !elements.contains(x)
    // can take advantage of improved implementations in subclasses
    if (!isIn(x)) {
      elements.add(x);
      size++;
    }
  }

  @Override
  public void remove(int x) { // rimozione diretta
    if (elements.remove(Integer.valueOf(x))) size--;
  }
  // perchè non facciamo come in h14 con rimozione con sostituizione?
  /*
   * int i = getIndex(x);
    if (i < 0) return;
    int last = els.size() - 1;
    els.set(i, els.get(last));
    els.remove(last);
   */
}
