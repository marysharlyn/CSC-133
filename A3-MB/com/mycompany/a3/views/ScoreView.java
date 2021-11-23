package com.mycompany.a3.views;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.model.GameWorld;

/**
 * this class containing code to output the current game/ant state information
 *
 */
public class ScoreView extends Container implements Observer {

	private Label time, lives, flag, foodLevel, healthLevel, sound;
	private Label timeValue, livesValue, flagValue, foodLevelValue, healthLevelVaue, soundValue;

	public ScoreView() {
		this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		this.getAllStyles().setBgTransparency(255);

		// create Time labels
		time = new Label("Time: ");
		time.getAllStyles().setFgColor(ColorUtil.BLUE);
		timeValue = new Label("");
		timeValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		timeValue.getAllStyles().setPaddingRight(5);

		// create lives labels
		lives = new Label("Lives Left: ");
		lives.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesValue = new Label("");
		livesValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesValue.getAllStyles().setPaddingRight(5);

		// create flag labels
		flag = new Label("Last Flag Reached: ");
		flag.getAllStyles().setFgColor(ColorUtil.BLUE);
		flagValue = new Label("");
		flagValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		flagValue.getAllStyles().setPaddingRight(5);

		// create foodLevel labels
		foodLevel = new Label("Food Level: ");
		foodLevel.getAllStyles().setFgColor(ColorUtil.BLUE);
		foodLevelValue = new Label("");
		foodLevelValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		foodLevelValue.getAllStyles().setPaddingRight(5);

		// create healthLevel labels
		healthLevel = new Label("Health Level: ");
		healthLevel.getAllStyles().setFgColor(ColorUtil.BLUE);
		healthLevelVaue = new Label("");
		healthLevelVaue.getAllStyles().setFgColor(ColorUtil.BLUE);
		healthLevelVaue.getAllStyles().setPaddingRight(5);

		// create sound labels
		sound = new Label("Sound: ");
		sound.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundValue = new Label("");
		soundValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundValue.getAllStyles().setPaddingRight(5);
		this.addAll(time,timeValue,lives,livesValue,flag,flagValue,foodLevel,foodLevelValue,
				healthLevel,healthLevelVaue,sound,soundValue);
	}

	public void update(Observable o, Object arg) {
		
		// code here to update labels from the game/ant state data
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
