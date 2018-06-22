package com.example.lap10715.mysimpleappmovie.Presenter;

import com.example.lap10715.mysimpleappmovie.Model.IModel;
import com.example.lap10715.mysimpleappmovie.Model.data.Movie;
import com.example.lap10715.mysimpleappmovie.Presenter.loader.MovieLoader;
import com.example.lap10715.mysimpleappmovie.View.IView;

import java.util.List;

import javax.inject.Inject;

public class MoviePresenter implements IPresenter {

    private IView view;
    private IModel model;
    private MovieLoader loader;

    public MoviePresenter(IModel model, MovieLoader loader) {
        this.model = model;
        this.model.setPresenter(this);
        this.loader = loader;
    }


    @Override
    public void load(int page) {
        model.loadData(page, loader);
    }

    @Override
    public void loadDetail(int id) {
        model.loadDetail(id, loader);
    }


    @Override
    public void notifyDataError() {
        if(view != null){
            view.notifyError();
        }
    }

    @Override
    public void notifyDataSetChanged(List<Movie> movieList) {
        if(view != null){
           view.notifyDataSetChanged(movieList);
        }
    }

    @Override
    public void notifyDataDetailReceive(Movie movie) {
        if(view != null){
            view.notifyDataDetailReceive(movie);
        }
    }

    @Override
    public void attach(IView view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
    }
}
