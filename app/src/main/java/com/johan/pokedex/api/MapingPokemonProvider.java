package com.johan.pokedex.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.johan.pokedex.api.factory.PokemonUriFactory;
import com.johan.pokedex.api.request.RequestGet;
import com.johan.pokedex.domain.Pokemon;
import com.johan.pokedex.utilities.AppUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JohanFabiel on 13/12/2015.
 */
public class MapingPokemonProvider {

  /**
   * retrieve the Pokemon list according to the page number
   * @param page
   * @return
   * @throws IOException
   */
  public List<Pokemon> getPokemonPage(int page) throws IOException {
    RequestGet requestGet = new RequestGet();
    String response = requestGet.doGetRequest(PokemonUriFactory.sharedInstance().getPokedexListUrl(AppUtils.getPageOffset(page)));
    List<Pokemon> pokemonList = new ArrayList<>();
    pokemonList = new Gson().fromJson(response, new TypeToken<List<Pokemon>>(){}.getType());
    return pokemonList;
  }

  /**
   * retrieve the sprite image of a certain pokemon
   * @param spriteDetail
   * @return
   * @throws IOException
   */
  public String getPokemonSpriteDetail(String spriteDetail) throws IOException {
    RequestGet requestGet = new RequestGet();
    String response = requestGet.doGetRequest(PokemonUriFactory.sharedInstance().getPokemonSprite(spriteDetail));
    return response;
  }
}
