package com.mycompany.a3.objects;

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
		this.getAllStyles().setBgColor(ColorUtil.BLUE);
		this.getAllStyles().setFgColor(ColorUtil.WHITE);
		this.getAllStyles().setPadding(5,5,5,5);
	}
	
	/*creates a button without a title
	 * as a title will be replaced by the command call
	 */
	public ButtonClass()
	{
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.BLUE);
		this.getAllStyles().setFgColor(ColorUtil.WHITE);
		this.getAllStyles().setPadding(5,5,5,5);
	}

}
