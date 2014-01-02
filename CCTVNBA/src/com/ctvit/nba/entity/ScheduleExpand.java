/**
 * 0.0.0.1
 */
package com.ctvit.nba.entity;

/**
 * 赛程信息拓展表
 * @author 高青
 * 2013-11-29
 */
public class ScheduleExpand<T> {
	/**赛程信息拓展表主键*/
	private String scheduleExpandID;
	
	/**动态字段名*/
	private String name;
	
	/**动态对应值*/
	private T value;
	
	/**赛程信息*/
	private Schedule schedule;

	/**
	 * @return the scheduleExpandID
	 */
	public String getScheduleExpandID() {
		return scheduleExpandID;
	}

	/**
	 * @param scheduleExpandID the scheduleExpandID to set
	 */
	public void setScheduleExpandID(String scheduleExpandID) {
		this.scheduleExpandID = scheduleExpandID;
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
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * @return the schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
}
