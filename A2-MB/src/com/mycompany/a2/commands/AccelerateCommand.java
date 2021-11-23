package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.model.GameWorld;

public class AccelerateCommand extends Command{

	private GameWorld gw;
	public AccelerateCommand(GameWorld gw) {
		super("Accelerate");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getKeyEvent() != -1) {
			gw.accelerate();
		}
	}

}
