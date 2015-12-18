package com.johan.pokedex.domain;

import java.util.List;

/**
 * Created by JohanFabiel on 13/12/2015.
 */
public class Pokemon {
  protected String name;
  protected String national_id;
  protected String created;
  protected String modified;
  protected List<PokemonAbilities> abilities;
  protected List<PokemonEvolution> evolutions;
  protected String description;
  protected List<PokemonMoves> moves;
  protected List<PokemonType> types;
  protected String catch_rate;
  protected String hp;
  protected String attack;
  protected String defense;
  protected String speed;
  protected String height;
  protected String weight;
  protected String male_female_ratio;

  protected boolean isFavorite;
  protected String sprite;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSprite() {
    return sprite;
  }

  public void setSprite(String sprite) {
    this.sprite = sprite;
  }

  public boolean isFavorite() {
    return isFavorite;
  }

  public void setIsFavorite(boolean isFavorite) {
    this.isFavorite = isFavorite;
  }

  public List<PokemonAbilities> getAbilities() {
    return abilities;
  }

  public void setAbilities(List<PokemonAbilities> abilities) {
    this.abilities = abilities;
  }

  public String getAttack() {
    return attack;
  }

  public void setAttack(String attack) {
    this.attack = attack;
  }

  public String getCatch_rate() {
    return catch_rate;
  }

  public void setCatch_rate(String catch_rate) {
    this.catch_rate = catch_rate;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public String getDefense() {
    return defense;
  }

  public void setDefense(String defense) {
    this.defense = defense;
  }

  public List<PokemonEvolution> getEvolutions() {
    return evolutions;
  }

  public void setEvolutions(List<PokemonEvolution> evolutions) {
    this.evolutions = evolutions;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public String getHp() {
    return hp;
  }

  public void setHp(String hp) {
    this.hp = hp;
  }

  public String getMale_female_ratio() {
    return male_female_ratio;
  }

  public void setMale_female_ratio(String male_female_ratio) {
    this.male_female_ratio = male_female_ratio;
  }

  public String getModified() {
    return modified;
  }

  public void setModified(String modified) {
    this.modified = modified;
  }

  public List<PokemonMoves> getMoves() {
    return moves;
  }

  public void setMoves(List<PokemonMoves> moves) {
    this.moves = moves;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNational_id() {
    return national_id;
  }

  public void setNational_id(String national_id) {
    this.national_id = national_id;
  }
  public String getSpeed() {
    return speed;
  }

  public void setSpeed(String speed) {
    this.speed = speed;
  }

  public List<PokemonType> getTypes() {
    return types;
  }

  public void setTypes(List<PokemonType> types) {
    this.types = types;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }
}
