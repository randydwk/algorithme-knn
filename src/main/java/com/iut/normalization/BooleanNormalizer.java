package com.iut.normalization;

import com.iut.model.IColumn;

public class BooleanNormalizer implements IValueNormalizer{

	@Override
	public double normalize(IColumn column, Object value) {
		return ((boolean) value) ? 0 : 1;
	}

	@Override
	public Object denormalize(IColumn column, double value) {
		return (value == 1);
	}

}
