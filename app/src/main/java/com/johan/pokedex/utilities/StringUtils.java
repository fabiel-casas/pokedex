package com.johan.pokedex.utilities;

/**
 * Created by JohanFabiel on 18/12/2015.
 */
public class StringUtils {
  public static String capitalizeText(String text) {
    try {
      if (text.length() > 0) {
        text = String.valueOf(text.charAt(0)).toUpperCase() + text.substring(1).toLowerCase();
      }
    } catch (Exception e) {
    }
    return text;
  }
}
