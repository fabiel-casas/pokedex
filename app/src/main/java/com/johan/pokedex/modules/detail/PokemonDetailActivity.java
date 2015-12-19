package com.johan.pokedex.modules.detail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.johan.pokedex.R;
import com.johan.pokedex.domain.RealmDBController;
import com.johan.pokedex.domain.entities.Pokemon;
import com.johan.pokedex.utilities.IntentUtils;

public class PokemonDetailActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pokemon_detail);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    Bundle bundle = getIntent().getExtras();
    Pokemon pokemon = RealmDBController.loadPokemonByCode(bundle.getString(IntentUtils.KEY_VALUE));
    if(null != pokemon) {
      String title = pokemon.getName();
      getSupportActionBar().setTitle(title);
    } else {
      getSupportActionBar().setTitle("");
    }
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    getFragmentManager().beginTransaction().replace(R.id.fragmentDetailContainer, PokemonDetailFragment.newInstance(bundle), null).commit();
  }

}
