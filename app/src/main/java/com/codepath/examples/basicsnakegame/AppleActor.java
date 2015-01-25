package com.codepath.examples.basicsnakegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import com.codepath.simplegame.AbstractGamePanel;
import com.codepath.simplegame.actors.PositionedActor;

public class AppleActor extends PositionedActor {
	
	public static final int DRAW_SIZE = 25;

	public AppleActor(int x, int y) {
		super(x, y, DRAW_SIZE, DRAW_SIZE);
	}

	@Override
	public void stylePaint(Paint p) {
		p.setColor(Color.RED);
		p.setStyle(Style.FILL);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRoundRect(getRectF(), 10, 10, getPaint());
	}
	
	public void reposition(AbstractGamePanel panel) { 
		setPos(randomCoordForPanel(panel.getWidth()), randomCoordForPanel(panel.getHeight()));
	}

	protected int randomCoordForPanel(int max) {
		int multiplier = max / DRAW_SIZE;
		int randomCoordinate = (int) (Math.random() * multiplier);
		return randomCoordinate * DRAW_SIZE;
	}

}
