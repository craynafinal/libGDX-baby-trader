package com.jsl.babytrader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jsl.babytrader.Screens.PlayScreen;

public class BabyTrader extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		// create new threads here?

		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}
	
	@Override
	public void dispose () {
		// clean up threads here

		super.render();
	}
}
