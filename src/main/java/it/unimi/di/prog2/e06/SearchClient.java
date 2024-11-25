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

/** Esercizio 4.2 di PDJ. */

/* Implementa la ricerca come specificato nella Figura 4.1  in due modi: 
  utilizzando cicli for e utilizzando cicli while (true) che vengono terminati 
  quando l'accesso all'array solleva un'eccezione IndexOutOfBoundsException. 
  Quale implementazione è migliore? Discuti.
  => searchWhileTrue è meno efficiente: il lancio e la gestione delle eccezioni hanno un costo significativo.

Some specifications with exceptions
public static int fact (int n) throws NonPositiveException
  // effects: If n is non-positive, throws NonPositiveException, else
  // returns the factorial of n.

public static int search (int[ ] a, int x) throws NullPointerException, NotFoundException
  // requires: a is sorted
  // effects: If a is null throws NullPointerException; else if x is not
  // in a, throws NotFoundException; else returns i such that a[i] = x.
*/

public class SearchClient {
  // OVERVIEW: La classe SearchClient implementa la ricerca di un intero in un array di interi ordinato in ordine crescente.

  /** . */
  private SearchClient() {}
  // costruttore privato perchè non voglio che venga creata un'istanza di questa classe

  /**
   * Cerca un intero in un array di interi ordinato in ordine crescente usando un ciclo for.
   * 
   * REQUIRES: a è ordinato in ordine crescente
   * EFFECTS: Restituisce la posizione dell'intero specificato nell'array, se presente
   *  altrimenti restituisce -1
   * 
   * @param a l'array di interi ordinato in ordine crescente
   * @param x l'intero da cercare
   * @return la posizione dell'intero specificato nell'array, o -1 se non presente
   * @throws NullPointerException se {@code a} è {@code null}
   * @throws IllegalArgumentException se {@code a} è vuoto
   * 
   */


  public static int searchFor(int[] a, int x) throws NullPointerException, IllegalArgumentException {
    if (a == null) throw new NullPointerException();
    if (a.length == 0) throw new IllegalArgumentException();
    int left = 0;
    int right = a.length - 1;

    for (; left <= right;) { // Controllo esplicito dei limiti
        int mid = left + (right - left) / 2;
        if (a[mid] == x) {
          return mid; // Elemento trovato
        } else if (a[mid] < x) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
    }
    return -1; // Elemento non trovato
  }

  /**
   * Cerca un intero in un array di interi ordinato in ordine crescente usando un ciclo while (true).
   * 
   * REQUIRES: a è ordinato in ordine crescente
   * EFFECTS: Restituisce la posizione dell'intero specificato nell'array, se presente, 
   * altrimenti -1
   * 
   * @param a l'array di interi ordinato in ordine crescente
   * @param x l'intero da cercare
   * @return  la posizione dell'intero specificato nell'array, altrimenti -1
   * @throws NullPointerException se {@code a} è {@code null}
   * @throws IllegalArgumentException se {@code a} è vuoto
   * 
   */

  public static int searchWhileTrue(int[] a, int x) throws NullPointerException, IndexOutOfBoundsException {
    if (a == null) throw new NullPointerException();
    if (a.length == 0) throw new IllegalArgumentException();
    int left = 0;
    int right = a.length - 1;

    while (true) {
      try {
        int mid = left + (right - left) / 2;
        if (a[mid] == x) {
          return mid; // Elemento trovato
        } else if (a[mid] < x) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      } catch (IndexOutOfBoundsException e) {
        return -1; // Elemento non trovato
      }
      
    }
  }
    

  // Il main di questa classe legge dal flusso di ingresso una sequenza di
  // interi (separati da spazi) e, assumendo che sia ordinata in ordine
  // crescente, emette nel flusso d'uscita la posizione dell'intero specificato
  // sulla linea di comando (se presente nell'input), o -1 viceversa.

  /**
   * 
   * REQUIRE: Il flusso di ingresso deve contenere una sequenza di interi ordinata in ordine crescente
   * MODIFIES: Il flusso di uscita
   * EFFECTS: Legge dal flusso di ingresso una sequenza di interi (separati da spazi) e, assumendo che sia ordinata in
   * ordine crescente, emette nel flusso d'uscita la posizione dell'intero specificato sulla linea di comando (se presente
   * nell'input), o -1 viceversa.
   * 
   * @Override parseInts di CombineClient 
   * 
   * @param args gli argomenti della riga di comando, non utilizzati
   */

    public static void main(String[] args) {
      try (Scanner s = new Scanner(System.in)) {
        String line = s.nextLine();
        int[] array = CombineClient.parseInts(line);
        int value = Integer.parseInt(args[0]);
        System.out.println(searchFor(array, value));
        //System.out.println(searchWhileTrue(array, value));
      }
    }



}
