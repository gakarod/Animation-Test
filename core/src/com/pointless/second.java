package com.pointless;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class second extends Actor {
	private Animation<TextureRegion> animation;
	TextureRegion img;
	private float showTime = 0;
	private TextureAtlas atlas;
	Array<TextureRegion> frames = new Array<TextureRegion>();
	come game;
	public Boolean over;

	public second(come game) {
		this.game = game;
		atlas = new TextureAtlas(Gdx.files.internal("second.atlas"));
		over = false;
		frames.add(new TextureRegion(atlas.findRegion("second", 0)));
		frames.add(new TextureRegion(atlas.findRegion("second", 1)));
		frames.add(new TextureRegion(atlas.findRegion("second", 2)));
		frames.add(new TextureRegion(atlas.findRegion("second", 3)));
		frames.add(new TextureRegion(atlas.findRegion("second", 4)));


		animation = new Animation<TextureRegion>(1 / 5f, frames);
	}



	@Override
	public void draw(Batch batch, float parentAlpha) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.draw(batch, parentAlpha);

	}

	@Override
	public void act(float delta) {
		super.act(delta);
		showTime += delta;
		//	batch.draw(img, 0, 0);
		img = animation.getKeyFrame(showTime, false);
		if(animation.getKeyFrame(showTime ) != null) {
			game.batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
		if (!animation.isAnimationFinished(showTime)) {
			over = false;
		} else {
			over = true;
		}
	}
}
