package com.mycompany.a1;

import java.util.ArrayList;

import ClosingForm.ClosingApp;
import Interfaces.IMovable;
import Objects.*;

public class GameWorld {

	// Attributes for the GameWorld class
	private ArrayList<GameObject> worldList;
	private Flag flag;
	private Ant ant;
	private Spider spider;
	private FoodStation foodStation;
	private int life;
	private int score;
	private int time;
	
	// behaviors for the class GameWorld
	
	//Default constructor for the class GameWorld
	public GameWorld() {
		
		init();
		
	}

	public void init() {
		// TODO Auto-generated method stub
		//ArrayList of the type GameObject to store all the objects created for the game
		worldList = new ArrayList<GameObject>();
		
		//Setting the scores, time and life for the start of the game
		score = 0;
		time = 0;
		life = 3;

		// Creating flag objects for the game
		for(int i = 0; i < 4; i++)
			createFlagObjects();
		
		createAnt();
		
		for(int i = 0; i < 2; i++) {
			createSpider();
			createFoodStation();
		}
		
	}
	
	// Method to create the flag objects
	public void createFlagObjects() {
		
		flag = new Flag();
		worldList.add(flag);
		System.out.println("New flag created");
		
	}
	
	// Method to create the player ant
	public void createAnt() {
		
		ant = new Ant();
		worldList.add(ant);
		System.out.println("New Player Ant created");
		
	}
	
	// Method to create Spider entities for the game
	public void createSpider() {
		
		spider = new Spider();
		worldList.add(spider);
		System.out.println("New Spider created");
		
	}
	
	// Method to create Spider entities for the game
	public void createFoodStation() {
		
		foodStation = new FoodStation();
		worldList.add(foodStation);
		System.out.println("New foodStation created");
		
	}


	public void accelerate() {
		// TODO Auto-generated method stub

		System.out.println("ANT speed increased");
		ant.increaseSpeedAnt();
		
	}

	public void brake() {
		// TODO Auto-generated method stub
		System.out.println("ANT speed decreased");
		ant.decreaseSpeedAnt();

	}

	public void left() {
		// TODO Auto-generated method stub
		System.out.println("turned Ant left");
		ant.steerLeft();
	}

	public void right() {
		// TODO Auto-generated method stub
		System.out.println("turned Ant Right");
		ant.steerRight();
	}

	public void collideflag(int flagNum) {
		// TODO Auto-generated method stub
		System.out.println("collided with flag");
		if(flagNum - ant.getLastFlag() == 1) {
			ant.setLastFlag(ant.getLastFlag() + 1);
		}
		
	}

	public void collideFoodStation() {
		// TODO Auto-generated method stub
		System.out.println("colided with foodStation");
		for(int i = 0; i < worldList.size(); i++) {
			
			if(worldList.get(i) instanceof FoodStation) {
				FoodStation obj = (FoodStation) worldList.get(i);
				if(obj.getCapacity() != 0) {
				
					obj.setCapacity(0);
					obj.updateColor(255,255,200);							// Set color to light yellow
					worldList.add(new FoodStation());
					break;
				}
			}
			
		}
			
		
	}

	public void collideSpider() {
		// TODO Auto-generated method stub
		
		System.out.println("Collide with spider");
		ant.setHealth(ant.getHealth() - 1);
		ant.updateColor(255-ant.getHealth() * 2, 0,0);
		ant.setMaxSpeed( (ant.getHealth() * 10)  * (ant.getLimit()/100));
		if(ant.getSpeed() > ant.getMaxSpeed())
			ant.setSpeed(ant.getMaxSpeed());
	}

	public void clockTicked() {
		// TODO Auto-generated method stub
		
		time++;
		System.out.println("Game Clock ticked");
		ant.updateFood();
		
		for(int i = 0; i< worldList.size(); i++) {
			
			if(worldList.get(i) instanceof IMovable) {
				
				IMovable mObj = (IMovable) worldList.get(i);
				mObj.move();
				
				if(mObj instanceof Spider) {

					Spider obj = (Spider)worldList.get(i);
					obj.updateDirection();
				}
				
			}
			
		}
		
		
	}

	public void display() {
		// TODO Auto-generated method stub
		System.out.println("\nGame State:\n*********************************************************************************************************\n");
		System.out.println("Time elapsed: " + time + "\n");
		System.out.println("Lives remaining: " + life + "\n");
		System.out.println("Highest Flag Reached: " + ant.getLastFlag() + "\n");
		System.out.println("Current Food Level: " + ant.getFoodLevel() + "\n");
		System.out.println("Ant's Health: " + ant.getHealth() + "\n");
		System.out.println("*********************************************************************************************************\n");
	}

	public void showMap() {
		// TODO Auto-generated method stubhttp://marketplace.eclipse.org/marketplace-client-intro?mpc_install=495
		for(GameObject temp : worldList) {
			
			System.out.print(temp);
			
		}
		
		System.out.println();
	}

	public void exit() {
		// TODO Auto-generated method stub
		System.out.println("Quit option selected");
		//System.out.println("Are you sure you want to exit? (y/n)");		
		System.exit(0);
		
		//y/n
		
	}
	
	
}
