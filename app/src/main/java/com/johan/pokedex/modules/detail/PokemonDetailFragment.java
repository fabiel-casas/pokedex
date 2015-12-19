package com.johan.pokedex.modules.detail;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.johan.pokedex.R;
import com.johan.pokedex.api.MappingPokemonProvider;
import com.johan.pokedex.api.factory.RequestConstants;
import com.johan.pokedex.domain.RealmDBController;
import com.johan.pokedex.domain.entities.Pokemon;
import com.johan.pokedex.utilities.AppUtils;
import com.johan.pokedex.utilities.IntentUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class PokemonDetailFragment extends Fragment {

  private Pokemon pokemon;

  @Bind(R.id.imageViewPokeImage)
  ImageView imageViewPokeImage;
  @Bind(R.id.imageViewFavorite)
  ImageView imageViewFavorite;
  @Bind(R.id.buttonNextEvolution)
  Button buttonNextEvolution;
  @Bind(R.id.textViewPokemonName)
  TextView textViewPokemonName;
  @Bind(R.id.textViewEvolutionName)
  TextView textViewEvolutionName;
  @Bind(R.id.textViewHP)
  TextView textViewHP;
  @Bind(R.id.textViewAttack)
  TextView textViewAttack;
  @Bind(R.id.textViewDefence)
  TextView textViewDefence;
  @Bind(R.id.textViewSpeed)
  TextView textViewSpeed;
  @Bind(R.id.textViewHeight)
  TextView textViewHeight;
  @Bind(R.id.textViewWeight)
  TextView textViewWeight;
  @Bind(R.id.textViewDescription)
  TextView textViewDescription;
  @Bind(R.id.progressBar)
  ProgressBar progressBar;

  private MappingPokemonProvider mappingPokemonProvider;
  private String descriptionID;

  public PokemonDetailFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);
    ButterKnife.bind(this, rootView);
    pokemon = getExtras();
    if(null != pokemon) {
      AppUtils.hideGoneViews(progressBar);
      initComponents();
    } else {
      AppUtils.showViews(progressBar);
      loadDetailPokemon();
    }
    return rootView;
  }

  private void loadDetailPokemon() {
    new AsyncTask<Void, String, String>(){

      @Override
      protected String doInBackground(Void... params) {
        MappingPokemonProvider mappingPokemonProvider = new MappingPokemonProvider();
        String response = null;
        try {
          Bundle bundle = getArguments();
          String detail = bundle.getString(IntentUtils.KEY_VALUE);
          response = mappingPokemonProvider.getPokemonDetail(detail);

        } catch (IOException e) {
          e.printStackTrace();
        }
        return response;
      }

      @Override
      protected void onPostExecute(String object) {
        super.onPostExecute(object);
        RealmDBController.mapRealmObjByObject(object, Pokemon.class);
        pokemon = getExtras();
        initComponents();
        AppUtils.hideGoneViews(progressBar);
      }
    }.execute();
  }

  private void initComponents() {

    textViewPokemonName.setText(getString(R.string.pokemon_name, pokemon.getName()));
    if(pokemon.getEvolutions() != null && !pokemon.getEvolutions().isEmpty()){
      textViewEvolutionName.setText(getString(R.string.pokemon_point_next_level, pokemon.getEvolutions().get(0).getLevel()));
    } else {
      textViewEvolutionName.setText(getString(R.string.pokemon_point_next_level, "Unknown"));
    }
    if(pokemon.isFavorite()){
      AppUtils.showViews(imageViewFavorite);
    }
    textViewHP.setText(getString(R.string.pokemon_hp, pokemon.getHp()));
    textViewAttack.setText(getString(R.string.pokemon_attack, pokemon.getAttack()));
    textViewDefence.setText(getString(R.string.pokemon_defence, pokemon.getDefense()));
    textViewSpeed.setText(getString(R.string.pokemon_speed, pokemon.getSpeed()));
    textViewHeight.setText(getString(R.string.pokemon_height, pokemon.getHeight()));
    textViewWeight.setText(getString(R.string.pokemon_weight, pokemon.getWeight()));
    if(null != pokemon.getEvolutions() && !pokemon.getEvolutions().isEmpty()){
      AppUtils.showViews(buttonNextEvolution);
      buttonNextEvolution.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          String evolutionCode = pokemon.getEvolutions().get(0).getResource_uri();
          IntentUtils.startPokemonDetail(getActivity(), evolutionCode);
        }
      });
    } else {
      AppUtils.hideGoneViews(buttonNextEvolution);
    }
    loadImage();
  }

  private void loadImage() {
    final int nationalID = pokemon.getNational_id();

    if(null != pokemon.getDescriptions() && !pokemon.getDescriptions().isEmpty()) {
      int randIndex = new Random().nextInt(pokemon.getDescriptions().size());
      descriptionID = pokemon.getDescriptions().get(randIndex).getResource_uri();
    }
    new AsyncTask<Void, Void, String[]>(){

      @Override
      protected String[] doInBackground(Void... params) {
        mappingPokemonProvider = new MappingPokemonProvider();
        String imageResponse = null, descriptionResponse = null;
        try {
          imageResponse = mappingPokemonProvider.getPokemonSpriteDetail(""+(nationalID + 1));
          JSONObject spriteJson = new JSONObject(imageResponse);
          imageResponse = spriteJson.getString("image");
          if(null != descriptionID) {
            descriptionResponse = mappingPokemonProvider.getPokemonDescription(descriptionID);
            JSONObject descriptionJson = new JSONObject(descriptionResponse);
            descriptionResponse = descriptionJson.getString("description");
          }
        } catch (IOException e) {
          e.printStackTrace();
        } catch (JSONException e) {
          e.printStackTrace();
        }
        String[] arrayResponse = new String[2];
        arrayResponse[0] = imageResponse;
        arrayResponse[1] = descriptionResponse;
        return arrayResponse;
      }

      @Override
      protected void onPostExecute(String[] response) {
        super.onPostExecute(response);
        String imageUrl = response[0];
        String description = response[1];
        Picasso.with(getActivity()).load(RequestConstants.POKEMON_DOMAIN_NAME + imageUrl).into(imageViewPokeImage);
        textViewDescription.setText(getString(R.string.pokemon_description, description));
      }
    }.execute();
  }

  private Pokemon getExtras() {
    Bundle bundle = getArguments();
    String name = bundle.getString(IntentUtils.KEY_VALUE);
    return RealmDBController.loadPokemonByCode(name);
  }

  public static PokemonDetailFragment newInstance(Bundle extras) {
    PokemonDetailFragment pokemonDetailFragment = new PokemonDetailFragment();
    pokemonDetailFragment.setArguments(extras);
    return pokemonDetailFragment;
  }
}
