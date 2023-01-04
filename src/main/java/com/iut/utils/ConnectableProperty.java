package com.iut.utils;

public class ConnectableProperty extends ObservableProperty implements Observer {

	protected boolean propagating;

	public ConnectableProperty() {
		propagating = false;
	}

	public void connectTo(ConnectableProperty other) {
		other.attach(this);
	}

	public void biconnectTo(ConnectableProperty other) {
		connectTo(other);
		update(other, other.getValue());
		other.connectTo(this);
	}

	public void unconnectFrom(ConnectableProperty other) {
		other.detach(this);
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(Object val) {
		if (!propagating) {
			propagating = true;
			super.setValue(val);
			propagating = false;
		}
	}

	@Override
	public void update(AbstractSubject subj) {
		// never called so does not do anything
	}

	@Override
	public void update(AbstractSubject other, Object data) {
		setValue(data);
	}

}
