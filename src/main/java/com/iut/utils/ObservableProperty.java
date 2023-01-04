package com.iut.utils;

public class ObservableProperty extends AbstractSubject {

	protected Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object val) {
		value = val;
		notifyObservers(val);
	}
}
