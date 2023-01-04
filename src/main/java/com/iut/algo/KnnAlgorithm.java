package com.iut.algo;

import com.iut.distance.Couple;
import com.iut.distance.CoupleComparator;
import com.iut.distance.IDistance;
import com.iut.model.Category;
import com.iut.model.ICategory;
import com.iut.model.IColumn;
import com.iut.model.IMVCModel;
import com.iut.points.IPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class KnnAlgorithm implements IAlgorithm {

    IMVCModel model;

    public KnnAlgorithm(IMVCModel model) {
        this.model = model;
    }

    @Override
    public ICategory calculateCategory(int k, IPoint toClass, IDistance distance) {
        if (k == 0) {
            return null;
        } else if (k == 1) {
            return new Category(toClass.getCategory().toString());
        }

        IPoint[] points = nearestNeighbours(k, toClass, distance);
        Map<Object, Integer> map = new HashMap<>();
        
        // putting all points in a map
        for (IPoint point : points) {
            int value = 1;
            Object category = point.getCategory();
            if (map.containsKey(category)) {
                value = map.get(category) + 1;
            }
            map.put(category, value);
        }
        
        // retrieving entry with most occurence
        Entry<Object,Integer> maxEntry = map.entrySet().iterator().next();
        for (Entry<Object,Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }

        return new Category(maxEntry.getKey().toString());
    }

    private IPoint[] nearestNeighbours(int k, IPoint toClass, IDistance d) {
        IPoint[] result = new IPoint[k];
        List<Couple<IPoint>> list = new ArrayList<>();

        for (IPoint next : model.getDataset()) {
            if (!next.equals(toClass)) {
                double distance = d.distance(next, toClass, this);
                list.add(new Couple<>(next, distance));
            }
        }

        list.sort(new CoupleComparator<>());
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i).getElement();
        }
        return result;
    }

    public List<IColumn> getNormalizableColumns() {
        return model.getNormalizableColumns();
    }

    
}
