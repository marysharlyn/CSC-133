package com.mycompany.a3.objects;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BGSound implements Runnable {
	 private Media media;
	 public BGSound(String fileName) {
	   try {
		    InputStream inputStream = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName); 
		    media =MediaManager.createMedia(inputStream, "audio/wav", this);
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	 }
	 
	 public void play()
	 {
		 if(media == null)
			 return;
		 media.setVolume(10);
		 media.setTime(0);
		 media.play();
	 }
 	public void pause()
 	{ 
 		if(media == null)
			 return;
 		media.pause();
 	} 
 	public void run()
 	{
 		if(media == null)
			 return;
 		
 		media.setTime(0);
 		media.play();
 	}
}
	 
