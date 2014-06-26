/**
 * 0.0.0.1
 */
package com.auto.lang.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @author 高青
 * 2014-5-29
 */
public class JDKObserverable extends Observable{

	/**
	 * 
	 * 2014-5-29
	 */
	public JDKObserverable() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public synchronized void addObserver(Observer o) {
		// TODO Auto-generated method stub
		super.addObserver(o);
	}
	
	@Override
	public synchronized void deleteObserver(Observer o) {
		// TODO Auto-generated method stub
		super.deleteObserver(o);
	}
	
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		super.notifyObservers();
	}

	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		super.notifyObservers(arg);
	}

	@Override
	protected synchronized void setChanged() {
		// TODO Auto-generated method stub
		super.setChanged();
	}
	
	@Override
	protected synchronized void clearChanged() {
		// TODO Auto-generated method stub
		super.clearChanged();
	}
	
	@Override
	public synchronized boolean hasChanged() {
		// TODO Auto-generated method stub
		return super.hasChanged();
	}
	
	

}
