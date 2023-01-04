package com.iut.points;

import com.iut.model.IColumn;

public class NullPoint implements IPoint {

    @Override
    public Object getValue(IColumn col) {
        return 0;
    }

    @Override
    public double getNormalizedValue(IColumn xcol) {
        return 0;
    }

    @Override
    public Object getCategory() {
        return 0;
    }

    @Override
    public String getHeader() {
        return "";
    }
}
