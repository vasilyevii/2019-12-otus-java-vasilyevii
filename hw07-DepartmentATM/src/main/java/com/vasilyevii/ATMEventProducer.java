package com.vasilyevii;

import java.util.ArrayList;
import java.util.List;

public class ATMEventProducer {

    private final List<Listener> listeners = new ArrayList<>();

    void addListener(Listener listener) {
        listeners.add(listener);
    }

    void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    void event(String event) {
        listeners.forEach(listener -> listener.eventHandler(event));
    }

}
