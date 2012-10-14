package com.gngrwzrd.android;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Vector;


public class EventDispatcher {
	
	protected HashMap<String, Vector<HashMap<String,Object>>> _listeners;
	
	public EventDispatcher() {
		_listeners = new HashMap<String, Vector<HashMap<String,Object>>>();
	}
	
	public void addEventListener(Object delegate, String name, String function) {
		//vars
		Vector<HashMap<String,Object>> vec = null;
		HashMap<String,Object> entry = null;
		
		//see if there's already entries for this event.
		//if so grap it's entry pointer. Otherwise add a
		//a new vector container for more entries.
		if(_listeners.containsKey(name)) {
			vec = _listeners.get(name);
		} else {
			vec = new Vector<HashMap<String,Object>>();
			_listeners.put(name,vec);
		}
		
		entry = new HashMap<String,Object>();
		entry.put("name",name);
		entry.put("delegate",delegate);
		if(function != null) entry.put("function",function);
		vec.add(entry);
	}
	
	public void removeEventListener(Object delegate, String name) {
		if(!_listeners.containsKey(name)) return;
		Vector<HashMap<String,Object>> entries = _listeners.get(name);
		String _name = null;
		String _object = null;
		for(HashMap<String,Object> hashMap : entries) {
			_name = (String)hashMap.get("name");
			_object = (String)hashMap.get("object");
			if(_name == name && _object == delegate) {
				entries.remove(hashMap);
			} else if(delegate == null && name == _name) {
				entries.remove(hashMap);
			}
		}
	}
	
	public void dispatchEvent(DelegateEvent event) {
		//vars
		Method listener = null;
		String function = null;
		String name = event.getName();
		Object delegate = null;
		
		//make sure there are any listeners.
		if(!_listeners.containsKey(name)) {
			return;
		}
		
		//get entries to go through.
		Vector<HashMap<String,Object>> entries = _listeners.get(name);
		
		//go through each entry and call it's callback function
		for(HashMap<String,Object> hashMap : entries) {
			if(hashMap.containsKey("function")) {
				function = (String)hashMap.get("function");
			}
			delegate = hashMap.get("delegate");
			
			try {
				if(function != null) {
					listener = delegate.getClass().getDeclaredMethod(function,DelegateEvent.class);
				} else {
					listener = delegate.getClass().getDeclaredMethod(name,DelegateEvent.class);
				}
				listener.invoke(delegate,event);
			}catch(Exception e) {
			}
		}
	}
}
