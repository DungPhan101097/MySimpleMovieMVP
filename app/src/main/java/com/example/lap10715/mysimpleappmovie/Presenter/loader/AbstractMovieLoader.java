package com.example.lap10715.mysimpleappmovie.Presenter.loader;

import com.example.lap10715.mysimpleappmovie.Model.ApiHTTPRequest;
import com.example.lap10715.mysimpleappmovie.Model.data.Movie;
import com.example.lap10715.mysimpleappmovie.Model.data.MoviesResponse;

import retrofit2.Call;

public abstract class AbstractMovieLoader implements MovieLoader{
    @Override
    public Call<MoviesResponse> loadMovies(ApiHTTPRequest apiHTTPRequest, int page) {
        return null;
    }

    @Override
    public Call<Movie> loadDetailMovie(ApiHTTPRequest apiHTTPRequest, int id) {
        return null;
    }
}
