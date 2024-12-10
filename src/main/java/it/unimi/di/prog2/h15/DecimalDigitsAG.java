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
import java.util.NoSuchElementException;

/*
 * Differenze tra Iteratore Anonimo Classe Separata, classe interna e Named Static Generator:
  Iteratore Anonimo:
  Definito direttamente all'interno di un metodo.
  Utilizzato quando l'iterazione è specifica per un particolare metodo.
  Non riutilizzabile al di fuori del metodo.

  Classe Separata per l'Iteratore:
  Utilizzata quando l'iterazione è una funzionalità centrale della classe.
  Riutilizzabile in più metodi o contesti.

  Classe Interna per l'Iteratore:
  Un generatore come classe interna è una classe definita all'interno di un'altra classe. Può essere una classe interna non statica. 
  Questo approccio è utile quando l'iteratore deve accedere ai membri non statici della classe esterna (DecimalDigitsIG) (quindi campi e metodi) o 
  quando si desidera mantenere l'implementazione dell'iteratore strettamente legata alla classe principale.

  NSG (Named Static Generator):
  L'iteratore è implementato come una classe interna statica con un nome specifico.
  Non può accedere ai membri non statici della classe esterna.
  Utilizzato quando l'iteratore deve essere riutilizzato all'interno della stessa classe ma non deve accedere ai membri non statici della classe esterna.

 */

/**
 * A class representing decimal digits of a long, endowed with a non zero digits iterator, based on
 * an <strong>anonymous</strong> class.
 */
public class DecimalDigitsAG {

  /** The number whose digits are represented by this class. */
  private final long number;

  /**
   * Creates a new instance representing the digits of the given number.
   *
   * @param number the number.
   */
  public DecimalDigitsAG(final long number) {
    this.number = number;
  }

  /**
   * Returns a <em>generator</em> on from the least significant to the most significant non zero
   * digits.
   *
   * @return the generator.
   */
  // genratore anonimo
  public Iterator<Integer> nonZeroDigits() {
    // no need to pass any value, nor to define any named class

    // La classe DecimalDigitsAG fornisce un metodo nonZeroDigits che restituisce un iteratore anonimo per iterare sulle cifre non zero di un numero long. 
    // Non è necessario che la classe implementi Iterable perché il suo scopo principale non è quello di rappresentare una collezione iterabile, ma piuttosto di fornire un iteratore specifico per una sequenza di cifre.

    return new Iterator<Integer>() {
      // Un iteratore anonimo è un'implementazione dell'interfaccia Iterator che viene definita direttamente all'interno di un metodo, senza creare una classe separata per l'iteratore. 
      // Questo tipo di iteratore è spesso utilizzato quando l'iterazione è specifica per un particolare metodo e non è necessario riutilizzare l'iteratore altrove.

      /** The remaining digits to return (except possibly for the trailing zeroes). */
      private long remaining = number;

      @Override
      public boolean hasNext() {
        while (remaining != 0 && remaining % 10 == 0) remaining /= 10;
        return remaining != 0;
      }

      @Override
      public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        int digit = (int) (remaining % 10);
        remaining /= 10;
        return digit;
      }
    };
  }
}
