package com.iut.normalization;

import com.iut.model.IColumn;

public class EnumerativeNormalizer implements IValueNormalizer {

	@Override
	public double normalize(IColumn column, Object value) {
		if((char)value=='S') return 0;
		else if((char)value=='C') return 0.5;
		else return 1;
	}

	@Override
	public Object denormalize(IColumn column, double value) {
		if (value==0) return 'S';
		else if(value==0.5) return 'C';
		else return 'Q';
	}

}
