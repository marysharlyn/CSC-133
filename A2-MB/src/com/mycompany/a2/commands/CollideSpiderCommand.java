package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.model.GameWorld;

public class CollideSpiderCommand extends Command{

	private GameWorld gw;
	public CollideSpiderCommand(GameWorld gw) {
		super("Collision With Spider");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getKeyEvent() != -1) {
			gw.collisionWithSpider();
		}
	}
}
