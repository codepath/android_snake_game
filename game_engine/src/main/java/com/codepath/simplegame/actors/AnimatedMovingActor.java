package com.codepath.simplegame.actors;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class AnimatedMovingActor extends SpriteMovingActor {
	private Rect sourceRect;	// the rectangle to be drawn from the animation bitmap
	private int frameNr;		// number of frames in animation
	private int currentFrame;	// the current frame
	private long frameTicker;	// the time of the last frame update
	private int framePeriod;	// milliseconds between each frame (1000/fps)

	private int spriteWidth;	// the width of the sprite to calculate the cut out rectangle
	private int spriteHeight;	// the height of the sprite
	
	public AnimatedMovingActor(Bitmap bitmap, int x, int y, int width, int height, int fps, int frameCount) {
		super(bitmap, x, y);
		this.bitmap = bitmap;
		currentFrame = 0;
		frameNr = frameCount;
		spriteWidth = bitmap.getWidth() / frameCount;
		spriteHeight = bitmap.getHeight();
		setDimensions(spriteWidth, spriteHeight);
		sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
		framePeriod = 1000 / fps;
		frameTicker = 0l;
	}
	
	// Updates sprite animation
	public void update() {
		long gameTime = System.currentTimeMillis();
		if (gameTime > frameTicker + framePeriod) {
			frameTicker = gameTime;
			// increment the frame
			currentFrame++;
			if (currentFrame >= frameNr) {
				currentFrame = 0;
			}
		}
		// define the rectangle to cut out sprite
		this.sourceRect.left = currentFrame * spriteWidth;
		this.sourceRect.right = this.sourceRect.left + spriteWidth;
	}
	
	@Override
	public void draw(Canvas canvas) {
	   // where to draw the sprite
	   Rect destRect = new Rect(getX(), getY(), getX() + spriteWidth, getY() + spriteHeight);
	   canvas.drawBitmap(bitmap, sourceRect, destRect, null);
    }


	@Override
	public void stylePaint(Paint p) {
		// Override to do nothing by default, optionally implemented by descendants
	}

}

/*
super(BitmapFactory.decodeResource(c.getResources(), R.drawable.walk_elaine)
				, x, y	// initial position
				, 30, 47	// width and height of sprite
				, 5, 5);	// FPS and number of frames in the animation)
*/