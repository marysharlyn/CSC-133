package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.model.GameWorld;

public class ExitCommand extends Command {

	private GameWorld gw;
	public ExitCommand(GameWorld gw) {
		super("Exit");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getKeyEvent() != -1) {
			gw.exit();
		}
	}



}
