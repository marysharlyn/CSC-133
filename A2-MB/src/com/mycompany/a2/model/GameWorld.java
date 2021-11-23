package com.mycompany.a2.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.mycompany.a2.interfaces.IIterator;
import com.mycompany.a2.objects.Ant;
import com.mycompany.a2.objects.Flag;
import com.mycompany.a2.objects.FoodStation;
import com.mycompany.a2.objects.GameObject;
import com.mycompany.a2.objects.GameObjectCollection;
import com.mycompany.a2.objects.Movable;
import com.mycompany.a2.objects.Spider;
import com.mycompany.a2.views.MapView;
import com.mycompany.a2.views.ScoreView;

public class GameWorld extends Observable{

	private int lives;
	private int time;	
	private GameObjectCollection goc;
	private boolean sound;
	private int lastSequenceNumber;
	private boolean antIsAdded;
	
	public GameWorld() {

		goc = new GameObjectCollection();
		lives = 3;
		time = 0;
		sound = true;
		init();
		notifyObservers();
		antIsAdded = false;
	}
	
	public void addObserver(Observer observer) {
		super.addObserver(observer);
		notifyObservers();
	}
	
	public void changeSoundStatus() {
		this.sound = !this.sound;
		notifyObservers();
	}
	public void notifyObservers() {
		setChanged();
		notifyObservers(this);
	}
	
	public void init()
	{

		goc.add(new Ant(new Point(50,50)));
		antIsAdded = true;
		lastSequenceNumber = 1;
		
		goc.add(new Flag(new Point(50,50), lastSequenceNumber));
		goc.add(new Flag(new Point(100,100), ++lastSequenceNumber));
		goc.add(new Flag(new Point(450,350), ++lastSequenceNumber));
		goc.add(new Flag(new Point(600,600), ++lastSequenceNumber));
		goc.add(new FoodStation());
		goc.add(new FoodStation());
		goc.add(new Spider());
		goc.add(new Spider());
		
	}
	
	public void tick () {
		IIterator it = goc.getIterator();
		GameObject gameObject;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Spider) {
				((Spider)gameObject).updateHeading();
			}
		}

		it = goc.getIterator();
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Movable) {
				((Movable)gameObject).move();
			}
		}

		it = goc.getIterator();
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				((Ant)gameObject).setFoodLevel(((Ant)gameObject).getFoodLevel() - ((Ant)gameObject).getFoodConsumptionRate());
				break;
			}
		}
		time++;
		checkForWin();
		notifyObservers();
	}
	

	public void checkForWin() {
		IIterator it = goc.getIterator();
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
			System.out.print("Game over! you won! Your Total time is: "+time);
			exit();
		}
		else if(lives == 0 ) {
			System.out.println("Game over! You failed!");
			exit();
		}
		else if(((Ant)gameObject).getSpeed() == 0) {
			System.out.println("Re-boot Game");
			lives--;
			if(lives < 0 )
				lives = 0;
		}
		notifyObservers();
	}

	
	public void accelerate() {
		IIterator it = goc.getIterator();
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
	
	
	public void brake() {
		IIterator it = goc.getIterator();
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
	

	public void left() {
		IIterator it = goc.getIterator();
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


	public void right() {
		IIterator it = goc.getIterator();
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
	
	public void collisionWithFlag(int sequenceNumber) {
		IIterator it = goc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).collisionWithFlag(sequenceNumber);
		notifyObservers();
	}
	

	public void collisionWithFoodStation() {
		Random rand = new Random();
		IIterator it;
		GameObject gameObject;
		int index;
		while(true) {
			index = rand.nextInt(goc.size());
			
			if(goc.get(index) instanceof FoodStation
					&& ((FoodStation)goc.get(index)).getCapacity() != 0) {
				it = goc.getIterator();
				gameObject = null;
				while(it.hasNext()) {
					gameObject = (GameObject)it.getNext();
					if(gameObject instanceof Ant) {
						break;
					}
				}
				if(gameObject != null)
					((Ant)gameObject).setFoodLevel(((Ant)gameObject).getFoodLevel() + ((FoodStation)goc.get(index)).getCapacity());
				((FoodStation)goc.get(index)).setCapacity(0);
				goc.get(index).setColor(ColorUtil.rgb(0, 200, 0) );
				goc.add(new FoodStation());
				break;
			}
		}
		checkForWin();
		notifyObservers();
	}
	
	public void collisionWithSpider() {
		IIterator it = goc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).collisionWithSpider();
		checkForWin();
		notifyObservers();
	}
	

	public void display() {

	}
	
	public int getLastFlagReached() {
		IIterator it = goc.getIterator();
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
		IIterator it = goc.getIterator();
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
		IIterator it = goc.getIterator();
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
	
	public void map() {
		IIterator it = goc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject != null)
			System.out.println((Ant)gameObject);
		it = goc.getIterator();
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
		return sound;
	}

	public boolean isAntIsAdded() {
		return antIsAdded;
	}

	public void setAntIsAdded(boolean antIsAdded) {
		this.antIsAdded = antIsAdded;
		notifyObservers();
	}

	public void exit() {
		if(Dialog.show("Quit", "Would you like to Quit?","Yes", "No")) 
		{
			System.exit(0);
		}
	}
}
