package com.mycompany.a2.objects;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class FoodStation extends Fixed {

	private int capacity;

	public FoodStation() {
		super((10 + new Random().nextInt(41)),
				new Point(1000 * new Random().nextFloat(), 1000 * new Random().nextFloat()), ColorUtil.rgb(0, 255, 0));
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

}
