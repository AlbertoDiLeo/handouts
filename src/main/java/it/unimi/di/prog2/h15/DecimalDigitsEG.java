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

package it.unimi.di.prog2.h15;

import java.util.Iterator;

/**
 * A class representing decimal digits of a long, endowed with a non zero digits iterator, based on
 * the <strong>external</strong> {@link NonZeroDigitsGenerator} class.
 */
public class DecimalDigitsEG {

  /** The number whose digits are represented by this class. */
  private final long number;

  /**
   * Creates a new instance representing the digits of the given number.
   *
   * @param number the number.
   */
  public DecimalDigitsEG(final long number) {
    this.number = number;
  }

  /**
   * Returns the digit corresponding to the given power of 10.
   *
   * @param power the power.
   * @return the corresponding digit.
   */
  public int digit(final int power) {
    if (power < 0) throw new IllegalArgumentException("The power must be positive.");
    long digit = number;
    for (int i = 0; i < power; i++) digit /= 10;
    return (int) (digit % 10);
  }

  /**
   * Returns a <em>generator</em> on from the least significant to the most significant non zero
   * digits.
   *
   * @return the generator.
   */
  // l'iteratore o generatore è implementato come una classe separata esterna. Questo approccio permette di riutilizzare l'iteratore in più contesti e classi.
  public Iterator<Integer> nonZeroDigits() {
    // we expose the representation to an external class 
    // traduzione: esponiamo la rappresentazione a una classe esterna
    return new NonZeroDigitsGenerator(number);
  }

  // DecimalDigitsEG: Fornisce una funzionalità specifica per iterare sulle cifre non zero di un numero long. Non rappresenta una collezione iterabile generica, quindi implementare Iterable non è necessario.

  // DecimalDigitsEG: Non implementa Iterable<Integer>, quindi non è utilizzabile direttamente nei cicli for-each. 
  // Fornisce invece un metodo specifico nonZeroDigits() per ottenere un iteratore.

  // Implementare Iterable non è necessario in questo caso perché la classe non rappresenta una collezione iterabile generica, ma fornisce una funzionalità specifica.
}
