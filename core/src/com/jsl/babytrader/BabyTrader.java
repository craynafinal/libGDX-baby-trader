package com.jsl.babytrader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jsl.babytrader.Screens.InitScreen;

public class BabyTrader extends Game {
    public SpriteBatch batch = null;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new InitScreen(this));
	}
	
	@Override
	public void dispose () {
		super.render();
	}
}
