package Objects;

import com.codename1.charts.util.ColorUtil;

public class FoodStation extends Fixed{
	
	//Attributes for the class SpaceStation
	
	private int capacity;
	private int size;
	
	//Behaviors for the class SpaceStation
	
	public FoodStation() {
		
		capacity = getRandom().nextInt(5) + size;
		
		//Setting the color of the FoodStation to yellow
		this.setColor(ColorUtil.rgb(255, 255, 0));
		
		
	}
	
	//Getter method for blink rate
	public int getCapacity(){
		
		return this.capacity;
		
	}
	
	// Setting the capacity of the FS
	public void setCapacity(int val) {
		
		this.capacity = val;
		
	}
	
	public void updateColor(int r, int g, int b) {
		
		this.setColor(ColorUtil.rgb(r,g,b));
		
	}
	
	//toString method for the class
	public String toString() {
		
		String desc = "FoodStation: " + super.toString() + " Capacity=" + this.getCapacity() + "\n";
		return desc;
		
	}

}
