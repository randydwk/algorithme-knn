package com.iut.algo;

import com.iut.distance.IDistance;
import com.iut.model.*;
import com.iut.points.IPoint;

import java.io.File;

public class KnnRobust extends KnnAlgorithm implements Robust{
    IMVCModel testModel;

    public KnnRobust(IMVCModel model, File file) {
        super(model);
        IDataset set = new Dataset(model.getDataset().getTitle() + "-Test", model.getDataset().getPointsClass());
        testModel = new Model(set);
        testModel.loadFromFile(file);
    }

    @Override
    public Double robust(int k, IDistance d) {
        int rightGuessCount = 0;
        for (IPoint point : testModel.getDataset()) {
            ICategory cat = calculateCategory(k, point, d);
            if (cat.getTitle().equals(point.getCategory().toString())) {
                rightGuessCount++;
            }
        }
        return (double) rightGuessCount / (double) testModel.getDataset().getNbLines();
    }
}
