package com.johan.pokedex.utilities;

import com.johan.pokedex.api.factory.RequestConstants;

/**
 * Created by JohanFabiel on 13/12/2015.
 */
public class AppUtils {

  public static int getPageOffset(int page){
    return page * RequestConstants.PAGE_LIMIT;
  }
}
