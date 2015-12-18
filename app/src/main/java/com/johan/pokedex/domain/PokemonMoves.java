package com.johan.pokedex.domain;

/**
 * Created by JohanFabiel on 13/12/2015.
 */
public class PokemonMoves {

  protected String learn_type;
  protected String name;
  protected String level;
  protected String resource_uri;

  public String getLearn_type() {
    return learn_type;
  }

  public void setLearn_type(String learn_type) {
    this.learn_type = learn_type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getResource_uri() {
    return resource_uri;
  }

  public void setResource_uri(String resource_uri) {
    this.resource_uri = resource_uri;
  }
}
