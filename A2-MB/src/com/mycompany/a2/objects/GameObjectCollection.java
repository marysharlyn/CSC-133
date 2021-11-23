package com.mycompany.a2.objects;

import java.util.ArrayList;

import com.mycompany.a2.interfaces.ICollection;
import com.mycompany.a2.interfaces.IIterator;

public class GameObjectCollection implements ICollection{

	private ArrayList<GameObject> gameObjs;
	
	public GameObjectCollection() {
		gameObjs = new ArrayList<GameObject>();
	}
	
	public GameObject get(int index) {
		if(index < 0 || index >= gameObjs.size())
			return null;
		return gameObjs.get(index);
	}
	
	@Override
	public void add(Object o) {
		
		gameObjs.add((GameObject) o);
	}

	public int size() {
		return gameObjs.size();
	}
	
	@Override
	public IIterator getIterator() {
		
		return new GameObjectCollectionIterator();
	}
	
	private class GameObjectCollectionIterator implements IIterator
	{
		private int index;
		
		public GameObjectCollectionIterator()
		{
			index = -1;	
		}
		
		public boolean hasNext()
		{
			if(gameObjs.size() <= 0 || index == gameObjs.size() - 1)
				return false;	
			return true;
		}

		public Object getNext() 
		{
			return(gameObjs.get(++index));
		}
		
		public void remove()
		{
			if(!hasNext())
				return ;
			gameObjs.remove(index--);
		}			
	}

}
