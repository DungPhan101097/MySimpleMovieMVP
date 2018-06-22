package com.example.lap10715.mysimpleappmovie.Presenter.loader;



import com.example.lap10715.mysimpleappmovie.Model.ApiClient;
import com.example.lap10715.mysimpleappmovie.Model.ApiHTTPRequest;
import com.example.lap10715.mysimpleappmovie.Model.data.MoviesResponse;

import retrofit2.Call;

public class NowPlayingLoader extends AbstractMovieLoader {

    @Override
    public Call<MoviesResponse> loadMovies(ApiHTTPRequest apiHTTPRequest, int page) {
        return apiHTTPRequest.getNowPlayingMovies(page, ApiClient.API_KEY);
    }

}
