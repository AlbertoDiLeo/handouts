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

import java.util.Scanner;

/**
 * Vedi <a
 * href="https://github.com/mapio/labprog/blob/master/esercizi/somma_strana/Testo.md">testo</a>, ma
 * leggendo gli addendi dal flusso di ingresso.
 */
public class SommaStrana {

  /** . */
  private SommaStrana() {}
  // private SommaStrana() perchè non voglio che venga creata un'istanza di questa classe
  // non posso creare oggetti di questa classe perchè il costruttore è privato


  // Per memorizzare un elenco di interi si può usare list<Integer>, grazie
  // all'boxing automatico https://dev.java/learn/numbers-strings/autoboxing/
  // se la lista è stata dichiarata come
  //
  // List<Integer> interi = new ArrayList<>(); => mutabile 
  // => StringBuilder è mutabile
  //
  // sono legittime espressioni del tipo
  //
  // interi.add(3); autoboxing => int 3 diventa Integer
  // int y = interi.get(0); unboxing => Integer diventa int
  //
  // dove vengono messi e prelevati dalla lista degli int, non degli Integer.

  public static void main(String[] args) {
    String numero1;
    String numero2;
    try (Scanner s = new Scanner(System.in)) {
      numero1 = s.nextLine();
      numero2 = s.nextLine();
    }
    int maxLength = Math.max(numero1.length(), numero2.length());
    numero1 = allineaNumeri(numero1, maxLength);
    numero2 = allineaNumeri(numero2, maxLength);
        
    StringBuilder risultato = new StringBuilder(); // ho preferito usare StringBuilder nonostante il prof abbia detto di usare una lista di interi

    int riporto = 0;
    for (int i = maxLength-1; i >= 0; i--) {
      int cifraNum1 = numero1.charAt(i) - '0'; // '0' è il carattere 0, quindi sottraendo '0' si ottiene il valore numerico
      int cifraNum2 = numero2.charAt(i) - '0';
      int somma = cifraNum1 + cifraNum2 + riporto;
      if (somma > 9) {
        risultato.append(9 - (somma % 10));
        riporto = 1;
      } else {
        risultato.append(somma);
        riporto = 0;
      }
    }
    if (riporto == 1) {
      risultato.append(1);
    }
    System.out.println(risultato.reverse());

  }

  private static String allineaNumeri(String input, int length) {
    StringBuilder sb = new StringBuilder();
    while (sb.length() + input.length() < length) { // sb.length() rappresenta il numero di zeri da aggiungere. input.length() è la lunghezza del numero
        sb.append('0'); // creo una stringa di zeri
    }
    sb.append(input); // appendo alla stringa di zeri il numero
    return sb.toString(); // toString() restituisce la stringa
  }

  /* VERSIONE CON LISTA DI INTERI
   public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Lettura dei numeri in input
        String num1 = scanner.next();
        String num2 = scanner.next();
        
        // Allineamento delle stringhe
        int maxLength = Math.max(num1.length(), num2.length());
        num1 = padLeftZeros(num1, maxLength);
        num2 = padLeftZeros(num2, maxLength);
        
        List<Integer> result = new ArrayList<>(); // QUI USO UNA LISTA DI INTERI
        int carry = 0;
        
        // Calcolo della strana somma usando una lista
        for (int i = maxLength - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';
            int digit2 = num2.charAt(i) - '0';
            
            int sum = digit1 + digit2 + carry;
            if (sum >= 10) {
                carry = 1;
                result.add(9 - (sum % 10));  // Aggiungi cifra modificata
            } else {
                carry = 0;
                result.add(sum);  // Aggiungi cifra normale
            }
        }
        
        // Gestione del riporto finale
        if (carry > 0) {
            result.add(carry);
        }

        // Invertire la lista per stampare il risultato corretto
        Collections.reverse(result); // IMPORTANTE => uso Collections perchè List non ha il metodo reverse
        
        // Stampa del risultato
        result.forEach(System.out::print); // Stampo il risultato senza segni e senza spazi
    }
  */

  
/* 

  //MIA SOLUZIONE => metodo allineaNumeri non molto efficiente
                  => insert è più costosa di append perchè sposta tutti gli elementi di una posizione a destra, può essere molto svantaggioso per grandi numeri
   public static void main(String[] args) {
    String numero1;
    String numero2;
    try (Scanner s = new Scanner(System.in)) {
      numero1 = s.nextLine();
      numero2 = s.nextLine();
    }
    String[] allineati = allineaNumeri(numero1, numero2);
    numero1 = allineati[0];
    numero2 = allineati[1];

    StringBuilder risultato = new StringBuilder();

    int riporto = 0;
    for (int i = numero1.length()-1; i >= 0; i--) {
      int cifraNum1 = Character.getNumericValue(numero1.charAt(i));
      int cifraNum2 = Character.getNumericValue(numero2.charAt(i));
      int somma = cifraNum1 + cifraNum2 + riporto;
      if (somma > 9) {
        int risultatoParziale = 9 - (somma % 10);
        risultato.insert(0, risultatoParziale);
        riporto = 1;
      } else {
        risultato.insert(0, somma);
        riporto = 0;
      }
    
    }
    if (riporto == 1) {
      risultato.insert(0, 1);
    }
    System.out.println(risultato);


  }

  public static String[] allineaNumeri(String numero1, String numero2) {
    int lunghezza1 = numero1.length();
    int lunghezza2 = numero2.length();

    int differenza = Math.abs(lunghezza1 - lunghezza2);

    if (lunghezza1 < lunghezza2) {
        numero1 = "0".repeat(differenza) + numero1;
    } else if (lunghezza2 < lunghezza1) {
        numero2 = "0".repeat(differenza) + numero2;
    }

    return new String[]{numero1, numero2}; 
  }

*/
}
