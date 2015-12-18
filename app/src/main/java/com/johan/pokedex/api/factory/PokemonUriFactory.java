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
  public String getPokedexListUrl(int offset){
    Uri.Builder uri = Uri.parse(RequestConstants.POKEMON_DOMAIN_NAME).buildUpon()
        .appendPath(RequestConstants.DOMAIN_TYPE_API)
        .appendPath(RequestConstants.API_VERSION_1)
        .appendPath(RequestConstants.POKEDEX_LIST)
        .appendQueryParameter(RequestConstants.QUERY_LIMIT, "" + RequestConstants.PAGE_LIMIT)
        .appendQueryParameter(RequestConstants.QUERY_OFFSET, "" + offset);
    return uri.toString();
  }

  /**
   * retrieve the url of a pokemon sprite
   * @param uriSprite
   * @return
   */
  public String getPokemonSprite(String uriSprite){
    Uri.Builder uri = Uri.parse(RequestConstants.POKEMON_DOMAIN_NAME + uriSprite).buildUpon();
    return uri.toString();
  }

}
