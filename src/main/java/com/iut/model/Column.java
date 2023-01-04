package com.iut.model;

import com.iut.normalization.IValueNormalizer;
import com.iut.points.IPoint;

public class Column implements IColumn {

    protected String name;
    protected IDataset dataset;
    protected IValueNormalizer valueNormalizer;

    public Column(String name, IDataset dataset) {
        this.name = name.toLowerCase();
        this.dataset = dataset;
        this.valueNormalizer = null;
    }

    @Override
    public void setNormalizer(IValueNormalizer valueNormalizer) {
        this.valueNormalizer = valueNormalizer;
    }

    @Override
    public double getNormalizedValue(IPoint point) {
        return valueNormalizer.normalize(this,point.getValue(this));
    }

    @Override
    public Object getDenormalizedValue(double value) {
        return valueNormalizer.denormalize(this, value);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IDataset getDataset() {
        return dataset;
    }

    @Override
    public boolean isNormalizable() {
        return valueNormalizer != null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((dataset == null) ? 0 : dataset.hashCode());
        result = prime * result + ((valueNormalizer == null) ? 0 : valueNormalizer.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Column other = (Column) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (dataset == null) {
            if (other.dataset != null)
                return false;
        } else if (!dataset.equals(other.dataset))
            return false;
        if (valueNormalizer == null) {
            if (other.valueNormalizer != null)
                return false;
        } else if (!valueNormalizer.equals(other.valueNormalizer))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Column [name=" + name + ", dataset=" + dataset + ", valueNormalizer=" + valueNormalizer + "]";
    }
    
    
}
