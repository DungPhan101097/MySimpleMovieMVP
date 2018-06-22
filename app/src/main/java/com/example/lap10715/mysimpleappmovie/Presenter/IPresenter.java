package com.example.lap10715.mysimpleappmovie.Presenter;

import com.example.lap10715.mysimpleappmovie.Model.ApiHTTPRequest;
import com.example.lap10715.mysimpleappmovie.Model.data.Movie;
import com.example.lap10715.mysimpleappmovie.Model.data.MoviesResponse;
import com.example.lap10715.mysimpleappmovie.View.IView;

import java.util.List;

public interface IPresenter {
    void notifyDataError();

    void notifyDataSetChanged(List<Movie> movieList);

    void notifyDataDetailReceive(Movie movie);

    void attach(IView view);

    void detach();

    void load(int page);

    void loadDetail(int id);
}
