package com.iut.normalization;

import com.iut.model.IColumn;

public class OrdinalNormalizer implements IValueNormalizer {

	@Override
	public double normalize(IColumn column, Object value) {
		if((int)value==1) return 1;
		if((int)value==2) return 0.5;
		else return 0;

		
	}

	@Override
	public Object denormalize(IColumn column, double value) {
		if(value==1) return 1;
		if(value==0.5) return 2;
		else return 3;
	}

}
