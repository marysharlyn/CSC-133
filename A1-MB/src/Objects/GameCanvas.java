package Objects;

public class GameCanvas {
	
	// Attributes for the class GAmeCAnvas to set the limits for the size of the canvas.
	
	private static double minimum = 0.0;
	private static double maximumX = 1000.0;
	private static double maximumY = 1000.0;

	//Behaviors
	
	public static boolean rangeCheck(double xVal, double yVal) {
		
		if(xVal >= minimum && yVal >= minimum && xVal <= maximumX && yVal <= maximumY)
			return true;
		else
			return false;
	}
	
	public static boolean rangeCheckX(double xVal) {
		
		if(xVal >= minimum && xVal <= maximumX)
			return true;
		else
			return false;
	}
	
	public static boolean rangeCheckY(double yVal) {
		
		if(yVal >= minimum && yVal <= maximumY)
			return true;
		else
			return false;
	}
	
	//Method to get the min and max values for reference
	
	public double getCanvasMin() {
		
		return minimum;
	}
	
	public double getCanvasXMax() {
		
		return maximumX;
	}
	
	public double getCanvasYMax() {
		
		return maximumY;
		
	}
}
