package com.iut.distance;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.iut.algo.KnnAlgorithm;
import com.iut.model.Dataset;
import com.iut.model.IDataset;
import com.iut.model.IMVCModel;
import com.iut.model.Model;
import com.iut.normalization.NumberNormalizer;
import com.iut.points.IPoint;
import com.iut.points.Iris;

class DistanceTest {

    IDistance distance;
    IDataset set;
    IMVCModel model;
    KnnAlgorithm algo;

    @BeforeEach
    void init() {
        set = new Dataset("iris", Iris.class);
        set.fillColumns();
        for (int i = 1; i <= 4; i++) {
            set.getColumns().get(i).setNormalizer(new NumberNormalizer());
        }
        model = new Model(set);
        algo = new KnnAlgorithm(model);
        
        

        set.addLine(new Iris(2, 6, 9, 5, "iris"));
        set.addLine(new Iris(6, 5, 1, 7, "iris"));
        set.addLine(new Iris(6, 5, 8, 4, "iris"));
        set.addLine(new Iris(1, 2, 5, 8, "iris"));
        set.addLine(new Iris(1, 5, 4, 7, "iris"));
        set.addLine(new Iris(1, 1, 1, 5, "iris"));
        
    }

    @Test
    void normalizedManhattanDistanceTest() {
        distance = new NormalizedManhattanDistance();
        Iterator<IPoint> points = set.iterator();
        IPoint p1, p2 = null;
        p1 = points.next();
        p2 = points.next();
        double value = distance.distance(p1, p2, algo);
        assertTrue(value >= 0);
        assertTrue(value <= 4);
    }

    @Test
    void normalizedEuclidianDistanceTest() {
        distance = new NormalizedEuclideanDistance();
        Iterator<IPoint> points = set.iterator();
        IPoint p1, p2 = null;
        p1 = points.next();
        p2 = points.next();
        double value = distance.distance(p1, p2, algo);
        assertTrue(value >= 0);
    }
}
