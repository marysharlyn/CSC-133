package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.model.GameWorld;

public class HelpCommand extends Command {

	private GameWorld gw;
	public HelpCommand(GameWorld gw) {
		super("Help");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getKeyEvent() != -1) {
			Dialog.show("Help","Press 'a' to Accelerate\n"
					+ "Press 'b' for Break\n"
					+ "Press 'l' to move Left\n"
					+ "Press 'r' to move Right\n"
					+ "Press 'f' to collide with Food Stations\n"
					+ "Press 'g' to collide with Spider\n"
					+ "Press 't' to Tick", "OK", null);
		}
	}


}
