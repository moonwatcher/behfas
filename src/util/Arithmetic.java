package util;

public final class Arithmetic {
	public static double max(double a, double b){
		return (a > b ? a : b);
	}
	
	public static double max(double a, double b, double c){
		return (a > b ?(a > c ? a : c) : (b > c ? b : c)); 	
	}
	
	public static double square(double a){
		return a*a;
	}
}
