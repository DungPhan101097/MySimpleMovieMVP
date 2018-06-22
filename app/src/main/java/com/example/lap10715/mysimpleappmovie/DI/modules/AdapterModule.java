package com.example.lap10715.mysimpleappmovie.DI.modules;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;


import com.example.lap10715.mysimpleappmovie.Model.ApiClient;
import com.example.lap10715.mysimpleappmovie.Model.ApiHTTPRequest;
import com.example.lap10715.mysimpleappmovie.Model.data.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AdapterModule {

    private Context context;

    public AdapterModule(Context context) {
        this.context = context;
    }

    @Provides
    public List<Movie> provideListMovie() {
        return new ArrayList<>();
    }

    @Provides
    public LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }


}
