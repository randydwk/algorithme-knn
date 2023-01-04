package com.iut.normalization;

import com.iut.model.IColumn;

public class NumberNormalizer implements IValueNormalizer {

	@Override
	public double normalize(IColumn column, Object value) {
		Number minValue = (Number) column.getDataset().getMinValue(column);
		Number val = (Number) value;
		Number maxValue = (Number) column.getDataset().getMaxValue(column);
		return (val.doubleValue()-minValue.doubleValue())/(maxValue.doubleValue()-minValue.doubleValue());
	}

	@Override
	public Object denormalize(IColumn column, double value) {
		Number minValue = (Number) column.getDataset().getMinValue(column);
		Number maxValue = (Number) column.getDataset().getMaxValue(column);
		return value*(maxValue.doubleValue()-minValue.doubleValue())+minValue.doubleValue();
	}

}
