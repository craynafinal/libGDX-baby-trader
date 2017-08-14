package com.jsl.babytrader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jsl.babytrader.Screens.CreditScreen;
import com.jsl.babytrader.Screens.GameOverScreen;
import com.jsl.babytrader.Screens.GameScreen;
import com.jsl.babytrader.Screens.InitScreen;
import com.jsl.babytrader.Screens.LogoScreen;
import com.jsl.babytrader.Screens.ReadyScreen;
import com.jsl.babytrader.Screens.SliderNegativeScreen;
import com.jsl.babytrader.Screens.SliderPositiveScreen;
import com.jsl.babytrader.Screens.TutorialScreen;

public class BabyTrader extends Game {
	public static InitScreen initScreen = null;
	public static SliderPositiveScreen sliderPositiveScreen = null;
	public static SliderNegativeScreen sliderNegativeScreen = null;
	public static CreditScreen creditScreen = null;
	public static GameScreen gameScreen = null;
	public static GameOverScreen gameOverScreen = null;
	public static TutorialScreen tutorialScreen = null;
	public static ReadyScreen readyScreen = null;
	public static LogoScreen logoScreen = null;

    public SpriteBatch batch = null;

	@Override
	public void create () {
		initScreen = new InitScreen(this);
		sliderPositiveScreen = new SliderPositiveScreen(this);
		sliderNegativeScreen = new SliderNegativeScreen(this);
		creditScreen = new CreditScreen(this);
		gameScreen = new GameScreen(this);
		gameOverScreen = new GameOverScreen(this);
		tutorialScreen = new TutorialScreen(this);
		readyScreen = new ReadyScreen(this);
		logoScreen = new LogoScreen(this);

		batch = new SpriteBatch();
		setScreen(logoScreen);
	}
	
	@Override
	public void dispose () {
		super.render();
	}
}
