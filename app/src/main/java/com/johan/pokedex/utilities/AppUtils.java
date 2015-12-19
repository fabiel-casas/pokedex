package com.johan.pokedex.utilities;

import android.view.View;

import com.johan.pokedex.api.factory.RequestConstants;

/**
 * Created by JohanFabiel on 13/12/2015.
 */
public class AppUtils {

  /**
   * retrieve the offset pagination value
   * @param page
   * @return
   */
  public static int getPageOffset(int page){
    return page * RequestConstants.PAGE_LIMIT;
  }

  /**
   * hide view in gone mode
   * @param views
   */
  public static void hideGoneViews(View... views){
    for(View view : views){
      view.setVisibility(View.GONE);
    }
  }

  /**
   * hide view in invisible mode
   * @param views
   */
  public static void hideInvisibleViews(View... views){
    for(View view : views){
      view.setVisibility(View.INVISIBLE);
    }
  }

  /**
   * show all views
   * @param views
   */
  public static void showViews(View... views){
    for(View view : views){
      view.setVisibility(View.VISIBLE);
    }
  }
}
