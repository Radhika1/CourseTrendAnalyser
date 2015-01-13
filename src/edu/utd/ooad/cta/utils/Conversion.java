package edu.utd.ooad.cta.utils;

public class Conversion {
	public static double Convert(int individualWeightage, int totalWeightage){
		return (Math.round(((individualWeightage*totalWeightage)/100)*100)/100);
	}
}
