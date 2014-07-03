/**
 * 0.0.0.1
 */
package com.common.controlexcel;

/**
 * Person 实体类
 * @author gaoqing
 * 2014-7-3
 */
public class Person {
	
	/** 姓名 */
	private String name;
	/** 年龄 */
	private int age;
	/** 地址 */
	private String address;
	/** 身高 */
	private double tall;

	/**
	 * 构造方法
	 */
	public Person() {
		
	}

	/**
	 * 构造方法
	 */
	public Person(String name, int age, String address, double tall) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
		this.tall = tall;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the tall
	 */
	public double getTall() {
		return tall;
	}

	/**
	 * @param tall the tall to set
	 */
	public void setTall(double tall) {
		this.tall = tall;
	}

}
