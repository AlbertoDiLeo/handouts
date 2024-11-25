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

package it.unimi.di.prog2.e05;

/** Esercizio 3.3 di PDJ. 
 * Specifica e implementa una procedura isPrime che determina se un numero intero è primo.
*/

public class IsPrimeClient {
  // OVERVIEW: Classe che determina se un numero intero è primo.

  /** . */
  private IsPrimeClient() {}

  public static boolean isPrime(int p) {
    // REQUIRES: p deve essere un intero positivo
    // EFFECTS: restituisce true se p è primo, false altrimenti
    if (p < 2) return false;
    for (int i = 2; i <= Math.sqrt(p); i++) {
      if (p % i == 0) return false;
    }
    return true;
  }

  // Aggiunga qui un main che invochi il metodo isPrime (che può sviluppare in
  // questa o altra classe) descritto dall'esercizio 3.3 di PDJ.

  // Il main riceve un intero come parametro sulla linea di comando ed emette
  // "true" nel flusso d'uscita se e solo se esso è primo.

  // REQUIRE: args.length == 1
  // MODIFIES: System.out
  // EFFECTS: stampa "true" se l'argomento è un numero primo, "false" altrimenti

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Usage: java IsPrimeClient <number>");
      System.exit(1);
    }
    int n = Integer.parseInt(args[0]);
    System.out.println(isPrime(n));
  }

}
