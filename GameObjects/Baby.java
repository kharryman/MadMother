package com.lfq.GameObjects;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.lfq.GameWorld.GameRenderer;
import com.lfq.GameWorld.GameWorld;
import com.lfq.GameWorld.GameWorld.GameState;
import com.lfq.Helpers.AssetLoader;
import com.lfq.Screens.GameScreen;

public class Baby {
	private int width;
	private int height;
	private double theta;
	final private int RADIUSA = 64;
	final private int RADIUSB = 130;
	final private int MIDX = 150;
	final private int MIDY = 230;
	public double posx;
	public double posy;
	private Rectangle baby_rectangle, hand_rectangle;
	private Hand hand;
	private GameWorld myWorld;
	private TextureRegion baby, baby_cries1, baby_cries2, baby_arms_cries1,
			baby_arms_cries2, baby_poopoo_cries1, baby_poopoo_cries2,
			baby_rattle,baby_superrattle;
	private TextureRegion baby_poopoo, baby_bottle, baby_banana, baby_ball,
			baby_arms_up;
	private TextureRegion which_baby_pic_cries, which_baby_pic_problems;
	private TextureRegion[] baby_cries, baby_problems;
	private String which_baby_pic_cries_string, which_baby_pic_problems_string;
	private Random rands;
	private int rannum;

	private boolean done_cried, done_rattled,done_superrattled;
	private int inc, ind, proind;
	private String[] baby_problems_string = { "arms", "banana", "ball",
			"bottle", "diaper" };
	public int number_babies_crying = 0;
	private GameState currentState;
	private int level_progress = 0;
	private GameRenderer myRenderer;
	private GameScreen myGameScreen;
	private float wait_rattled,wait_superrattled;
	
	

	public Baby(double start_posx, double start_posy,int width, int height, GameWorld myWorld) {
		this.width = width;
		this.height = height;
		posx=start_posx;
		posy=start_posy;
		GameRenderer myRenderer = myWorld.getRenderer();
		baby_rectangle = new Rectangle((int) posx, (int) posy, width, height);
		hand = myWorld.getHand();
		hand_rectangle = hand.getHandRectangle();
		baby = AssetLoader.baby;
		baby_cries1 = AssetLoader.baby_cries1;
		baby_cries2 = AssetLoader.baby_cries2;
		baby_arms_cries1 = AssetLoader.baby_arms_cries1;
		baby_arms_cries2 = AssetLoader.baby_arms_cries2;
		baby_poopoo_cries1 = AssetLoader.baby_poopoo_cries1;
		baby_poopoo_cries2 = AssetLoader.baby_poopoo_cries2;
		baby_rattle = AssetLoader.baby_rattle;
		baby_superrattle = AssetLoader.baby_superrattle;
		baby_arms_up = AssetLoader.baby_arms_up;
		baby_ball = AssetLoader.baby_ball;
		baby_banana = AssetLoader.baby_banana;
		baby_bottle = AssetLoader.baby_bottle;
		baby_poopoo = AssetLoader.baby_poopoo;
		rands = new Random();
		this.done_cried = false;
		this.done_rattled = false;
		this.wait_rattled = 0;
		this.done_superrattled = false;
		this.wait_superrattled = 0;
		which_baby_pic_cries_string = "baby";
		which_baby_pic_problems_string = "nothing";
		which_baby_pic_cries = baby;
	}

	public void update(float delta, double new_posx,double new_posy, GameWorld myWorld) {
		myRenderer = myWorld.getRenderer();
		posx=new_posx;
        posy=new_posy;
		baby_rectangle = new Rectangle((int) posx, (int) posy, this.width,
				this.height);
		hand_rectangle = hand.getHandRectangle();
		TextureRegion[] baby_cries = { baby, baby_cries1, baby_cries2,
				baby_arms_cries1, baby_arms_cries2, baby_poopoo_cries1,
				baby_poopoo_cries2 };
		TextureRegion[] baby_problems = { baby_arms_up, baby_banana, baby_ball,
				baby_bottle, baby_poopoo };
		rannum = rands.nextInt(10000);

		if (this.done_rattled == true) {
			if (this.wait_rattled < 10) {
				this.wait_rattled += delta;
				
			} else {
				this.wait_rattled = 0;
				this.done_rattled = false;
				AssetLoader.rattling.stop();
			}
		}
		
		if (this.done_superrattled == true) {
			if (this.wait_superrattled < 20) {
				this.wait_superrattled += delta;
				
			} else {
				this.wait_superrattled = 0;
				this.done_superrattled = false;
				AssetLoader.rattling.stop();
			}
		}
		
		if (inc == 0 && this.done_cried == false
				&& this.done_rattled == false && this.done_superrattled==false) {
			which_baby_pic_problems = null;
			which_baby_pic_problems_string = "nothing";
			which_baby_pic_cries_string = "baby";
			which_baby_pic_cries = baby;
		}

		if (rannum < myWorld.getWaitInt() && this.done_cried == false
				&& this.done_rattled == false && this.done_superrattled==false) {
			inc++;
			if (inc == 1) {
				proind = rands.nextInt(5);
				which_baby_pic_problems = baby_problems[proind];
				which_baby_pic_problems_string = baby_problems_string[proind];
			}
			if (inc == 1) {
				which_baby_pic_cries = baby_cries1;
				which_baby_pic_cries_string = "baby_cries1";
				if (proind == 0) {
					which_baby_pic_cries = baby_arms_cries1;
					which_baby_pic_cries_string = "baby_arms_cries1";
					which_baby_pic_problems = null;
				}
				if (proind == 4) {
					which_baby_pic_cries = baby_poopoo_cries1;
					which_baby_pic_cries_string = "baby_poopoo_cries1";
					which_baby_pic_problems = null;
				}
			}
			if (inc == 2) {
				which_baby_pic_cries = baby_cries2;
				which_baby_pic_cries_string = "baby_cries2";
				if (proind == 0) {
					which_baby_pic_cries = baby_arms_cries2;
					which_baby_pic_cries_string = "baby_arm_cries2";
					which_baby_pic_problems = null;
				}
				if (proind == 4) {
					which_baby_pic_cries = baby_poopoo_cries2;
					which_baby_pic_cries_string = "baby_poopoo_cries2";
					which_baby_pic_problems = null;
				}
				this.done_cried = true;
				AssetLoader.baby_cry.play();
			}
		}
		if (hand_rectangle.overlaps(baby_rectangle)) {
			if (hand.getWhichString().equals("rattle")) {
				which_baby_pic_cries = baby_rattle;
				which_baby_pic_problems = null;
				this.done_cried = false;
				this.done_rattled = true;
				which_baby_pic_problems_string = "nothing";
				hand.restart();
				inc = 0;
				myWorld.addScore();
				
				AssetLoader.rattling.loop();
				}
			if (hand.getWhichString().equals("superrattle")) {
				which_baby_pic_cries =baby_superrattle;
				which_baby_pic_problems = null;
				this.done_cried = false;
				this.done_superrattled = true;
				which_baby_pic_problems_string = "nothing";
				hand.restart();
				inc = 0;
				myWorld.subtractSuperRattles();
				AssetLoader.rattling.loop();
			}
			if (which_baby_pic_problems_string == hand.getWhichString()
					&& myWorld.getGameState() != GameState.GAMEOVER) {
				if (myWorld.getNextEnergy() == true
						&& myWorld.getNumberBabiesCrying() >= 4) {
					myWorld.setNextEnergy(false);
				}
				myWorld.setSupplySubtracted(false);
				which_baby_pic_cries = baby;
				which_baby_pic_problems = null;
				inc = 0;
				this.done_cried = false;
				which_baby_pic_problems_string = "nothing";
				myWorld.addScore();
				myWorld.addLevelProgress();
				hand.restart();
				myRenderer.shuffleBabyTools();
				if (myWorld.getLevelProgress() == 20) {
					level_progress = 0;
					myWorld.resetLevelProgress();
					myWorld.addLevel();
					int wait = myWorld.getWaitInt();
					myWorld.setWaitInt(wait + 5);
					AssetLoader.level_up.play();
				}

			}
		}
	}

	public int isCrying() {
		int result = 0;
		if (this.done_cried == true) {
			result = 1;
		}
		if (this.done_cried == false) {
			result = 0;
		}
		return result;
	}

	public void setHappy() {
		this.done_cried = false;
		inc = 0;
	}

	public TextureRegion getWhichBabyPicCries() {
		return which_baby_pic_cries;
	}

	public TextureRegion getWhichBabyPicProblems() {
		return which_baby_pic_problems;
	}

	public double getX() {
		return posx;
	}

	public double getY() {
		return posy;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	public void setTheta(double newtheta){
		this.theta=newtheta;
	}

}
