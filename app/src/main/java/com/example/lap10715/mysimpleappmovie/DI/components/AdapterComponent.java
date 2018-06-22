package com.example.lap10715.mysimpleappmovie.DI.components;

import com.example.lap10715.mysimpleappmovie.DI.modules.AdapterModule;
import com.example.lap10715.mysimpleappmovie.View.DetailMovieActivity;
import com.example.lap10715.mysimpleappmovie.View.adapters.MyAdapter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AdapterModule.class})
public interface AdapterComponent {

    void inject(MyAdapter myAdapter);

}
