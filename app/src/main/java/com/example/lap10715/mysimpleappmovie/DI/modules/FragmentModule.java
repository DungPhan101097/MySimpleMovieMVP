package com.example.lap10715.mysimpleappmovie.DI.modules;

import android.content.Context;
import android.support.v7.widget.RecyclerView;


import com.example.lap10715.mysimpleappmovie.Model.ApiClient;
import com.example.lap10715.mysimpleappmovie.Model.ApiHTTPRequest;
import com.example.lap10715.mysimpleappmovie.Model.IModel;
import com.example.lap10715.mysimpleappmovie.Model.MovieModel;
import com.example.lap10715.mysimpleappmovie.Model.data.Movie;
import com.example.lap10715.mysimpleappmovie.Presenter.IPresenter;
import com.example.lap10715.mysimpleappmovie.Presenter.MoviePresenter;
import com.example.lap10715.mysimpleappmovie.Presenter.loader.DetailLoader;
import com.example.lap10715.mysimpleappmovie.Presenter.loader.NowPlayingLoader;
import com.example.lap10715.mysimpleappmovie.Presenter.loader.PopularLoader;
import com.example.lap10715.mysimpleappmovie.Presenter.loader.TopRatedLoader;
import com.example.lap10715.mysimpleappmovie.Presenter.loader.UpComingLoader;
import com.example.lap10715.mysimpleappmovie.View.adapters.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class FragmentModule {
    private Context context;
    private RecyclerView recyclerView;

    public FragmentModule(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Provides
    public MyAdapter provideAdapter(){
        return new MyAdapter(context, recyclerView);
    }


    @Provides
    public NowPlayingLoader provideNowPlayingLoader(){
        return new NowPlayingLoader();
    }

    @Provides
    public TopRatedLoader provideTopRatedLoader(){
        return new TopRatedLoader();
    }

    @Provides
    public PopularLoader providePopularLoader(){
        return new PopularLoader();
    }

    @Provides
    public UpComingLoader provideUpComingLoader(){
        return new UpComingLoader();
    }

    @Provides
    public IModel provideModel(List<Movie> movies, ApiHTTPRequest apiHTTPRequest){
        return new MovieModel(movies, apiHTTPRequest);
    }

    @Provides
    public List<Movie> provideListMovie(){
        return new ArrayList<>();
    }

    @Provides
    public ApiHTTPRequest provideApiHTTPRequest(Retrofit retrofit){
        return retrofit.create(ApiHTTPRequest.class);
    }

    @Provides
    public Retrofit provideRetrofit(){
        return ApiClient.getRetrofit();
    }

    @Provides
    @Named(value = "now_playing_presenter")
    public IPresenter provideNowPlayingPresenter(IModel model, NowPlayingLoader loader){
        return new MoviePresenter(model, loader);
    }

    @Provides
    @Named(value = "popular_presenter")
    public IPresenter providePopularPresenter(IModel model, PopularLoader loader){
        return new MoviePresenter(model, loader);
    }

    @Provides
    @Named(value = "up_coming_presenter")
    public IPresenter provideUpComingPresenter(IModel model, UpComingLoader loader){
        return new MoviePresenter(model, loader);
    }

    @Provides
    @Named(value = "top_rated_presenter")
    public IPresenter provideTopRatedPresenter(IModel model, TopRatedLoader loader){
        return new MoviePresenter(model, loader);
    }


}
