package com.mycompany.a3.objects;

import com.codename1.charts.models.Point;
import com.codename1.ui.geom.Point2D;

public abstract class Movable extends GameObject {

	private int heading , speed;
	public Movable(int size, Point location,int color) {
		super(size,location,color);
	}
	
	public void setHeading(int heading) {
		this.heading = heading;
	}
	
	public int getHeading() {
		return heading;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void move(int elapsedTime) {
		float x = (float) (speed*(elapsedTime/100)*Math.cos(Math.toRadians(90-getHeading()))) + getLocation().getX();
		float y = (float) (speed*(elapsedTime/100)*Math.sin(Math.toRadians(90-getHeading()))) + getLocation().getY();
		// prevent Spider to go out of Game World
		if(this instanceof Spider) {
			if(x <= 550 && y <= 300 && x >=0 && y >=0) {
				setLocation(new Point(x,y));
			}
			else {
				setHeading(getHeading() - 90);
				move(elapsedTime);
			}
		}
		else
			setLocation(new Point(x,y));
		
	}
	
	@Override
	public String toString() {
		// loc=180.2,450.3 color=[255,0,0] heading=355 speed=50 size=40
		return super.toString()+" heading="+getHeading()+
				" speed="+getSpeed()+" size="+getSize();
	}
}
