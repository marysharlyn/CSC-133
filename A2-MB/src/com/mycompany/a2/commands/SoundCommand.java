package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.model.GameWorld;

public class SoundCommand extends Command {

	private GameWorld gw;
	public SoundCommand(GameWorld gw) {
		super("Sound");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getKeyEvent() != -1) {
			gw.changeSoundStatus();
		}
	}

}
