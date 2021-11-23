package com.mycompany.a3.objects;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.interfaces.IDrawable;

public class FoodStation extends Fixed implements IDrawable{

	/**
	 * The initial capacity is proportional to the size of the food station
	 */
	private int capacity;

	public FoodStation() {
		super((15 + new Random().nextInt(41)),
				new Point(550 * new Random().nextFloat(), 300 * new Random().nextFloat()), ColorUtil.rgb(0, 255, 0));
		this.capacity = getSize();
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public String toString() {
		return "FoodStation: "+super.toString()+
				" size="+getSize()+" capacity="+getCapacity();
	}

	@Override
	public void draw(Graphics g, Point2D pCmpRelPrnt) {
		// pCmpRelPrnt.getX()   121.0
		// pCmpRelPrnt.getY()  32.0
		// max x = 550
		// max y = 300
		g.setColor(this.getColor());
		if(this.selected)
			g.drawRect((int)(this.getLocation().getX() + pCmpRelPrnt.getX()-this.getSize()/2),(int)( this.getLocation().getY() + pCmpRelPrnt.getY()-this.getSize()/2), this.getSize(),this.getSize());
		else
			g.fillRect((int)(this.getLocation().getX() + pCmpRelPrnt.getX()-this.getSize()/2),(int)( this.getLocation().getY() + pCmpRelPrnt.getY()-this.getSize()/2), this.getSize(),this.getSize());
		g.setColor(ColorUtil.BLACK);
		g.drawString(""+this.getCapacity(), (int)(this.getLocation().getX() + pCmpRelPrnt.getX()-this.getSize()/2+this.getSize()/4),(int)( this.getLocation().getY() + pCmpRelPrnt.getY()-this.getSize()/2+this.getSize()/4));
	}

	@Override
	public boolean collidesWith(GameObject otherObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	@Override
	public boolean isSelected() {
		return this.selected;
	}
	@Override
	public boolean contains(Point2D p1, Point2D p2) {
		int x1 = (int) p1.getX();
		int y1 = (int) p1.getY();
		int x2 = (int) (p2.getX() + this.getLocation().getX());
		int y2 = (int) (p2.getY() + this.getLocation().getY());
		
		if((x1 >= x2 - getSize()/2) && (x1 <= x2 + this.getSize()/2)
			&& (y1 >= y2 - getSize()/2) && (y1 <= y2 + this.getSize()/2))
			return true;
		else
			return false;
	}

}
