package com.johan.pokedex.utilities;

import android.app.Activity;
import android.content.Intent;

import com.johan.pokedex.modules.detail.PokemonDetailActivity;

/**
 * Created by JohanFabiel on 18/12/2015.
 */
public class IntentUtils {

  public static final String KEY_VALUE = "key_value";

  public static void startPokemonDetail(Activity activity, String code){
    Intent intent = new Intent(activity, PokemonDetailActivity.class);
    intent.putExtra(KEY_VALUE, code);
    activity.startActivity(intent);
  }
}
