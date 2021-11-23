package com.mycompany.a2.objects;

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
	
	public void move() {
		float x = (float) (speed*Math.cos(Math.toRadians(90-getHeading()))) + getLocation().getX();
		float y = (float) (speed*Math.sin(Math.toRadians(90-getHeading()))) + getLocation().getY();

		if(this instanceof Spider) {
			if(x <= 1000 && y <= 1000 && x >=0 && y >=0) {
				setLocation(new Point(x,y));
			}
			else {
				setHeading(getHeading() - 90);
				move();
			}
		}
		else
			setLocation(new Point(x,y));
		
	}
	
	@Override
	public String toString() {
		return super.toString()+" heading="+getHeading()+
				" speed="+getSpeed()+" size="+getSize();
	}
}
