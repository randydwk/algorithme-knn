package com.iut.distance;

import com.iut.algo.KnnAlgorithm;
import com.iut.points.IPoint;

public class RandomDistance implements IDistance {

    @Override
    public double distance(IPoint p1, IPoint p2, KnnAlgorithm algorithm) {
        return Math.random();
    }

    @Override
    public String toString() {
        return "Distance aléatoire";
    }
}
