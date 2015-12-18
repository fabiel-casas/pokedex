package com.johan.pokedex.api.request;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by JohanFabiel on 13/12/2015.
 */
public class RequestGet {

  OkHttpClient client = new OkHttpClient();

  public String doGetRequest(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .build();

    Response response = client.newCall(request).execute();
    return response.body().string();
  }
}
