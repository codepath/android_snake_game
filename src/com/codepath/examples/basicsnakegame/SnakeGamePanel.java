package com.codepath.examples.basicsnakegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.codepath.simplegame.AbstractGamePanel;

public class SnakeGamePanel extends AbstractGamePanel {
	
	public SnakeGamePanel(Context context) {
		super(context);
	}

	private SnakeActor snake;
	private AppleActor apple;  

	@Override
	public void onStart() {
		this.snake = new SnakeActor(100, 100);
		this.apple = new AppleActor(200, 50);
	}

	@Override
	public void onTimer() {
		if (this.snake.checkBoundsCollision(this)) {
			this.snake.setEnabled(false);
		}
		this.snake.move();
		if (this.apple.intersect(this.snake)) {
			this.apple.reposition(this);
		}
	}

	@Override
	public void redrawCanvas(Canvas canvas) {
		if (this.snake.isEnabled()) {
			this.snake.draw(canvas);
			this.apple.draw(canvas);	
		} else {
			Paint p = getPaint();
			p.setTextSize(50);
			p.setColor(Color.BLACK);
			canvas.drawText("Game over!", 100, 100, p);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d("SnakeGame", String.valueOf(keyCode));
		this.snake.handleKeyInput(keyCode);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("SnakeGame", event.toString());
		this.snake.handleTouchInput(event);
		return true;
	}

}
