package com.mycompany.a3.controller;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.commands.AboutCommand;
import com.mycompany.a3.commands.AccelerateCommand;
import com.mycompany.a3.commands.BrakeCommand;
import com.mycompany.a3.commands.ExitCommand;
import com.mycompany.a3.commands.HelpCommand;
import com.mycompany.a3.commands.LeftCommand;
import com.mycompany.a3.commands.PauseCommand;
import com.mycompany.a3.commands.PositionCommand;
import com.mycompany.a3.commands.RightCommand;
import com.mycompany.a3.commands.SoundCommand;
import com.mycompany.a3.model.GameWorld;
import com.mycompany.a3.objects.ButtonClass;
import com.mycompany.a3.views.MapView;
import com.mycompany.a3.views.ScoreView;

/**
 * controller class extend from the built-in Form class (which lives in
 * com.codename1.ui package
 *
 */
public class Game extends Form implements Runnable{

	private GameWorld gw;
	private MapView mv; // new in A2
	private ScoreView sv; // new in A2
//	private char lastCommand = ' ';
	private Container westContainer , eastContainer , southContainer;
	private Command accelerateCommand, brakeCommand, leftCommand, 
			rightCommand, collideFoodCommand , collideSpiderCommand , tikeCommand,
			collideFlagCommand , soundCommand, aboutCommand , helpCommand
			,exitCommand , positionCommand ,pauseCommand ;
	private ButtonClass accelerateButton , leftButton , brakeButton , rightButton ,
	positionButton , pauseButton,
	accelerateButtonbar , aboutButton , helpButton, exitButton;
	private CheckBox soundCheckBox;
	private Toolbar tb;
	//////////////////////////////////
	private boolean isPaused;
	private int gameSpeed;
	private UITimer timer;
	/**
	 * The Game constructor instantiates a GameWorld, calls a GameWorld method
	 * init() to set the initial state of the game and then starts the game by
	 * calling a Game method play()
	 */
	public Game() {
		isPaused  = false;
		gameSpeed = 1500;
		
		mv = new MapView(); // create an “Observer” for the map
		sv = new ScoreView(); // create an “Observer” for the game/ant state data
		gw = new GameWorld(gameSpeed); // create “Observable” GameWorld
		gw.addObserver(mv); // register the map observer
		gw.addObserver(sv); // register the score observer

		// code here to create Command objects for each command,
		// add commands to side menu and title bar area, bind commands to keys, create
		// control containers for the buttons, add buttons to the control containers,
		// add commands to the buttons, and add control containers, MapView, and
		// ScoreView to the form
		
		accelerateCommand = new AccelerateCommand(gw);
		brakeCommand = new BrakeCommand(gw);
		leftCommand = new LeftCommand(gw);
		rightCommand = new RightCommand(gw);
		soundCommand = new SoundCommand(gw);
		aboutCommand = new AboutCommand(gw);
		helpCommand = new HelpCommand(gw);
		exitCommand = new ExitCommand(gw);
		positionCommand = new PositionCommand(this);
		pauseCommand = new PauseCommand(this);
		
		// create Buttons
		createButtons();
		createContainers();
		addKeyBindings();
		tb= this.getToolbar();
		Toolbar.setGlobalToolbar(true);
		this.getToolbar().getAllStyles().setBgColor(ColorUtil.BLUE);
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
		this.setTitle("OnTarget Game");
		this.repaint();
		this.show();
		timer = new UITimer(this);
		timer.schedule(gameSpeed, true, this);
		positionButton.setEnabled(false);
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
		
		positionButton  = new ButtonClass("Position");
		positionButton.setCommand(positionCommand);
		
		pauseButton  = new ButtonClass("Pause");
		pauseButton.setCommand(pauseCommand);
		
		accelerateButtonbar  = new ButtonClass("Accelerate");
		accelerateButtonbar.setCommand(accelerateCommand);
		
		aboutButton  = new ButtonClass("About");
		aboutButton.setCommand(aboutCommand);
		
		helpButton  = new ButtonClass("Help");
		helpButton.setCommand(helpCommand);
		
		exitButton  = new ButtonClass("Exit");
		exitButton.setCommand(exitCommand);
		
		soundCheckBox  = new CheckBox("Sound");
		soundCheckBox.setSelected(false);
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
		southContainer.getAllStyles().setPadding(2,2,200,2);
		southContainer.addAll(positionButton , pauseButton);
		
		
	}
	
	
	public void removeKeyBindings() {
		this.removeKeyListener('a', accelerateCommand);
		this.removeKeyListener('A', accelerateCommand);
		
		this.removeKeyListener('b', brakeCommand);
		this.removeKeyListener('B', brakeCommand);
		
		this.removeKeyListener('l', brakeCommand);
		this.removeKeyListener('L', brakeCommand);
		
		this.removeKeyListener('r', rightCommand);
		this.removeKeyListener('R', rightCommand);
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
		
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		gw.tick();	
	}
	
	public void handelPause() {
		this.isPaused = !this.isPaused;
		if(isPaused == true)
			pause();
		else
			resume();
	}
	
	public void pause() {
		gw.pause();
		timer.cancel();
		pauseButton.setText("Play");
		
		removeKeyBindings();
		accelerateButton.setEnabled(false);
		leftButton.setEnabled(false);
		brakeButton.setEnabled(false);
		rightButton.setEnabled(false);
		accelerateButtonbar.setEnabled(false);
		soundCheckBox.setEnabled(false);
		positionButton.setEnabled(true);
	}
	
	public void resume() {
		
		gw.resume();
		timer.schedule(gameSpeed, true, this);
		pauseButton.setText("Pause");
		addKeyBindings();
		accelerateButton.setEnabled(true);
		leftButton.setEnabled(true);
		brakeButton.setEnabled(true);
		rightButton.setEnabled(true);
		accelerateButtonbar.setEnabled(true);
		soundCheckBox.setEnabled(true);
		positionButton.setEnabled(false);
	}

	public boolean isPaused() {
		return isPaused;
	}
	
	

}
