package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.model.GameWorld;

public class BrakeCommand extends Command{

	private GameWorld gw;
	public BrakeCommand(GameWorld gw) {
		super("Brake");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getKeyEvent() != -1) {
			gw.brake();
		}
	}


}
