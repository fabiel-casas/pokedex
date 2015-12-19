package com.johan.pokedex.api;

import com.johan.pokedex.api.factory.PokemonUriFactory;
import com.johan.pokedex.api.request.RequestGet;
import com.johan.pokedex.utilities.AppUtils;

import java.io.IOException;

/**
 * Created by JohanFabiel on 13/12/2015.
 */
public class MappingPokemonProvider {

  /**
   * retrieve the Pokemon list according to the page number
   * @param page
   * @return
   * @throws IOException
   */
  public String getPokemonPage(int page) throws IOException {
    RequestGet requestGet = new RequestGet();
    String response = requestGet.doGetRequest(PokemonUriFactory.sharedInstance().getPokemonListUrl(AppUtils.getPageOffset(page)));
    return response;
  }

  /**
   * retrieve detail info of a pokemon
   * @param uriDetail
   * @return
   * @throws IOException
   */
  public String getPokemonDetail(String uriDetail) throws IOException {
    RequestGet requestGet = new RequestGet();
    String response = requestGet.doGetRequest(PokemonUriFactory.sharedInstance().getPokemonDetail(uriDetail));
    return response;
  }

  /**
   * retrieve the description of a pokemon
   * @param pokemonDescription
   * @return
   * @throws IOException
   */
  public String getPokemonDescription(String pokemonDescription) throws IOException {
    RequestGet requestGet = new RequestGet();
    String response = requestGet.doGetRequest(PokemonUriFactory.sharedInstance().getPokemonDescription(pokemonDescription));
    return response;
  }

  /**
   * retrieve the sprite image of a certain pokemon
   * @param pokemonIndex
   * @return
   * @throws IOException
   */
  public String getPokemonSpriteDetail(String pokemonIndex) throws IOException {
    RequestGet requestGet = new RequestGet();
    String response = requestGet.doGetRequest(PokemonUriFactory.sharedInstance().getPokemonSprite(pokemonIndex));
    return response;
  }
}
