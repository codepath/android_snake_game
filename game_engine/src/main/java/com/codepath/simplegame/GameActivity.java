package com.codepath.simplegame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// switchFullscreen();
	}

	// Set the view as full screen to hide the actionbar
	public void switchFullscreen() {
		// requesting to turn the title OFF
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// making it full screen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		   WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}
