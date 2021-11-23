package Objects;

import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;

/*
 * Abstract class GameObject to provide implementations for the different objects of the game
 */
public abstract class GameObject {

	//Attributes
	
	private double x;
	private double y;
	private int color;
	private Random rand;
	private Point2D objectLocation;
	private GameCanvas canvas;
	private int size;
	//private ObjectColor objColor;
	
	//Behaviors
	
	//Default constructor for the class GameObject
	public GameObject() {
		
		//objColor = new ObjectColor();
		canvas = new GameCanvas();
		rand = new Random();
		x = (double) rand.nextInt((int)(canvas.getCanvasXMax()));
		y = (double) rand.nextInt((int)(canvas.getCanvasYMax()));
		objectLocation = new Point2D(x,y);
		size = getRandom().nextInt(40) + 10;								// Assigning a random size b/w the value 10 and 50
		
	}
	
	// Setter method for the size attribute
	protected void setSize(int val) {
		
		this.size  = val;
		
	}
	
	// Getter method for the size attribute
	protected int getSize() {
		
		return this.size;
		
	}
	
	//Getter and setter method for X and Y (position variables)
	protected double getX() {
		
		return this.x;
		
	}
	
	protected double getY() {
		
		return this.y;
		
	}
	
	protected void setX(double newX) {
		
		if(GameCanvas.rangeCheckX(newX))
			this.x = newX;
		
	}
	
	protected void setY(double newY) {
		
		if(GameCanvas.rangeCheckY(newY))
			this.y = newY;
		
	}
	
	//Getter method for color
	protected int getColor() {
		
		return this.color;
		
	}
	
	//Setter method for color
	protected void setColor(int newColor){
	
		this.color = newColor;
	
	}
	
	//Method to get the random object
	public Random getRandom() {
		
		return rand;
		
	}
	
	//Method to set the location of the object by checking if the coordinates are within the specification 
	protected void setLocation(Point2D newLocation) {
		
		try {
			
			double tempX = newLocation.getX();
			double tempY = newLocation.getY();
			
			if(GameCanvas.rangeCheck(tempX, tempY)) {
			
				setX(tempX);
				setY(tempY);
				
			}
			else
				throw new Exception("The coordinates are out of range.");
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}

	}
	
	//Method to return the location of the object
	protected Point2D getLocation() {
		
		return this.objectLocation;
		 
	}
	
	//Method to over-ride the default toString method for the class GamObject
	public String toString() {
		
		String desc = "loc=" + Math.round(this.getX() * 10.0) / 10.0 + "," + Math.round(this.getY() * 10.0) / 10.0 + " color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]  size=" + getSize();
		return desc;
		
	}
}