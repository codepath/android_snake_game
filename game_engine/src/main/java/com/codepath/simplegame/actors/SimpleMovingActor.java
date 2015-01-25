package com.codepath.simplegame.actors;

import com.codepath.simplegame.Velocity;

public abstract class SimpleMovingActor extends PositionedActor {
	private Velocity velocity;

	public SimpleMovingActor(int x, int y) {
		super(x, y);
		this.velocity = new Velocity();
	}
	
	public SimpleMovingActor(int x, int y, int width, int height) { 
		this(x, y);
		this.setDimensions(width, height);
	}
	
	// Called to move position based on the velocity
	public void move() {
		if (this.isEnabled()) {
			getPoint().x += (velocity.getXSpeed() * velocity.getXDirection());
			getPoint().y += (velocity.getYSpeed() * velocity.getYDirection());
		}
	}
	
	public Velocity getVelocity() {
		return velocity;
	}
}
