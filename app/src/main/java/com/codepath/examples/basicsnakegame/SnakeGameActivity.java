package com.codepath.examples.basicsnakegame;

import android.os.Bundle;

public class SnakeGameActivity extends com.codepath.simplegame.GameActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		switchFullscreen();
		setContentView(new SnakeGamePanel(this));
	}
}
