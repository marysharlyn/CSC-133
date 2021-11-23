package com.mycompany.a3.commands;

import com.codename1.ui.Command;

import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.controller.Game;
public class PauseCommand extends Command{

	private Game gw;
	
	public PauseCommand(Game gw) {
		super("Pause");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getKeyEvent() != -1) {
			 gw.handelPause();
		}
	}

}
