package Objects;

import com.codename1.charts.util.ColorUtil;

public class Flag extends Fixed{

	//Attributes for the class SpaceStation
	
	private static int instanceCounter = 0;
	private int sequenceNumber = 0;
		
	//Behaviors for the class SpaceStation
		
	public Flag() {
			
		setSize(10);												// Setting the size of the flag to 10
		
		//Setting the color of the FoodStation to white
		this.setColor(ColorUtil.rgb(255, 255, 255));
			
		// Setting the sequenceNumber for the respective flag
		instanceCounter++;
		sequenceNumber = instanceCounter;
		
		assignFlagLocationManual();
		
			
	}
	
	private void assignFlagLocationManual() {
		// TODO Auto-generated method stub
		
		if(this.sequenceNumber == 1) { setX(200);setY(200);}
		else if(this.sequenceNumber == 2) {setX(200);setY(800);}
		else if(this.sequenceNumber == 3) {setX(700);setY(800);}
		else if(this.sequenceNumber == 4) {setX(900);setY(400);}
		else
			System.out.println("Invalid flag");

	}
		
	//toString method for the class
	public String toString() {
			
		String desc = "Flag: " + super.toString() + " seqNum=" + this.sequenceNumber + "\n";
		return desc;
			
	}	
	
}
