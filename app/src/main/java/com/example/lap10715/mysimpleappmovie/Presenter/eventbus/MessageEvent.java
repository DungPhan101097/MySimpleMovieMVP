package com.example.lap10715.mysimpleappmovie.Presenter.eventbus;


public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
