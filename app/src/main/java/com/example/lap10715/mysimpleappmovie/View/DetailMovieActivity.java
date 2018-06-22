package com.example.lap10715.mysimpleappmovie.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lap10715.mysimpleappmovie.DI.components.ActivityComponent;
import com.example.lap10715.mysimpleappmovie.DI.components.DaggerActivityComponent;
import com.example.lap10715.mysimpleappmovie.DI.components.DaggerFragmentComponent;
import com.example.lap10715.mysimpleappmovie.DI.components.FragmentComponent;
import com.example.lap10715.mysimpleappmovie.DI.modules.ActivityModule;
import com.example.lap10715.mysimpleappmovie.DI.modules.FragmentModule;
import com.example.lap10715.mysimpleappmovie.GlideApp;
import com.example.lap10715.mysimpleappmovie.Model.ApiClient;
import com.example.lap10715.mysimpleappmovie.Model.data.Movie;
import com.example.lap10715.mysimpleappmovie.Presenter.IPresenter;
import com.example.lap10715.mysimpleappmovie.R;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieActivity extends AppCompatActivity implements IView {

    @Inject
    IPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent != null) {
            Integer id = intent.getExtras().getInt(MyMovieItemClickListenner.ID_MOVIE);

            ActivityComponent component = DaggerActivityComponent
                    .builder()
                    .activityModule(new ActivityModule(this))
                    .build();

            component.inject(this);

            presenter.attach(this);

            presenter.loadDetail(id);
        }

    }

    @Override
    public void notifyDataSetChanged(List<Movie> movies) {

    }

    @Override
    public void notifyDataDetailReceive(Movie movie) {
        if (movie != null) {
            setTitle(movie.getTitle());

            TextView tvTitle = findViewById(R.id.detail_title);
            TextView tvRelease = findViewById(R.id.detail_release);
            TextView tvRated = findViewById(R.id.detail_rate);
            TextView tvOverview = findViewById(R.id.detail_overview);
            ImageView imPoster = findViewById(R.id.detail_poster);

            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            tvRated.setText("Rate: " + movie.getVoteAverage());
            tvRelease.setText("Release: " + movie.getReleaseDate());
            String pathPoster = ApiClient.IMAGE_PATH + movie.getBackdropPath();

            GlideApp.with(DetailMovieActivity.this)
                    .load(Uri.parse(pathPoster))
                    .placeholder(R.drawable.placeholder)
                    .into(imPoster);

        }
    }

    @Override
    public void notifyError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
