package com.iut.algo;

import com.iut.distance.IDistance;
import com.iut.model.ICategory;
import com.iut.points.IPoint;

/**
 * Represents an algorithm that returns the class of a point, whatever its method is.
 */
public interface IAlgorithm {
    public ICategory calculateCategory(int k, IPoint toClass, IDistance distance);
}
