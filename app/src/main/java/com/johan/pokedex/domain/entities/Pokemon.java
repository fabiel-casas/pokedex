package com.johan.pokedex.domain.entities;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by JohanFabiel on 13/12/2015.
 */
public class Pokemon extends RealmObject {
  @PrimaryKey
  private String resource_uri;
  private String name;
  private int national_id;
  private String created;
  private String modified;
  private RealmList<PokemonAbilities> descriptions;
  private RealmList<PokemonAbilities> abilities;
  private RealmList<PokemonEvolution> evolutions;
  private RealmList<PokemonMoves> moves;
  private RealmList<PokemonType> types;
  private String catch_rate;
  private String hp;
  private String attack;
  private String defense;
  private String speed;
  private String height;
  private String weight;
  private String male_female_ratio;

  public RealmList<PokemonAbilities> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(RealmList<PokemonAbilities> descriptions) {
    this.descriptions = descriptions;
  }

  private boolean isFavorite;
  private String sprite;

  public String getResource_uri() {
    return resource_uri;
  }

  public void setResource_uri(String resource_uri) {
    this.resource_uri = resource_uri;
  }

  public RealmList<PokemonAbilities> getAbilities() {
    return abilities;
  }

  public void setAbilities(RealmList<PokemonAbilities> abilities) {
    this.abilities = abilities;
  }

  public RealmList<PokemonEvolution> getEvolutions() {
    return evolutions;
  }

  public void setEvolutions(RealmList<PokemonEvolution> evolutions) {
    this.evolutions = evolutions;
  }

  public RealmList<PokemonMoves> getMoves() {
    return moves;
  }

  public void setMoves(RealmList<PokemonMoves> moves) {
    this.moves = moves;
  }

  public RealmList<PokemonType> getTypes() {
    return types;
  }

  public void setTypes(RealmList<PokemonType> types) {
    this.types = types;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNational_id() {
    return national_id;
  }

  public void setNational_id(int national_id) {
    this.national_id = national_id;
  }

  public String getSpeed() {
    return speed;
  }

  public void setSpeed(String speed) {
    this.speed = speed;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }
}
