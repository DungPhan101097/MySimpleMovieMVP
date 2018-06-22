package com.example.lap10715.mysimpleappmovie.Model;

import com.example.lap10715.mysimpleappmovie.Presenter.IPresenter;
import com.example.lap10715.mysimpleappmovie.Presenter.loader.MovieLoader;

public interface IModel {
    void loadData(int page, MovieLoader loader);

    void loadDetail(int id, MovieLoader loader);

    void setPresenter(IPresenter presenter) ;
}
