package com.johan.pokedex.domain.entities;

import io.realm.RealmObject;

/**
 * Created by JohanFabiel on 13/12/2015.
 */
public class PokemonAbilities extends RealmObject {
  private String name;
  private String resource_uri;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getResource_uri() {
    return resource_uri;
  }

  public void setResource_uri(String resource_uri) {
    this.resource_uri = resource_uri;
  }
}
