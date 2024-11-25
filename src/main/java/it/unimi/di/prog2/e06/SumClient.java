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

package it.unimi.di.prog2.e06;

import java.util.Scanner;

/** Esercizio 4.3 di PDJ. */
/*
 * Scrivere Una specifica per una procedura che calcola la somma degli elementi in un array di interi 
 * potrebbe richiedere un array non vuoto, restituire 0 se l'array è vuoto, 
 * oppure lanciare un'eccezione se l'array è vuoto. Discuti quale alternativa è la migliore 
 * e fornisci la specifica per la procedura.
 * 
 * => La specifica che richiede di restituire 0 se l'array è vuoto è la migliore, perchè non si deve 
 * gestire l'eccezione, ma si può gestire il caso in cui l'array è vuoto direttamente nel codice.
 * Gestione naturale degli array vuoti: Restituire 0 è intuitivo e coerente con il concetto matematico di somma.
 * Conformità alle buone pratiche: Evita l'uso improprio delle eccezioni, che dovrebbero rappresentare errori imprevisti e gravi, non condizioni attese come un array vuoto.
 * Efficienza: Non c'è overhead per il lancio e la gestione delle eccezioni.
 * 
 */
public class SumClient {

  /** . */
  private SumClient() {}
  // private SumClient() perchè non voglio che venga creata un'istanza di questa classe


  /**
   * calcola la somma degli elementi dell'array a senza usare eccezioni
   * 
   * REQUIRES: a deve essere un array di interi e non nullo
   * EFFECTS: restituisce la somma degli elementi dell'array a
   * 
   * @param a array di interi
   * @return la somma degli elementi dell'array a
   * 
   */

  public static int sum(int[] a) {
    if (a.length == 0) {
      return 0;
    }
    int sum = 0;
    for (int i = 0; i < a.length; i++) {
      sum += a[i];
    }
    return sum;
  }



  /**
   * calcola la somma degli elementi dell'array a usando eccezioni
   * 
   * REQUIRES: a deve essere un array di interi e non nullo
   * EFFECTS: restituisce la somma degli elementi dell'array a
   * 
   * @param a array di interi
   * @return la somma degli elementi dell'array a
   * @throws IllegalArgumentException se l'array è vuoto
   */


  public static int sumWithException(int[] a) throws IllegalArgumentException {
    if (a.length == 0) {
      throw new IllegalArgumentException();
    }
    int sum = 0;
    for (int i = 0; i < a.length; i++) {
      sum += a[i];
    }
    return sum;
  }

  // Il main di questa classe legge dal flusso di ingresso una sequenza di al
  // più 100 interi e ne emette la somma nel flusso d'uscita.

  /**
   * Legge dal flusso di ingresso una sequenza di al più 100 interi e ne emette la somma nel flusso
   * d'uscita.
   * 
   * REQUIRES: la sequenza di interi in ingresso non può essere null
   * MODIFIES: il flusso di uscita
   * EFFECTS: legge dal flusso di ingresso una sequenza di al più 100 interi e ne emette la somma nel
   * 
   * @param args gli argomenti da riga di comando, non utilizzati
   */
  public static void main(String[] args) {
    int[] a = new int[100];
    //int[] b = new int[0];
    try (Scanner s = new Scanner(System.in)) {
      for (int i = 0; i < a.length; i++) {
        if (s.hasNextInt()) {
          int n = s.nextInt();
          a[i] = n;
        }
      }
    }
    System.out.println(sum(a));
    //System.out.println(sumWithException(a));
  }

}
