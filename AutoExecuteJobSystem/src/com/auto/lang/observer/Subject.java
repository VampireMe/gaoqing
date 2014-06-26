/**
 * 0.0.0.1
 */
package com.auto.lang.observer;

/**
 * 主题接口
 * @author 高青
 * 2014-5-29
 */
public interface Subject {
	
	/**
	 * 添加观察者
	 * @author 高青
	 * 2014-5-29
	 * @param observer 观察者
	 * @return 空
	 */
	public void attach(Observer... observer);
	
	/**
	 * 删除观察者
	 * @author 高青
	 * 2014-5-29
	 * @param observer 观察者
	 * @return 空
	 */
	public void detach(Observer observer);
	
	/**
	 * 
	 * 通过观察者
	 * @author 高青
	 * 2014-5-29
	 */
	public void notifyObserver();

}
