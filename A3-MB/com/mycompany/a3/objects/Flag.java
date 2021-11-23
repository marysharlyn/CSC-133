package com.mycompany.a3.objects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.interfaces.IDrawable;

// size of all flags can be 10
public class Flag extends Fixed implements IDrawable{

	private int sequenceNumber;
	private boolean reached;
	private int x1 , x2 , x3 , y1 , y2 , y3;
	public Flag(Point location, int sequenceNumber) {
		super(60,location,ColorUtil.rgb(0,0, 255));
		this.sequenceNumber = sequenceNumber;
		reached = false;
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public boolean isReached() {
		return reached;
	}
	public void setReached(boolean reached) {
		this.reached = reached;
	}
	
	@Override
	public String toString() {
		// Flag: loc=200.0,200.0 color=[0,0,255] size=10 seqNum=1
		return "Flage: "+super.toString()+
				" size="+getSize()+" seqNum="+getSequenceNumber();
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
		if(this.selected)
			g.drawPolygon(pointsx, pointsy, 3);
		else
			g.fillPolygon(pointsx, pointsy, 3);
		g.setColor(ColorUtil.BLACK);
		g.drawString(""+this.getSequenceNumber(),(int)(x1+this.getSize()/3) ,(int)(y1+this.getSize()/4));
	
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
