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

package it.unimi.di.prog2.s19;

import static it.unimi.di.prog2.s19.IteratorUtils.join;

import java.util.Scanner;

/** A class to test {@link OrderedIntList}. */
public class OrderedIntListClient {

  /** . */
  private OrderedIntListClient() {}

  /**
   * Reads a sequence of integers from standard input and inserts them into an ordered list; then
   * prints the list in ascending and descending order.
   *
   * @param args not used.
   */
  public static void main(String[] args) {
    OrderedIntList list = new OrderedIntList();
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNextInt()) list.add(s.nextInt());
    }
    System.out.println(join(list.smallToBig(), ", "));
    System.out.println(join(list.bigToSmall(), ", "));
  }
}
