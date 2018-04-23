package com.pointless;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class come extends Game {
	BitmapFont font;
	BitmapFont font2;
	public int incorrect;
	public SpriteBatch batch;
	public float screenWidth, screenHeight;
	public long lastClick;
	public String speechOutput;
	public Sprite backgroundSprite;
	public ExerciseData exerciseData;

	public come(TextSpeechCore textToSpeech, SpeechTextCore speechToText , ImageLoaderCore imageLoader, UrlHandler urlHandler) {
		CommonObjects.textToSpeech = textToSpeech;
		CommonObjects.speechToText = speechToText;
		CommonObjects.imageLoader = imageLoader;
		CommonObjects.urlHandler = urlHandler;
	}

	@Override
	public void create() {
		CommonObjects.urlHandler.callServer();
		incorrect = 0;
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		font = new BitmapFont(Gdx.files.internal("close.fnt"));
		font2 = new BitmapFont(Gdx.files.internal("close2.fnt"));
		font.setColor(Color.PURPLE);
		font.getData().setScale(1,3);
		batch = new SpriteBatch();
		setScreen(new StartScreen(this , exerciseData));

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
