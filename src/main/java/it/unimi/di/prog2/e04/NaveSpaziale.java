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

/**
 * Vedi <a
 * href="https://github.com/mapio/labprog/blob/master/esercizi/nave_spaziale/Testo.md">testo</a>.
 */
public class NaveSpaziale {

  /** . */
  private NaveSpaziale() {}
  // private NaveSpaziale() perchè non voglio che venga creata un'istanza di questa classe
  // non posso creare oggetti di questa classe perchè il costruttore è privato

  // Se String[] args è il vettore che contiene gli argomenti sulla linea
  // di comando, potete convertire i primi due in numeri interi con le
  // dichiarazioni (e inizializzazioni) seguenti
  //
  // int from = Integer.parseInt(args[0]);
  // int to = Integer.parseInt(args[1]);
  //
  // non c'è bisogno di importare alcun package per poter usare Integer.


  public static void main(String[] args) {
    int a = Integer.parseInt(args[0]);
    int b = Integer.parseInt(args[1]);
    StringBuilder sequenza = new StringBuilder(); // per evitare di creare tante stringhe temporanee. creo un'unica stringa e ci appendo i caratteri
    while (b > a) {
      if (b % 2 == 1 || b < 4 * a || b % 4 != 0) {  
          sequenza.append("P");
          b --;
      } else {  
          sequenza.append("S");
          b /= 4;
      }
  }
    System.out.println(sequenza.reverse());
    
  }

}
