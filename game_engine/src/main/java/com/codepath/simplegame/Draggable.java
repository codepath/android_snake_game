package com.codepath.simplegame;

public interface Draggable {
	public boolean isTouched();
	public void setTouched(boolean touched);
	public void handleActionDown(int eventX, int eventY);
	public void setX(int x);
	public void setY(int y);
	public int getHeight();
	public int getWidth();
}
