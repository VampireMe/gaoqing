/**
 * 0.0.0.1
 */
package com.auto.lang.observer;

import java.util.Vector;

/**
 * 主题的实现类
 * @author 高青
 * 2014-5-29
 */
public class ConcreteSubject implements Subject{
	
	/** 观察者集 */
	private Vector<Observer> observers;
	
	/** 状态是否改变 */
	private boolean isChanged = false;
	
	{
		observers = new Vector<Observer>();
	}

	/**
	 * 构造方法
	 * 2014-5-29
	 */
	public ConcreteSubject() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attach(Observer... observer) {
		//将观察者添加到集合中
		for (Observer observer2 : observer) {
			observers.addElement(observer2);
		}
	}

	@Override
	public void detach(Observer observer) {
		// 将观察者移除集合
		observers.removeElement(observer);
	}

	@Override
	public void notifyObserver() {
		// 判断当前状态是否改变
		if (isChanged) {
			/*
			 * 1、列出集合中的所有观察者
			 * 2、通知观察者，更新其方法
			 */
			
			//1、列出集合中的所有观察者
			for (Observer observer : observers) {
				
				//2、通知观察者，更新其方法
				observer.update();
			}
			//恢复状态
			isChanged = false;
		}
	}

	/**
	 * @return the observers
	 */
	public Vector<Observer> getObservers() {
		return observers;
	}

	/**
	 * @param observers the observers to set
	 */
	public void setObservers(Vector<Observer> observers) {
		this.observers = observers;
	}

	/**
	 * @return the isChanged
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * @param isChanged the isChanged to set
	 */
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
	
}
