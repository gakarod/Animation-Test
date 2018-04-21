package com.pointless;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class come extends Game {
	BitmapFont font;
	public int incorrect;
	public SpriteBatch batch;
	public float screenWidth, screenHeight;
	public long lastClick;
	public String speechOutput;
	public Sprite backgroundSprite;

	public come(TextSpeechCore textToSpeech, SpeechTextCore speechToText) {
		CommonObjects.textToSpeech = textToSpeech;
		CommonObjects.speechToText = speechToText;
	}

	@Override
	public void create() {
		incorrect = 0;
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		font = new BitmapFont(Gdx.files.internal("score.fnt"));
		batch = new SpriteBatch();
		setScreen(new StartScreen(this));
	}

	public void checkPermissions() {
		CommonObjects.speechToText.checkRecord();

	}
	public void showToast(String message) {
		CommonObjects.speechToText.showToast(message, 5000);
	}

	public void setTextFieldText(String text) {
		speechOutput = text;
	}
}
