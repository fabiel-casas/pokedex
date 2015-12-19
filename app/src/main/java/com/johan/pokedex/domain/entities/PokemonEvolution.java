package com.johan.pokedex.domain.entities;

import io.realm.RealmObject;

/**
 * Created by JohanFabiel on 13/12/2015.
 */
public class PokemonEvolution extends RealmObject {

  private String level;
  private String method;
  private String resource_uri;
  private String to;

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getResource_uri() {
    return resource_uri;
  }

  public void setResource_uri(String resource_uri) {
    this.resource_uri = resource_uri;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }
}
