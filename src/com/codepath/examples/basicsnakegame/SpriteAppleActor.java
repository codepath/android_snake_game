package com.codepath.examples.basicsnakegame;

import android.content.Context;

import com.codepath.simplegame.AbstractGamePanel;
import com.codepath.simplegame.actors.SpriteMovingActor;

public class SpriteAppleActor extends SpriteMovingActor {

	public SpriteAppleActor(Context c, int x, int y) {
		super(c, R.drawable.apple, x, y);
	}
	
	public void reposition(AbstractGamePanel panel) { 
		setPos(randomCoordForPanel(panel.getWidth()), randomCoordForPanel(panel.getHeight()));
	}

	protected int randomCoordForPanel(int max) {
		int multiplier = max / 25;
		int randomCoordinate = (int) (Math.random() * multiplier);
		return randomCoordinate * 25;
	}

}
