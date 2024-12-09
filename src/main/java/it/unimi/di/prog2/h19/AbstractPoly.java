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

/** A partial implementation of a {@link Poly}. */
public abstract class AbstractPoly implements Poly {

  /** The degree of the polynomial. */
  private final int degree;

  /*-
   * RI: degree >= 0
   * AF: concrete instances of this class will have degree
   *     corresponding to the value of the degree field.
   */

  /**
   * Initializes this to be the a polynomial with given degree.
   *
   * @param degree the degree.
   * @throws IllegalArgumentException if {@code n} is not positive.
   */
  protected AbstractPoly(final int degree) {
    if (degree < 0) throw new IllegalArgumentException();
    this.degree = degree;
  }

  @Override
  public int degree() {
    return degree;
  }

  /*-
   * This class provides the overriding of some Object's medhod that will not
   * fit the Poly interface.
   */

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof Poly)) return false;
    final Poly q = (Poly) other;
    if (degree != q.degree()) return false;
    final Iterator<Poly.Term> terms = iterator(), otherTerms = q.iterator();
    while (terms.hasNext() && otherTerms.hasNext())
      if (!terms.next().equals(otherTerms.next())) return false;
    return !(terms.hasNext() || otherTerms.hasNext());
  }

  @Override
  public int hashCode() {
    final Iterator<Poly.Term> terms = iterator();
    int result = 0;
    while (terms.hasNext()) result = 31 * result + terms.next().hashCode();
    return result;
  }

  @Override
  public String toString() {
    if (degree() == 0 && coefficient(0) == 0) return "Poly: 0";
    Iterator<Poly.Term> it = iterator();
    Term t = it.next();
    StringBuilder result = new StringBuilder(this.getClass().getSimpleName() + ": ");
    if (t.degree() == 0) result.append(t.coefficient());
    else {
      if (t.coefficient() < -1) result.append(t.coefficient());
      else if (t.coefficient() == -1) result.append("-");
      else if (t.coefficient() > 1) result.append(t.coefficient());
      result.append("x" + (t.degree() > 1 ? "^" + t.degree() : ""));
    }
    while (it.hasNext()) {
      t = it.next();
      if (t.coefficient() < -1) result.append(" - " + (-t.coefficient()));
      else if (t.coefficient() == -1) result.append(" - ");
      else if (t.coefficient() == 1) result.append(" + ");
      else result.append(" + " + t.coefficient());
      result.append("x" + (t.degree() > 1 ? "^" + t.degree() : ""));
    }
    return result.toString();
  }
}
