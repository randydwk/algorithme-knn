package com.iut.distance;

import com.iut.algo.KnnAlgorithm;
import com.iut.points.IPoint;

public interface IDistance {
    public double distance(IPoint p1, IPoint p2, KnnAlgorithm algorithm);
}
