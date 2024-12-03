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

import java.util.Objects;

/**
 * A rational number is an immutable number that can be expressed as the quotient or fraction \( p/q
 * \) of two {@code int}s, a numerator \( p \) and a non-zero denominator \( q \).
 */

 /**
  * Un numero razionale è un numero immutabile che può essere espresso come il quoziente o frazione \( p/q \)
 * di due {@code int}, un numeratore \( p \) e un denominatore non nullo \( q \).
  */
public class RationalNumber {

  // EXERCISE: complete following the specification (with particular attention
  // to the eventual exceptions) and provide an implementation (including the
  // equals, hashCode, and toString methods); add methods that are adequate to
  // the specification.

  // ESERCIZIO: completare seguendo la specifica (con particolare attenzione
  // alle eventuali eccezioni) e fornire un'implementazione (inclusi i metodi
  // equals, hashCode e toString); aggiungere metodi che siano adeguati alla specifica.


  // FIELDS

  /** il numeratore del numero razionale */
  public final int numerator; // PERCHÉ PUBLIC?

  /** il denominatore del numero razionale */
  public final int denominator;


  /*-
   * AF:
   *
   *   AF(numerator, denominator) = numerator/denominator
   *
   * RI:
   *
   *  - denominator > 0
   *  - gcd(numerator, denominator) == 1
   *
   */


  // CONSTRUCTORS

  /**
   * Creates a new rational number.
   *
   * @param numerator the numerator.
   * @param denominator the denominator.
   * @throws IllegalArgumentException if {@code denominator} is zero.
   * @throws IllegalArgumentException if the numerator or the denominator reduced to minimum terms
   *     are too large to be represented as {@code int}s. fatto dal prof

   */
  public RationalNumber(long numerator, long denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("Denominatore nullo");
    }
    if (denominator < 0) {
      numerator = -numerator;
      denominator = -denominator;
    }
    long mcd = MCD(numerator > 0 ? numerator : -numerator, denominator > 0 ? denominator : -denominator); // (numerator, denominator)
    // non chiaro del perchè ci sia bisogno di fare questo controllo, perchè non usare Math.abs se non vogliamo i segni?
    long reducedNumerator = numerator / mcd;
    long reducedDenominator = denominator / mcd;

    /*if (reducedNumerator < Integer.MIN_VALUE || reducedNumerator > Integer.MAX_VALUE) // fatto dal prof
      throw new IllegalArgumentException(
          "numerator (reduced to minimum terms) " + reducedNumerator + " does not fit into an int");
    if (reducedDenominator > Integer.MAX_VALUE)
      throw new IllegalArgumentException(
          "denominator (reduced to minimum terms) "
              + reducedDenominator
              + " does not fit into an int"); */

    this.numerator = (int) reducedNumerator;
    this.denominator = (int) reducedDenominator;

    
  }

  /**
   * Creates a new integer number.
   *
   * @param value the value.
   */
  public RationalNumber(int value) {
    this(value, 1);
  }



  // METHODS

  /**
   * Returns the sum of this rational number and another one.
   *
   * @param other the other rational number.
   * @return the sum of this rational number and {@code other}.
   */
  public RationalNumber add(RationalNumber other) {
    if (this.denominator == other.denominator) { // il prof non usa questo if
      return new RationalNumber((long) this.numerator + other.numerator, (long) this.denominator);
    } else {
      return new RationalNumber(
        (long) this.numerator * other.denominator + other.numerator * this.denominator, 
        (long) this.denominator * other.denominator);
    }
  }

  /**
   * Returns the product of this rational number and another one.
   *
   * @param other the other rational number.
   * @return the product of this rational number and {@code other}.
   */
  public RationalNumber mul(RationalNumber other) {
    return new RationalNumber( 
      (long) this.numerator * other.numerator, (long) this.denominator * other.denominator);
  }

  /**
   * Tells whether this rational number is an integer.
   *
   * @return {@code true} if this rational number is an integer, {@code false} otherwise.
   */
  public boolean isInteger() { // fatto dal prof
    return denominator == 1;
  }

  /**
   * Tells whether this rational number is positive.
   *
   * @return {@code true} if this rational number is positive, {@code false} otherwise.
   */
  public boolean isPositive() { // fatto dal prof
    return numerator > 0;
  }

  /**
   * Tells whether this rational number is equal to zero.
   *
   * @return {@code true} if this rational number is zero, {@code false} otherwise.
   */
  public boolean isZero() { // fatto dal prof
    return numerator == 0;
  }

  /**
   * Returns the greatest common divisor of two integers.
   *
   * @param a the first integer.
   * @param b the second integer.
   * @return the greatest common divisor of {@code a} and {@code b}.
   */
  private long MCD(long a, long b) { // usare long per evitare overflow
    while (b > 0) {
      long r = a % b;
      a = b;
      b = r;
    }
    return a;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RationalNumber other)) return false;
    return numerator == other.numerator && denominator == other.denominator;
    // Why the following is not correct?
    // return (double) numerator / denominator == (double) other.numerator / other.denominator;
    // Il confronto di numeri razionali usando la divisione in double non è affidabile a causa della precisione limitata dei numeri in virgola mobile e degli errori di arrotondamento.
  }

  @Override
  public int hashCode() {
    return Objects.hash(numerator, denominator);
  }

  @Override
  public String toString() {
    if (denominator == 1) return Integer.toString(numerator);
    return numerator + "/" + denominator;
  }
}
