package com.lfq.Screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lfq.Helpers.AssetLoader;
import com.lfq.Helpers.IActivityRequestHandler;
import com.lfq.TweenAccessors.SpriteAccessor;
import com.lfq.madmother.Madmother;

public class SplashScreen implements Screen {
	private Madmother game;
	private TweenManager manager;
	private SpriteBatch batcher;
	private Sprite sprite;
	private IActivityRequestHandler myRequestHandler;
	public SplashScreen(Madmother game,IActivityRequestHandler handler) {
		this.game = game;
		myRequestHandler=handler;
	}

	@Override
	public void render(float delta) {
		manager.update(delta);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batcher.begin();
		sprite.draw(batcher);
		batcher.end();

	}

	@Override
	public void show() {
		sprite = new Sprite(AssetLoader.mad_mother_logo);
		sprite.setColor(1, 1, 1, 0);

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		float desiredWidth = width * .7f;
		float scale = desiredWidth / sprite.getWidth();

		sprite.setSize(width, height);
		sprite.setPosition(0, 0);
		setupTween();
		batcher = new SpriteBatch();

	}

	private void setupTween() {
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		manager = new TweenManager();
		

		TweenCallback cb = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				game.setScreen(new GameScreen(myRequestHandler));
			}
		};

		Tween.to(sprite, SpriteAccessor.ALPHA, .8f).target(1)
				.ease(TweenEquations.easeInOutQuad).repeatYoyo(1, .4f)
				.setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
				.start(manager);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
