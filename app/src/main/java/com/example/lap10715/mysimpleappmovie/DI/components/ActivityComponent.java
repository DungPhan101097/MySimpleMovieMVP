package com.example.lap10715.mysimpleappmovie.DI.components;

import android.app.Activity;

import com.example.lap10715.mysimpleappmovie.DI.modules.ActivityModule;
import com.example.lap10715.mysimpleappmovie.View.DetailMovieActivity;

import dagger.Component;

@Component(modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(DetailMovieActivity activity);
}
