/**
 * 0.0.0.1
 */
package com.auto.lang.observer;

/**
 * 自定义观察者模式的测试类
 * @author 高青
 * 2014-5-29
 */
public class MyObserverTest {

	/**
	 * 构造方法
	 * 2014-5-29
	 */
	public MyObserverTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 主线程方法
	 * @author 高青
	 * 2014-5-29
	 * @param args 字符串集
	 * @return void 空
	 */
	public static void main(String[] args) {
		/*
		 * 1、实例化主题对象
		 * 2、实例化观察者对象
		 * 3、将观察者注册到主题中
		 * 4、改变主题状态
		 * 5、执行通知操作
		 */
		
		ConcreteSubject subject = new ConcreteSubject();
		
		Observer observer_1 = new ConcreteObserver("gaoqing", "beijing");
		Observer observer_2 = new ConcreteObserver("lixiaoyang", "qingdao");
		
		subject.attach(observer_1, observer_2);
		
		for (int i = 0; i < 10; i++) {
			if (i == 5) {
				subject.setChanged(true);
				subject.notifyObserver();
			}
		}

	}

}
