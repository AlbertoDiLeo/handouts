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

package it.unimi.di.prog2.h19;

/**
 * {@code Poly}s are immutable polynomials with integer coefficients.
 *
 * <p>A typical {@code Poly} is \( p = c_0 + c_1 x + c_2 x^2 + \cdots + c_n x^n \).
 */
public interface Poly extends Iterable<Poly.Term> {

  /**
   * A non-null term of a polynomial.
   *
   * <p>A term is a pair of a coefficient \( c \) and a degree \( d \), representing \(cx^d\).
   *
   * @param coefficient the coefficient of the term.
   * @param degree the degree of the term.
   */
  public record Term(int coefficient, int degree) {

    /**
     * Creates a new term with the given coefficient and degree.
     *
     * @param coefficient the coefficient of the term.
     * @param degree the degree of the term.
     * @throws IllegalArgumentException if the degree is negative or the coefficient is zero.
     */
    public Term {
      if (degree < 0) throw new IllegalArgumentException("The degree must be non-negative.");
      if (coefficient == 0) throw new IllegalArgumentException("The coefficient must be non-zero.");
    }
  }

  /**
   * Returns the degree of this polynomial.
   *
   * @return the largest exponent with a non-zero coefficient; returns 0 if this is the zero {@code
   *     Poly}.
   */
  public int degree();

  /**
   * Returns the negated polynomial.
   *
   * <p>If \( p \) is this polynomial, returns \( -p \).
   *
   * @return this polynomial multiplied by \( -1 \).
   */
  public Poly minus();

  /**
   * Performs polynomial addition.
   *
   * <p>If \( p \) is this polynomial, returns \( p + q \).
   *
   * @param q the polynomial to add to this one.
   * @return the sum among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public Poly add(Poly q);

  /**
   * Performs polynomial subtraction.
   *
   * <p>If \( p \) is this polynomial, returns \( p - q \).
   *
   * @param q the polynomial to subtract from this one.
   * @return the subtraction among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public default Poly sub(Poly q) {
    return this.add(q.minus());
  }

  /**
   * Performs polynomial multiplication.
   *
   * <p>If \( p \) is this polynomial, returns \( p q \).
   *
   * @param q the polynomial to multiply by this one.
   * @return the product among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public Poly mul(Poly q);

  /**
   * Returns the coefficient of the term of given exponent.
   *
   * @param degree the exponent of the term to consider.
   * @return the coefficient of the considered term.
   */
  public default int coefficient(int degree) {
    for (Poly.Term t : this) if (t.degree == degree) return t.coefficient;
    return 0;
  }
}
