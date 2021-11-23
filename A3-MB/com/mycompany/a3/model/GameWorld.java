package com.mycompany.a3.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.mycompany.a3.interfaces.IIterator;
import com.mycompany.a3.interfaces.ISelectable;
import com.mycompany.a3.objects.Ant;
import com.mycompany.a3.objects.BGSound;
import com.mycompany.a3.objects.Fixed;
import com.mycompany.a3.objects.Flag;
import com.mycompany.a3.objects.FoodStation;
import com.mycompany.a3.objects.GameObject;
import com.mycompany.a3.objects.GameWorldCollection;
import com.mycompany.a3.objects.Movable;
import com.mycompany.a3.objects.Sound;
import com.mycompany.a3.objects.Spider;
import com.mycompany.a3.views.MapView;
import com.mycompany.a3.views.ScoreView;

/**
 * Model Class
 * that ThePath game world size is fixed and covers 1000 (width) x 1000
 * (height) area (although we are going to change this later). 
 * The origin of the “world” (location (0,0)) is the lower left hand corner
 *
 */
public class GameWorld extends Observable{

	private int lives;
	private int time;	
	private GameWorldCollection gwc;
	private boolean issoundON;
	private boolean isPaused;
	private int lastSequenceNumber;
	private int height;
	private int width;
	private int timerTickRate;
	private GameObject ant;
	//////////////////////
	//////////////////////
	private Sound hitFoodStationSound ;
	private Sound hitSpiderSound;
	private Sound hitFlagSound;
	private BGSound gameSound;
	
	public GameWorld(int timerTickRate) {
		// create all Game Objects
		this.timerTickRate  = timerTickRate;
		gwc = new GameWorldCollection();
		lives = 3;
		time = 0;
		issoundON = false;
		isPaused = false;
		setHeight(1000);
		setWidth(1000);
		init();
		createSounds();
	}
	
	public void createSounds() {
		hitFoodStationSound = new Sound("hitFoodStationSound.wav");
		hitSpiderSound = new Sound("hitSpiderSound.wav");
		hitFlagSound = new Sound("hitFlagSound.wav");
		gameSound = new BGSound("gameSound.wav");
		if(issoundON)
			playGameSound();
	}
	
	
	
	public void pause() {
		this.isPaused = true;
		stopGameSound();
		notifyObservers();
		
	}
	
	public void resume() {
		IIterator it = gwc.getIterator();
		GameObject gameObject;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof ISelectable) {
				((ISelectable)gameObject).setSelected(false);
			}
		}
		this.isPaused = false;
		playGameSound();
		notifyObservers();
		
	}
	
	
	public void addObserver(Observer observer) {
		super.addObserver(observer);
		if(observer instanceof MapView) {
			setHeight(((MapView) observer).getWidth());
			setWidth(((MapView) observer).getHeight());
		}
		notifyObservers();
	}
	
	public void changeSoundStatus() {
		this.issoundON = !this.issoundON;
		if(issoundON == true)
			playGameSound();
		else
			stopGameSound();
		notifyObservers();
	}
	
	public void notifyObservers() {
		setChanged();
		notifyObservers(this);
	}
	
	
	/**
	 * creating the initial state of the world. This should include
	 * adding to the game world at least the following: 
	 * a minimum of four Flag objects, positioned 
	 * and sized as you choose and numbered sequentially 
	 * defining the path (you may add more
	 * than four initial flags if you like - 
	 * maximum number of flags you can add is nine); one Ant,
	 * initially positioned at the flag #1 with initial heading 
	 * which is zero, initial positive non-zero speed as you choose, 
	 * and initial size as you choose; 
	 * at least two Spider objects, randomly 
	 * positioned with a randomly-generated size, 
	 * heading, and speed; 
	 * and at least two FoodStation objects with random locations and with random sizes
	 */
	public void init()
	{
		// create an object of Ant Game Object
		gwc.add(Ant.getAntObj());
		// add for Flags to the list
		// add four flags
		lastSequenceNumber = 1;
		gwc.add(new Flag(new Point(100,50), lastSequenceNumber));
		gwc.add(new Flag(new Point(400,50), ++lastSequenceNumber));
		gwc.add(new Flag(new Point(200,250), ++lastSequenceNumber));
		gwc.add(new Flag(new Point(550,200), ++lastSequenceNumber));
		// add two food station
		gwc.add(new FoodStation());
		gwc.add(new FoodStation());
		// add two spiders
		gwc.add(new Spider());
		gwc.add(new Spider());
		
	}
	
	/**
	 * If the ant can no longer move (i.e., its food or health level 
	 * has reached zero), the game stops (the player “loses a life”) 
	 * and the game world is re-initialized (but the number of clock
	 * ticks is not set back to zero).
	 * 
	 * A clock tick in the game world has the following effects: 
	 * (1) Spiders update their heading as indicated above. 
	 * (2) all moveable objects are told to update their 
	 * positions according to their current heading and speed, and 
	 * (3) the ant’s food level is reduced by the amount 
	 * indicated by its foodConsumptionRate, 
	 * (4) the elapsed time “game clock” is incremented by one 
	 * (the game clock for this assignment is simply a 
	 * variable which increments by one with each tick). 
	 * Note that all commands take immediate effect 
	 * and not depend on ‘t’ command
	 * (e.g., if ‘a’ is hit, the ant’s speed value would 
	 * be increased right away without waiting for
	 * the next ‘t’ command to be entered)
	 */
	public void tick () {
		// (1) Spiders update their heading as indicated above
		// loop all game objects to get Spider Objects
		IIterator it = gwc.getIterator();
		GameObject gameObject;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Spider) {
				((Spider)gameObject).updateHeading();
			}
		}
		/**
		 * (2) all moveable objects are told to update their 
		 * positions according to their current heading and speed 
		 */
		it = gwc.getIterator();
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Movable) {
				((Movable)gameObject).move(this.timerTickRate);
			}
		}
		/**
		 * (3) the ant’s food level is reduced by the amount 
		 * indicated by its foodConsumptionRate
		 */
		ant = null;
		it = gwc.getIterator();
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				ant = gameObject;
				((Ant)gameObject).setFoodLevel(((Ant)gameObject).getFoodLevel() - ((Ant)gameObject).getFoodConsumptionRate());
				break;
			}
		}
		
		if(ant != null) {
			it = gwc.getIterator();
			while(it.hasNext()) {
				gameObject = (GameObject)it.getNext();
				if(gameObject instanceof Ant)
					continue;
				
				if(ant.collidesWith(gameObject)) {
					if( gameObject instanceof Spider) {
						playHitSpiderSound();
					}
					else if( gameObject instanceof Flag) {
						playHitFlagSound();
					}
					else if( gameObject instanceof FoodStation) {
						playHitFoodStationSound();
					}
					ant.handleCollision(gameObject);
					break;
				}
			}
		}
		// (4) the elapsed time “game clock” is incremented by one
		time++;
		checkForWin();
		notifyObservers();
	}
	
	
	public void playHitFoodStationSound() {
		if(issoundON == false || isPaused)
			return;
		hitFoodStationSound.play();
	}
	
	public void playHitFlagSound() {
		if(issoundON == false || isPaused)
			return;
		hitFlagSound.play();
		
	}
	
	public void playHitSpiderSound() {
		if(issoundON == false || isPaused)
			return;
		hitSpiderSound.play();
	}
	
	public void playGameSound() {
		if(issoundON == false || isPaused)
			return;
		gameSound.play();
	}
	
	public void stopGameSound() {
		gameSound.pause();
	}
	
	
	/**
	 * check if the player win or losse the game
	 */
	public void checkForWin() {
		IIterator it = gwc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return;
		if(((Ant)gameObject).getLastFlagReached() == 4) {
			System.out.print("Game over, you win! Total time: "+time);
			exit();
		}
		else if(lives == 0 ) {
			System.out.println("Game over, you failed!");
			exit();
		}
		else if(((Ant)gameObject).getSpeed() == 0) {
			System.out.println("Re-initializ Game");
			lives--;
			if(lives < 0 )
				lives = 0;
		}
		notifyObservers();
	}
	
	/**
	 * accelerate (increase the speed of) the ant by a small amount
	 */
	public void accelerate() {
		IIterator it = gwc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).setSpeed(((Ant)gameObject).getSpeed()+5);
		checkForWin();
		notifyObservers();
	}
	
	/**
	 * brake (reduce the speed of) the ant by a small amount
	 */
	public void brake() {
		IIterator it = gwc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).setSpeed(((Ant)gameObject).getSpeed()-5);
		checkForWin();
		notifyObservers();
	}
	
	/**
	 * change the heading of the ant by 5 degrees to the
	 * left (in the negative direction on the compass)
	 */
	public void left() {
		IIterator it = gwc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).moveLeft();
		checkForWin();
		notifyObservers();
	}
	
	/**
	 * change the heading of the ant by 5 degrees to the right (in the
	 * positive direction on the compass)
	 */
	public void right() {
		IIterator it = gwc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).moveRight();
		notifyObservers();
	}
		
	public int getLastFlagReached() {
		IIterator it = gwc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return -1;
		return ((Ant)gameObject).getLastFlagReached();
	}
	
	public int getFoodLevel() {
		IIterator it = gwc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return -1;
		return ((Ant)gameObject).getFoodLevel();
	}
	
	public int getHealthLevel() {
		IIterator it = gwc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return -1;
		return ((Ant)gameObject).getHealthLevel();
	}
	
	/**
	 * tell the game world to output a “map” showing the current world 
	 */
	public void map() {
		IIterator it = gwc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject != null)
			System.out.println((Ant)gameObject);
		it = gwc.getIterator();
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if( gameObject instanceof Spider)
				System.out.println(((Spider)gameObject).toString());
			else if( gameObject instanceof FoodStation) 
				System.out.println(((FoodStation)gameObject).toString());
			else if( gameObject instanceof Flag)
				System.out.println(((Flag)gameObject).toString());
		}
	}
	
	
	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
		checkForWin();
		notifyObservers();
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
		checkForWin();
		notifyObservers();
	}

	
	public boolean isSound() {
		return issoundON;
	}

	public void exit() {
		if(Dialog.show("Quit", "Do you Want Exit?!!!!","Yes", "No")) 
		{
			System.exit(0);
		}
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	
	
	public GameWorldCollection getGwc() {
		return gwc;
	}

	public boolean isPaused() {
		return isPaused;
	}
	
	
	
}
