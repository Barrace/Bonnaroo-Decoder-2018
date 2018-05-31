package com.barrace.decoderbonnaroo2018;

import java.util.Arrays;
import java.util.List;

/**
 * Created by matthewshannon on 5/31/18.
 */
public class Bit {
  public final String msg;
  public final int number;
  public final char letter;
  public final char symbol;

  public static Bit from(String msg, char letter, int number, char symbol) {
    return new Bit(msg, letter, number, symbol);
  }

  private Bit(String msg, char letter, int number, char symbol) {
    this.letter = letter;
    this.number = number;
    this.symbol = symbol;
    this.msg = msg;
  }

  public static List<Bit> populateList() {
    return Arrays.asList(
      Bit.from(" ", ' ', 0, ' '),   // blank space
      Bit.from("A", 'H', 26, ')'),
      Bit.from("B", 'R', 6, '}'),
      Bit.from("C", 'V', 11, '$'),
      Bit.from("D", 'S', 4, '='),
      Bit.from("E", 'O', 13, ']'),
      Bit.from("F", 'A', 18, '('),
      Bit.from("G", 'J', 12, '\''), // string escape slash
      Bit.from("H", 'I', 19, ':'),
      Bit.from("I", 'M', 16, '^'),
      Bit.from("J", 'N', 10, '<'),
      Bit.from("K", 'C', 20, '{'),
      Bit.from("L", 'F', 5, '>'),
      Bit.from("M", 'D', 3, '!'),
      Bit.from("N", 'U', 15, '+'),
      Bit.from("O", 'W', 7, ';'),
      Bit.from("P", 'K', 25, '#'),
      Bit.from("Q", 'E', 2, '*'),
      Bit.from("R", 'B', 18, '€'),  // euro symbol?
      Bit.from("S", 'T', 17, '©'),  // copyright symbol
      Bit.from("T", 'G', 24, '%'),
      Bit.from("U", 'X', 9, '/'),
      Bit.from("V", 'Q', 8, '['),
      Bit.from("W", 'Y', 23, '&'),
      Bit.from("X", 'P', 14, '@'),
      Bit.from("Y", 'Z', 21, '~'),
      Bit.from("Z", 'L', 1, '-')
    );
  }


  public static String messageForNumber(List<Bit> bits, int number) {
   for (Bit bit : bits) if (number == bit.number) return bit.msg;
   return "";
  }

  public static String messageForLetter(List<Bit> bits, char letter) {
    for (Bit bit : bits) if (letter == bit.letter) return bit.msg;
    return "";
  }

  public static String messageForSymbol(List<Bit> bits, char symbol) {
    for (Bit bit : bits) if (symbol == bit.symbol) return bit.msg;
    return "";
  }

}
