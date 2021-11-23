package com.mycompany.a3.objects;

import com.codename1.charts.models.Point;
import com.mycompany.a3.interfaces.ISelectable;

public abstract class Fixed extends GameObject implements ISelectable{

	protected boolean selected;
	
	public Fixed(int size, Point location,int color) {
		super(size,location,color);
		selected = false;
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
