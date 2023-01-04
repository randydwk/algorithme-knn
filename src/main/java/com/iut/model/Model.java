package com.iut.model;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.iut.points.IPoint;
import com.iut.utils.AbstractSubject;
import com.opencsv.bean.CsvToBeanBuilder;

public class Model extends AbstractSubject implements IMVCModel {
    IDataset dataset;
    List<ICategory> categories;

    public Model(IDataset dataset) {
        this.dataset = dataset;
        categories = new ArrayList<>();
    }

    private void load(Reader reader) {
        List<IPoint> points;
        try {
            points = new CsvToBeanBuilder<IPoint>(reader)
                    .withSeparator(',')
                    .withType(dataset.getPointsClass())
                    .build().parse();
        } catch (IllegalStateException e) {
            System.err.println("Error while loading CSV file : " + e);
            e.printStackTrace();
            points = new ArrayList<>();
        }
        dataset.setLines(points);
        dataset.fillColumns();
    }

    @Override
    public void loadFromFile(String datafile) {
        try {
            load(Files.newBufferedReader(Paths.get(datafile)));
        } catch (IOException e) {
            System.err.println("Error while loading CSV file : " + e);
            e.printStackTrace();
        }
    }

    public void loadFromFile(File data) {
        loadFromFile(data.getAbsolutePath());
    }

    @Override
    public void loadFromString(String data) {
        load(new StringReader(data));
    }

    @Override
    public IColumn defaultXCol() {
        return dataset.getColumns().get(2);
    }

    @Override
    public IColumn defaultYCol() {
        return dataset.getColumns().get(3);
    }

    @Override
    public void addCategory(ICategory classe) {
        this.categories.add(classe);
    }

    @Override
    public Collection<ICategory> allCategories() {
        return categories;
    }

    @Override
    public int nbColumns() {
        return dataset.getColumns().size();
    }

    @Override
    public List<IColumn> getNormalizableColumns() {
        List<IColumn> result = new LinkedList<>();
        for (IColumn column : dataset.getColumns()) {
            if (column.isNormalizable()) {
                result.add(column);
            }
        }
        return result;

    }

    @Override
    public IDataset getDataset() {
        return dataset;
    }

    @Override
    public void createPoint(String string) {
        Iterator<IPoint> it = dataset.iterator();
        String header = it.next().getHeader();
        System.out.println(header + "\n" + string);
        Reader reader = new StringReader(header + "\n" + string);
        List<IPoint> points;
        try {
            points = new CsvToBeanBuilder<IPoint>(reader)
                    .withSeparator(',')
                    .withType(dataset.getPointsClass())
                    .build().parse();
        } catch (IllegalStateException e) {
            System.err.println("Error while loading CSV file : " + e);
            e.printStackTrace();
            points = new ArrayList<>();
        }
        getDataset().addAllLine(points);
        notifyObservers();
    }
}
