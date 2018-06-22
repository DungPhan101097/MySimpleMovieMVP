package com.example.lap10715.mysimpleappmovie.DI.modules;

import android.content.Context;

import com.example.lap10715.mysimpleappmovie.Model.ApiClient;
import com.example.lap10715.mysimpleappmovie.Model.ApiHTTPRequest;
import com.example.lap10715.mysimpleappmovie.Model.IModel;
import com.example.lap10715.mysimpleappmovie.Model.MovieModel;
import com.example.lap10715.mysimpleappmovie.Model.data.Movie;
import com.example.lap10715.mysimpleappmovie.Presenter.IPresenter;
import com.example.lap10715.mysimpleappmovie.Presenter.MoviePresenter;
import com.example.lap10715.mysimpleappmovie.Presenter.loader.DetailLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ActivityModule {
    private Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    public IPresenter provideDetailPresenter(IModel model, DetailLoader loader){
        return new MoviePresenter(model, loader);
    }

    @Provides
    public DetailLoader provideDetailLoader(){
        return new DetailLoader();
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

}
