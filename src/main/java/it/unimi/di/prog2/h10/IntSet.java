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

package it.unimi.di.prog2.h10;

import it.unimi.di.prog2.h08.impl.EmptyException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code IntSet}s are mutable, unbounded sets of integers.
 *
 * <p>A typical IntSet is \( S = \{x_1, \ldots, x_n \} \).
 */
public class IntSet {

  // Fields

  /** The {@link List} containing this set elements. */
  private final List<Integer> els;

  // Constructors

  /**
   * Initializes this set to be empty.
   *
   * <p>Builds the set \( S = \varnothing \).
   */
  public IntSet() { // Costruttore di default, ci deve essere lo stesso, anche col copy constructor
    els = new ArrayList<>();
  }

  /**
   * A *copy constructor*.
   *  Un copy constructor permette di creare una copia indipendente di un oggetto. Questo è utile quando si desidera modificare la copia senza influenzare l'originale.
   *  In questo caso, il copy constructor di IntSet crea una copia indipendente di un altro oggetto IntSet.
   *  Questo significa che le due istanze di IntSet condivideranno lo stesso contenuto, ma saranno due oggetti separati.
   *  Questo è utile quando si desidera modificare un oggetto senza influenzare l'originale.
   *  il copy constructor è un metodo che crea un nuovo oggetto copiando i valori di un altro oggetto.
   *  simile al clone, ma il copy constructor è più flessibile e può essere utilizzato per creare copie di oggetti in modo più controllato.
   * 
   * esempio di utilizzo del copy constructor nel main:
   * Utilizzo del copy constructor per creare una copia dell'insieme originale
   * 
   * IntSet copiedSet = new IntSet(originalSet);
   * 
   * @param other the {@code IntSet} to copy from. 
   * other è l'istanza di IntSet da cui copiare i dati
   * 
   */
  public IntSet(IntSet other) {
    els = new ArrayList<>(other.els);
  }

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
    els.set(i, els.get(last));
    els.remove(last);
  }

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
   * @return an arbitrary element from this set.
   * @throws EmptyException if this set is empty.
   */
  public int choose() throws EmptyException {
    if (els.isEmpty()) throw new EmptyException("Can't choose from an empty set");
    return els.get(els.size() - 1);
  }

  @Override // Questo metodo è stato sovrascritto per garantire che due oggetti IntSet siano uguali se contengono gli stessi elementi.
  // @Override informa il compilatore che il metodo è destinato a sovrascrivere un metodo in una superclasse.
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof IntSet other)) return false; // Se obj è un'istanza di IntSet, viene assegnato alla variabile locale other utilizzando il pattern matching per l'operatore instanceof
    if (els.size() != other.els.size()) return false;
    for (int e : els) if (!other.isIn(e)) return false;
    return true;
  }
  // this si riferisce all'oggetto corrente su cui è chiamato il metodo
  // obj (other) è l'oggetto passato come argomento al metodo
  // other e obj si riferiscono allo stesso oggetto, ma other è utilizzato per accedere ai metodi e ai campi specifici della classe IntSet dopo aver verificato il tipo di obj
  // instanceof è un operatore che verifica se un oggetto è un'istanza di una classe specifica (IntSet) o di una sua sottoclasse

  @Override
  // scegliere una buona funzione hash è importante per garantire che gli oggetti siano distribuiti uniformemente in una tabella hash
  public int hashCode() { // utilizzato per calcolare un codice hash per un oggetto. Questo codice hash è un valore intero che rappresenta l'oggetto
    int result = 0;
    for (int e : els) result += e; // This is a very bad hash function! è SOLO UN ESEMPIO
    return result;
  }

  /*
   * Le variabili locali e i campi di una classe non richiedono una funzione hash perché non vengono utilizzati in strutture dati basate su hash. 
   * Le variabili locali e i campi sono semplicemente memorizzati in memoria e non richiedono una distribuzione uniforme per ottimizzare le prestazioni delle operazioni di accesso.
   * Gli oggetti vengono spesso memorizzati in strutture dati basate su hash, come HashMap, HashSet e Hashtable, perché queste strutture offrono un accesso rapido ed efficiente agli elementi
   */

  /*
   * CONTRATTO TRA equals E hashCode
   * Se due oggetti sono uguali secondo il metodo equals, devono avere lo stesso codice hash.
   * Se due oggetti hanno lo stesso codice hash, non è necessariamente vero che sono uguali secondo il metodo equals, ma è una buona pratica che oggetti diversi abbiano codici hash diversi per ridurre le collisioni.
   */

  @Override
  public String toString() { // Questo metodo è stato sovrascritto per restituire una rappresentazione testuale (una stringa) dell'oggetto IntSet
    String lst = els.toString();
    return "IntSet: {" + lst.substring(1, lst.length() - 1) + "}";
  }
  // Utilizza il metodo substring per rimuovere i delimitatori [ e ] dalla rappresentazione stringa della lista.
}
