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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Esercizio 4.4 di PDJ. */
public class CombineClient {
  // OVERVIEW: La classe CombineClient implementa la combinazione di due array di interi.

  /** . */
  private CombineClient() {}
  // private CombineClient() perchè non voglio che venga creata un'istanza di questa classe
  // // non posso creare oggetti di questa classe perchè il costruttore è privato
 

  /**
   * Decodifica una stringa contenente interi separati da spazi.
   * 
   * REQUIRES: La stringa in ingresso non può essere null e deve contenere interi separati da spazi
   * EFFECTS: Restituisce un array contenente gli interi presenti nella stringa.
   *
   * @param string la stringa in ingresso, non può essere {@code null} e deve contenere interi
   *     separati da spazi.
   * @return gli interi contenuti nella stringa.
   */
  public static int[] parseInts(String string) {
    List<Integer> list = new ArrayList<>();
    try (Scanner sl = new Scanner(string)) {
      while (sl.hasNextInt()) list.add(sl.nextInt());
    }
    int[] result = new int[list.size()];
    for (int i = 0; i < list.size(); i++) result[i] = list.get(i);
    return result;
  }

  //moltiplica ciascun elemento di a per la somma degli elementi di b; 
  //ad esempio, se a = [1, 2, 3] e b = [4, 5], allora al ritorno a = [9, 18, 27]. 
  //Cosa dovrebbe fare questa procedura se a o b sono nulli o vuoti? 
  //Fornisci una specifica per combine che risponda a queste domande e 
  //spiega perché la tua specifica è buona.

  /**
   * Moltiplica ciascun elemento di {@code a} per la somma degli elementi di {@code b}.
   * 
   * REQUIRES: {@code a} e {@code b} non possono essere nulli e devono contenere almeno un elemento.
   * MODIFIES: {@code a}
   * EFFECTS: Moltiplica ciascun elemento di {@code a} per la somma degli elementi di {@code b}.
   * 
   * // no eccezioni perchè sto assumendo che a e b non siano nulli e non vuoti (clausola REQUIRES)
   * @param a l'array di interi da moltiplicare
   * @param b l'array di interi di cui calcolare la somma
   * 
   */
  public static void combine(int[] a, int[] b) {
    int sumB = 0;
    for (int value : b) sumB += value;
    for (int i = 0; i < a.length; i++) a[i] *= sumB;
  }
    

  // Il main di questa classe legge due righe dal flusso di ingresso ciascuna
  // delle quali contiene gli interi (separati da spazi) di uno dei due array da
  // combinare e ne emette il risultato della combinazione (separando gli interi
  // uno per linea). Può avvalersi della funzione precedente per decodificare
  // ciascuna delle due linee in ingresso.

  /**
   * Legge due righe dal flusso di ingresso ciascuna delle quali contiene gli interi (separati da
   * spazi) di uno dei due array da combinare e ne emette il risultato della combinazione (separando
   * gli interi uno per linea).
   * 
   * REQUIRE: Il flusso di ingresso deve contenere due righe, ciascuna delle quali contiene interi
   * MODIFIES: Il flusso di uscita
   * EFFETS: Legge due righe dal flusso di ingresso ciascuna delle quali contiene gli interi
   *    (separati da spazi) di uno dei due array da combinare e ne emette il risultato della
   *   combinazione (separando gli interi uno per linea).
   * @param args gli argomenti della riga di comando, non utilizzati
   */

  public static void main(String[] args) {
    try (Scanner s = new Scanner(System.in)) {
      int[] a = parseInts(s.nextLine());
      int[] b = parseInts(s.nextLine());
      combine(a, b);
      for (int value : a) System.out.println(value);
    }
  }

}
