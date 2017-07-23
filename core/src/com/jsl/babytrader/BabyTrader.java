package com.jsl.babytrader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jsl.babytrader.Screens.CreditScreen;
import com.jsl.babytrader.Screens.GameScreen;
import com.jsl.babytrader.Screens.InitScreen;
import com.jsl.babytrader.Screens.SliderNegativeScreen;
import com.jsl.babytrader.Screens.SliderPositiveScreen;

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
