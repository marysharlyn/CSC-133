package com.mycompany.a2.objects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Flag extends Fixed {

	private int sequenceNumber;
	private boolean reached;
	public Flag(Point location, int sequenceNumber) {
		super(10,location,ColorUtil.rgb(0,0, 255));
		this.sequenceNumber = sequenceNumber;
		reached = false;
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public boolean isReached() {
		return reached;
	}
	public void setReached(boolean reached) {
		this.reached = reached;
	}
	
	@Override
	public String toString() {
	
		return "Flage: "+super.toString()+
				" size="+getSize()+" seqNum="+getSequenceNumber();
	}
	
	
}
