package com.mycompany.a2.views;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a2.model.GameWorld;

public class ScoreView extends Container implements Observer {

	private Label time, lives, flag, foodLevel, healthLevel, sound;
	private Label timeValue, livesValue, flagValue, foodLevelValue, healthLevelVaue, soundValue;

	public ScoreView() {
		this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		this.getAllStyles().setBgColor(ColorUtil.rgb(100, 100, 100));
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		this.getAllStyles().setBgTransparency(255);

		// Time Labels
		time = new Label("Time: ");
		time.getAllStyles().setFgColor(ColorUtil.BLACK);
		timeValue = new Label("");
		timeValue.getAllStyles().setFgColor(ColorUtil.BLACK);
		timeValue.getAllStyles().setPaddingRight(5);

		// Lives Labels
		lives = new Label("Lives Remaining: ");
		lives.getAllStyles().setFgColor(ColorUtil.BLACK);
		livesValue = new Label("");
		livesValue.getAllStyles().setFgColor(ColorUtil.BLACK);
		livesValue.getAllStyles().setPaddingRight(5);

		// Flag Labels
		flag = new Label("Last Flag Reached: ");
		flag.getAllStyles().setFgColor(ColorUtil.BLACK);
		flagValue = new Label("");
		flagValue.getAllStyles().setFgColor(ColorUtil.BLACK);
		flagValue.getAllStyles().setPaddingRight(5);

		// FoodLevel Labels
		foodLevel = new Label("Food Level: ");
		foodLevel.getAllStyles().setFgColor(ColorUtil.BLACK);
		foodLevelValue = new Label("");
		foodLevelValue.getAllStyles().setFgColor(ColorUtil.BLACK);
		foodLevelValue.getAllStyles().setPaddingRight(5);

		// HealthLevel Labels
		healthLevel = new Label("Health Level: ");
		healthLevel.getAllStyles().setFgColor(ColorUtil.BLACK);
		healthLevelVaue = new Label("");
		healthLevelVaue.getAllStyles().setFgColor(ColorUtil.BLACK);
		healthLevelVaue.getAllStyles().setPaddingRight(5);

		// Sound Labels
		sound = new Label("Sound: ");
		sound.getAllStyles().setFgColor(ColorUtil.BLACK);
		soundValue = new Label("");
		soundValue.getAllStyles().setFgColor(ColorUtil.BLACK);
		soundValue.getAllStyles().setPaddingRight(5);
		this.addAll(time,timeValue,lives,livesValue,flag,flagValue,foodLevel,foodLevelValue,
				healthLevel,healthLevelVaue,sound,soundValue);
	}

	public void update(Observable o, Object arg) {
		
		if(arg == null)
			return;
		timeValue.setText(Integer.toString(((GameWorld)arg).getTime()));
		livesValue.setText(Integer.toString(((GameWorld)arg).getLives()));
		flagValue.setText (Integer.toString(((GameWorld)arg).getLastFlagReached()));
		foodLevelValue.setText (Integer.toString(((GameWorld)arg).getFoodLevel()));
		healthLevelVaue.setText (Integer.toString(((GameWorld)arg).getHealthLevel()));
		if(((GameWorld)arg).isSound())
			soundValue.setText("ON");
		else
			soundValue.setText("OFF");
		repaint();
	}
}
