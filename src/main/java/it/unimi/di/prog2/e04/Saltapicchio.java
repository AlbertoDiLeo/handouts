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

package it.unimi.di.prog2.e04;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Vedi <a
 * href="https://github.com/mapio/labprog/blob/master/esercizi/saltapicchio/Testo.md">testo</a>, ma
 * senza il vincolo sul valore massimo per `N`.
 */
public class Saltapicchio {

  /** . */
  private Saltapicchio() {}


  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    if (N < 1 || N > 1024) {
      System.err.println("N deve essere compreso tra 1 e 1024");
      return;
    }

    int[] sequenza = new int[N];

    try (Scanner s = new Scanner(System.in)) {
      for (int i = 0; i < N; i++) {
        if (s.hasNextInt()) { // se non ci sono più interi, non leggo
          sequenza[i] = s.nextInt(); // leggo un intero. assegno a sequenza[i] il valore letto
        }
      }
    }

    if (isSaltapicchio(sequenza)) {
      System.out.println("saltapicchio");
    }
    
  }


  private static boolean isSaltapicchio(int[] sequenza) {
    int N = sequenza.length;
    if (N == 1) {
      return true;
    }
    HashSet<Integer> differenze = new HashSet<>(); // per controllare che le differenze siano tutte diverse (senza duplicati)
    for (int i = 1; i < N; i++) {
      int diff = Math.abs(sequenza[i] - sequenza[i - 1]);
      if (diff < 1 || diff > N-1) {
        return false;
      }
      differenze.add(diff);
    }
    return differenze.size() == N - 1;
  }
}
