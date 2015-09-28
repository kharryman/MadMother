package com.lfq.madmother;

import com.badlogic.gdx.Game;
import com.lfq.Helpers.AssetLoader;
import com.lfq.Helpers.IActivityRequestHandler;
import com.lfq.Screens.SplashScreen;

public class Madmother extends Game{
	private IActivityRequestHandler myRequestHandler;

	public Madmother(IActivityRequestHandler handler) {
		myRequestHandler=handler;
	}

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this,myRequestHandler));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
	
	public IActivityRequestHandler getMyRequestHandler(){
		return myRequestHandler;
	}

}
