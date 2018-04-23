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
		atlas = new TextureAtlas(Gdx.files.internal("list.atlas"));
		over = false;
		frames.add(new TextureRegion(atlas.findRegion("button",0)));
		frames.add(new TextureRegion(atlas.findRegion("button",1)));


		animation = new Animation<TextureRegion>(1 / 5f, frames);
	}



	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

			getStage().getBatch().draw(animation.getKeyFrame(showTime,true),2*game.screenWidth/3-85, 120-85);

	}

	@Override
	public void act(float delta) {
		super.act(delta);
		showTime += delta;
		//	batch.draw(img, 0, 0);

	}
}
