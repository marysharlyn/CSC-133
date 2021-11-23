package Objects;

import Enum.SpeedEnum;
import Enum.DirectionEnum;

import Interfaces.IMovable;
import com.codename1.ui.geom.Point2D;

public abstract class Movable extends GameObject implements IMovable{

	//Attributes for the class Movable----------------------------------------
	
	private int speed;
	private int heading;
	
	//Behaviors for the class Movable----------------------------------------
	
	//Constructor for the class Movable
	public Movable() {
		
		this.speed = getRandom().nextInt(SpeedEnum.MaxSpeed.speed);
		this.heading = getRandom().nextInt(DirectionEnum.MaxDegrees.degrees);
		
	}
	
	//Method to implement the move() method
	public void move() {
		
		Point2D oldLocation = getLocation();
		double delX = Math.cos(Math.toRadians(heading)) * speed;
		double delY = Math.sin(Math.toRadians(heading)) * speed;
		Point2D newLocation = new Point2D( (oldLocation.getX() + delX) , (oldLocation.getY() + delY) );
		setLocation(newLocation);
		
	}
	
	//Method to get the heading
	public int getheading() {
		
		return this.heading;
		
	}
	
	//Method to set the heading
	public void setheading(int newheading) {
		
		if(newheading > 359)
			newheading -= 360;
		else if(newheading < 0)
			newheading += 360;
		
		this.heading = newheading;
		
	}
	
	//Method to get the speed
	public int getSpeed() {
		
		return this.speed;
		
	}
	
	//Method to set new speed
	public void setSpeed(int newSpeed) {
		
		if(newSpeed <= 15 && newSpeed >= 0)
			this.speed = newSpeed;
		
	}
	
	//Method to override the default toString()
	public String toString() {
		
		String desc = super.toString() + " speed=" + this.getSpeed() + " dir=" + this.getheading();
		return desc;
		
	}
}
