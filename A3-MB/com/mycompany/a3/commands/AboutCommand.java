package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.model.GameWorld;

public class AboutCommand extends Command {

	private GameWorld gw;
	public AboutCommand(GameWorld gw) {
		super("About");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getKeyEvent() != -1) {
			Dialog.show("About","On Target Game by: Mary Ballesteros " + "Enjoy the game!\n" + 
					"Fall 2021\n" + 
					"Dr. Muyan", "OK", null);
		}
	}

}
