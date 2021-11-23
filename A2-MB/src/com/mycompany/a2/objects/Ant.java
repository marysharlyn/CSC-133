package com.mycompany.a2.objects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.mycompany.a2.interfaces.Isteerable;

public class Ant extends Movable implements Isteerable {

	private int maximumSpeed;
	private final int MAXSPEED = 100;
	private int foodLevel;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	private int redValue;
	private static Ant antObj=new Ant(new Point(50,50));
	public Ant(Point location) {
		
		super(20, location, ColorUtil.rgb(255, 0, 0));
		setMaximumSpeed(MAXSPEED);
		setFoodLevel(100);
		setFoodConsumptionRate(2);
		setHealthLevel(10);
		setLastFlagReached(1);
		setHeading(0);
		setSpeed(10);
		redValue = 255;
	}

	@Override
	public void moveLeft() {
		setHeading(getHeading() - 5);
		if (getHeading() < 0) {
			setHeading(360 - -getHeading());
		}
	}

	@Override
	public void moveRight() {
		setHeading(getHeading() + 5);
		if (getHeading() >= 360) {
			setHeading(getHeading() - 360);
		}
	}

	@Override
	public void setSpeed(int speed) {
		if(getFoodLevel() == 0 || getHealthLevel() == 0 || speed < 0 )
			super.setSpeed(0);
		else if (speed <= getMaximumSpeed() && speed >= 0)
			super.setSpeed(speed);
	}
	
	public void collisionWithFlag(int sequenceNumber) {
		if(sequenceNumber == getLastFlagReached()+1) {
			setLastFlagReached(sequenceNumber);
		}
	}
	
	public void collisionWithSpider() {
		setHealthLevel(getHealthLevel()-1);
		if(getHealthLevel() < 0 )
			setHealthLevel(0);
		maximumSpeed = (int) (MAXSPEED * (getHealthLevel()/ 10.0));
		if (getSpeed() > maximumSpeed)
			setSpeed(maximumSpeed);
		redValue -= 25;
		setColor(ColorUtil.rgb(redValue, 0, 0));
	}

	public int getMaximumSpeed() {
		return maximumSpeed;
	}

	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	public int getFoodLevel() {
		return foodLevel;
	}

	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
		if(getFoodLevel() == 0)
			setSpeed(0);
	}

	public int getFoodConsumptionRate() {
		return foodConsumptionRate;
	}

	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate = foodConsumptionRate;
	}

	public int getHealthLevel() {
		return healthLevel;
	}

	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
		if(getHealthLevel() == 0)
			setSpeed(0);
	}

	public int getLastFlagReached() {
		return lastFlagReached;
	}

	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}

	public int getRedValue() {
		return redValue;
	}
	
	
	
	public static Ant getAntObj() {
		return antObj;
	}

	@Override
	public String toString() {

		return "Ant: "+super.toString()+"\n"
				+"maxSpeed="+getMaximumSpeed()+" foodConsumptionRate="+
				getFoodConsumptionRate();
	}

}
