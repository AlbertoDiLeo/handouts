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

import java.util.Scanner;

/** Esercizio 3.1 di PDJ. 
 * "Calcolare il massimo comune divisore tramite sottrazioni ripetute (vedi Figura 2.1 nel Capitolo 2) 
 * non è molto efficiente. Reimplementa la funzione gcd utilizzando la divisione al suo posto."*/

/*    Figura 2.1
          public class Num {
            // Classe che fornisce utili routine numeriche
            
            public static int gcd(int n, int d) {
                // REQUIRES: n e d devono essere maggiori di zero
                // Il massimo comune divisore viene calcolato tramite sottrazioni ripetute
                while (n != d) {
                    if (n > d) {
                        n = n - d;
                    } else {
                        d = d - n;
                    }
                }
                return n;
            }

            public static boolean isPrime(int p) {
                // L'implementazione va qui
            }
        }

 */
public class GcdClient {
    // OVERVIEW: Classe che calcola il massimo comune divisore (GCD) di due numeri interi 
    // utilizzando l'algoritmo Euclideo basato sulla divisione.

  /** . */
  private GcdClient() {}
  // private GcdClient() perchè non voglio che venga creata un'istanza di questa classe
  // non posso creare oggetti di questa classe perchè il costruttore è privato

    /**
     * Calcola il massimo comune divisore di due numeri interi.
     *
     * @param n il primo numero intero
     * @param d il secondo numero intero
     * @return il massimo comune divisore di {@code n} e {@code d}
     */

  public static int gcd(int n, int d) {
    // REQUIRES: n e d devono essere maggiori di zero
    // EFFECTS: Restituisce il massimo comune divisore di n e d
    while (d != 0) {
      int r = n % d;
      n = d;
      d = r;
    }
    return n;
  }

  // Aggiunga qui un main che invochi il metodo gcd (che può sviluppare in
  // questa o altra classe) descritto dall'esercizio 3.1 di PDJ.

  // Il main legge dal flusso di ingresso coppie di numeri ed emette nel flusso
  // d'uscita il loro gcd.

    /**
     * Metodo client per il calcolo del massimo comune divisore.
     *
     * <p>Legge coppie di numeri interi dal flusso di ingresso e ne emette il massimo comune divisore
     * nel flusso d'uscita.
     *
     * @param args non usato.
     */
    

    public static void main(String[] args) {
        // REQUIRES: Il flusso di ingresso deve contenere un numero pari di interi.
        // MODIFIES: System.in, System.out
        // EFFECTS: Legge coppie di numeri interi dal flusso di ingresso e stampa il loro GCD.
        try (Scanner s = new Scanner(System.in)) {
            while (s.hasNextInt()) {
                int n = s.nextInt();
                if (!s.hasNextInt()) {
                    System.err.println("Numero dispari di input. Inserisci coppie di numeri.");
                    return;
                }
                int d = s.nextInt();
                System.out.println(gcd(n, d));
            }
        }
    }

}
