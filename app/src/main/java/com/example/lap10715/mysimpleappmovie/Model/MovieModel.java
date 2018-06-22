package com.example.lap10715.mysimpleappmovie.Model;

import com.example.lap10715.mysimpleappmovie.Presenter.loader.MovieLoader;
import com.example.lap10715.mysimpleappmovie.Model.data.Movie;
import com.example.lap10715.mysimpleappmovie.Model.data.MoviesResponse;
import com.example.lap10715.mysimpleappmovie.Presenter.IPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieModel implements IModel {

    private List<Movie> movieList;
    private ApiHTTPRequest apiHTTPRequest;
    private IPresenter presenter;


    public MovieModel(List<Movie> movies, ApiHTTPRequest apiHTTPRequest) {
        this.movieList = movies;
        this.apiHTTPRequest = apiHTTPRequest;
    }

    @Override
    public void setPresenter(IPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void loadData(int page, MovieLoader loader) {
        Call<MoviesResponse> call =loader.loadMovies(apiHTTPRequest, page);

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                try {
                    movieList = response.body().getResults();
                    presenter.notifyDataSetChanged(movieList);
                }
                catch (NullPointerException e){
                    presenter.notifyDataError();
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                presenter.notifyDataError();
            }
        });
    }

    @Override
    public void loadDetail(int id, MovieLoader loader) {
        final Call<Movie> movieCall = loader.loadDetailMovie(apiHTTPRequest, id);

        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                try{
                    Movie movie = response.body();
                    presenter.notifyDataDetailReceive(movie);
                }catch (NullPointerException e){
                    presenter.notifyDataError();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                presenter.notifyDataError();
            }
        });
    }

}
