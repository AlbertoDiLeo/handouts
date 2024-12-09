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

import java.util.Iterator;
import java.util.NoSuchElementException;

/** A dense polynomial with integer coefficients. */
public class DensePoly extends AbstractPoly {

  /** The array of coefficients, the {@code coefficients[i]} is the coefficient of \( x^i \). */
  private final int[] coefficients;

  /** Initializes this to be the zero polynomial, that is \( p = 0 \). */
  public DensePoly() {
    super(0);
    coefficients = new int[1];
  }

  /**
   * Initializes this to be the polynomial \(p = cx^n\).
   *
   * @param coefficient the coefficient.
   * @param degree the degree.
   * @throws IllegalArgumentException if {@code n} &lt; 0.
   */
  public DensePoly(int coefficient, int degree) throws IllegalArgumentException {
    super(degree);
    coefficients = new int[degree + 1];
    coefficients[degree] = coefficient;
  }

  /**
   * Initializes a polynomial of given degree (with all coefficients equal to 0).
   *
   * @param degree the degree.
   */
  private DensePoly(int degree) {
    super(degree);
    coefficients = new int[degree + 1];
  }

  @Override
  public int coefficient(int degree) {
    if (degree < 0 || degree > this.degree()) return 0;
    else return coefficients[degree];
  }

  @Override
  public DensePoly add(Poly q) throws NullPointerException {
    int newdeg = degree() > q.degree() ? degree() : q.degree();
    if (degree() == q.degree()) // decrease according to trailing zeros
    for (int k = degree(); k > 0; k--)
        if (coefficients[k] != -q.coefficient(k)) break;
        else newdeg--;
    DensePoly r = new DensePoly(newdeg);
    for (int i = 0; i <= newdeg; i++)
      r.coefficients[i] = (i <= degree() ? coefficients[i] : 0) + q.coefficient(i);
    return r;
  }

  @Override
  public DensePoly mul(Poly q) throws NullPointerException {
    if ((q.degree() == 0 && q.coefficient(0) == 0) || (degree() == 0 && coefficients[0] == 0))
      return new DensePoly();
    DensePoly r = new DensePoly(degree() + q.degree());
    for (int i = 0; i <= degree(); i++)
      for (Poly.Term t : q) r.coefficients[i + t.degree()] += coefficients[i] * t.coefficient();
    return r;
  }

  @Override
  public DensePoly minus() {
    DensePoly r = new DensePoly(degree());
    for (int i = 0; i <= degree(); i++) r.coefficients[i] = -coefficients[i];
    return r;
  }

  @Override
  public Iterator<Term> iterator() {
    return new Iterator<Poly.Term>() {

      /** A lower bound for the degree of the next term to return. */
      private int i = 0;

      /*-
       * RI: - 0 <= i <= degree() + 1
       * AF: - if i == degree() + 1, there are no more terms to return;
       *     - else the next termi is coeffs[j] * x^j where j is the smallest integer
       *       such that j >= i e coeffs[j] != 0.
       */

      @Override
      public boolean hasNext() {
        if (degree() == 0 && coefficients[0] == 0) return false;
        return i <= degree();
      }

      @Override
      public Term next() {
        if (!hasNext()) throw new NoSuchElementException();
        while (coefficients[i] == 0) i++;
        final int j = i++;
        return new Poly.Term(coefficients[j], j);
      }
    };
  }
}
