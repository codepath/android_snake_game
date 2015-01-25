package com.codepath.simplegame.actors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class SpriteMovingActor extends SimpleMovingActor {
	protected Bitmap bitmap;
	
	public SpriteMovingActor(Context c, int drawable, int x, int y) {
		super(x, y);
		setDrawable(c, drawable);
		setDimensions(bitmap.getWidth(), bitmap.getHeight());
	}
	
	public SpriteMovingActor(Bitmap bitmap, int x, int y) {
		super(x, y, bitmap.getWidth(), bitmap.getHeight());
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, getX(), getY(), null);
	}
	
	@Override
	public void stylePaint(Paint p) {
		// Override to do nothing by default, optionally implemented by descendants
	}
	
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public void setDrawable(Context c, int drawable) {
		this.bitmap = BitmapFactory.decodeResource(c.getResources(), drawable);
	}
}
