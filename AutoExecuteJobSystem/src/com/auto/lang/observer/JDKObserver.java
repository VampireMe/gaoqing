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
public class JDKObserver implements Observer{

	/**
	 * 
	 * 2014-5-29
	 */
	public JDKObserver() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("the observer is executing!");
	}

}
