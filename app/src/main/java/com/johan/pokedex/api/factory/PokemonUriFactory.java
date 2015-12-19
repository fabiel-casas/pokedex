package com.johan.pokedex.api.factory;

import android.net.Uri;

/**
 * Created by Johan Fabiel on 13/12/2015.
 * this class has the function to create all url content to use in the api with method GET
 */
public class PokemonUriFactory {

  private static PokemonUriFactory mInstance;

  public static PokemonUriFactory sharedInstance(){
    if(mInstance == null){
      mInstance = new PokemonUriFactory();
    }
    return mInstance;
  }

  /**
   * get the url to access a pokedex list
   * @return
   */
  public String getPokemonListUrl(int offset){
    Uri.Builder uri = Uri.parse(RequestConstants.POKEMON_DOMAIN_NAME).buildUpon()
        .appendPath(RequestConstants.DOMAIN_TYPE_API)
        .appendPath(RequestConstants.API_VERSION_1)
        .appendPath(RequestConstants.POKEMON_LIST)
        .appendQueryParameter(RequestConstants.QUERY_LIMIT, "" + RequestConstants.PAGE_LIMIT)
        .appendQueryParameter(RequestConstants.QUERY_OFFSET, "" + offset);
    return uri.toString();
  }

  /**
   * get url to access a detail of a pokemon
   * @param uriDetail
   * @return
   */
  public String getPokemonDetail(String uriDetail){
    Uri.Builder uri = Uri.parse(RequestConstants.POKEMON_DOMAIN_NAME + uriDetail).buildUpon();
    return uri.toString();
  }

  /**
   * retrieve the url of a pokemon sprite
   * @param index
   * @return
   */
  public String getPokemonSprite(String index){
    Uri.Builder uri = Uri.parse(RequestConstants.POKEMON_DOMAIN_NAME).buildUpon()
        .appendPath(RequestConstants.DOMAIN_TYPE_API)
        .appendPath(RequestConstants.API_VERSION_1)
        .appendPath(RequestConstants.SPRITE_LIST)
        .appendPath(index);
    return uri.toString();
  }

  /**
   * retrieve the url of a pokemon description
   * @param uriDescription
   * @return
   */
  public String getPokemonDescription(String uriDescription){
    Uri.Builder uri = Uri.parse(RequestConstants.POKEMON_DOMAIN_NAME + uriDescription).buildUpon();
    return uri.toString();
  }

}
