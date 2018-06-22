package com.example.lap10715.mysimpleappmovie.View.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lap10715.mysimpleappmovie.DI.components.AdapterComponent;
import com.example.lap10715.mysimpleappmovie.DI.components.DaggerAdapterComponent;
import com.example.lap10715.mysimpleappmovie.DI.modules.AdapterModule;
import com.example.lap10715.mysimpleappmovie.GlideApp;
import com.example.lap10715.mysimpleappmovie.Model.ApiClient;
import com.example.lap10715.mysimpleappmovie.Model.ApiHTTPRequest;
import com.example.lap10715.mysimpleappmovie.Model.data.Movie;
import com.example.lap10715.mysimpleappmovie.Model.data.MoviesResponse;
import com.example.lap10715.mysimpleappmovie.Presenter.IPresenter;
import com.example.lap10715.mysimpleappmovie.Presenter.MoviePresenter;
import com.example.lap10715.mysimpleappmovie.R;
import com.example.lap10715.mysimpleappmovie.View.IView;
import com.example.lap10715.mysimpleappmovie.View.MyMovieItemClickListenner;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;


public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IView{

    @Inject
    List<Movie> movieListCurrent;
    @Inject
    LinearLayoutManager linearLayoutManager;

    private IPresenter presenter;

    private Context context;
    private int curPage = 1;


    public MyAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;

        AdapterComponent adapterComponent = DaggerAdapterComponent
                .builder()
                .adapterModule(new AdapterModule(context))
                .build();

        adapterComponent.inject(this);


        recyclerView.setLayoutManager(linearLayoutManager);

    }

    public void setPresenter(IPresenter presenter) {
        this.presenter = presenter;
    }

    public void loadData() {
        presenter.load(curPage);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(context)
                .inflate(R.layout.layout_movie_item, parent, false);
        return new MyHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = movieListCurrent.get(position);
        if(holder instanceof MyHolder){
            MyHolder myHolder = (MyHolder)holder;
            myHolder.movieTitle.setText(movie.getTitle());
            myHolder.release.setText(movie.getReleaseDate());
            myHolder.rate.setText("Rated: "  + movie.getVoteAverage());

            String overview = movie.getOverview();
            if(!overview.isEmpty()){
                if(overview.length() > 100){
                    overview = overview.substring(0, 100) + " ...";
                }
                myHolder.overview.setText(overview);
            }

            String imagePath = ApiClient.IMAGE_PATH + movie.getPosterPath();
            GlideApp.with(context)
                    .load(Uri.parse(imagePath))
                    .placeholder(R.drawable.placeholder)
                    .into(myHolder.poster);

            // Set click lister for each item.
            myHolder.card.setOnClickListener(new MyMovieItemClickListenner(movie.getId()));
        }
    }

    @Override
    public int getItemCount() {
        return movieListCurrent.size();
    }

    @Override
    public void notifyDataSetChanged(List<Movie> movies) {
        movieListCurrent = movies;
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataDetailReceive(Movie movie) {

    }

    @Override
    public void notifyError() {

    }

    class MyHolder extends  RecyclerView.ViewHolder{

        public ImageView poster;
        public TextView release;
        public TextView movieTitle;
        public TextView overview;
        public CardView card;
        public TextView rate;

        public MyHolder(View itemView) {
            super(itemView);
            card = (CardView)itemView;
            poster = itemView.findViewById(R.id.iv_poster);
            release = itemView.findViewById(R.id.tv_release);
            movieTitle = itemView.findViewById(R.id.tv_title);
            overview = itemView.findViewById(R.id.tv_overview);
            rate = itemView.findViewById(R.id.tv_rate);

        }
    }
}
