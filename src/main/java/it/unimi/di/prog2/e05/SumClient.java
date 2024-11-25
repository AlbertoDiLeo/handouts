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

/** Esercizio 3.2 di PDJ. */
/*
 * Specifica e implementa un metodo con l'intestazione:
 * public static int sum(int[] a)
  che restituisce la somma degli elementi dell'array a.
 */
public class SumClient {
  // OVERVIEW: Classe che calcola la somma degli elementi di un array di interi.

  /** . */
  private SumClient() {}

  public static int sum(int[] a) {
    // REQUIRES: a deve essere un array di interi e non nullo
    // EFFECTS: restituisce la somma degli elementi dell'array a
    int sum = 0;
    for (int i = 0; i < a.length; i++) {
      sum += a[i];
    }
    return sum;
  }

  // Aggiunga qui un main che invochi il metodo sum (che può sviluppare in
  // questa o altra classe) descritto dall'esercizio 3.2 di PDJ.

  // Il main riceve un elenco di interi come parametri da linea di comando e
  // ne emette la somma nel flusso d'ingresso.

  // REQUIRES: Il flusso di ingresso deve contenere un elenco di numeri interi.
  // MODIFIES: System.out
  // EFFECTS: Legge un elenco di numeri interi dal flusso di ingresso e stampa la loro somma.
  public static void main(String[] args) {

    int[] a = new int[args.length]; // Converte i parametri della linea di comando in un array di interi
    for (int i = 0; i < args.length; i++) {
      a[i] = Integer.parseInt(args[i]);
    }

    System.out.println(sum(a));
  }

}
