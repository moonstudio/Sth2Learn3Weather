package com.moonstudio.weather.bean;

public class Sk {
	/**
	 * 当前风力
	 */
	private String windStrength;
	/**
	 * 当前温度
	 */
	private String temp;
	/**
	 * 更新时间
	 */
	private String time;
	/**
	 * 当前湿度
	 */
	private String humidity;
	/**
	 * 当前风向
	 */
	private String windDirection;


	public String getWindStrength() {
		return windStrength;
	}


	public void setWindStrength(String windStrength) {
		this.windStrength = windStrength;
	}


	public String getWindDirection() {
		return windDirection;
	}


	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}


	public String getTemp() {
		return temp;
	}


	public void setTemp(String temp) {
		this.temp = temp;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getHumidity() {
		return humidity;
	}


	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

}
