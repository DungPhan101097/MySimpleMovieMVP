package com.example.lap10715.mysimpleappmovie.View;

import com.example.lap10715.mysimpleappmovie.Model.data.Movie;

import java.util.List;

public interface IView {
    void notifyDataSetChanged(List<Movie> movies);

    void notifyDataDetailReceive(Movie movie);

    void notifyError();
}
