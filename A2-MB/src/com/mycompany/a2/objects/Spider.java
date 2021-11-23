package com.mycompany.a2.objects;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Spider extends Movable {
	public Spider() {
		super((10 + new Random().nextInt(41)),
				new Point(1000 * new Random().nextFloat(), 1000 * new Random().nextFloat()), ColorUtil.rgb(0, 0, 0));
		setSpeed(5 + new Random().nextInt(6));
		setHeading(new Random().nextInt(360));
	}
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
}
