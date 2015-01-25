package com.codepath.simplegame.threads;

import com.codepath.simplegame.AbstractGamePanel;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class FrameGameLoopThread extends BaseGameThread {
	// desired fps
	private final static int MAX_FPS = 50;
	// maximum number of frames to be skipped
	private final static int MAX_FRAME_SKIPS = 5;
	// the frame period
	private final static int FRAME_PERIOD = 1000 / MAX_FPS;
	
	public FrameGameLoopThread(SurfaceHolder surfaceHolder, AbstractGamePanel gamePanel) {
		super(surfaceHolder, gamePanel);
	}

	@Override
	public void run() {
		Canvas canvas;
		long beginTime;		// the time when the cycle begun
		long timeDiff;		// the time it took for the cycle to execute
		int sleepTime;		// ms to sleep (<0 if we're behind)
		int framesSkipped;	// number of frames being skipped 

		sleepTime = 0;
		// execute onStart
		this.gamePanel.onStart();
		// execute game loop
		while (running) {
			canvas = null;
			// try locking the canvas for exclusive pixel editing
			// in the surface
			try {
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					beginTime = System.currentTimeMillis();
					framesSkipped = 0;	// resetting the frames skipped
					// update game state
					this.gamePanel.onTimer();
					// draws the canvas on the panel
					if (canvas != null) {
					  this.gamePanel.render(canvas);
					}
					
					// calculate how long did the cycle take
					timeDiff = System.currentTimeMillis() - beginTime;
					// calculate sleep time
					sleepTime = (int)(FRAME_PERIOD - timeDiff);
					
					if (sleepTime > 0) {
						// if sleepTime > 0 we're OK
						try {
							// send the thread to sleep for a short period
							// very useful for battery saving
							Thread.sleep(sleepTime);	
						} catch (InterruptedException e) {
							// Do nothing
						}
					}
					
					while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
						// we need to catch up
						this.gamePanel.onTimer(); // update without rendering
						sleepTime += FRAME_PERIOD;	// add frame period to check if in next frame
						framesSkipped++;
					}
				}
			} finally {
				// in case of an exception the surface is not left in 
				// an inconsistent state
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}	// end finally
		}
	}
}
