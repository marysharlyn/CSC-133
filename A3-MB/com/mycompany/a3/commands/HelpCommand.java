package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.model.GameWorld;

public class HelpCommand extends Command {

	private GameWorld gw;
	public HelpCommand(GameWorld gw) {
		super("Help");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getKeyEvent() != -1) {
			Dialog.show("Help","Pres a to accelerate\n"
					+ "Press b for break\n"
					+ "Press l to left move\n"
					+ "Press r to right move\n"
					+ "Press f to collision with food stations\n"
					+ "Press g to collision with spider\n"
					+ "Press t to tick", "OK", null);
		}
	}


}
