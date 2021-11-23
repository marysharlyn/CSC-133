package com.mycompany.a3.commands;
//
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.model.GameWorld;

public class AccelerateCommand extends Command{

	private GameWorld gw;
	public AccelerateCommand(GameWorld gw) {
		super("Accelerate");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getKeyEvent() != -1) {
			gw.accelerate();
		}
	}

}
