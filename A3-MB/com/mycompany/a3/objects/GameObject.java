package com.mycompany.a3.objects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.mycompany.a3.interfaces.ICollider;

public abstract class GameObject implements ICollider{

	private int size;
	// The point (X,Y) is the center of the object
	private Point location;
	private int color;

	public GameObject(int size, Point location,int color) {
		this.size = size;
		this.location = location;
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public Point getLocation() {
		return location;
	}
	
	public int getColor() {
		return this.color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public void setLocation(Point newLocation) {
		this.location = newLocation;
	}
	
	@Override
	public String toString() {
		return "loc="+Math.round(getLocation().getX()*10.0)/10.0+","+
				Math.round(getLocation().getY()*10.0)/10.0+
				" color=["+ColorUtil.red(getColor())+","+
				ColorUtil.green(getColor())+","+
				ColorUtil.blue(getColor())+","+"]";
	}
}
