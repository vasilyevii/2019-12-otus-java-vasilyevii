package com.vasilyevii.observer;

import java.util.ArrayList;
import java.util.List;

public class ATMEventProducer {

    private final List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public void event(String event) {
        listeners.forEach(listener -> listener.eventHandler(event));
    }

}
