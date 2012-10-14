package com.gngrwzrd.android;

public class DelegateEvent {
	
	public Object _data;
	public Object _source;
	public String _name;
	
	public DelegateEvent(Object source, String name, Object data) {
		_data = data;
		_source = source;
		_name = name;
	}
	
	public String getName() {
		return _name;
	}
	
	public Object getData() {
		return _data;
	}
	
	public Object getSource() {
		return _source;
	}
}
