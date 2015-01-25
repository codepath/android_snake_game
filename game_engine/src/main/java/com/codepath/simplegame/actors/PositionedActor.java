package com.codepath.simplegame.actors;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

/*
 * 
 * Represents a game object that has a position and width / height on screen.
 *  
 */
public abstract class PositionedActor extends Actor {
	private Point pos;
	private int height;
	private int width;
	
	public PositionedActor(int x, int y) {
		this.pos = new Point(x, y);
	}
	
	public PositionedActor(int x, int y, int width, int height) { 
		this(x, y);
		this.setDimensions(width, height);
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public Point getPoint() {
		return pos;
	}
	
	public Rect getRect() {
		Rect rect = new Rect();
		getRectF().round(rect);
		return rect;
	}
	
	public RectF getRectF() {
		return new RectF(getX(), getY(), getX() + width, getY() + height);
	}
	
	public void setPos(int x, int y) {
		this.pos = new Point(x, y);
	}

	public int getX() {
		return this.pos.x;
	}

	public void setX(int x) {
		this.pos.x = x;
	}

	public int getY() {
		return this.pos.y;
	}

	public void setY(int y) {
		this.pos.y = y;
	}
	
	public boolean intersect(SimpleMovingActor actor) {
		return getRect().intersect(actor.getRect());
	}

}
