package com.mycompany.a2.views;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;
import com.mycompany.a2.model.GameWorld;

public class MapView extends Container implements Observer {

	public MapView() {
		
		this.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(255, 0, 0)));
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBgTransparency(255);
	}
	public void update(Observable o, Object arg) {
		System.out.println("***************************MAP VIEW************************\n");
		((GameWorld)arg).map();
		System.out.println("\n***************************************************");
		
	}
}
