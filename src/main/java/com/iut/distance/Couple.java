package com.iut.distance;

public class Couple<E> {
    protected E element;
    protected Double distance;

    public Couple(E element, Double distance) {
        this.element = element;
        this.distance = distance;
    }

    public E getElement() {
        return element;
    }

    public Double getDistance() {
        return distance;
    }
}
