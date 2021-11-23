package com.mycompany.a2.objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.plaf.Border;

public class ButtonClass extends Button
{
	public ButtonClass(String title)
	{
		super(title);
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.GRAY);
		this.getAllStyles().setFgColor(ColorUtil.WHITE);
		this.getAllStyles().setPadding(5,5,5,5);
	}
	
	public ButtonClass()
	{
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.GRAY);
		this.getAllStyles().setFgColor(ColorUtil.WHITE);
		this.getAllStyles().setPadding(5,5,5,5);
	}

}
