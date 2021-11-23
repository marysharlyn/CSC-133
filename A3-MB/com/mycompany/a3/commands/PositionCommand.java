package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.controller.Game;
import com.mycompany.a3.model.GameWorld;

public class PositionCommand extends Command{

	private Game gw;
	
	public PositionCommand(Game gw) {
		super("Position");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getKeyEvent() != -1 && gw.isPaused()) {
			
		}
	}
}
