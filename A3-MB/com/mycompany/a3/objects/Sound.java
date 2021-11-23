package com.mycompany.a3.objects;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound {

	private Media media;
	 
	 public Sound(String fileName) {
	  try {
	   InputStream inputStream = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
	   media = MediaManager.createMedia(inputStream, "audio/wav");
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  
	 }
	 public void play() {
		 if(media == null)
			 return;
		 media.setTime(0); 
		 media.play();
	}
	 
	 public void setVolume(int volume)
	 {
		 if(media == null)
			 return;
		 media.setVolume(volume);
	 }
}
