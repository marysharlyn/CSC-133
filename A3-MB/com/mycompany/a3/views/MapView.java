package com.mycompany.a3.views;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.interfaces.IDrawable;
import com.mycompany.a3.interfaces.IIterator;
import com.mycompany.a3.interfaces.ISelectable;
import com.mycompany.a3.model.GameWorld;
import com.mycompany.a3.objects.Ant;
import com.mycompany.a3.objects.Flag;
import com.mycompany.a3.objects.FoodStation;
import com.mycompany.a3.objects.GameObject;
import com.mycompany.a3.objects.GameWorldCollection;
import com.mycompany.a3.objects.Spider;

/**
 * this class containing code to output the map
 */
public class MapView extends Container implements Observer {
	
	private GameWorld gw;
	private GameWorldCollection gwc;
	private IIterator it;
	private GameObject gameObject;
	private Point2D location , p1 , p2;
	
	public MapView() {
		
		this.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(255, 0, 0)));
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBgTransparency(255);
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);	
		location = new Point2D(getX(),getY());	
		// draw Game Objects
		it = gwc.getIterator();
		gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof IDrawable)
				((IDrawable) gameObject).draw(g, location);
		}
	}
	
	public void pointerPressed(int x, int y)
	{
		if(gw.isPaused())
		{
			x = x - getParent().getAbsoluteX();
			y = y - getParent().getAbsoluteY();
			p1 = new Point2D(x,y);
			p2 = new Point2D(getX(), getY());
			gwc = gw.getGwc();
			IIterator iter = gwc.getIterator();
			while(iter.hasNext())
			{
				gameObject = (GameObject)iter.getNext();
				if(gameObject instanceof ISelectable)
				{
					if( ((ISelectable) gameObject).contains(p1, p2))	
					{
						((ISelectable) gameObject).setSelected(true);
					}else
						((ISelectable) gameObject).setSelected(false);
				}
			}
		}
		gw.notifyObservers();
		repaint();
	}
	
	public void update(Observable o, Object arg) {
		if(! (arg instanceof GameWorld))
			return;
		gw = (GameWorld)arg ;
		// code here to call the method in GameWorld (Observable) that output the
		// game object information to the console
		System.out.println("***************************MAP************************\n");
		gw.map();
		System.out.println("\n***************************************************");
		gwc = gw.getGwc();
		repaint();
		
	}

	
	
	public Point2D getLocation() {
		return location;
	}
	
	
}
