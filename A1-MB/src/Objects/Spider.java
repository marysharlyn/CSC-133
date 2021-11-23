package Objects;

import com.codename1.charts.util.ColorUtil;

import Interfaces.ISteerable;

public class Spider extends Movable implements ISteerable{

	// Attributes for the Spider class
	
	
	// Behaviors for the Spider class 
	
	public Spider() {
		
		// Setting the color of the spider to black
		this.setColor(ColorUtil.rgb(0, 0, 0));
		
		
	}

	@Override
	public void steerLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void steerRight() {
		// TODO Auto-generated method stub
		
	}
	
	//Method to over-ride the toString method
	public String toString() {
		
		String desc = "Spider: " + super.toString() + "\n";
		return desc;
		
	}

	public void updateDirection() {
		// TODO Auto-generated method stub
		
	}
	
}
