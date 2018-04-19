package com.pointless;

import com.badlogic.gdx.Game;

public class come extends Game {

	@Override
	public void create() {
		setScreen(new StartScreen(this));
	}
}
