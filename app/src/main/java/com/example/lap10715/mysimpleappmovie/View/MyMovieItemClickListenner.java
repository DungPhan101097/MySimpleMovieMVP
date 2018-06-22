package com.example.lap10715.mysimpleappmovie.View;

import android.content.Intent;
import android.view.View;

public class MyMovieItemClickListenner implements View.OnClickListener{

    public static final String ID_MOVIE = "IDMoive";
    private Integer movieId;

    public MyMovieItemClickListenner(Integer movieId) {
        this.movieId = movieId;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), DetailMovieActivity.class);
        intent.putExtra(ID_MOVIE, movieId);
        v.getContext().startActivity(intent);
    }
}
