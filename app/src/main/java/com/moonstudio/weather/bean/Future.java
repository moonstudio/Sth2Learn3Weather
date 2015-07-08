package com.moonstudio.weather.bean;

public class Future {
	/**
	 * 风力等级
	 */
	private String wind;
	/**
	 * 天气
	 */
	private String weather;
	/**
	 * 天气唯一标示a
	 */
	private String weatherIdFa;
	/**
	 * 天气唯一标示b
	 */
	private String weatherIdFb;
	/**
	 * 时间
	 */
	private String date;
	/**
	 * 星期
	 */
	private String week;
	/**
	 * 温度范围
	 */
	private String temperature;


	public String getWind() {
		return wind;
	}


	public void setWind(String wind) {
		this.wind = wind;
	}


	public String getWeather() {
		return weather;
	}


	public void setWeather(String weather) {
		this.weather = weather;
	}


	public String getWeatherIdFa() {
		return weatherIdFa;
	}


	public void setWeatherIdFa(String weatherIdFa) {
		this.weatherIdFa = weatherIdFa;
	}


	public String getWeatherIdFb() {
		return weatherIdFb;
	}


	public void setWeatherIdFb(String weatherIdFb) {
		this.weatherIdFb = weatherIdFb;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getWeek() {
		return week;
	}


	public void setWeek(String week) {
		this.week = week;
	}


	public String getTemperature() {
		return temperature;
	}


	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

}
