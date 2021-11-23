package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.model.GameWorld;

public class AboutCommand extends Command {

	private GameWorld gw;
	public AboutCommand(GameWorld gw) {
		super("About");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getKeyEvent() != -1) {
			Dialog.show("About","The Target Game\n" + "A2 GUI by Mary Ballesteros\n" + "CSC 133-01\n" + 
					"FALL 2021\n" + 
					"Enjoy the Game!", "OK", null);
		}
	}

}
