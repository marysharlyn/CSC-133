package com.mycompany.a3.objects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.interfaces.IDrawable;
import com.mycompany.a3.interfaces.Isteerable;

public class Ant extends Movable implements Isteerable ,IDrawable{

	private int maximumSpeed;
	private final int MAXSPEED = 100;

	private int foodLevel;

	private int foodConsumptionRate;
	private int healthLevel;

	private int lastFlagReached;
	private int redValue;
	private static Ant antObj=new Ant(new Point(50,50));
	private Ant(Point location) {
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
	/**
	 * this function handel collision Ant with all Game Objects
	 * Flag , Spider and FoodStation
	 * @param obj
	 */
	public void handleCollision(GameObject obj) { 
		// if collision with Spider occurs
		if( obj instanceof Spider) {
			setHealthLevel(getHealthLevel()-1);
			if(getHealthLevel() < 0 )
				setHealthLevel(0);
			maximumSpeed = (int) (MAXSPEED * (getHealthLevel()/ 10.0));
			if (getSpeed() > maximumSpeed)
				setSpeed(maximumSpeed);
			redValue -= 25;
			setColor(ColorUtil.rgb(redValue, 0, 0));
			
		}
		// if collision with FoodStation occurs
		else if(obj instanceof FoodStation && ((FoodStation)obj).getCapacity() != 0 ) {
			setFoodLevel(getFoodLevel() + ((FoodStation)obj).getCapacity());
			((FoodStation)obj).setCapacity(0);
		}
		// if collision with Flag occurs
		else if(obj instanceof Flag && ((Flag)obj).getSequenceNumber() == getLastFlagReached()+1) {
			setLastFlagReached(((Flag)obj).getSequenceNumber());
			((Flag)obj).setReached(true);
		}
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
		//Ant: loc=180.2,450.3 color=[255,0,0] heading=355 speed=50 size=40
		// maxSpeed=50 foodConsumptionRate=2
		return "Ant: "+super.toString()+"\n"
				+"maxSpeed="+getMaximumSpeed()+" foodConsumptionRate="+
				getFoodConsumptionRate();
	}

	@Override
	public void draw(Graphics g, Point2D pCmpRelPrnt) {
		g.setColor(this.getColor());
		g.fillArc((int)(this.getLocation().getX() + pCmpRelPrnt.getX() - this.getSize()/2),(int)(this.getLocation().getY() + pCmpRelPrnt.getY()- this.getSize()/2), getSize(),getSize(), 0, 360);
	}

	@Override
	public boolean collidesWith(GameObject otherObject) {
		int centerX = (int)this.getLocation().getX() + this.getSize()/2;
		int centerY = (int)this.getLocation().getY() + this.getSize()/2;
		int otherObjectCenterX = (int)(otherObject.getLocation().getX() + otherObject.getSize()/2);
		int otherObjectCenterY = (int)(otherObject.getLocation().getY() + otherObject.getSize()/2);
		int dx = centerX - otherObjectCenterX;
		int dy = centerY - otherObjectCenterY;
		int dxy = (dx*dx + dy*dy);
		int radius = this.getSize()/2;
		int otherObjectRadius = otherObject.getSize()/2;	
		int dRadius = (radius * radius + 2*radius*otherObjectRadius + otherObjectRadius*otherObjectRadius);
		if(dxy <= dRadius)
			return true;
		return false;
	}

}
