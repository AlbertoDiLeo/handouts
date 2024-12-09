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

package it.unimi.di.prog2.e12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A map from {@link String} to {@link Integer}.
 *
 * <p>A <em>map</em> is a collection that associates keys to values. In this case, the keys are
 * strings and the values are integers. The map cannot contain duplicate keys, which means that each
 * key can be associated to at most one value.
 */

 /**
  * <p>Una <em>mappa</em> è una collezione che associa chiavi a valori. In questo caso, le chiavi sono
 * stringhe e i valori sono interi. La mappa non può contenere chiavi duplicate, il che significa che
 * ogni chiave può essere associata al massimo a un valore.
  */
public class StringToIntMap {

  // EXERCISE: provide a representation, together with its AF and RI
  // Note: do not use the Map in Java Collections, the point is to implement it from scratch!

  // ESERCIZIO: fornire una rappresentazione, insieme alla sua AF e RI
  // Nota: non utilizzare la classe Map delle Java Collections, l'obiettivo è implementarla da zero!


  // FIELDS

  /** La lista che contiene tutte le chiavi della mappa */
  private final List<String> keys;

  /** La lista che contiene tutti i valori della mappa */
  private final List<Integer> values;


  /*-
   * AF:
   *      f: keys -> values (funzione biunivoca)
   *      f(keys[i]) = values[i] for each i in [0, keys.length)
   *      AF(keys, values) = una mappa che associa ad ogni chiave keys.get(i) un solo valore, quello di values.get(i) => funzione iniettiva
   *      AF(keys, values) = { (keys.get(i), values.get(i)) | 0 <= i < keys.length }
   * 
   * RI:
   *     - keys != null && values != null
   *     - keys.size() == values.size()
   *     - keys.get(i) != null for each i in [0, keys.size()) (ogni valore di keys deve essere diverso da null)
   *     - values.get(i) != null for each i in [0, values.size()) (ogni valore di values deve essere diverso da null)
   *     - keys sono in ordine lessicografico strettamente crescente (non ci possono essere chiavi uguali quindi non ci saranno duplicati)
   *      
   *       NON NECESSARIO
   *     ? keys.contains(x) == true => values.contains(x) == true for each x in [0, keys.size()) (se una chiave è presente, allora deve essere presente anche il valore)
   *     ? keys.contains(x) == false => values.contains(x) == false for each x in [0, keys.size()) (se una chiave non è presente, allora non deve essere presente neanche il valore)
   *     ? keys.get(i) != keys.get(j) for each i, j in [0, keys.size()) with i != j (le chiavi non possono contenere duplicati)
   * 
   */

   /*-  FATTO DAL PROFESSORE
   * AF:
   *
   *   AF(keys, values) = a map where keys.get(i) is associated to values.get(i) for each i in [0, keys.size()).
   *
   * RI:
   *
   *  - keys != null and does not contain nulls,
   *  - values != null and does not contain nulls,
   *  - keys.size() == values.size(),
   *  - keys is in strictly increasing lexicographyc order.
   *
   */



  // CONSTRUCTORS

  /** Creates a new empty map. */
  public StringToIntMap() {
    keys = new ArrayList<>();
    values = new ArrayList<>();
  }




  // METHODS

  /**
   * Returns the size of this map.
   *
   * @return the number of key-value mappings in this map.
   */
  public int size() {
    return keys.size();
  }

  /**
   * Returns if this map is empty.
   *
   * @return {@code true} iff this map contains no key-value mappings.
   */
  public boolean isEmpty() {
    //return keys.size() == 0 ;
    return keys.isEmpty();

  }

  /**
   * Returns if this map contains the specified key.
   *
   * @param key the key to search for.
   * @return {@code true} iff this map contains a key-value mappings with the given {@code key}.
   */
  public boolean containsKey(String key) {
    return dichotomicSearch(keys, key) >= 0;
  }

  /**
   * Returns if this map contains the specified value.
   *
   * @param value the value to search for.
   * @return {@code true} iff this map contains a key-value mappings with the given {@code value}.
   */
  public boolean containsValue(int value) {
    return values.indexOf(value) != -1;
  }

  /**
   * Returns the value to which the specified key is mapped.
   *
   * @param key the key whose associated value is to be returned.
   * @return the value to which the specified key is mapped.
   * @throws NoSuchElementException if this map contains no mapping for the key, or the key is
   *     {@code null}.
   */
  public int get(String key) throws NoSuchElementException {
    int insertionPoint = dichotomicSearch(keys, key);
    if (insertionPoint < 0) throw new NoSuchElementException();
    return values.get(insertionPoint);
  }

  /**
   * Associates in this map the new key with the specified value.
   *
   * @param key the key with which the specified value is to be associated.
   * @param value the value to be associated with the specified key.
   * @throws IllegalArgumentException if the map already contain a mapping for the key.
   * @throws NullPointerException if the key is {@code null}.
   */
  public boolean put(String key, int value) {
    int insertionPoint = dichotomicSearch(keys, key); // restituisce l'indice della chiave cercata, se presente, altrimenti restituisce l'indice dove inserire la chiave
    if (insertionPoint >= 0) { //Se l'elemento è presente, la funzione restituisce l'indice dell'elemento.
      values.set(insertionPoint, value); // aggiorna il valore associato alla chiave già presente
      return false;
    } else { 
        // Se la funzione restituisce un valore negativo, significa che l'elemento non è presente nella lista e quindi va aggiunto o all'inzio o alla fine della lista
      keys.add(-insertionPoint - 1, key);
      values.add(-insertionPoint - 1, value);
      return true;
    }
  }

  /**
   * Removes the mapping for a key from this map if it is present.
   *
   * @param key the key whose mapping is to be removed from the map.
   * @return {@code true} iff this map contained a mapping for the specified key, and hence is
   *     modified by this operation.
   */
  public boolean remove(String key) {
    int insertionPoint = dichotomicSearch(keys, key);
    if (insertionPoint < 0) return false;
    keys.remove(insertionPoint);
    values.remove(insertionPoint);
    return true;
  }

  /** Removes all of the mappings from this map. */
  public void clear() {
    keys.clear();
    values.clear();
  }



  /** FATTO DAL PROFESSORE
   * Trova l'indice (o punto di inserimento) di una stringa in una lista di stringhe mantenute in ordine
   * lessicografico crescente.
   *
   * <p>Se la lista contiene la stringa data, restituisce il suo indice. Altrimenti, restituisce {@code
   * -(insertion_point) - 1} dove {@code insertion_point} è l'indice della prima stringa maggiore
   * di {@code needle}; nota che questo implica che il valore restituito è non negativo se e solo se la lista
   * contiene la stringa.
   *
   * @see Collections#binarySearch(List, Object)
   * @param haystack la lista non {@code null} di stringhe non {@code null} in ordine crescente
   *     lessicografico.
   * @param needle la stringa da cercare.
   * @return l'indice della stringa data, o {@code -insertion_point - 1} se non è presente.
   */
  private static int dichotomicSearch(final List<String> haystack, final String needle) { 
    // Haystack = La lista di stringhe ordinate in ordine lessicografico crescente.
    // Needle = La stringa da cercare.
    int lo = 0; // insertion_point
    int hi = haystack.size() - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int cmp = needle.compareTo(haystack.get(mid));
      if (cmp < 0) hi = mid - 1;
      else if (cmp > 0) lo = mid + 1;
      else return mid;
    }
    return -lo - 1;
    // Se la stringa needle non è trovata, viene restituito -(insertion_point) - 1, dove insertion_point è l'indice lo al termine del ciclo. 
    //Questo valore negativo può essere utilizzato per determinare il punto di inserimento della stringa needle nella lista mantenendo l'ordine.
  }



  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof StringToIntMap_nonfunzionante other)) return false;
    return keys.equals(other.keys) && values.equals(other.values);
  }

  @Override
  public int hashCode() {
    return keys.hashCode() + values.hashCode();
    //    return Objects.hash(keys, values);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("StringToIntMap: {");
    for (int i = 0; i < keys.size(); i++) {
      sb.append(keys.get(i) + " -> " + values.get(i));
      if (i < keys.size() - 1) sb.append(", ");
    }
    sb.append("}");
    return sb.toString();
  }
}
