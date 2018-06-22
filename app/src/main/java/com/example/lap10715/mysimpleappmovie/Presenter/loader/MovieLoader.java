package com.example.lap10715.mysimpleappmovie.Presenter.loader;


import com.example.lap10715.mysimpleappmovie.Model.ApiHTTPRequest;
import com.example.lap10715.mysimpleappmovie.Model.data.Movie;
import com.example.lap10715.mysimpleappmovie.Model.data.MoviesResponse;

import retrofit2.Call;

public interface MovieLoader {
    Call<MoviesResponse> loadMovies(ApiHTTPRequest apiHTTPRequest, int page);
    Call<Movie> loadDetailMovie(ApiHTTPRequest apiHTTPRequest, int id);
}
