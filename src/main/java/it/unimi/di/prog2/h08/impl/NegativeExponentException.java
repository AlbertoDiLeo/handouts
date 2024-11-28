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

package it.unimi.di.prog2.h08.impl;

/**
 * Thrown when an application attempts to use a negative exponent in a {@link
 * it.unimi.di.prog2.h08.Poly}.
 */
public class NegativeExponentException extends IllegalArgumentException {
  /*
   * La classe NegativeExponentException è un'eccezione personalizzata che viene lanciata quando un'applicazione tenta di utilizzare un esponente negativo in un oggetto della classe Poly (presumibilmente una classe che rappresenta un polinomio).
   * Estendendo IllegalArgumentException, questa eccezione indica che un metodo ha ricevuto un argomento illegale o inappropriato, in questo caso, un esponente negativo.
   */

  /** THe serial version. */
  static final long serialVersionUID = 1L;

  /** Builds a new exception. */
  public NegativeExponentException() {
    super();
  }

  /**
   * Builds an exception with a given message.
   *
   * @param message the message.
   */
  public NegativeExponentException(String message) {
    super(message);
  }

  /**
   * Builds an exception with a given message and cause.
   *
   * @param message the message.
   * @param cause the cause.
   */
  public NegativeExponentException(String message, Throwable cause) {
    super(message, cause);
  }
}
