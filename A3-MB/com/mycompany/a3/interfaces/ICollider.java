package com.mycompany.a3.interfaces;

import com.mycompany.a3.objects.GameObject;

public interface ICollider {
	
	public boolean collidesWith(GameObject otherObject);
	
	public void handleCollision(GameObject otherObject);
}
