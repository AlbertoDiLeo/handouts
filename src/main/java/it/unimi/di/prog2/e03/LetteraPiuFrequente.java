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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Vedi <a
 * href="https://github.com/mapio/labprog/blob/master/esercizi/lettera_piu_frequente/Testo.md">testo</a>,
 */
public class LetteraPiuFrequente {

  /** . */
  private LetteraPiuFrequente() {}
  // private LetteraPiùFrequente() perchè non voglio che venga creata un'istanza di questa classe
  // non posso creare oggetti di questa classe perchè il costruttore è privato

  public static void main(String[] args) {
        List<String> parole = new ArrayList<>();

    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNext()) { // finchè c'è un'altra parola
        final String parola = s.nextLine();
        parole.add(parola);
      }
    }

    int[] frequenze = new int[26];
    for (String parola : parole) { 
      for (char c : parola.toCharArray()) { // trasformo la stringa in un array di caratteri
        if (Character.isLetter(c)) {
          frequenze[Character.toLowerCase(c) - 'a']++; // trasformo il carattere in minuscolo e sottraggo il valore ASCII di 'a' per ottenere l'indice dell'array
        }
      }
    }
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < frequenze.length; i++) {
      if (frequenze[i] > max) {
        max = frequenze[i];
      }
    }
    System.out.println(max);

    
  }

}