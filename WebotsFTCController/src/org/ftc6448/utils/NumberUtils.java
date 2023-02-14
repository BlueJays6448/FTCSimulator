package org.ftc6448.utils;

public class NumberUtils {

	public static final double distance(double x,double y,double x1,double y1) {
	    double dx = x - x1;
	    double dy = y - y1;

	    return Math.sqrt(dx * dx + dy * dy); 
	}
	
	public static final double scale(final double valueIn, final double baseMin, final double baseMax, final double limitMin, final double limitMax) {
       
		double value=limit(valueIn,baseMin,baseMax);
		
		return ((limitMax - limitMin) * (value - baseMin) / (baseMax - baseMin)) + limitMin;
    }
	
	
	public static final double limit(double value,double min,double max) {
	    return (value >max) ?max : (value < min ? min: value );
	}
	

	public static final double normalizeHeading(double angleDegrees) {
		//make it positive via a multiple of 360 and then get remainder
		return (angleDegrees+3600)%360; 
	}

}
