package com.codepath.examples.basicsnakegame;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.codepath.simplegame.AbstractGamePanel;
import com.codepath.simplegame.Velocity;
import com.codepath.simplegame.actors.SpriteMovingActor;

public class SpriteSnakeActor extends SpriteMovingActor {
	public static final int DRAW_SIZE = 25;
	public static final int STEP = 25;
	public ArrayList<Point> tailPos;
	private Context context;

	public SpriteSnakeActor(Context c, int x, int y) {
		super(c, R.drawable.snake_head_right, x, y);
		this.context = c;
		getVelocity().stop().setXDirection(Velocity.DIRECTION_RIGHT).setXSpeed(STEP);
		tailPos = new ArrayList<Point>();
		tailPos.add(new Point(x - this.getWidth(), y));
		tailPos.add(new Point(x - this.getWidth() * 2, y));
	}

	@Override
	public void stylePaint(Paint p) {
		p.setColor(Color.GREEN); // #01f316
		p.setStyle(Style.FILL);
	}

	@Override
	public void draw(Canvas canvas) {
		getPaint().setColor(Color.GREEN);
		canvas.drawBitmap(bitmap, getX(), getY(), null);
		for (Point p : tailPos) {
			RectF r = new RectF(p.x, p.y, p.x + this.getWidth(), p.y + this.getHeight());
			canvas.drawRoundRect(r, 10f, 10f, getPaint());
		}
	}

	public void move() {
		if (this.isEnabled()) {
			int headX = getPoint().x;
			int headY = getPoint().y;
			for (int x = tailPos.size() - 1; x > 0; x--) {
				tailPos.get(x).set(tailPos.get(x - 1).x, tailPos.get(x - 1).y);
			}
			tailPos.get(0).set(headX, headY);
			super.move();
		}
	}
 
	public void grow() {
		this.tailPos.add(new Point(tailPos.get(0).x, tailPos.get(0).y));
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
		if (keyCode == KeyEvent.KEYCODE_W) {
			getVelocity().stop().setYDirection(Velocity.DIRECTION_UP).setYSpeed(STEP);
		} else if (keyCode == KeyEvent.KEYCODE_S) {
			getVelocity().stop().setYDirection(Velocity.DIRECTION_DOWN).setYSpeed(STEP);
		} else if (keyCode == KeyEvent.KEYCODE_A) {
			getVelocity().stop().setXDirection(Velocity.DIRECTION_LEFT).setXSpeed(STEP);
		} else if (keyCode == KeyEvent.KEYCODE_D) {
			getVelocity().stop().setXDirection(Velocity.DIRECTION_RIGHT).setXSpeed(STEP);
		}
	}

	public void handleTouchInput(MotionEvent event) {
		if (getVelocity().getYSpeed() == 0) {
			if (event.getY() < this.getY()) {
				getVelocity().stop().setYDirection(Velocity.DIRECTION_UP).setYSpeed(STEP);
				setDrawable(context, R.drawable.snake_head_up);
			} else if (event.getY() > this.getY() && getVelocity().getYSpeed() == 0) {
				getVelocity().stop().setYDirection(Velocity.DIRECTION_DOWN).setYSpeed(STEP);
				setDrawable(context, R.drawable.snake_head_down);
			}
		} else if (getVelocity().getXSpeed() == 0) {
			if (event.getX() < this.getX()) {
				getVelocity().stop().setXDirection(Velocity.DIRECTION_LEFT).setXSpeed(STEP);
				setDrawable(context, R.drawable.snake_head_left);
			} else if (event.getX() > this.getX()) {
				getVelocity().stop().setXDirection(Velocity.DIRECTION_RIGHT).setXSpeed(STEP);
				setDrawable(context, R.drawable.snake_head_right);
			}
		}
	}
}
