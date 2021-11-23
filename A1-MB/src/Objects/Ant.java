package Objects;

import com.codename1.charts.util.ColorUtil;

import Interfaces.ISteerable;


public class Ant extends Movable implements ISteerable{
	
	//attributes for the Ant class
	
	private final int SpeedLimit;
	private int maxSpeed;
	private int foodLevel;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	private int steerDegrees;
	
	//Behavior for the ant class
	
	public Ant() {
		
		// Setting the initial location of the Ant to the known location of flag number 1 (hard coded)
		setX(200);
		setY(200);
		
		SpeedLimit = 10;
		setSpeed(1);
		setheading(0);
		steerDegrees = 5;
		maxSpeed = SpeedLimit;
		foodLevel = 50;									// Initial value
		foodConsumptionRate = 2;						
		healthLevel = 10;
		lastFlagReached = 1;
		setSize(20);									// Setting the size of the Ant to 20
		
		// Setting the color to red
		this.setColor(ColorUtil.rgb(255, 0, 0));
		
	}
	
	public void update() {
		
		//update
		
	}

	//Method to steer the player ship left
	public void steerLeft() {
		
		setheading(getheading() + steerDegrees);
		update();
		
	}
	
	//Method to steer the player ship right
	public void steerRight() {
		
		setheading(getheading() - steerDegrees);
		update();
		
	}

	
	//Method to increase the speed of the player ship
	public void increaseSpeedAnt() {
		
		setSpeed(getSpeed() + 1);
		update();
		
	}
	
	//Method to decrease the speed of the player ship
	public void decreaseSpeedAnt() {
		
		setSpeed(getSpeed() - 1);
		update();
		
	}
	
	// Method to set the healthLevel of the Ant player
	public void setHealth(int val) {
		
		this.healthLevel = val;
		
	}
	
	// Method to get the healthLevel of the Ant player
	public int getHealth() {
		
		return this.healthLevel;
		
	}
	
	// Method to retrieve the lastFlagReached
	public int getLastFlag() {
		
		return this.lastFlagReached;
		
	}
	
	//Update color	
	public void updateColor(int r, int g, int b) {
		
		this.setColor(ColorUtil.rgb(r,g,b));
		
	}
	
	// Method to set the lastFlagReached
	public void setLastFlag(int val) {
		
		this.lastFlagReached = val;
		
	}
	
	// MEthod to set the food Level of the ant
	public void setFoodLevel(int val) {
		
		this.foodLevel = val;
		
	}
	
	// MEthod to get the food Level of the ant
	public int getFoodLevel() {
		
		return this.foodLevel;
		
	}
	
	// Method to set the max speed
	public void setMaxSpeed(int val) {
		
		this.maxSpeed = val;
		
	}
	
	//Get limit
	public int getLimit() {
		
		return this.SpeedLimit;
		
	}
	
	//Get max speed
	public int getMaxSpeed() {
		
		return this.maxSpeed;
		
	}
	
	//Method to over-ride the toString method
	public String toString() {
		
		String desc = "Ant: " + super.toString() + "maxSpeed=" + this.maxSpeed + "  foodComsumptionRate=" + this.foodConsumptionRate + "\n";
		return desc;
		
	}

	public void updateFood() {
		// TODO Auto-generated method stub
		
	}
}
