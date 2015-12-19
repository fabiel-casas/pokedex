package com.johan.pokedex.app;

import android.app.Application;

import com.johan.pokedex.domain.RealmDBController;

/**
 * Created by Johan Fabiel on 18/12/2015.
 */
public class App extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    RealmDBController.getInstance(getApplicationContext());

  }
}
