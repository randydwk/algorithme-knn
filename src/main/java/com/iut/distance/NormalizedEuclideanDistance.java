package com.iut.distance;

import com.iut.algo.KnnAlgorithm;
import com.iut.model.IColumn;
import com.iut.points.IPoint;

public class NormalizedEuclideanDistance implements IDistance {

    @Override
    public double distance(IPoint p1, IPoint p2, KnnAlgorithm algorithm) {
        double sum = 0;
        for (IColumn column : algorithm.getNormalizableColumns()) {
            sum += Math.pow(p1.getNormalizedValue(column) - p2.getNormalizedValue(column), 2);
        }
        return Math.sqrt(sum);
    }

    @Override
    public String toString() {
        return "Distance euclidiéenne";
    }
}
