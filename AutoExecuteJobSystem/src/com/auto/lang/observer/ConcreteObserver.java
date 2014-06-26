/**
 * 0.0.0.1
 */
package com.auto.lang.observer;

import org.apache.commons.lang3.StringUtils;

/**
 * 观察者的实现类
 * @author 高青
 * 2014-5-29
 */
public class ConcreteObserver implements Observer{
	
	private String name;
	private String address;

	/**
	 * 默认构造方法
	 * 2014-5-29
	 */
	public ConcreteObserver() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 构造方法
	 * @author 高青
	 * 2014-5-29
	 * @param name 姓名
	 * @param address地址
	 */
	public ConcreteObserver(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	@Override
	public void update() {
		
		System.out.println(StringUtils.join(name, ",", address, "观察者的更新方法执行了！"));
		
	}

}
