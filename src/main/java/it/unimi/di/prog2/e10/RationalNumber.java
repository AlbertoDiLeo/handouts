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
  private int numerator;

  /** il denominatore del numero razionale */
  private int denominator;


  // CONSTRUCTORS

  /**
   * Creates a new rational number.
   *
   * @param numerator the numerator.
   * @param denominator the denominator.
   */
  public RationalNumber(int numerator, int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("Denominatore nullo");
    }
    int mcd = MCD(numerator, denominator);
    this.numerator = numerator / mcd;
    this.denominator = denominator / mcd;

    if (this.denominator < 0) {
      this.numerator = -this.numerator;
      this.denominator = -this.denominator;
    }
  }



  // METHODS

  /**
   * Returns the sum of this rational number and another one.
   *
   * @param other the other rational number.
   * @return the sum of this rational number and {@code other}.
   */
  public RationalNumber add(RationalNumber other) {
    if (this.denominator == other.denominator) {
      return new RationalNumber(this.numerator + other.numerator, this.denominator);
    } else {
      return new RationalNumber(this.numerator * other.denominator + other.numerator * this.denominator, this.denominator * other.denominator);
    }
  }

  /**
   * Returns the product of this rational number and another one.
   *
   * @param other the other rational number.
   * @return the product of this rational number and {@code other}.
   */
  public RationalNumber mul(RationalNumber other) {
    return new RationalNumber(this.numerator * other.numerator, this.denominator * other.denominator);
  }

  /**
   * Returns the greatest common divisor of two integers.
   *
   * @param a the first integer.
   * @param b the second integer.
   * @return the greatest common divisor of {@code a} and {@code b}.
   */
  private static int MCD(int a, int b) {
    while (b != 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }
    return Math.abs(a);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RationalNumber other = (RationalNumber) o;
    return numerator == other.numerator && denominator == other.denominator;
  }

  @Override
  public int hashCode() {
    int result = numerator;
    result = 31 * result + denominator;
    return result;
  }

  @Override
  public String toString() {
    return numerator + "/" + denominator;
  }
}
