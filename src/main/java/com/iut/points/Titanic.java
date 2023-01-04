package com.iut.points;

import com.iut.model.IColumn;
import com.opencsv.bean.CsvBindByName;

public class Titanic implements IPoint {
	public static final String HEADER = "Survived,Pclass,Sex,Age,SibSp,Fare,Embarked";

	@CsvBindByName(column = "Survived")
	protected int survived;

	@CsvBindByName(column = "Pclass")
	protected int pClass;

	@CsvBindByName(column = "Sex")
	protected String sex;

	@CsvBindByName(column = "Age")
	protected double age;

	@CsvBindByName(column = "SibSp")
	protected int sibSp;

	@CsvBindByName(column = "Fare")
	protected double fare;

	@CsvBindByName(column = "Embarked")
	protected char embarked;

	public Titanic(int survived, int pclass, String sex, double age, int sibsp,
			double fare, char embarked) {
		this.survived = survived;
		this.pClass = pclass;
		this.sex = sex;
		this.age = age;
		this.sibSp = sibsp;
		this.fare = fare;
		this.embarked = embarked;
	}

	public Titanic() {
	}

	@Override
	public Object getValue(IColumn col) {
		if (col.getName().equalsIgnoreCase("Survived"))
			return survived;
		else if (col.getName().equalsIgnoreCase("Pclass"))
			return pClass;
		else if (col.getName().equalsIgnoreCase("Sex"))
			return sex;
		else if (col.getName().equalsIgnoreCase("Age"))
			return age;
		else if (col.getName().equalsIgnoreCase("SibSp"))
			return sibSp;
		else if (col.getName().equalsIgnoreCase("Fare"))
			return fare;
		else if (col.getName().equalsIgnoreCase("Embarked"))
			return embarked;
		return null;
	}
	
	

	@Override
	public double getNormalizedValue(IColumn xcol) {
		return xcol.getNormalizedValue(this);
	}

	@Override
	public String toString() {
		return "Titanic [survived=" + survived + ", pClass=" + pClass + ", sex=" + sex + ", age=" + age + ", sibSp="
				+ sibSp + ", fare=" + fare + ", embarked=" + embarked + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + survived;
		result = prime * result + pClass;
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		long temp;
		temp = Double.doubleToLongBits(age);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) sibSp;
		temp = Double.doubleToLongBits(fare);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + embarked;
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
		Titanic other = (Titanic) obj;
		if (survived != other.survived)
			return false;
		if (pClass != other.pClass)
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (Double.doubleToLongBits(age) != Double.doubleToLongBits(other.age))
			return false;
		if (sibSp != other.sibSp)
			return false;
		if (Double.doubleToLongBits(fare) != Double.doubleToLongBits(other.fare))
			return false;
		if (embarked != other.embarked)
			return false;
		return true;
	}

	@Override
	public Object getCategory() {
		return survived;
	}

	@Override
	public String getHeader() {
		return HEADER;
	}
}
