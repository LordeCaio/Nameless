package com.engine.data;

import java.util.Random;


public abstract class Functions{
	
	public static Random random = new Random();
	

	///===CHOOSE===///
	public static int choose(int... values) {
		return values[random.nextInt(values.length)];
	}
	
	public static double choose(double... values) {
		return values[random.nextInt(values.length)];
	}
	
	public static float choose(float... values) {
		return values[random.nextInt(values.length)];
	}
	
	public static String choose(String... values) {
		return values[random.nextInt(values.length)];
	}
	
	///===CLAMP===///
	public static int clamp(int value, int min, int max) {		
		if(value < min) {value = min;}
		if(value > max) {value = max;}
		return value;		
	}
	
	public static double clamp(double value, double min, double max) {		
		if(value < min) {value = min;}
		if(value > max) {value = max;}
		return value;		
	}
	
	public static float clamp(float value, float min, float max) {		
		if(value < min) {value = min;}
		if(value > max) {value = max;}
		return value;
		
	}
	
	///===LINEAR INTERPOLATION===///
	public static float lerp(float a, float b, float amount) {
		return (1-amount)*a + amount*b;	
	}	
	
	///===DISTANCE===///
	public double getDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
	}

	
}
