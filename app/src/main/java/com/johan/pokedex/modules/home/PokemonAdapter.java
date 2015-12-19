package com.johan.pokedex.modules.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.johan.pokedex.R;
import com.johan.pokedex.domain.RealmDBController;
import com.johan.pokedex.domain.entities.Pokemon;
import com.johan.pokedex.utilities.StringUtils;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Johan Fabiel on 18/12/2015.
 */
public class PokemonAdapter extends UltimateViewAdapter<PokemonAdapter.PokemonViewHolder> {

  private List<Pokemon> pokemonList;
  private Animation scaleAnimation;

  public PokemonAdapter(List<Pokemon> pokemonList) {
    this.pokemonList = pokemonList;
  }


  private PokeListEventListener listener;

  public void setPokeListEventListener(PokeListEventListener pokeListEventListener) {
    this.listener = pokeListEventListener;
  }

  public void setItems(List<Pokemon> items) {
    this.pokemonList = items;
    notifyDataSetChanged();
  }

  public interface PokeListEventListener{
    void OnClickItem(int position, Pokemon pokemon);
    void OnFavoriteClickItem(int position, Pokemon pokemon);
  }

  @Override
  public PokemonViewHolder getViewHolder(View view) {
    return new PokemonViewHolder(view, false);
  }

  @Override
  public PokemonViewHolder onCreateViewHolder(ViewGroup parent) {
    View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pokemon_list, null);
    scaleAnimation = AnimationUtils.loadAnimation(parent.getContext(), R.anim.scale);
    PokemonViewHolder pokemonViewHolder = new PokemonViewHolder(convertView, true);
    return pokemonViewHolder;
  }

  @Override
  public int getAdapterItemCount() {
    return pokemonList.size();
  }

  @Override
  public long generateHeaderId(int position) {
    return 0;
  }

  @Override
  public void onBindViewHolder(final PokemonViewHolder holder, final int position) {
    if(position < pokemonList.size()) {
      final Pokemon pokemon = pokemonList.get(position);
      holder.textViewName.setText(StringUtils.capitalizeText(pokemon.getName()));
      holder.textViewName.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (null != listener) {
            listener.OnClickItem(position, pokemon);
          }
        }
      });
      holder.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (null != listener) {
            listener.OnFavoriteClickItem(position, pokemon);
          }
          RealmDBController.updatePokemonFavoriteStatus(pokemon.getResource_uri(), !pokemon.isFavorite());
          holder.imageViewFavorite.startAnimation(scaleAnimation);
          notifyDataSetChanged();
        }
      });
      if (pokemon.isFavorite()) {
        holder.imageViewFavorite.setImageResource(R.drawable.ic_favorite_on);
      } else {
        holder.imageViewFavorite.setImageResource(R.drawable.ic_favorite_off);
      }
    }
  }

  @Override
  public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
    return null;
  }

  @Override
  public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

  }

  public class PokemonViewHolder extends UltimateRecyclerviewViewHolder{
    @Bind(R.id.textViewName)
    TextView textViewName;
    @Bind(R.id.imageViewFavorite)
    ImageView imageViewFavorite;

    public PokemonViewHolder(View itemView, boolean item) {
      super(itemView);
      if(item){
        ButterKnife.bind(this, itemView);
      }
    }
  }
}
