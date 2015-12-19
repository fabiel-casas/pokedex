package com.johan.pokedex.modules.home;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johan.pokedex.R;
import com.johan.pokedex.api.MappingPokemonProvider;
import com.johan.pokedex.api.factory.RequestConstants;
import com.johan.pokedex.domain.RealmDBController;
import com.johan.pokedex.domain.entities.Pokemon;
import com.johan.pokedex.utilities.IntentUtils;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by JohanFabiel on 18/12/2015.
 */
public class PokemonListFragment extends Fragment {

  @Bind(R.id.ultimate_recycler_view)
  UltimateRecyclerView ultimate_recycler_view;

  private int pageNum = 0;
  private List<Pokemon> pokemonList;
  private PokemonAdapter pokemonAdapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
    ButterKnife.bind(this, rootView);
    initList();
    return rootView;
  }

  private void initList() {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    ultimate_recycler_view.setLayoutManager(linearLayoutManager);
    ultimate_recycler_view.enableLoadmore();
    ultimate_recycler_view.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
      @Override
      public void loadMore(int itemsCount, int maxLastVisiblePosition) {
        if(itemsCount >= pokemonList.size()) {
          loadPokemon();
        }
      }
    });
    loadContentList();
  }

  private void loadContentList() {
    pokemonList = RealmDBController.getPokemonList();
    if(pokemonList == null || pokemonList.isEmpty()){
      loadPokemon();
    } else {
      updateAdapterList();
    }
  }

  private void updateAdapterList() {
    pageNum = pokemonList.size() / RequestConstants.PAGE_LIMIT;
    if(ultimate_recycler_view.getAdapter() == null) {
      pokemonAdapter = new PokemonAdapter(pokemonList);
      pokemonAdapter.setCustomLoadMoreView(LayoutInflater.from(getActivity())
          .inflate(R.layout.custom_bottom_progressbar, null));
      pokemonAdapter.setPokeListEventListener(new PokemonAdapter.PokeListEventListener() {
        @Override
        public void OnClickItem(int position, Pokemon pokemon) {
          IntentUtils.startPokemonDetail(getActivity(), pokemon.getResource_uri());
        }

        @Override
        public void OnFavoriteClickItem(int position, Pokemon pokemon) {


        }
      });
      ultimate_recycler_view.setAdapter(pokemonAdapter);
    } else {
      pokemonAdapter.setItems(pokemonList);
    }
  }

  private void loadPokemon() {
    new AsyncTask<Void, String, String>(){

      @Override
      protected String doInBackground(Void... params) {
        MappingPokemonProvider mappingPokemonProvider = new MappingPokemonProvider();
        String response = null;
        try {
          String results = mappingPokemonProvider.getPokemonPage(pageNum);
          JSONObject jsonObject = new JSONObject(results);
          JSONArray objects = jsonObject.getJSONArray("objects");
          response = objects.toString();
        } catch (IOException e) {
          e.printStackTrace();
        } catch (JSONException e) {
          e.printStackTrace();
        }
        return response;
      }

      @Override
      protected void onPostExecute(String values) {
        super.onPostExecute(values);
        RealmDBController.mapRealmObjByJsonArray(values, Pokemon.class);
        pageNum++;
        loadContentList();
      }
    }.execute();
  }
}
