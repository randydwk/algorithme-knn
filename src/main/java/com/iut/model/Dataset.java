package com.iut.model;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.iut.points.IPoint;

public class Dataset implements IDataset {
    public static final File DEFAULT_CSV = new File("src/main/resources/iris.csv");

    protected final String name;
    protected List<IPoint> points;
    protected final List<IColumn> columns;
    protected final Class<? extends IPoint> pointsClass;

    public Dataset(String name, Class<? extends IPoint> classe) {
        points = new LinkedList<>();
        this.name = name;
        this.pointsClass = classe;
        this.columns = new ArrayList<>();
    }

	@Override
    public String getTitle() {
        return name;
    }

    // Utile pour les tests
    public List<IPoint> getPoints() {
        return points;
    }

    @Override
    public int getNbLines() {
        return points.size();
    }

    @Override
    public void setLines(List<IPoint> lines) {
        points = lines;
    }

    @Override
    public void addLine(IPoint element) {
        points.add(element);
    }

    @Override
    public void addAllLine(List<IPoint> element) {
        points.addAll(element);
    }

    @Override
    public Iterator<IPoint> iterator() {
        return points.iterator();
    }

    public IColumn getColumn(int i) {
        try {
            return columns.get(i);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public IColumn getColumnByName(String name) {
        for (IColumn column : columns) {
            if (name.equalsIgnoreCase(column.getName())) {
                return column;
            }
        }
        return null;
    }

    @Override
    public List<IColumn> getColumns() {
        return columns;
    }

    @Override
    public void fillColumns() {
        for (Field field : pointsClass.getDeclaredFields()) {
            Column toAdd = new Column(field.getName(), this);
            if (!columns.contains(toAdd) && !toAdd.getName().equals("header")) {
                columns.add(toAdd);
            }
        }
    }

    @Override
    public Double getMinValue(IColumn column) {
        Number min = (Number) points.get(0).getValue(column);
        for (IPoint point : points) {
            min = Math.min(min.doubleValue(), ((Number) point.getValue(column)).doubleValue());
        }
        return min.doubleValue();
    }

    @Override
    public Double getMaxValue(IColumn column) {
        Number max = (Number) points.get(0).getValue(column);
        for (IPoint point : points) {
            max = Math.max(max.doubleValue(), ((Number) point.getValue(column)).doubleValue());
        }
        return max.doubleValue();
    }

    @Override
    public Class<? extends IPoint> getPointsClass() {
        return pointsClass;
    }

}
