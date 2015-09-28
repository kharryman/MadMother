package com.lfq.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.lfq.GameWorld.GameRenderer;
import com.lfq.GameWorld.GameWorld;
import com.lfq.Helpers.IActivityRequestHandler;
import com.lfq.Helpers.InputHandler;

public class GameScreen implements Screen {
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime = 0, delta = 0;
	private int midPointY;
	private InputHandler inputHandler;
	private float gameHeight, gameWidth;
	public int getMidPointY;
	public float scaleFactorX, scaleFactorY;
	private IActivityRequestHandler myRequestHandler;

	public GameScreen(IActivityRequestHandler handler) {
		myRequestHandler=handler;
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		gameWidth = 272;
		gameHeight = 408;
		midPointY = (int) gameHeight / 2;
		scaleFactorX = screenWidth / gameWidth;
		scaleFactorY = screenHeight / gameHeight;
		world = new GameWorld(this,myRequestHandler); // initialize world
		renderer = new GameRenderer(world, this); // initialize renderer
		inputHandler = new InputHandler(renderer, world, this);
		Gdx.input.setInputProcessor(inputHandler);
	}

	public InputHandler getInputHandler() {
		return inputHandler;
	}

	public int getGameWidth() {
		return (int) gameWidth;
	}

	public int getGameHeight() {
		return (int) gameHeight;
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(delta, runTime);
		inputHandler.updateInput();

	}

	public float getDelta() {
		return delta;
	}

	public float getRunTime() {
		return runTime;
	}

	@Override
	public void resize(int width, int height) {
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 272;
		gameHeight = 408;
		midPointY = (int) gameHeight / 2;
		scaleFactorX = screenWidth / gameWidth;
		scaleFactorY = screenHeight / gameHeight;
	}

	public int getMidPointY() {
		return midPointY;
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	public float getScaleFactorX() {

		return scaleFactorX;
	}

	public float getScaleFactorY() {
		return scaleFactorY;
	}

}
