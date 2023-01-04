package com.iut.points;

import com.iut.model.IColumn;
import com.opencsv.bean.CsvBindByName;

public class Iris implements IPoint {

	public static final String HEADER = "sepal.length,sepal.width,petal.length,petal.width,variety";

	@CsvBindByName(column = "variety")
	protected String variety;
	
	@CsvBindByName(column = "sepal.length")
	protected double sepalLength;

	@CsvBindByName(column = "sepal.width")
	protected double sepalWidth;

	@CsvBindByName(column = "petal.length")
	protected double petalLength;

	@CsvBindByName(column = "petal.width")
	protected double petalWidth;

	public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String variety) {
		this.sepalLength = sepalLength;
		this.sepalWidth = sepalWidth;
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
		this.variety = variety;
	}

	public Iris() {
	}

	@Override
	public Object getValue(IColumn col) {
		String nameCol = col.getName();
		if (nameCol.equalsIgnoreCase("sepallength"))
			return this.sepalLength;
		else if (nameCol.equalsIgnoreCase("sepalwidth"))
			return this.sepalWidth;
		else if (nameCol.equalsIgnoreCase("petallength"))
			return this.petalLength;
		else if (nameCol.equalsIgnoreCase("petalwidth"))
			return this.petalWidth;
		return this.variety;
	}

	@Override
	public double getNormalizedValue(IColumn xcol) {
		return xcol.getNormalizedValue(this);
	}

	@Override
	public String toString() {
		return "Iris [sepalLength=" + sepalLength + ", sepalWidth=" + sepalWidth + ", petalLength=" + petalLength
				+ ", petalWidth=" + petalWidth + ", variety=" + variety + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(sepalLength);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(sepalWidth);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(petalLength);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(petalWidth);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((variety == null) ? 0 : variety.hashCode());
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
		Iris other = (Iris) obj;
		if (Double.doubleToLongBits(sepalLength) != Double.doubleToLongBits(other.sepalLength))
			return false;
		if (Double.doubleToLongBits(sepalWidth) != Double.doubleToLongBits(other.sepalWidth))
			return false;
		if (Double.doubleToLongBits(petalLength) != Double.doubleToLongBits(other.petalLength))
			return false;
		if (Double.doubleToLongBits(petalWidth) != Double.doubleToLongBits(other.petalWidth))
			return false;
		if (variety == null) {
			if (other.variety != null)
				return false;
		} else if (!variety.equals(other.variety))
			return false;
		return true;
	}

	@Override
	public Object getCategory() {
		return this.variety;
	}

	@Override
	public String getHeader() {
		return HEADER;
	}
}
