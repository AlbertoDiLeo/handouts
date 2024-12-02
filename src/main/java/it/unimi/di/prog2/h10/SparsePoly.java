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

import it.unimi.di.prog2.h08.impl.NegativeExponentException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * {@code SparsePoly}s are immutable polynomials with integer coefficients such that the number of
 * nonzero coefficient is small with respect to the degree.
 *
 * è progettato per essere efficiente quando il polinomio ha pochi termini non nulli, anche se il grado del polinomio è elevato.
 * 
 * 
 * <p>A typical {@code Poly} is \( p = c_0 + c_1 x + c_2 x^2 + \cdots + c_n x^n \).
 */
public class SparsePoly {

  /**
   * A record holding a non-zero term of the polynomial.
   * 
   * Al posto di usare un array di coefficienti, usiamo una lista di termini, ciascuno dei quali contiene il coefficiente e l'esponente del termine corrispondente.
   * ogni termine è un record
   * 
   * In una rappresentazione sparsa, come quella utilizzata nella classe SparsePoly, si memorizzano solo i termini non nulli del polinomio. Ogni termine non nullo 
   * è rappresentato da un record Term che contiene il coefficiente e il grado del termine. Questi record sono memorizzati in una lista.
   * p = 3x^2 + 2 
   * [Term(3, 2), Term(2, 0)]
   * 
   * @param coefficient the coefficient.
   * @param degree the degree.
   */
  public record Term(int coefficient, int degree) { // può essere considerato una sorta di sotto classe statica di SparsePoly
    /**
     * Builds a term.
     * Il record Term rappresenta un singolo termine non nullo del polinomio, con un coefficiente e un grado.
     * Un record è una struttura dati concisa e immutabile che è ideale per rappresentare contenitori di dati semplici.
     * si può considerare come una sorta di struct in Golang
     * 
     * @throws NegativeExponentException if {@code n} &lt; 0.
     */
    public Term { // using the compact constructor
      // Il compact constructor è una caratteristica dei record in Java che permette di definire costruttori in modo più conciso e leggibile. 
      if (degree < 0)
        throw new NegativeExponentException("A term cannot have a negative exponent.");
      if (coefficient == 0)
        throw new IllegalArgumentException("A term cannot have a zero coefficient.");
    }
  }

  /** The array of terms (in increasing degree). */ // MA DOVE è L'ORDINAMENTO? guardare addTerm
  private final List<Term> terms; // campo di SparsePoly, rappresenta tutti i termini del polinomio
  // Dichiarare il campo terms come final significa che il riferimento alla lista non può essere riassegnato dopo l'inizializzazione. Tuttavia, questo non impedisce di modificare il contenuto della lista (aggiungere o rimuovere elementi).
  // Questo garantisce che la lista stessa rimanga la stessa istanza per tutta la durata dell'oggetto SparsePoly.


  /*
   * Variabili di Classe: I CAMPI STATIC sono variabili di classe, il che significa che esiste una sola copia del campo per tutte le istanze della classe. Sono condivisi tra tutte le istanze della classe.
     Costanti: Le costanti sono spesso dichiarate come STATIC FINAL perché il loro valore non cambia e devono essere condivise tra tutte le istanze.
    Utilità: I campi statici possono essere utilizzati per memorizzare dati comuni a tutte le istanze della classe, come contatori o configurazioni globali.
   */

  /** Initializes this to be the zero polynomial, that is \( p = 0 \). */
  public SparsePoly() {
    terms = Collections.emptyList(); // lista immutabile vuota, non CHIARO perchè uso questo e non new ArrayList<>()
  }

  /**
   * Initializes this to be the polynomial \(p = cx^n\).
   *
   * @param c the coefficient.
   * @param n the degree.
   * @throws NegativeExponentException if {@code n} &lt; 0.
   */
  public SparsePoly(int c, int n) throws NegativeExponentException {
    // Se il costruttore del record Term lancia l'eccezione NegativeExponentException, allora il costruttore SparsePoly(int c, int n) deve dichiarare che può lanciare questa eccezione, 
    // anche se non la gestisce direttamente. Questo è necessario per propagare correttamente l'eccezione.
    terms = c == 0 ? Collections.emptyList() : List.of(new Term(c, n));
  }
  // Se il coefficiente c è 0, la lista terms viene inizializzata come una lista vuota immutabile utilizzando Collections.emptyList()
  // Se il coefficiente c è diverso da 0, la lista terms viene inizializzata con un singolo elemento, un nuovo oggetto Term con il coefficiente c e il grado n, utilizzando List.of(new Term(c, n))

  // Le liste create con List.of sono immutabili. Questo significa che non possono essere modificate dopo la loro creazione. Non è possibile aggiungere, rimuovere o modificare elementi nella lista.
  // List.of è progettato per essere efficiente in termini di memoria e prestazioni. Le liste create con List.of sono generalmente più leggere rispetto alle liste mutabili come ArrayList.

  /**
   * Initializes this to be the polynomial from a list of terms in increasing degree order.
   *
   * @param lst the not {@code null} list, not containing {@code null}s and in increasing degree
   *     order.
   */
  private SparsePoly(final List<Term> lst) { // Il costruttore privato SparsePoly(final List<Term> lst) crea una vista immutabile della lista lst e la assegna al campo terms
    terms = Collections.unmodifiableList(lst); // lst la uso solo nei metodi privati (quindi senza istanziare oggetti) come findbydegree
    // inizializza la lista terms come una vista immutabile della lista lst passata come parametro. Questo garantisce che la lista terms non possa essere modificata dopo la sua creazione.
   } 
   /*
    * private SparsePoly(final List<Term> lst) corrisponde al private Poly(int n) in Poly
    * dato che è una classe immutabile ogni volta devo creare un nuovo oggetto per modificare lo stato
    * Prima creavo ogni volta un array con la dimensione di degree e lo riempivo nel metodo, 
    * ora prima creo la lista con i valori aggiornati e poi creo un nuovo oggetto SparsePoly con la lista aggiornata
    */



  /*
   * I metodi statici non possono accedere direttamente ai campi di istanza della classe. Pertanto, devono accettare la lista lst (ad esempio) come parametro se devono operare su di essa.
   */

  /*
   * Metodi di Classe: I METODI STATIC sono metodi di classe, il che significa che possono essere chiamati senza creare un'istanza della classe. Sono associati alla classe stessa piuttosto che a un'istanza specifica.
     Utilità: I metodi statici sono utili per operazioni che non dipendono dallo stato di un'istanza specifica (metodi di utilità)
   */

  /**
   * Finds the index of a term of given degree in a list of terms in increasing degree order.
   * ASSUMO CHE LA LISTA SIA ORDINATA, MA NON C'è NESSUN METODO CHE LA ORDINA(?)
   *
   * @param lst the not {@code null} list of not {@code null} terms and in increasing degree order.
   * @param d the degree.
   * @return the index of a term of given degree, or -1 if none is present.
   */
  private static int findByDegree(List<Term> lst, int d) { 
    // metodo di utilità (statico) quindi non può accedere direttamente ai campi di istanza della classe.
    // per questo motivo passo come parametro la lista lst e non l'ogetto SparsePoly (non programmo ad oggetti qui dentro dato che non istanzio oggetti) 
    // è associato alla classe SparsePoly e non a una specifica istanza di SparsePoly
    for (int i = 0; i < lst.size(); i++) {
      final int degree = lst.get(i).degree;
      if (degree == d) return i;
      if (degree > d) return -1; // se il grado del termine corrente è maggiore del grado cercato, il termine cercato non è presente nella lista dato che è ordinata in modo crescente
    }
    return -1; // posso usare numeri "speciali" perchè tanto è un metodo privato e non verrà mai chiamato dall'esterno
  }

  /*
   * PERCHè USARE UN METODO STATIC PER FINDBYDEGREE/addTerm?
   * Passare una lista come parametro rende il metodo più flessibile e riutilizzabile. 
   * Può essere utilizzato per cercare termini in qualsiasi lista di termini, non solo nella lista terms di un oggetto SparsePoly.
   * metodo chiamato da un altro metodo, quindi utile solo per la mia implementazione interna
   */



  /**
   * Returns the coefficient of the term of given exponent.
   *
   * @param d the exponent of the term to consider.
   * @return the coefficient of the considered term.
   */
  public int coeff(int d) {
    if (d < 0 || d > degree()) return 0; // se l'esponente è negativo o maggiore del grado del polinomio restituisce 0
    int i = findByDegree(terms, d);
    return i != -1 ? terms.get(i).coefficient : 0; // condizione ? espressione_se_vera : espressione_se_falsa;
  }

  /**
   * Returns the degree of this polynomial.
   *
   * @return the largest exponent with a non-zero coefficient; returns 0 if this is the zero {@code
   *     Poly}.
   */
  public int degree() {
    return terms.isEmpty() ? 0 : terms.getLast().degree;
  }

  /**
   * Adds a term to a list of terms in increasing degree order.
   *
   * <p>The list will remain in increasing degree order, in case a term with the same degree was
   * present, the two will be added (and removed if the coefficient will become 0).
   *
   * @param lst the not {@code null} list of not {@code null} terms in increasing degree order.
   * @param term the not {@code null} term.
   */
  private static void addTerm(List<Term> lst, Term term) {
    if (term.coefficient == 0) return;
    int i = findByDegree(lst, term.degree);
    if (i != -1) { // se c'è quel grado è nella lista (nel mio polinomio) sommo i coefficienti con quel grado
      int c = lst.get(i).coefficient + term.coefficient;
      if (c != 0) lst.set(i, new Term(c, term.degree)); // aggiungo un record alla lista (creo per ogni grado il mio polinomio risultante) con il nuovo coefficiente 
      else lst.remove(i); // altrimenti rimuovo il record dalla lista
    } else { // se non c'è quel grado in lst, lo aggiungo in modo ordinato
      for (i = 0; i < lst.size(); i++) if (lst.get(i).degree > term.degree) break;
      lst.add(i, term); // se il grado del termine corrente è maggiore del grado del termine term (il secondo addendo), aggiungo il nuovo termine alla lista in posizione i
    }
  }

  /**
   * Performs polynomial addition.
   *
   * <p>If \( p \) is this polynomial, returns \( p + q \).
   *
   * @param q the polynomial to add to this one.
   * @return the sum among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public SparsePoly add(SparsePoly q) throws NullPointerException {
    Objects.requireNonNull(q, "The polynomial to add cannot be null.");
    List<Term> result = new LinkedList<>(this.terms); // lista doppiamente concatenata, più efficiente di ArrayList per inserimenti e rimozioni
    // creo una copia della lista terms di this, uso LinkedList perchè devo poter modificare la lista
    for (Term t : q.terms) addTerm(result, t);
    return new SparsePoly(result);
  }
  // è necessario creare una nuova lista result con la copia  dei termini di this
  // perchè a questa copia aggiungo poi i termini del secondo addendo q.

  /*
    * Se il metodo addTerm comporta frequenti operazioni di inserimento e rimozione di elementi, 
    * LinkedList è più efficiente rispetto a ArrayList per queste operazioni. 
    * Questo perché LinkedList non richiede lo spostamento degli elementi successivi.
    * Se la lista result viene modificata frequentemente durante l'operazione di somma, 
    * LinkedList offre prestazioni migliori per queste modifiche rispetto a ArrayList.
    *
    * ArrayList: È implementata come un array dinamico. Gli elementi sono memorizzati in un array che può essere ridimensionato automaticamente.
   */

  /**
   * Performs polynomial multiplication.
   *
   * <p>If \( p \) is this polynomial, returns \( p q \).
   *
   * @param q the polynomial to multiply by this one.
   * @return the product among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public SparsePoly mul(SparsePoly q) throws NullPointerException {
    Objects.requireNonNull(q, "The polynomial to multiply cannot be null.");
    List<Term> lst = new LinkedList<>();
    for (Term tq : q.terms)
      for (Term tt : terms)
        addTerm(lst, new Term(tq.coefficient * tt.coefficient, tq.degree + tt.degree));
    return new SparsePoly(lst);
  }
  // sto creando lst da zero, quindi non ho bisogno di fare una copia di terms come in add (new LinkedList<>(this.terms))
  // infatti faccio un doppio ciclo for per moltiplicare tutti i termini di q con tutti i termini di this

  /**
   * Performs polynomial subtraction.
   *
   * <p>If \( p \) is this polynomial, returns \( p - q \).
   *
   * @param q the polynomial to subtract from this one.
   * @return the subtraction among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public SparsePoly sub(SparsePoly q) throws NullPointerException {
    Objects.requireNonNull(q, "The polynomial to subtract cannot be null.");
    return add(q.minus());
  }

  /**
   * Returns the negate polynomial.
   *
   * <p>If \( p \) is this polynomial, returns \( -p \).
   *
   * @return this polynomial multiplied by \( -1 \).
   */
  public SparsePoly minus() {
    List<Term> lst = new LinkedList<>();
    for (Term t : terms) lst.add(new Term(-t.coefficient, t.degree));
    return new SparsePoly(lst);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof SparsePoly other)) return false;
    return terms.equals(other.terms);
  }

  @Override
  public int hashCode() {
    return terms.hashCode();
  }

  @Override
  public String toString() {
    if (degree() > 0) {
      StringBuilder sb = new StringBuilder("SparsePoly: ");
      int pos = terms.size() - 1;
      Term t = terms.get(pos);
      if (t.coefficient < -1) sb.append("-" + (-t.coefficient));
      else if (t.coefficient == -1) sb.append("-");
      else if (t.coefficient > 1) sb.append(t.coefficient);
      sb.append("x" + (t.degree > 1 ? "^" + t.degree : ""));
      while (--pos >= 0) {
        t = terms.get(pos);
        if (t.degree == 0) break;
        if (t.coefficient < -1) sb.append(" - " + (-t.coefficient));
        else if (t.coefficient == -1) sb.append(" - ");
        else if (t.coefficient == 1) sb.append(" + ");
        else sb.append(" + " + t.coefficient);
        sb.append("x" + (t.degree > 1 ? "^" + t.degree : ""));
      }
      if (t.degree == 0)
        if (t.coefficient > 0) sb.append(" + " + t.coefficient);
        else if (t.coefficient < 0) sb.append(" - " + (-t.coefficient));
      return sb.toString();
    } else return "SparsePoly: " + (terms.isEmpty() ? 0 : terms.get(0).coefficient);
  }
}
