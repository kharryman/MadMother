package com.lfq.Helpers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.lfq.GameObjects.Hand;
import com.lfq.GameObjects.Mother;
import com.lfq.GameWorld.GameRenderer;
import com.lfq.GameWorld.GameWorld;
import com.lfq.GameWorld.GameWorld.GameState;
import com.lfq.Screens.GameScreen;

public class InputHandler implements InputProcessor {

	private Mother myMother;
	private Hand myHand;
	private GameWorld myWorld;
	private Rectangle tryAgainButton_bounds, gameOverToMenuButton_bounds,
			easyButton_bounds, mediumButton_bounds, difficultButton_bounds,
			reset_bounds, play_pause_bounds, store_bounds, quitButton_bounds,
			helpButton_bounds, menuButton_bounds, levToStoreButton_bounds,
			levToBabiesButton_bounds, cigbox_bounds, superrattle_bounds,
			helpLeft_bounds, helpRight_bounds, nullStartButton_bounds,
			nullContinueButton_bounds, levelBar_bounds,
			nullStartPlayButton_bounds;
	private Rectangle energy_bounds, cigs_bounds, meds_bounds,
			back_to_babies_bounds;
	private Button play_button;
	private int handx, handy;
	private float scaleFactorX;
	private int midPointY;
	private String which_button;
	private GameRenderer renderer;
	private int savbefX, savbefY;
	public Rectangle vbar1, vbar2, hbar1, hbar2, hbar3, hbar4, hbar5;
	private GameScreen myGameScreen;
	private float scaleFactorY;
	private String difficulty;
	private Actor hover;
	private float xmark_int, xmax_levelBar;
	private int highest_level;

	public InputHandler(GameRenderer newrenderer, GameWorld myWorld,
			GameScreen gamescreen) {
		this.myWorld = myWorld;
		difficulty = myWorld.getWhichDifficulty();
		this.midPointY = myWorld.getMidPointY();

		myMother = myWorld.getMother();
		myHand = myWorld.getHand();
		myGameScreen = gamescreen;

		tryAgainButton_bounds = new Rectangle(60, 80, 80, 40);
		reset_bounds = new Rectangle(272 - 70, 0, 32, 24);
		store_bounds = new Rectangle(272 - 32, 66, 32, 24);
		play_pause_bounds = new Rectangle(272 - 38, 0, 38, 30);
		energy_bounds = new Rectangle(0, 15, 65, 52);
		cigs_bounds = new Rectangle(0, 70, 65, 52);
		meds_bounds = new Rectangle(0, 176, 65, 52);
		cigbox_bounds = new Rectangle(0, 123, 65, 52);
		superrattle_bounds = new Rectangle(0, 231, 65, 52);
		back_to_babies_bounds = new Rectangle(171, 278, 101, 91);
		easyButton_bounds = new Rectangle(0, 80, 70, 32);
		mediumButton_bounds = new Rectangle(0, 112, 70, 32);
		difficultButton_bounds = new Rectangle(0, 144, 70, 32);
		quitButton_bounds = new Rectangle(272 - 32, 98, 32, 24);
		helpButton_bounds = new Rectangle(220, 376, 52, 32);
		menuButton_bounds = new Rectangle(0, 315, 50, 50);
		gameOverToMenuButton_bounds = new Rectangle(0, 355, 50, 50);
		levToStoreButton_bounds = new Rectangle(182, 268, 90, 70);
		levToBabiesButton_bounds = new Rectangle(182, 338, 90, 70);
		helpLeft_bounds = new Rectangle(55, 340, 25, 25);
		helpRight_bounds = new Rectangle(85, 340, 25, 25);
		nullStartButton_bounds = new Rectangle(0, 176, 81, 47);
		nullContinueButton_bounds = new Rectangle(0, 230, 81, 47);
		nullStartPlayButton_bounds = new Rectangle(100, 120, 80, 60);
		highest_level = myWorld.getMostNowLevel();
		if (highest_level != 0) {
			xmark_int = 260 / highest_level;
		}
		xmax_levelBar = xmark_int * highest_level;
		levelBar_bounds = new Rectangle(0, 295, xmax_levelBar - 1, 12);

		which_button = "playButtonUp";
		renderer = newrenderer;

	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		highest_level = myWorld.getMostNowLevel();
		if (myWorld.isMenu()) {

			if (easyButton_bounds.contains(screenX, screenY)) {
				myWorld.setWhichDifficulty("Easy");
			}
			if (mediumButton_bounds.contains(screenX, screenY)
					&& AssetLoader.isMediumUnLocked() == true) {
				myWorld.setWhichDifficulty("Medium");
			}
			if (difficultButton_bounds.contains(screenX, screenY)
					&& AssetLoader.isDifficultUnLocked() == true) {
				myWorld.setWhichDifficulty("Difficult");
			}
			if (helpButton_bounds.contains(screenX, screenY)) {
				myWorld.goToHelp();
			}
			if (nullStartButton_bounds.contains(screenX, screenY)) {
				myWorld.start(0);
			}
			if (highest_level != 0) {
				xmark_int = 260 / highest_level;
				xmax_levelBar = xmark_int * highest_level;
				levelBar_bounds = new Rectangle(0, 295, xmax_levelBar - 1, 12);
				if (nullContinueButton_bounds.contains(screenX, screenY)) {
					int continue_level = 0;
					int selected_level = myWorld.getSelectedLevel();
					continue_level = highest_level;
					if (selected_level != 0) {
						continue_level = selected_level;
					}
					myWorld.start(continue_level);
				}
				if (levelBar_bounds.contains(screenX, screenY)) {
					int selected_level = (int) (screenX / xmark_int);
					myWorld.setSelectedLevel(selected_level + 1);
				} else {
					myWorld.setSelectedLevel(0);
				}
			}
		}
		if (myWorld.isLevelUp()) {
			if (levToStoreButton_bounds.contains(screenX, screenY)) {
				myWorld.setGameState(GameState.STORE);
			}
			if (levToBabiesButton_bounds.contains(screenX, screenY)) {
				myWorld.setGameState(GameState.RUNNING);
			}
		}
		if (myWorld.isRunning()) {
			if (reset_bounds.contains(screenX, screenY)) {
				myWorld.restart("restart");
			}
			if (store_bounds.contains(screenX, screenY)) {
				myWorld.goToStore();
			}
			if (play_pause_bounds.contains(screenX, screenY)
					|| (nullStartPlayButton_bounds.contains(screenX, screenY) && myWorld
							.getPlay() == false)) {
				myWorld.togglePlayPause();
			}
			if (quitButton_bounds.contains(screenX, screenY)) {
				myWorld.restart("quit");
			}
		}
		if (myWorld.isStore()) {
			int cash = myWorld.getCash();
			if (energy_bounds.contains(screenX, screenY)) {
				if (cash >= 5) {
					myWorld.setCash(cash - 5);
					myWorld.addEnergy();
					renderer.setShopTalk("Thank you.", "", "");
				} else {
					renderer.setShopTalk("You don't have",
							"the cash for that.", "");
				}
			}
			if (cigs_bounds.contains(screenX, screenY)) {
				if (cash >= 10) {
					myWorld.setCash(cash - 10);
					myWorld.addCigs();
					renderer.setShopTalk("Thank you.", "", "");
				} else {
					renderer.setShopTalk("You don't have",
							"the cash for that.", "");
				}
			}
			if (cigbox_bounds.contains(screenX, screenY)) {
				if (cash >= 30) {
					myWorld.setCash(cash - 30);
					myWorld.addCigBoxes();
					renderer.setShopTalk("Thank you.", "", "");
				} else {
					renderer.setShopTalk("You don't have",
							"the cash for that.", "");
				}
			}
			if (meds_bounds.contains(screenX, screenY)
					&& AssetLoader.isMediumUnLocked() == true) {
				if (cash >= 50) {
					myWorld.setCash(cash - 50);
					myWorld.addMeds();
					renderer.setShopTalk("Thank you.", "", "");
				} else {
					renderer.setShopTalk("You don't have",
							"the cash for that.", "");
				}
			}
			if (superrattle_bounds.contains(screenX, screenY)
					&& AssetLoader.isDifficultUnLocked() == true) {
				if (cash >= 50) {
					myWorld.setCash(cash - 50);
					myWorld.addSuperRattles();
					renderer.setShopTalk("Thank you.", "", "");
				} else {
					renderer.setShopTalk("You don't have",
							"the cash for that.", "");
				}
			}
			if (back_to_babies_bounds.contains(screenX, screenY)) {
				myWorld.setGameState(GameState.RUNNING);
			}
		}
		if (myWorld.isGameOver() || myWorld.isHighScore()) {
			if (tryAgainButton_bounds.contains(screenX, screenY)) {
				myWorld.restart("restart");
			}
			if (gameOverToMenuButton_bounds.contains(screenX, screenY)) {
				myWorld.setGameState(GameState.MENU);
			}
		}
		if (myWorld.isHelp1() || myWorld.isHelp2() || myWorld.isHelp3()) {
			if (menuButton_bounds.contains(screenX, screenY)) {
				myWorld.setGameState(GameState.MENU);
			}
			if (myWorld.isHelp1()
					&& helpRight_bounds.contains(screenX, screenY)) {
				myWorld.setGameState(GameState.HELP2);
			} else if (myWorld.isHelp2()
					&& helpLeft_bounds.contains(screenX, screenY)) {
				myWorld.setGameState(GameState.HELP1);
			} else if (myWorld.isHelp2()
					&& helpRight_bounds.contains(screenX, screenY)) {
				myWorld.setGameState(GameState.HELP3);
			} else if (myWorld.isHelp3()
					&& helpLeft_bounds.contains(screenX, screenY)) {
				myWorld.setGameState(GameState.HELP2);
			}

		}

		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		myHand.onDrag(screenX, screenY);
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	public String getWhichButton() {
		return which_button;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	private int scaleX(int screenX) {
		return (int) (screenX / myGameScreen.getScaleFactorX());
	}

	private int scaleY(int screenY) {
		return (int) (screenY / myGameScreen.getScaleFactorY());
	}

	@Override
	public boolean keyDown(int keycode) {

		if (keycode == Keys.SPACE) {

			if (myWorld.isMenu()) {
				myWorld.start(0);
			} else if (myWorld.isReady()) {
				myWorld.start(0);
			}

			if (myWorld.isGameOver() || myWorld.isHighScore()) {
				myWorld.restart("restart");
			}

		}

		return true;
	}

	@Override
	public boolean keyUp(int keycode) {

		return false;

	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	public void updateInput() {
		int screenX = Gdx.input.getX();
		int screenY = Gdx.input.getY();
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		highest_level = myWorld.getMostNowLevel();
		if (myWorld.isRunning()) {
			if (reset_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("resetDown");
			} else {
				renderer.setWhichButton("resetUp");
			}
			if (store_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("storeDown");
			} else {
				renderer.setWhichButton("storeUp");
			}
			if (quitButton_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("QuitDown");
			} else {
				renderer.setWhichButton("QuitUp");
			}
			if (myWorld.getPlay() == false) {
				if (nullStartPlayButton_bounds.contains(screenX, screenY)) {
					renderer.setWhichButton("nullStartPlayButtonDown");
				} else {
					renderer.setWhichButton("nullStartPlayButtonUp");
				}
			}
		}

		if (myWorld.isLevelUp()) {
			if (levToStoreButton_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("LevToStoreDown");
			} else {
				renderer.setWhichButton("LevToStoreUp");
			}
			if (levToBabiesButton_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("LevToBabiesDown");
			} else {
				renderer.setWhichButton("LevToBabiesUp");
			}
		}

		if (myWorld.isMenu()) {
			difficulty = myWorld.getWhichDifficulty();

			if (highest_level != 0) {
				xmark_int = 260 / highest_level;
				xmax_levelBar = xmark_int * highest_level;
				levelBar_bounds = new Rectangle(0, 295, xmax_levelBar - 1, 12);
				if (levelBar_bounds.contains(screenX, screenY)) {
					int selected_level = (int) Math.ceil(screenX / xmark_int);
					renderer.setHighlightedLevel(selected_level);
				} else {
					renderer.setHighlightedLevel(0);
				}
			}

			if (easyButton_bounds.contains(screenX, screenY)
					|| difficulty.equals("Easy")) {
				renderer.setWhichButton("EasyDown");
			} else {
				renderer.setWhichButton("EasyUp");
			}

			if ((mediumButton_bounds.contains(screenX, screenY) || difficulty
					.equals("Medium"))
					&& AssetLoader.isMediumUnLocked() == true) {
				renderer.setWhichButton("MediumDown");
			} else {
				renderer.setWhichButton("MediumUp");
			}

			if ((difficultButton_bounds.contains(screenX, screenY) || difficulty
					.equals("Difficult"))
					&& AssetLoader.isDifficultUnLocked() == true) {
				renderer.setWhichButton("DifficultDown");
			} else {
				renderer.setWhichButton("DifficultUp");
			}
			if (helpButton_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("HelpDown");
			} else {
				renderer.setWhichButton("HelpUp");
			}
			if (nullStartButton_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("nullStartButtonDown");
			} else {
				renderer.setWhichButton("nullStartButtonUp");
			}
			if (highest_level != 0) {
				if (nullContinueButton_bounds.contains(screenX, screenY)) {
					renderer.setWhichButton("nullContinueButtonDown");
				} else {
					renderer.setWhichButton("nullContinueButtonUp");
				}
			}
		}
		if (myWorld.isGameOver() || myWorld.isHighScore()) {
			if (tryAgainButton_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("tryAgainDown");
			} else {
				renderer.setWhichButton("tryAgainUp");
			}
			if (gameOverToMenuButton_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("gameOverToMenuDown");
			} else {
				renderer.setWhichButton("gameOverToMenuUp");
			}
		}
		if (myWorld.isStore()) {
			if (energy_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("energyDown");
			} else {
				renderer.setWhichButton("energyUp");
			}
			if (cigs_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("cigsDown");
			} else {
				renderer.setWhichButton("cigsUp");
			}
			if (cigbox_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("cigBoxDown");
			} else {
				renderer.setWhichButton("cigBoxUp");
			}
			if (meds_bounds.contains(screenX, screenY) && AssetLoader.isMediumUnLocked() == true) {
				renderer.setWhichButton("medsDown");
			} else {
				renderer.setWhichButton("medsUp");
			}
			if (superrattle_bounds.contains(screenX, screenY)
					&& AssetLoader.isDifficultUnLocked() == true) {
				renderer.setWhichButton("superRattleDown");
			} else {
				renderer.setWhichButton("superRattleUp");
			}
			if (back_to_babies_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("backToBabiesDown");
			} else {
				renderer.setWhichButton("backToBabiesUp");
			}
			if (!energy_bounds.contains(screenX, screenY)
					&& !cigs_bounds.contains(screenX, screenY)
					&& !cigbox_bounds.contains(screenX, screenY)
					&& !back_to_babies_bounds.contains(screenX, screenY)
					&& (AssetLoader.isMediumUnLocked() == false || !meds_bounds
							.contains(screenX, screenY))
					&& (AssetLoader.isDifficultUnLocked() == false || !superrattle_bounds
							.contains(screenX, screenY))) {
				renderer.setShopTalk("What do you want?!", "", "");
			}

		}
		if (myWorld.isHelp1() || myWorld.isHelp2() || myWorld.isHelp3()) {
			if (menuButton_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("MenuDown");
			} else {
				renderer.setWhichButton("MenuUp");
			}
			if ((myWorld.isHelp2() || myWorld.isHelp3())
					&& helpLeft_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("helpLeftDown");
			} else {
				renderer.setWhichButton("helpLeftUp");
			}
			if ((myWorld.isHelp1() || myWorld.isHelp2())
					&& helpRight_bounds.contains(screenX, screenY)) {
				renderer.setWhichButton("helpRightDown");
			} else {
				renderer.setWhichButton("helpRightUp");
			}
		}

	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

}
