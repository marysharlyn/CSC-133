package com.mycompany.a3.objects;

import java.util.ArrayList;

import com.mycompany.a3.interfaces.ICollection;
import com.mycompany.a3.interfaces.IIterator;

public class GameWorldCollection implements ICollection{

	private ArrayList<GameObject> gameObjs;
	
	public GameWorldCollection() {
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
		
		return new GameWorldCollectionIterator();
	}
	
	private class GameWorldCollectionIterator implements IIterator
	{
		private int index;
		
		public GameWorldCollectionIterator()
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
