package com.codepath.examples.basicsnakegame;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.codepath.simplegame.AbstractGamePanel;
import com.codepath.simplegame.Velocity;
import com.codepath.simplegame.actors.SimpleMovingActor;

public class SnakeActor extends SimpleMovingActor {
	public static final int DRAW_SIZE = 25;
	public static final int STEP = 3; 
	public ArrayList<Point> tailPos;

	public SnakeActor(int x, int y) {
		super(x, y, DRAW_SIZE, DRAW_SIZE);
		getVelocity().stop().setXDirection(Velocity.DIRECTION_RIGHT)
				.setXSpeed(STEP);
		tailPos = new ArrayList<Point>();
		tailPos.add(new Point(x - this.getWidth(), y));
		tailPos.add(new Point(x - this.getWidth() * 2, y));
	}

	@Override
	public void stylePaint(Paint p) {
		p.setColor(Color.GREEN);
		p.setStyle(Style.FILL);
	}

	@Override
	public void draw(Canvas canvas) {
		getPaint().setColor(Color.GREEN);
		canvas.drawRect(getRect(), getPaint());
		getPaint().setColor(Color.YELLOW);
		for (Point p : tailPos) {
			Rect r = new Rect(p.x, p.y, p.x + this.getWidth(), p.y
					+ this.getHeight());
			canvas.drawRect(r, getPaint());
		}
	}

	public void move() {
		if (this.isEnabled()) {
			Point headPos = getPoint();
			for (int x = 0; x <= tailPos.size(); x++) {
				//int xSpeed = getVelocity().getXSpeed();
				//int ySpeed = getVelocity().getYSpeed()
				//tailPos.get(x).set(, tailPos.get(x - 1).y);
			}
			super.move();
		}
	}

	public boolean checkBoundsCollision(AbstractGamePanel panel) {
		if (this.getX() < 0) {
			return true;
		} else if (this.getX() >= (panel.getWidth() - this.getWidth())) {
			return true;
		} else if (this.getY() < 0) {
			return true;
		} else if (this.getY() >= (panel.getHeight() - this.getHeight())) {
			return true;
		}
		return false;
	}

	public void handleKeyInput(int keyCode) {
		Log.d("DEBUG", String.valueOf(keyCode));
		if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
			getVelocity().stop().setYDirection(Velocity.DIRECTION_UP)
					.setYSpeed(STEP);
		} else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
			getVelocity().stop().setYDirection(Velocity.DIRECTION_DOWN)
					.setYSpeed(STEP);
		} else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
			getVelocity().stop().setXDirection(Velocity.DIRECTION_LEFT)
					.setXSpeed(STEP);
		} else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
			getVelocity().stop().setXDirection(Velocity.DIRECTION_RIGHT)
					.setXSpeed(STEP);
		}
	}

	public void handleTouchInput(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (getVelocity().getYSpeed() == 0) {
				if (event.getY() < this.getY()) {
					getVelocity().stop().setYDirection(Velocity.DIRECTION_UP)
							.setYSpeed(STEP);
				} else if (event.getY() > this.getY()
						&& getVelocity().getYSpeed() == 0) {
					getVelocity().stop().setYDirection(Velocity.DIRECTION_DOWN)
							.setYSpeed(STEP);
				}
			} else if (getVelocity().getXSpeed() == 0) {
				if (event.getX() < this.getX()) {
					getVelocity().stop().setXDirection(Velocity.DIRECTION_LEFT)
							.setXSpeed(STEP);
				} else if (event.getX() > this.getX()) {
					getVelocity().stop()
							.setXDirection(Velocity.DIRECTION_RIGHT)
							.setXSpeed(STEP);
				}
			}
		}
	}
}
