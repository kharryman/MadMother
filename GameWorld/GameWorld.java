package com.lfq.GameWorld;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.lfq.GameObjects.Baby;
import com.lfq.GameObjects.Hand;
import com.lfq.GameObjects.Mother;
import com.lfq.GameObjects.Speech_Bubble;
import com.lfq.Helpers.AssetLoader;
import com.lfq.Helpers.IActivityRequestHandler;
import com.lfq.Screens.GameScreen;
import com.lfq.madmother.Madmother;

public class GameWorld {
	final private int RADIUSA = 64;
	final private int RADIUSB = 130;
	final private int MIDX = 150;
	final private int MIDY = 230;
	public double posx;
	public double posy;
	private static Preferences prefs;
	
    private double Radius;
	private Random random;
	private Mother myMother;
	private double theta1, theta2, theta3, theta4, theta5, theta6;
	public Hand hand;
	private int score, cash, special_score, num_energy, num_cigs, num_meds,
			num_cigboxes, num_superrattles;
	private int number_babies_crying, temper;
	private GameRenderer myRenderer;
	private int midPointY, storeX, levelUpX;
	private float delta = 0, runTime = 0, wintime = 0;
	private GameScreen gamescreen;
	private int level_progress;
	private int easy_wait_int = 50;
	private int medium_wait_int = 50;
	private int difficult_wait_int = 50;
	private int wait_int = easy_wait_int;
	private int ranratx, ranraty;

	public static Baby baby1, baby2, baby3, baby4, baby5, baby6;
	public static Speech_Bubble spebub1, spebub2, spebub3, spebub4, spebub5,
			spebub6;

	public static GameState currentState;
	private Boolean is_play;
	private Boolean next_energy, next_meds, supply_subtracted;
	private int level;
	private String difficulty;
	private Boolean is_rattle, set_rattle, is_mother_screamed;
	private int selected_level;
	private float drinkTime, medsTime;
	private int ranx_offset=0,ct_ranx_offset=0;
	
	private Madmother game;
	private IActivityRequestHandler myRequestHandler;
	

	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE, STORE, NULL, HELP1, HELP2, HELP3, LEVELUP, WAIT_LEVELUP
	}

	public GameWorld(GameScreen gamescreen,IActivityRequestHandler handler) {
		myRequestHandler=handler;
		difficulty = "Easy";
		drinkTime = 0;
	  medsTime = 0;
		selected_level = 0;
		supply_subtracted = false;
		next_energy = false;
		next_meds = false;
		is_rattle = false;
		set_rattle = false;
		is_mother_screamed = false;
		level = 0;
		level_progress = 0;
		currentState = GameState.MENU;
		random = new Random();
		this.midPointY = gamescreen.getMidPointY;
		hand = new Hand(midPointY, this);
		myMother = new Mother(82, 92, this);
		baby1 = new Baby(getObjX(theta1),getObjY(theta1), 40, 40, this);
		baby2 = new Baby(getObjX(theta2), getObjY(theta2),40, 40, this);
		baby3 = new Baby(getObjX(theta3), getObjY(theta3), 40, 40, this);
		baby4 = new Baby(getObjX(theta4), getObjY(theta4), 40, 40, this);
		baby5 = new Baby(getObjX(theta5), getObjY(theta5), 40, 40, this);
		baby6 = new Baby(getObjX(theta6), getObjY(theta6), 40, 40, this);
		spebub1 = new Speech_Bubble(getObjX(theta1), getObjY(theta1), 44, 32);
		spebub2 = new Speech_Bubble(getObjX(theta2), getObjY(theta2), 44, 32);
		spebub3 = new Speech_Bubble(getObjX(theta3), getObjY(theta3), 44, 32);
		spebub4 = new Speech_Bubble(getObjX(theta4), getObjY(theta4), 44, 32);
		spebub5 = new Speech_Bubble(getObjX(theta5), getObjY(theta5), 44, 32);
		spebub6 = new Speech_Bubble(getObjX(theta6), getObjY(theta6), 44, 32);
		temper = 1000;
		number_babies_crying = 0;
		score = 0;
		special_score = 0;
		num_energy = 0;
		num_cigs = 0;
		num_meds = 0;
		storeX = 0;
		levelUpX = 0;
		is_play = false;
		ranratx = 0;
		ranraty = 0;

	}

	public void update(float delta) {
		
		if (AssetLoader.getEasyHighScore() >= 100) {
			
			AssetLoader.setMediumUnlocked();
			}
		if (AssetLoader.getMediumHighScore() >= 100) {
			AssetLoader.setDifficultUnlocked();
			
		}
		switch (currentState) {
		case HELP1:
			myRequestHandler.changeTopBottom(false);
			break;
		case HELP2:
			myRequestHandler.changeTopBottom(false);
			break;
		case HELP3:
			myRequestHandler.changeTopBottom(false);
			break;
		case READY:
			break;
		case MENU:
			updateReady(delta);
			break;
		case WAIT_LEVELUP:
			updateLevelUpX();
			break;
		case NULL:
			myRequestHandler.changeTopBottom(false);
			myRequestHandler.showAds(true);
			updateStore(delta);
			break;
		case RUNNING:
			updateRunning(delta);
			break;
		case STORE:
			myRequestHandler.changeTopBottom(false);
			myRequestHandler.showAds(true);
		default:
			break;
		}

	}

	private void updateStore(float delta) {
		if (storeX > -164) {
			storeX -= 2;
		}

		hand.update(delta);
	}

	public void updateReady(float delta) {
		myRequestHandler.changeTopBottom(true);
		myRequestHandler.showAds(true);
	}

	public void updateRunning(float delta) {
		myRequestHandler.changeTopBottom(true);
		if (is_play == true) {
			myRequestHandler.showAds(false);
			theta1 = theta1 + Math.toRadians(1);
			if (theta1 >= 2 * Math.PI) {
				theta1 = 0;
			}
			theta2 = theta2 + Math.toRadians(1);
			if (theta2 >= 2 * Math.PI) {
				theta2 = 0;
			}
			theta3 = theta3 + Math.toRadians(1);
			if (theta3 >= 2 * Math.PI) {
				theta3 = 0;
			}
			theta4 = theta4 + Math.toRadians(1);
			if (theta4 >= 2 * Math.PI) {
				theta4 = 0;
			}
			if (difficulty.equals("Medium")) {
				theta5 = theta5 + Math.toRadians(1);
				if (theta5 >= 2 * Math.PI) {
					theta5 = 0;
				}				
				baby5.update(delta, getObjX(theta5),getObjY(theta5), this);
				spebub5.update(delta, getObjX(theta5), getObjY(theta5));
			}
			if (difficulty.equals("Difficult")) {
				theta5 = theta5 + Math.toRadians(1);
				if (theta5 >= 2 * Math.PI) {
					theta5 = 0;
				}
				theta6 = theta6 + Math.toRadians(1);
				if (theta6 >= 2 * Math.PI) {
					theta6 = 0;
				}
				baby5.update(delta, getObjX(theta5), getObjY(theta5), this);
				baby6.update(delta, getObjX(theta6), getObjY(theta6), this);
				spebub5.update(delta, getObjX(theta5), getObjY(theta5));
				spebub6.update(delta, getObjX(theta6), getObjY(theta6));
			}
			wintime += delta;
			baby1.update(delta, getObjX(theta1), getObjY(theta1), this);
			baby2.update(delta, getObjX(theta2), getObjY(theta2), this);
			baby3.update(delta, getObjX(theta3), getObjY(theta3), this);
			baby4.update(delta, getObjX(theta4), getObjY(theta4), this);
			spebub1.update(delta, getObjX(theta1), getObjY(theta1));
			spebub2.update(delta, getObjX(theta2), getObjY(theta2));
			spebub3.update(delta, getObjX(theta3), getObjY(theta3));
			spebub4.update(delta, getObjX(theta4), getObjY(theta4));
			myMother.update(delta);
			hand.update(delta);
			int high_score = 0;
			if (difficulty.equals("Easy")) {
				high_score = AssetLoader.getEasyHighScore();
				if (score > high_score) {
					AssetLoader.setEasyHighScore(score);
					}
			}
			if (difficulty.equals("Medium")) {
				high_score = AssetLoader.getMediumHighScore();
				if (score > high_score) {
					AssetLoader.setMediumHighScore(score);
					}
			}
			if (difficulty.equals("Difficult")) {
				high_score = AssetLoader.getDifficultHighScore();
				if (score > high_score) {
					AssetLoader.setDifficultHighScore(score);
					}
			}
			if (score >= 100) {
				
				if (difficulty.equals("Medium")) {
					AssetLoader.setMediumUnlocked();
				}
				if (difficulty.equals("Difficult")) {
					AssetLoader.setDifficultUnlocked();
				}
			}

			if (wintime > 10) {
				wintime = 0;
				special_score = 0;
				is_rattle = false;
				set_rattle = false;
			}
			if (special_score >= 5 && set_rattle == false) {
				is_rattle = true;
				set_rattle = true;
				do {
					ranratx = random.nextInt(202) + 50;
					ranraty = random.nextInt(313) + 50;
				} while (ranratx >= 200 && ranraty <= 165);
			}
			if (getNumberBabiesCrying() < 4) {
				AssetLoader.mother_screaming.stop();
				is_mother_screamed = false;
			}
			if (getNumberBabiesCrying() >= 4) {
				if (getNextEnergy() == false && getNextMeds() == false) {
					temperUpdate();
					if (is_mother_screamed == false) {
						AssetLoader.mother_screaming.loop();
						is_mother_screamed = true;
					}
				} else {
					if (getNextEnergy() == true) {
						drinkTime += delta;
						if (drinkTime > 3) {
							setNextEnergy(false);
						}
					}
					if (getNextMeds() == true) {
						medsTime += delta;
						if (medsTime > 10) {
							setNextMeds(false);
						}
					}
				}
				if (temper <= 0) {
					currentState = GameState.GAMEOVER;
					
				}
			}
		}
	}

	private double getObjY(double newtheta) {
		posy = MIDY + RADIUSB * Math.sin(newtheta);
		return posy;
	}

	private double getObjX(double newtheta) {
		if (newtheta==0){ranx_offset=random.nextInt(15);}
		double posx_offset=(ranx_offset*Math.sin(newtheta*4));
		posx = MIDX + (RADIUSA * Math.cos(newtheta))+posx_offset;
		
		return posx;
	}

	public Baby getBaby1() {
		return baby1;

	}

	public Baby getBaby2() {
		return baby2;

	}

	public Baby getBaby3() {
		return baby3;
	}

	public Baby getBaby4() {
		return baby4;

	}

	public Baby getBaby5() {
		return baby5;

	}

	public Baby getBaby6() {
		return baby6;

	}

	public Speech_Bubble getSpeBub1() {
		return spebub1;

	}

	public Speech_Bubble getSpeBub2() {
		return spebub2;

	}

	public Speech_Bubble getSpeBub3() {
		return spebub3;

	}

	public Speech_Bubble getSpeBub4() {
		return spebub4;

	}

	public Speech_Bubble getSpeBub5() {
		return spebub5;

	}

	public Speech_Bubble getSpeBub6() {
		return spebub6;

	}

	public Mother getMother() {
		return myMother;
	}

	public Hand getHand() {
		return hand;
	}

	public int getScore() {
		return score;
	}

	public void resetStoreX() {
		storeX = 0;
	}

	public void addScore() {
		if (currentState != GameState.GAMEOVER) {
			score += 1;
			cash += 1;
			if (hand.getWhichString()=="rattle"){
				cash+=2;
			}
			if (wintime <= 10) {
				special_score += 1;
			}
		}
	}

	public Boolean isRattle() {
		return is_rattle;
	}

	public void setIsRattle(Boolean bool) {
		is_rattle = bool;
	}

	public int getRanRatX() {
		return ranratx;
	}

	public int getRanRatY() {
		return ranraty;
	}

	public int getMidPointY() {
		return midPointY;
	}

	public int getStoreX() {
		return storeX;
	}

	public int getNumberBabiesCrying() {
		int number_crying = baby1.isCrying() + baby2.isCrying()
				+ baby3.isCrying() + baby4.isCrying();
		if (difficulty.equals("Medium")) {
			number_crying += baby5.isCrying();
		}
		if (difficulty.equals("Difficult")) {
			number_crying += baby5.isCrying() + baby6.isCrying();
		}
		return number_crying;
	}

	public static GameState getGameState() {
		return currentState;
	}

	public void setGameState(GameState newgamestate) {
		currentState = newgamestate;

	}

	public boolean isMenu() {
		return currentState == GameState.MENU;
	}

	public boolean isLevelUp() {

		return currentState == GameState.LEVELUP;
	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public void start(int continue_level) {
		currentState = GameState.RUNNING;
		level = continue_level;
		is_play = false;
		wait_int = 50 + (5 * level);
		if (difficulty.equals("Easy")) {
			theta1 = 0;
			theta2 = Math.PI / 2;
			theta3 = Math.PI;
			theta4 = 3 * (Math.PI / 2);
		}
		if (difficulty.equals("Medium")) {
			theta1 = 0;
			theta2 = (2 * Math.PI) / 5;
			theta3 = (4 * Math.PI) / 5;
			theta4 = (6 * Math.PI) / 5;
			theta5 = (8 * Math.PI) / 5;

		}
		if (difficulty.equals("Difficult")) {
			theta1 = 0;
			theta2 = (Math.PI) / 3;
			theta3 = (2 * Math.PI) / 3;
			theta4 = Math.PI;
			theta5 = (4 * Math.PI) / 3;
			theta6 = (5 * Math.PI) / 3;
		}
		baby1.setTheta(theta1);
		baby2.setTheta(theta2);
		baby3.setTheta(theta3);
		baby4.setTheta(theta4);
		baby5.setTheta(theta5);
		baby6.setTheta(theta6);
		spebub1.setTheta(theta1);
		spebub2.setTheta(theta2);
		spebub3.setTheta(theta3);
		spebub4.setTheta(theta4);
		spebub5.setTheta(theta5);
		spebub6.setTheta(theta6);
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}

	public boolean isStore() {
		return currentState == GameState.STORE;
	}

	public boolean isNull() {
		return currentState == GameState.NULL;
	}

	public boolean isHelp1() {
		return currentState == GameState.HELP1;
	}

	public boolean isHelp2() {
		return currentState == GameState.HELP2;
	}

	public boolean isHelp3() {
		return currentState == GameState.HELP3;
	}

	public void restart(String state) {
		drinkTime=0;
		medsTime=0;
		cash = 0;
		runTime = 0;
		delta = 0;
		wintime = 0;
		score = 0;
		selected_level = 0;
		level_progress = 0;
		myRenderer.setHighlightedLevel(0);
		supply_subtracted = false;
		next_energy = false;
		is_rattle = false;
		set_rattle = false;
		is_mother_screamed = false;
		baby1.setHappy();
		baby2.setHappy();
		baby3.setHappy();
		baby4.setHappy();
		baby5.setHappy();
		baby6.setHappy();
		int wait = 0;
		if (difficulty.equals("Easy")) {
			wait = easy_wait_int;
		}
		if (difficulty.equals("Medium")) {
			wait = medium_wait_int;
		}
		if (difficulty.equals("Difficult")) {
			wait = difficult_wait_int;
		}

		setWaitInt(wait);
		if (difficulty.equals("Easy")) {
			theta1 = 0;
			theta2 = Math.PI / 2;
			theta3 = Math.PI;
			theta4 = 3 * (Math.PI / 2);
		}
		if (difficulty.equals("Medium")) {
			theta1 = 0;
			theta2 = (2 * Math.PI) / 5;
			theta3 = (4 * Math.PI) / 5;
			theta4 = (6 * Math.PI) / 5;
			theta5 = (8 * Math.PI) / 5;

		}
		if (difficulty.equals("Difficult")) {
			theta1 = 0;
			theta2 = (Math.PI) / 3;
			theta3 = (2 * Math.PI) / 3;
			theta4 = Math.PI;
			theta5 = (4 * Math.PI) / 3;
			theta6 = (5 * Math.PI) / 3;
		}
		baby1.setTheta(theta1);
		baby2.setTheta(theta2);
		baby3.setTheta(theta3);
		baby4.setTheta(theta4);
		baby5.setTheta(theta5);
		baby6.setTheta(theta6);
		spebub1.setTheta(theta1);
		spebub2.setTheta(theta2);
		spebub3.setTheta(theta3);
		spebub4.setTheta(theta4);
		spebub5.setTheta(theta5);
		spebub6.setTheta(theta6);
		cash = 0;
		num_energy = 0;
		num_cigs = 0;
		num_meds = 0;
		num_cigboxes = 0;
		num_superrattles = 0;
		hand.restart();
		resetTemper();
		if (state.equals("restart")) {
			currentState = GameState.RUNNING;
		}
		if (state.equals("quit")) {
			currentState = GameState.MENU;
		}
		setNextEnergy(false);
	}

	public void setRenderer(GameRenderer renderer) {
		myRenderer = renderer;
	}

	public GameRenderer getRenderer() {
		return myRenderer;
	}

	public void togglePlayPause() {
		if (is_play == true) {
			is_play = false;
		} else {
			is_play = true;
		}

	}

	public Boolean getPlay() {
		return is_play;
	}

	public void goToStore() {
		currentState = GameState.NULL;
		is_play = false;
		myRenderer.setShopTalk("What do you want?", "", "");
		resetStoreX();
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int newcash) {
		cash = newcash;
	}

	public void addEnergy() {
		num_energy += 1;
	}

	public void subtractEnergy() {
		num_energy -= 1;
	}

	public int getEnergy() {
		return num_energy;
	}

	public void addCigs() {
		num_cigs += 1;
	}

	public void subtractCigs() {
		num_cigs -= 1;
	}

	public int getCigs() {
		return num_cigs;
	}

	public void addMeds() {
		num_meds += 1;
	}

	public void subtractMeds() {
		num_meds -= 1;
	}

	public int getMeds() {
		return num_meds;
	}

	public int getCigBoxes() {
		return num_cigboxes;
	}

	public void addCigBoxes() {
		num_cigboxes += 1;
	}

	public void subtractCigBoxes() {
		num_cigboxes -= 1;
	}

	public int getSuperRattles() {
		return num_superrattles;
	}

	public void addSuperRattles() {
		num_superrattles += 1;
	}

	public void subtractSuperRattles() {
		num_superrattles -= 1;
	}

	public void temperUpdate() {
		temper -= 3;

	}

	public int getTemper() {
		return temper;

	}

	public void resetTemper() {
		temper = 1000;
	}

	public void setTemper(int add) {
		temper += add;
		if (temper > 1000) {
			temper = 1000;
		}
	}

	public void setNextEnergy(Boolean bool) {
		next_energy = bool;
	}

	public Boolean getNextEnergy() {
		return next_energy;
	}

	public void setNextMeds(Boolean bool) {
		next_meds = bool;
	}

	public Boolean getNextMeds() {
		return next_meds;
	}

	public void setSupplySubtracted(boolean b) {
		supply_subtracted = b;

	}

	public Boolean getSupplySubtracted() {
		return supply_subtracted;

	}

	public void addLevel() {
		level++;
		prefs = Gdx.app.getPreferences("Mad-Mother");
		String put_string = "";
		if (difficulty.equals("Easy")) {
			put_string = "easyLevel";
		}
		if (difficulty.equals("Medium")) {
			put_string = "mediumLevel";
		}
		if (difficulty.equals("Difficult")) {
			put_string = "difficultLevel";
		}
		if (prefs.getInteger(put_string)<level){
		prefs.putInteger(put_string, level);
		prefs.flush();
		}
		currentState = GameState.WAIT_LEVELUP;
		is_play = false;
		resetLevelUpX();

	}

	public int getLevel() {
		return level;

	}

	public void addLevelProgress() {
		level_progress++;
	}

	public int getLevelProgress() {
		return level_progress;
	}

	public void resetLevelProgress() {
		level_progress = 0;
		
	}

	public void setWaitInt(int new_wait_int) {
		wait_int = new_wait_int;

	}

	public int getWaitInt() {
		return wait_int;
	}

	public void setWhichDifficulty(String newdifficulty) {
		difficulty = newdifficulty;
		if (difficulty.equals("Easy")) {
			wait_int = easy_wait_int;
		}
		if (difficulty.equals("Medium")) {
			wait_int = medium_wait_int;
		}
		if (difficulty.equals("Difficult")) {
			wait_int = difficult_wait_int;
		}
	}

	public String getWhichDifficulty() {
		return difficulty;
	}

	public void goToHelp() {
		currentState = GameState.HELP1;
		is_play = false;

	}

	public int getLevelUpX() {
		return levelUpX;
	}

	public void updateLevelUpX() {
		if (levelUpX > -220) {
			levelUpX -= 2;
		}
	}

	public void resetLevelUpX() {
		levelUpX = 0;
	}

	public boolean isWaitLevelUp() {
		return currentState == GameState.WAIT_LEVELUP;
	}

	public int getMostNowLevel() {
		int highest_level = 0;
		if (difficulty.equals("Easy")) {
			highest_level = AssetLoader.getEasyLevel();

		}
		if (difficulty.equals("Medium")) {
			highest_level = AssetLoader.getMediumLevel();
		}
		if (difficulty.equals("Difficult")) {
			highest_level = AssetLoader.getDifficultLevel();
		}
		return highest_level;
	}

	public void setSelectedLevel(int new_selected_level) {
		selected_level = new_selected_level;
	}

	public int getSelectedLevel() {
		return selected_level;
	}

	public void resetDrinkTime() {
		drinkTime = 0;
	}

	public void resetMedsTime() {
		medsTime = 0;
	}
}
