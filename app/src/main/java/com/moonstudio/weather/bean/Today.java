package com.moonstudio.weather.bean;

import java.util.List;


public class Today {
	/**
	 * 风力等级
	 */
	private String wind;
	/**
	 * 紫外线强度
	 */
	private String uvIndex;
	/**
	 * 旅游指数
	 */
	private String travelIndex;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 今日温度
	 */
	private String temperature;
	/**
	 * 舒适度指数
	 */
	private String comfortIndex;
	/**
	 * 日期
	 */
	private String dateY;
	/**
	 * 洗车指数
	 */
	private String washIndex;
	/**
	 * 晨练指数
	 */
	private String exerciseIndex;
	/**
	 * 今日天气
	 */
	private String weather;
	/**
	 * 干燥指数
	 */
	private String dryingIndex;
	/**
	 * 天气唯一标示a
	 */
	private String weatherIdFa;
	/**
	 * 天气唯一标示b
	 */
	private String weatherIdFb;
	/**
	 * 穿衣建议
	 */
	private String dressingAdvice;
	/**
	 * 穿衣指数
	 */
	private String dressingIndex;
	/**
	 * 星期
	 */
	private String week;
	/**
	 * 实时天气
	 */
	private Sk sk;
	/**
	 * 未来天气列表
	 */
	private List<Future> futureList;


	public List<Future> getFutureList() {
		return futureList;
	}


	public void setFutureList(List<Future> futureList) {
		this.futureList = futureList;
	}


	public Sk getSk() {
		return sk;
	}


	public void setSk(Sk sk) {
		this.sk = sk;
	}


	public String getWind() {
		return wind;
	}


	public void setWind(String wind) {
		this.wind = wind;
	}


	public String getUvIndex() {
		return uvIndex;
	}


	public void setUvIndex(String uvIndex) {
		this.uvIndex = uvIndex;
	}


	public String getTravelIndex() {
		return travelIndex;
	}


	public void setTravelIndex(String travelIndex) {
		this.travelIndex = travelIndex;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getTemperature() {
		return temperature;
	}


	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}


	public String getComfortIndex() {
		return comfortIndex;
	}


	public void setComfortIndex(String comfortIndex) {
		this.comfortIndex = comfortIndex;
	}


	public String getDateY() {
		return dateY;
	}


	public void setDateY(String dateY) {
		this.dateY = dateY;
	}


	public String getWashIndex() {
		return washIndex;
	}


	public void setWashIndex(String washIndex) {
		this.washIndex = washIndex;
	}


	public String getExerciseIndex() {
		return exerciseIndex;
	}


	public void setExerciseIndex(String exerciseIndex) {
		this.exerciseIndex = exerciseIndex;
	}


	public String getWeather() {
		return weather;
	}


	public void setWeather(String weather) {
		this.weather = weather;
	}


	public String getDryingIndex() {
		return dryingIndex;
	}


	public void setDryingIndex(String dryingIndex) {
		this.dryingIndex = dryingIndex;
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


	public String getDressingAdvice() {
		return dressingAdvice;
	}


	public void setDressingAdvice(String dressingAdvice) {
		this.dressingAdvice = dressingAdvice;
	}


	public String getDressingIndex() {
		return dressingIndex;
	}


	public void setDressingIndex(String dressingIndex) {
		this.dressingIndex = dressingIndex;
	}


	public String getWeek() {
		return week;
	}


	public void setWeek(String week) {
		this.week = week;
	}

}
