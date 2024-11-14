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

package it.unimi.di.prog2.e03;

/**
 * Vedi <a
 * href="https://github.com/mapio/labprog/blob/master/esercizi/genera_quadrato_magico/Testo.md">testo</a>,
 */
public class GeneraQuadratoMagico {

  /** . */
  private GeneraQuadratoMagico() {}

  public static void main(String[] args) {
    final int N = Integer.parseInt(args[0]);
    if (N % 2 == 0) {
      System.out.println("Il quadrato magico è definito solo per N dispari");
      return;
    }
    if (N > 100) {
      System.out.println("N deve essere minore di 100");
      return;
    }
    int[][] quadratoMagico = new int[N][N];
    int numero = 1;
    int riga = 0;
    int colonna = N / 2;
    quadratoMagico[riga][colonna] = numero;

    for (numero = 2; numero <= N * N; numero++) {
      int nuovaRiga = (riga - 1 + N) % N;
      int nuovaColonna = (colonna + 1) % N;
      if (quadratoMagico[nuovaRiga][nuovaColonna] == 0) {
        riga = nuovaRiga;
        colonna = nuovaColonna;
      } else {
        riga = (riga + 1) % N;
      }
      quadratoMagico[riga][colonna] = numero;
    }
    
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            System.out.print(quadratoMagico[i][j] + " ");
        }
        System.out.println();
    }
    
  }

}
