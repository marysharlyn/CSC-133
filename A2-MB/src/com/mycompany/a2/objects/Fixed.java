package com.mycompany.a2.objects;

import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject{

	public Fixed(int size, Point location,int color) {
		super(size,location,color);
	}
	
	public void setColor(int color) {
		
	}
	
	public void setLocation(Point newLocation) {
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
