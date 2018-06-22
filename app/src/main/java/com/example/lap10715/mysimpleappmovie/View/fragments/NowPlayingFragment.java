package com.example.lap10715.mysimpleappmovie.View.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lap10715.mysimpleappmovie.DI.components.DaggerFragmentComponent;
import com.example.lap10715.mysimpleappmovie.DI.components.FragmentComponent;
import com.example.lap10715.mysimpleappmovie.DI.modules.FragmentModule;
import com.example.lap10715.mysimpleappmovie.Presenter.IPresenter;
import com.example.lap10715.mysimpleappmovie.R;
import com.example.lap10715.mysimpleappmovie.View.adapters.MyAdapter;
import com.example.lap10715.mysimpleappmovie.Presenter.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;
import javax.inject.Named;

public class NowPlayingFragment extends Fragment{

    @Inject
    MyAdapter myAdapter;
    @Inject
    @Named(value = "now_playing_presenter")
    IPresenter presenter;

    public NowPlayingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_now_playing);


        FragmentComponent fragmentComponent = DaggerFragmentComponent
                .builder()
                .fragmentModule(new FragmentModule(getContext(),recyclerView ))
                .build();

        fragmentComponent.inject(this);
        recyclerView.setAdapter(myAdapter);

        presenter.attach(myAdapter);
        myAdapter.setPresenter(presenter);
        myAdapter.loadData();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true)
    public void sendMessage(Integer type){
        if(type == 0){
            MessageEvent event = new MessageEvent("Tab Now Playing is selected!");
            EventBus.getDefault().post(event);
        }
    }
}
