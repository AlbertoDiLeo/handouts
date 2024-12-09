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

package it.unimi.di.prog2.h18.refactored;

import java.util.Scanner;

/** A class to test {@link IntSet} and {@link OrderedIntSet}. */
public class IntSetsClient {

  /** . */
  private IntSetsClient() {}

  /**
   * Tests some methods of {@link IntSet}, {@link OrderedIntSet} and {@link PreallocatedIntSet}.
   *
   * <p>This method reads integers from standard input and inserts them into a set, then it emits
   * the set size and elements in the standard output. The first parameter tells which
   * implementation to use.
   *
   * @param args if the set should be ordered.
   */
  public static void main(String[] args) {
    if (args.length != 1)
      throw new IllegalArgumentException("There must one command line argument.");
    AbstractIntSet set =
        switch (args[0]) {
          case "i" -> new IntSet();
          case "o" -> new OrderedIntSet();
          case "p" -> new PreallocatedIntSet();
          default ->
              throw new IllegalArgumentException(
                  "Unknown set type, must be one of 'i', 'o', or 'p'.");
        };
    try (Scanner sc = new Scanner(System.in)) {
      while (sc.hasNextInt()) set.insert(sc.nextInt());
    }
    System.out.println("Size: " + set.size());
    for (int x : set) System.out.println(x);
  }
}
