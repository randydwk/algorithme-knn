package com.iut.model;

import com.iut.points.IPoint;

import java.util.ArrayList;
import java.util.List;

public class Category implements ICategory {

    String name;
    List<IPoint> points;

    public Category(String name) {
        this.name = name;
        points = new ArrayList<>();
    }

    @Override
    public String getTitle() {
        return name;
    }

}
