package com.example.lap10715.mysimpleappmovie.DI.components;

import com.example.lap10715.mysimpleappmovie.DI.modules.FragmentModule;
import com.example.lap10715.mysimpleappmovie.View.fragments.NowPlayingFragment;
import com.example.lap10715.mysimpleappmovie.View.fragments.PopularFragment;
import com.example.lap10715.mysimpleappmovie.View.fragments.TopRatedFragment;
import com.example.lap10715.mysimpleappmovie.View.fragments.UpComingFragment;

import dagger.Component;

@Component(modules = {FragmentModule.class})
public interface FragmentComponent {
    void inject(NowPlayingFragment fragment);
    void inject(PopularFragment fragment);
    void inject(TopRatedFragment fragment);
    void inject(UpComingFragment fragment);

}
