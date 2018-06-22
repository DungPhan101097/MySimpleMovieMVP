package com.example.lap10715.mysimpleappmovie.Presenter.loader;

import com.example.lap10715.mysimpleappmovie.Model.ApiClient;
import com.example.lap10715.mysimpleappmovie.Model.ApiHTTPRequest;
import com.example.lap10715.mysimpleappmovie.Model.data.Movie;
import com.example.lap10715.mysimpleappmovie.Model.data.MoviesResponse;

import retrofit2.Call;

public class DetailLoader extends AbstractMovieLoader {
    @Override
    public Call<Movie> loadDetailMovie(ApiHTTPRequest apiHTTPRequest, int id) {
        return apiHTTPRequest.getMovieDetails(id, ApiClient.API_KEY);
    }
}
