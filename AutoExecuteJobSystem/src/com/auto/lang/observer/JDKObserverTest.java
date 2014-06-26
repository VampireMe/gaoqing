/**
 * 0.0.0.1
 */
package com.auto.lang.observer;

/**
 * 
 * @author 高青
 * 2014-5-29
 */
public class JDKObserverTest {

	/**
	 * 
	 * 2014-5-29
	 */
	public JDKObserverTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @author 高青
	 * 2014-5-29
	 * @param 
	 * @return 
	 */
	public static void main(String[] args) {
		JDKObserverable observerable = new JDKObserverable();
		observerable.addObserver(new JDKObserver());
		
		observerable.setChanged();
		
		observerable.notifyObservers();

	}

}
