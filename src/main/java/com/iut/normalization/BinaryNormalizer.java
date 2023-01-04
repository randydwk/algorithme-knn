package com.iut.normalization;

import com.iut.model.IColumn;

public class BinaryNormalizer implements IValueNormalizer{

	@Override
	public double normalize(IColumn column, Object value) {
		if(value.equals("male")) return 0;
		else return 1;
	}

	@Override
	public Object denormalize(IColumn column, double value) {
		if(value==0) return "male";
		else return "female";
	}

}
