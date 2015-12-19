package com.johan.pokedex.domain;

import android.content.Context;
import android.util.Log;

import com.johan.pokedex.domain.entities.Pokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by JohanFabiel on 18/12/2015.
 */
public class RealmDBController {

  private static String LOG_TAG = RealmDBController.class.getSimpleName();
  private static Context mContext;
  private static RealmDBController mInstance;
  private static Realm realm;
  private static final String REALM_VERSION = "1.0";

  public RealmDBController(Context context) {
    mContext = context;
    mInstance = this;
    realm = Realm.getInstance(mContext);
  }

  public static synchronized RealmDBController getInstance(Context context) {
    return (mInstance == null) ? new RealmDBController(context) : mInstance;
  }

  /**
   * map the string results in realm
   * @param stringJsonArray
   * @param eClass
   */
  public static void mapRealmObjByJsonArray(String stringJsonArray, Class<? extends io.realm.RealmObject> eClass) {
    if (stringJsonArray == null) {
      return;
    }
    realm.beginTransaction();
    try {
      JSONArray allObject = new JSONArray(stringJsonArray);
      realm.createOrUpdateAllFromJson(eClass, allObject);
    } catch (JSONException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "json error exception", e);
    }
    realm.commitTransaction();
  }

  /**
   * map object string result in realm
   * @param stringJsonArray
   * @param eClass
   */
  public static void mapRealmObjByObject(String stringJsonArray, Class<? extends io.realm.RealmObject> eClass) {
    if (stringJsonArray == null) {
      return;
    }
    realm.beginTransaction();
    realm.createOrUpdateObjectFromJson(eClass, stringJsonArray);
    realm.commitTransaction();
  }

  /**
   * retrieve all available pokemon
   * @return
   */
  public static RealmResults<Pokemon> getPokemonList(){
    return realm.where(Pokemon.class).findAll();
  }

  /**
   * retrieve the list of all favorite pokemons
   * @return
   */
  public static RealmResults<Pokemon> getFavoritePokemonList(){
    return realm.where(Pokemon.class).equalTo("isFavorite", true).findAll();
  }

  /**
   * Get a specific pokemon data
   * @param code
   * @return
   */
  public static Pokemon loadPokemonByCode(String code){
    return realm.where(Pokemon.class).equalTo("resource_uri", code).findFirst();
  }

  /**
   * set favorite status to specific pokemon
   * @param code
   * @param favorite
   * @return
   */
  public static Pokemon updatePokemonFavoriteStatus(String code, boolean favorite){
    Pokemon pokemon = loadPokemonByCode(code);
    realm.beginTransaction();
    pokemon.setIsFavorite(favorite);
    realm.commitTransaction();
    return pokemon;
  }
}
