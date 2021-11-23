package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


public class Game extends Form{

	private GameWorld gw;
	
	public Game() {
		
		gw = new GameWorld();
		gw.init();
		play();
		
	}
	
	//method that accepts user commands
	private void play() {
		
		Label myLabel=new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField=new TextField();
		this.addComponent(myTextField);
		this.show();
		
		myTextField.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent evt) {
				String sCommand=myTextField.getText().toString();
				myTextField.clear();
				if(sCommand.length() != 0)
					switch (sCommand.charAt(0)) {
					
					case 'a':
						gw.accelerate();
						break;
						
					case 'b':
						gw.brake();
						break;
						
					case 'l':
						gw.left();
						break;
						
					case 'r':
						gw.right();
						break;
						
					case '1':
						gw.collideflag(1);
						break;
					case '2':
						gw.collideflag(2);
						break;
					case '3':
						gw.collideflag(3);
						break;
					case '4':
						gw.collideflag(4);
						break;
					case 'f':
						gw.collideFoodStation();
						break;
					
					case 'g':
						gw.collideSpider();
						break;
					
					case 't':
						gw.clockTicked();
						break;
						
					case 'd':
						gw.display();
						break;
					
					case 'm':
						gw.showMap();
						break;
						
					case 'x':
						gw.exit();
						break;
					
					//add code that handles rest of the commands
			
			} //switch
			} //actionPerformed
			} //new ActionListener()
			); //addActionListener
	
		
	}
	
}
