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

import java.util.Objects;

import it.unimi.di.prog2.h08.impl.NegativeExponentException;

/**
 * {@code Poly}s are immutable polynomials with integer coefficients.
 *
 * <p>A typical {@code Poly} is \( p = c_0 + c_1 x + c_2 x^2 + \cdots + c_n x^n \).
 */
public class Poly { // we don't extend Cloneable, see EJ 3.13

  // Fields

  /** The array of coefficients, the {@code coefficient[i]} is the coefficient of \( x^i \). */
  private final int[] coefficient;
  // coefficient[0] is the coefficient of x^0, coefficient[1] is the coefficient of x^1, and so on
  // In un array, l'indice rappresenta naturalmente l'esponente del termine del polinomio
  // l'ordine dei coefficenti conta nei polinomi, l'ordine in un insieme no

  /** The degree of the polynomial. */
  private final int degree;

  // Constructors

  /** Initializes this to be the zero polynomial, that is \( p = 0 \). */
  public Poly() {
    coefficient = new int[1]; // coefficiente del termine costante x^0, array di un solo elemento
    degree = 0;
  }

  /**
   * Initializes this to be the polynomial \(p = cx^n\).
   *
   * @param c the coefficient.
   * @param n the degree.
   * @throws NegativeExponentException if {@code n} &lt; 0.
   */
  public Poly(int c, int n) throws NegativeExponentException {
    if (n < 0)
      throw new NegativeExponentException("Can't create a monomial with negative exponent");
    if (c == 0) degree = 0;
    else degree = n;
    coefficient = new int[degree + 1];
    coefficient[degree] = c;
  }
  // esempio: Poly(3, 2) crea il polinomio 3x^2 (array di 3 elementi, il terzo è 3)
  // Il polinomio creato è ( p = 3x^2 ), rappresentato dall'array dei coefficienti [0, 0, 3].

  /**
   * Initializes a polynomial of given degree (with all coefficients equal to 0).
   *
   * @param degree the degree, must be non negative.
   */
  private Poly(int degree) {
    this.degree = degree; // assegna il valore del parametro degree al campo degree della classe.
    coefficient = new int[degree + 1];
  }
  // utile perchè mi permette di creare un polinomio vuoto con un certo grado
  // Può essere utile inizializzare un polinomio con coefficienti zero come punto di partenza per ulteriori operazioni matematiche, come l'aggiunta di termini o la derivazione.
  // private perchè crea polinomi intermedi

  // Methods

  /**
   * A factory method returning a monomial. (see EJ 2.1)
   * 
   * Un factory method (metodo di fabbrica) è un metodo statico (puo anche non esserlo) che ritorna un'istanza di una classe (Poly).
   * Possono avere nomi descrittivi che chiariscono l'intento della creazione dell'oggetto
   *
   * @param c the coefficient.
   * @param n the degree.
   * @throws NegativeExponentException if {@code n} &lt; 0.
   * @return the monomial, if {@code n} &gt;= 0.
   */
  public static Poly monomialWithCoeffAndDegree(int c, int n) {
    return new Poly(c, n);
  }

  /*
   * STATICO: non dipende dallo stato di un'istanza specifica della classe. Può essere chiamato direttamente sulla classe (ESEMPIO: Poly.monomialWithCoeffAndDegree(3, 2))
   * NON STATICO: dipende dallo stato di un'istanza specifica della classe. Deve essere chiamato su un'istanza della classe (ESEMPIO: Poly poly = new Poly(); poly.degree())
   * 
   */

  /**
   * Returns the degree of this polynomial.
   *
   * @return the largest exponent with a non-zero coefficient; returns 0 if this is the zero {@code
   *     Poly}.
   */
  public int degree() {
    return degree;
  }

  /**
   * Returns the coefficient of the term of given exponent.
   *
   * @param degree the exponent of the term to consider.
   * @return the coefficient of the considered term.
   */
  public int coeff(int degree) {
    if (degree < 0 || degree > this.degree) return 0;
    else return coefficient[degree];
  }

  /*
   * esempio
   *  Poly poly = new Poly(3, 2); // 3x^2
      poly = poly.add(new Poly(2, 1)); // 3x^2 + 2x
      poly = poly.add(new Poly(1, 0)); // 3x^2 + 2x + 1

      int coeff0 = poly.coeff(0); // Restituisce 1
      int coeff1 = poly.coeff(1); // Restituisce 2
      int coeff2 = poly.coeff(2); // Restituisce 3
      int coeff3 = poly.coeff(3); // Restituisce 0 (poiché non esiste un termine con x^3)
   */



  /**
   * Performs polynomial addition.
   *
   * <p>If \( p \) is this polynomial, returns \( p + q \).
   *
   * @param q the polynomial to add to this one.
   * @return the sum among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public Poly add(Poly q) throws NullPointerException { //soluzione del prof
    Objects.requireNonNull(q, "The polynomial must not be null."); // Objects.requireNonNull è utilizzato per verificare che un riferimento non sia null. Se il riferimento è null, lancia una NullPointerException con un messaggio
    // UnsupportedOperationException è utilizzata per indicare che un'operazione non è supportata. Questo è tipicamente usato per metodi che non sono implementati o che non dovrebbero essere chiamati in un certo contesto.
    // Lanciare un'UnsupportedOperationException non sarebbe appropriato in questo contesto perché l'operazione di aggiunta è supportata, ma l'argomento q non deve essere null.
    final Poly larger, smaller; // Indica chiaramente che larger e smaller sono destinati a essere utilizzati come riferimenti costanti all'interno del metodo.
    if (degree() > q.degree()) {
      larger = this;
      smaller = q;
    } else {
      larger = q;
      smaller = this;
    }
    int resultDegree = larger.degree();
    if (degree() == q.degree()) {
      for (int k = degree(); k > 0; k--)
        if (coefficient[k] + q.coefficient[k] != 0) break;
        else resultDegree--;
        // devo diminuire il grado del polinomio risultante perchè i coefficienti di grado maggiore sono nulli
    }
    Poly result = new Poly(resultDegree); // get a new Poly
    int i;
    for (i = 0; i <= smaller.degree() && i <= resultDegree; i++) // La somma dei termini deve essere eseguita solo per i gradi che esistono in entrambi i polinomi e che sono validi nel polinomio risultante.
      result.coefficient[i] = smaller.coefficient[i] + larger.coefficient[i];
    for (int j = i; j <= resultDegree; j++) result.coefficient[j] = larger.coefficient[j];
    return result;
  }
  /*
   * Il primo ciclo for somma i termini comuni ai due polinomi, cioè i termini fino al grado del polinomio più piccolo. Questo garantisce che tutti i termini comuni siano sommati correttamente.
   * Il secondo ciclo for copia i termini rimanenti del polinomio più grande nel polinomio result. Questo è necessario perché il polinomio più grande potrebbe avere termini che il polinomio più piccolo non ha.
   * 
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
  public Poly mul(Poly q) throws NullPointerException {
    if (q == null) throw new NullPointerException("The polynomial must not be null.");
    if ((q.degree == 0 && q.coefficient[0] == 0) || (degree == 0 && coefficient[0] == 0))
      return new Poly(); // Se uno dei due polinomi è il polinomio zero, il risultato della moltiplicazione è il polinomio zero.
    Poly result = new Poly(degree + q.degree);
    for (int i = 0; i <= degree; i++)
      for (int j = 0; j <= q.degree; j++)
        result.coefficient[i + j] = result.coefficient[i + j] + coefficient[i] * q.coefficient[j];
    return result;
  }

  /*
   * esempio
   * Poly p1 = new Poly(2, 1); // 2x => [0, 2]
     p1 = p1.add(new Poly(1, 0)); // 2x + 1 => [1, 2]

     Poly p2 = new Poly(1, 1); // x => [0, 1]
     p2 = p2.add(new Poly(3, 0)); // x + 3 => [3, 1]

     Poly product = p1.mul(p2); // Prodotto dei polinomi
     2x^2 + 7x + 3 => [3, 7, 2]
     il termine noto (costante) è quello più a sinistra

   */

  /**
   * Performs polynomial subtraction.
   *
   * <p>If \( p \) is this polynomial, returns \( p - q \).
   *
   * @param q the polynomial to subtract from this one.
   * @return the subtraction among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public Poly sub(Poly q) throws NullPointerException {
    if (q == null) throw new NullPointerException("The polynomial must not be null.");
    return add(q.minus());
  }
  // p - q = p + (-q)

  /**
   * Returns the negate polynomial.
   *
   * <p>If \( p \) is this polynomial, returns \( -p \).
   *
   * @return this polynomial multiplied by \( -1 \).
   */
  public Poly minus() {
    Poly r = new Poly(degree);
    // Se si utilizzasse new Poly(), si creerebbe un polinomio zero con grado 0 e un array di coefficienti di dimensione 1. Questo non sarebbe sufficiente per rappresentare un polinomio con un grado maggiore di 0.
    for (int i = 0; i <= degree; i++) r.coefficient[i] = -coefficient[i];
    return r;
  }

  /*
   * esempio
   * Supponiamo di avere un polinomio ( p = 3x^2 + 2x + 1 ), rappresentato dall'array dei coefficienti [1, 2, 3].

    Polinomio Iniziale:
    Coefficienti: [1, 2, 3] (rappresenta ( 3x^2 + 2x + 1 ))
    Grado: 2

    Creazione del Polinomio Negato:
    Poly r = new Poly(degree);
I   Inizializza un nuovo polinomio r con grado 2 e coefficienti [0, 0, 0].
    grazie al ciclo for nega tutti i coefficienti
   */
}
