package com.mycompany.a2.controller;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a2.commands.AboutCommand;
import com.mycompany.a2.commands.AccelerateCommand;
import com.mycompany.a2.commands.BrakeCommand;
import com.mycompany.a2.commands.CollideFlagCommand;
import com.mycompany.a2.commands.CollideFoodCommand;
import com.mycompany.a2.commands.CollideSpiderCommand;
import com.mycompany.a2.commands.ExitCommand;
import com.mycompany.a2.commands.HelpCommand;
import com.mycompany.a2.commands.LeftCommand;
import com.mycompany.a2.commands.RightCommand;
import com.mycompany.a2.commands.SoundCommand;
import com.mycompany.a2.commands.TickCommand;
import com.mycompany.a2.model.GameWorld;
import com.mycompany.a2.objects.ButtonClass;
import com.mycompany.a2.views.MapView;
import com.mycompany.a2.views.ScoreView;


public class Game extends Form {

	private GameWorld gw;
	private MapView mv; 
	private ScoreView sv; 
	private Container westContainer , eastContainer , southContainer;
	private Command accelerateCommand, brakeCommand, leftCommand, 
			rightCommand, collideFoodCommand , collideSpiderCommand , tickCommand,
			collideFlagCommand , soundCommand, aboutCommand , helpCommand
			,exitCommand;
	private ButtonClass accelerateButton , leftButton , brakeButton , rightButton ,
	collideFoodButton , collideSpiderButton , tickButton , collideFlagButton,
	accelerateButtonbar , aboutButton , helpButton, exitButton;
	private CheckBox soundCheckBox;
	private Toolbar tb;

	public Game() {
		mv = new MapView(); 
		sv = new ScoreView(); 
		gw = new GameWorld(); 
		gw.addObserver(mv); 
		gw.addObserver(sv); 


		this.show();

		gw.init(); 

		accelerateCommand = new AccelerateCommand(gw);
		brakeCommand = new BrakeCommand(gw);
		leftCommand = new LeftCommand(gw);
		rightCommand = new RightCommand(gw);
		collideFoodCommand = new CollideFoodCommand(gw);
		collideSpiderCommand = new CollideSpiderCommand(gw);
		collideFlagCommand = new CollideFlagCommand(gw);
		tickCommand = new TickCommand(gw);
		soundCommand = new SoundCommand(gw);
		aboutCommand = new AboutCommand(gw);
		helpCommand = new HelpCommand(gw);
		exitCommand = new ExitCommand(gw);
		
		//Buttons
		createButtons();
		createContainers();
		addKeyBindings();
		tb= this.getToolbar();
		Toolbar.setGlobalToolbar(true);
		this.getToolbar().getAllStyles().setBgColor(ColorUtil.BLACK);
		this.getToolbar().getAllStyles().setBgTransparency(255);
		tb.addComponentToLeftSideMenu(accelerateButtonbar);
		tb.addComponentToLeftSideMenu(soundCheckBox);
		tb.addComponentToLeftSideMenu(aboutButton);
		tb.addComponentToLeftSideMenu(exitButton);
		
		tb.addComponentToRightSideMenu(helpButton);
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.SOUTH, southContainer);
		this.add(BorderLayout.CENTER, mv);
		this.add(BorderLayout.WEST, westContainer);
		this.add(BorderLayout.EAST, eastContainer);
		this.setTitle("On Target Game");
		this.repaint();
		this.show();
	}
	
	public void createButtons() {
		accelerateButton  = new ButtonClass("Accelerate");
		accelerateButton.setCommand(accelerateCommand);

		leftButton  = new ButtonClass("Left");
		leftButton.setCommand(leftCommand);

		brakeButton  = new ButtonClass("Break");
		brakeButton.setCommand(brakeCommand);

		rightButton  = new ButtonClass("Right");
		rightButton.setCommand(rightCommand);

		collideFoodButton  = new ButtonClass("Collide with Food Stations");
		collideFoodButton.setCommand(collideFoodCommand);

		collideSpiderButton  = new ButtonClass("Collide with Spider");
		collideSpiderButton.setCommand(collideSpiderCommand);

		tickButton  = new ButtonClass("Tick");
		tickButton.setCommand(tickCommand);

		collideFlagButton  = new ButtonClass("Collide with Flag");
		collideFlagButton.setCommand(collideFlagCommand);

		accelerateButtonbar  = new ButtonClass("Accelerate");
		accelerateButtonbar.setCommand(accelerateCommand);

		aboutButton  = new ButtonClass("About");
		aboutButton.setCommand(aboutCommand);
		
		helpButton  = new ButtonClass("Help");
		helpButton.setCommand(helpCommand);
		
		exitButton  = new ButtonClass("Exit");
		exitButton.setCommand(exitCommand);
		
		soundCheckBox  = new CheckBox("Sound");
		soundCheckBox.setSelected(true);
		soundCheckBox.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
		soundCheckBox.getAllStyles().setBgTransparency(255);
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.GRAY);
		soundCheckBox.getAllStyles().setFgColor(ColorUtil.WHITE);
		soundCheckBox.getAllStyles().setPadding(2,2,2,2);
		soundCheckBox.setCommand(soundCommand);

	}

	public void createContainers() {
		westContainer = new Container();
		westContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		westContainer.getAllStyles().setBgColor(ColorUtil.WHITE);
		westContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		westContainer.getAllStyles().setBgTransparency(255);
		westContainer.getAllStyles().setPadding(50,2,2,2);
		westContainer.addAll(accelerateButton , leftButton);

		eastContainer = new Container();
		eastContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		eastContainer.getAllStyles().setBgColor(ColorUtil.WHITE);
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		eastContainer.getAllStyles().setBgTransparency(255);
		eastContainer.getAllStyles().setPadding(50,2,2,2);
		eastContainer.addAll(brakeButton , rightButton);

		southContainer = new Container();
		southContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		southContainer.getAllStyles().setBgColor(ColorUtil.WHITE);
		southContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		southContainer.getAllStyles().setBgTransparency(255);
		southContainer.getAllStyles().setPadding(2,2,50,2);
		southContainer.addAll(collideSpiderButton, collideFlagButton, collideFoodButton, tickButton);
		
	}
	
	
	public void addKeyBindings() {
		this.addKeyListener('a', accelerateCommand);
		this.addKeyListener('A', accelerateCommand);
		
		this.addKeyListener('b', brakeCommand);
		this.addKeyListener('B', brakeCommand);
		
		this.addKeyListener('l', brakeCommand);
		this.addKeyListener('L', brakeCommand);
		
		this.addKeyListener('r', rightCommand);
		this.addKeyListener('R', rightCommand);
		
		this.addKeyListener('f', collideFoodCommand);
		this.addKeyListener('F', collideFoodCommand);
		
		this.addKeyListener('g', collideSpiderCommand);
		this.addKeyListener('G', collideSpiderCommand);
		
		this.addKeyListener('t', tickCommand);
		this.addKeyListener('T', tickCommand);
	}

	private void play() {

	}

}
