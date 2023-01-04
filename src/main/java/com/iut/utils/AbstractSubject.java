package com.iut.utils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSubject {
    protected List<Observer> attached;

    protected AbstractSubject() {
        attached = new ArrayList<>();
    }

    public void attach(Observer obs) {
        if (!attached.contains(obs)) {
            attached.add(obs);
        }
    }

    public void detach(Observer obs) {
        if (attached.contains(obs)) {
            attached.remove(obs);
        }
    }

    public void notifyObservers() {
        for (Observer o : attached) {
            o.update(this);
        }
    }

    public void notifyObservers(Object data) {
        for (Observer o : attached) {
            o.update(this, data);
        }
    }

}
