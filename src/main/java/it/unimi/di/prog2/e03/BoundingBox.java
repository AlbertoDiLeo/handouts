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
 * href="https://github.com/mapio/labprog/blob/master/esercizi/bounding_box/Testo.md">testo</a>,
 */
public class BoundingBox {

  /** . */
  private BoundingBox() {} // non posso creare oggetti di questa classe perchè il costruttore è privato



  public static void main(String[] args) {
    List<String> lines = new ArrayList<>(); // creo una lista di stringhe e non un array di stringhe, perchè non so quanti elementi ci saranno

    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNext()) {
        final String linea = s.nextLine(); // con final dico che la variabile linea non può essere riassegnata e modificata
        lines.add(linea);
      }
    }

    int top = Integer.MAX_VALUE;
    int bottom = Integer.MIN_VALUE;
    int left = Integer.MAX_VALUE;
    int right = Integer.MIN_VALUE;
  
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
  
      if (line.contains("*")) {
        top = Math.min(top, i);
        bottom = Math.max(bottom, i);
  
        for (int j = 0; j < line.length(); j++) {
          if (line.charAt(j) == '*') {
            left = Math.min(left, j);
            right = Math.max(right, j);
          }
        }
      }
    }
  
    int height = bottom - top + 1; // +1 perchè se top e bottom sono uguali, l'altezza è 1
    int width = right - left + 1; // +1 perchè se left e right sono uguali, la larghezza è 1
  
    System.out.println(height + " " + width); 
  }

 
}
