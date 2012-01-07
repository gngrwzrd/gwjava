package com.gngrwzrd;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewController {
	
	protected Object _delegate;
	protected Activity _activity;
	protected View _view;
	protected int _resource;
	protected EventDispatcher _dispatcher;
	
	public static void removeViewFromParentView(View view) {
		if(view.getParent() == null) return;
		((ViewGroup)view.getParent()).removeView(view);
	}
	
	public ViewController(int resource) {
		_resource = resource;
		init();
	}
	
	public ViewController(Activity activity, int resource) {
		_activity = activity;
		_resource = resource;
		init();
	}
	
	protected void init() {
		_dispatcher = new EventDispatcher();
	}
	
	public void addEventListener(Object delegate, String name, String function) {
		if(_dispatcher == null) _dispatcher = new EventDispatcher();
		_dispatcher.addEventListener(delegate,name,function);
	}
	
	public void dispatchEvent(DelegateEvent event) {
		if(_dispatcher == null) _dispatcher = new EventDispatcher();
		_dispatcher.dispatchEvent(event);
	}
	
	public void setActivity(Activity activity) {
		_activity = activity;
	}
	
	public void setDelegate(Object delegate) {
		_delegate = delegate;
	}
	
	public View getView() {
		if(_view != null) return _view;
		LayoutInflater inflater = _activity.getLayoutInflater();
		_view = inflater.inflate(_resource,null);
		viewDidLoad();
		return _view;
	}
	
	protected void viewDidLoad() {
	}
	
	public void showAlert(String messageTitle, String messageBody, String closeButtonTitle){
		
	}
}
