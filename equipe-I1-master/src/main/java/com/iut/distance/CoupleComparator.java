package com.iut.distance;

import java.util.Comparator;

public class CoupleComparator<E> implements Comparator<Couple<E>> {
    @Override
    public int compare(Couple<E> o1, Couple<E> o2) {
        if (o1.getDistance() < o2.getDistance()) {
            return -1;
        } else if (o1.getDistance() > o2.getDistance()) {
            return 1;
        }
        return 0;
    }
}