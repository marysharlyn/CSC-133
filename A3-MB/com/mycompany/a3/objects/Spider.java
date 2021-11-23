package com.mycompany.a3.objects;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.interfaces.IDrawable;

public class Spider extends Movable implements IDrawable{

	private int x1 , x2 , x3 , y1 , y2 , y3;
	/**
	 * moveable (but not steerable) objects which walk on the ground the path is on.
	 * They add (or subtract) small random values (e.g., 5 degrees) to their heading
	 * while they move so as to not run in a straight line. If the spider’s center
	 * hits a side of the world, it changes heading and does not move out of bounds.
	 * If the spider gets to the same location as the ant it decreases the health
	 * level of the ant by one. Spiders are not allowed to change color once they
	 * are created. Speed of a spider should be initialized to a reasonable random
	 * value (e.g., ranging between 5 and 10) at the time of instantiation. Heading
	 * of a spider should be initialized to a random value (ranging between 0 and
	 * 359) at the time of instantiation.
	 */
	public Spider() {
		super((60 + new Random().nextInt(10)),
				new Point(550 * new Random().nextFloat(), 300 * new Random().nextFloat()), ColorUtil.rgb(0, 0, 0));
		setSpeed(5 + new Random().nextInt(6));
		setHeading(new Random().nextInt(360));
	}
	/**
	 * Spiders are not allowed to change color once they are created
	 */
	public void setColor(int color) {
	}

	public void updateHeading() {
		setHeading(getHeading() - 5);
		if (getHeading() < 0) {
			setHeading(360 - -getHeading());
		}
	}
	
	@Override
	public String toString() {
		
		return "Spider: "+super.toString();
	}
	@Override
	public void draw(Graphics g, Point2D pCmpRelPrnt) {
		// TODO Auto-generated method stub
		g.setColor(this.getColor());
		x1 = (int)(this.getLocation().getX() + pCmpRelPrnt.getX() - getSize()/2);
		y1 = (int)( this.getLocation().getY() + pCmpRelPrnt.getY()- getSize()/2);
		x2 = x1 + this.getSize();
		y2 = y1;
		x3 = (int)(x1+this.getSize()/2);
		y3 = y1+this.getSize();
		int pointsx[] = {x1 , x2 , x3};
		int pointsy[] = {y1 , y2 , y3};
		g.drawPolygon(pointsx, pointsy, 3);
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
}
