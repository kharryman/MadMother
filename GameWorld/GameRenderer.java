package com.lfq.GameWorld;

import java.util.Random;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.lfq.GameObjects.Baby;
import com.lfq.GameObjects.Hand;
import com.lfq.GameObjects.Mother;
import com.lfq.GameObjects.Speech_Bubble;
import com.lfq.GameWorld.GameWorld.GameState;
import com.lfq.Helpers.AssetLoader;
import com.lfq.Helpers.InputHandler;
import com.lfq.Screens.GameScreen;
import com.lfq.TweenAccessors.Value;
import com.lfq.TweenAccessors.ValueAccessor;

public class GameRenderer {
	private String shop_talk, shop_talk2, shop_talk3;
	private GameWorld myWorld;
	private OrthographicCamera cam;
	private SpriteBatch batcher;
	private int gameHeight, midPointY;
	private TextureRegion mad_mother_logo_flipped, background, store, LFQlogo,
			baby, baby_poopoo, baby_bottle, baby_banana, baby_ball,
			baby_arms_up, baby_bottle_all, baby_banana_all, baby_ball_all,
			mother, mother1, mother2, mother3, mother4, bottle, diaper, banana,
			ball, arms;
	private Baby babies, baby1, baby2, baby3, baby4, baby5, baby6;
	private Hand hand1;
	private Speech_Bubble spebub1, spebub2, spebub3, spebub4, spebub5, spebub6;
	private TextureRegion baby_cries1, baby_cries2, baby_arms_cries1,
			baby_arms_cries2, baby_poopoo_cries1, baby_poopoo_cries2;
	private TextureRegion hand;
	private TextureRegion hand_diaper, hand_bottle, hand_arms, hand_banana,
			hand_ball, hand_superrattle, hand_cigbox;
	private TextureRegion[] baby_cries, baby_problems;
	private TextureRegion levelBar, levelMarker;
	public Mother myMother;

	private TweenManager manager;
	private Value alpha = new Value();
	private TextureRegion which_button, playButtonUp, playButtonDown,
			which_Easy_button, which_Medium_button, which_Difficult_button,
			tryAgainButtonUp, tryAgainButtonDown, resetUp, resetDown, play,
			pause, storeUp, storeDown;
	public static TextureRegion energy, cigs, meds, cigbox, superrattle,
			temper_bar, temper_rect;
	private TextureRegion energyUp, energyDown, cigsUp, cigsDown, medsUp,
			medsDown, cigBoxUp, cigBoxDown, superRattleUp, superRattleDown,
			backToBabiesUp, backToBabiesDown;
	private TextureRegion energy_power;
	private Color transitionColor;
	private ShapeRenderer shapeRenderer;
	final private int MIDX = 150;
	final private int MIDY = 230;
	private InputHandler inputHandler;
	private GameScreen myGameScreen;
	private boolean is_first_gameover;
	TextureRegion play_pause, reset_up_down, store_up_down, energy_up_down,
			cigs_up_down, meds_up_down, which_cig_box, which_super_rattle,
			back_to_babies_up_down;
	private Animation motherEatsAnimation, motherSmokesAnimation,
			motherTakesMeds;
	private Animation babyMovesArms, babyBouncesBall, babyPeelsBanana,
			babyEatsBanana, babyDrinksBottle, babyDiaperChanged;
	private TextureRegion arm_ani_feet;
	private String which_animation = "none";
	private float runTimeStartAnim;
	private TextureRegion[] babyTools;
	private Random random;
	private int ranind;
	private int[] randomBabyToolsList = { 0, 1, 2, 3, 4 };
	private TextureRegion EasyUp, MediumUp, DifficultUp, EasyDown, MediumDown,
			DifficultDown;
	private TextureRegion QuitUp, QuitDown, which_Quit_button;
	private TextureRegion HelpUp, HelpDown, which_Help_button, MenuUp,
			MenuDown, which_Menu_button, help_background, level_up_background,
			lev_to_store_up_down, lev_to_babies_up_down, which_help_left,
			which_help_right;
	private TextureRegion LevToStoreUp, LevToStoreDown;
	private TextureRegion rattle, lock, unlock, which_MediumLock,
			which_DifficultLock, whichNullStartButton,
			which_nullContinueButton, which_tryAgain, which_game_over_to_menu,
			which_nullSelectLevelButton, whichNullStartPlayButton;
	private TextureRegion nullButtonUp, nullButtonDown, nullButtonDownPink;
	private int continue_level;

	private TextureRegion helpLeftUp, helpLeftDown, helpRightUp, helpRightDown;
	private int ranratx = 50, ranraty = 50;
	
	private TextureRegion mother_on_couch,motherOnMeds;
	private float ctpeel;
	private int selected_level;
	private int highlighted_level;

	public GameRenderer(GameWorld world, GameScreen gamescreen) {
		continue_level = 0;
		selected_level = 0;
		highlighted_level = 0;
		myWorld = world;
		ctpeel = 0;
		myGameScreen = gamescreen;
		myWorld.setRenderer(this);
		shop_talk = "What do you want?";
		shop_talk2 = "";
		shop_talk3 = "";
		String which_animation = "none";
		runTimeStartAnim = 0;
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 272, 408);
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();

		shapeRenderer.setProjectionMatrix(cam.combined);

		initiate_Assets();
		initiate_Objects();
		TextureRegion[] babyTools = { arms, ball, banana, bottle, diaper };
		int[] randomBabyToolsList = { 0, 1, 2, 3, 4 };
		TextureRegion[] baby_cries = { baby, baby_cries1, baby_cries2,
				baby_arms_cries1, baby_arms_cries2, baby_poopoo_cries1,
				baby_poopoo_cries2 };
		TextureRegion[] baby_problems = { baby_arms_up, baby_banana, baby_ball,
				baby_bottle, baby_poopoo };
		which_button = playButtonUp;
		is_first_gameover = true;
		play_pause = play;
		reset_up_down = resetUp;
		store_up_down = storeUp;
		energy_up_down = energyUp;
		cigs_up_down = cigsUp;
		meds_up_down = medsUp;
		which_cig_box = cigBoxUp;
		which_super_rattle = superRattleUp;
		back_to_babies_up_down = backToBabiesUp;
		which_Easy_button = EasyDown;
		which_Medium_button = MediumUp;
		which_Difficult_button = DifficultUp;
		which_Quit_button = QuitUp;
		which_Help_button = HelpUp;
		which_Menu_button = MenuUp;
		lev_to_store_up_down = LevToStoreUp;
		lev_to_babies_up_down = backToBabiesUp;
		which_help_left = helpLeftUp;
		which_help_right = helpRightUp;
		which_MediumLock = lock;
		which_DifficultLock = lock;
		whichNullStartButton = nullButtonUp;
		which_nullContinueButton = nullButtonUp;
		which_nullSelectLevelButton = nullButtonUp;
		whichNullStartPlayButton = nullButtonUp;
		which_tryAgain = tryAgainButtonUp;
		which_game_over_to_menu = MenuUp;
		random = new Random();
	}

	void prepareTransition(int r, int g, int b, float duration) {
		transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
		alpha.setValue(1);
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager = new TweenManager();
		Tween.to(alpha, -1, duration).target(0)
				.ease(TweenEquations.easeOutQuad).start(manager);

	}

	private void setupTweens() {
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager = new TweenManager();
		Tween.to(alpha, -1, .5f).target(0).ease(TweenEquations.easeOutQuad)
				.start(manager);
	}

	private void drawTransition(float delta) {
		if (alpha.getValue() > 0) {
			manager.update(delta);
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(1, 1, 1, alpha.getValue());
			shapeRenderer.rect(0, 0, 272, 300);
			shapeRenderer.end();
			Gdx.gl.glDisable(GL10.GL_BLEND);

		}
	}

	private void initiate_Assets() {
		background = AssetLoader.background;
		baby = AssetLoader.baby;
		baby_arms_up = AssetLoader.baby_arms_up;
		baby_ball = AssetLoader.baby_ball;
		baby_banana = AssetLoader.baby_banana;
		baby_bottle = AssetLoader.baby_bottle;
		baby_ball_all = AssetLoader.baby_ball_all;
		baby_banana_all = AssetLoader.baby_banana_all;
		baby_bottle_all = AssetLoader.baby_bottle_all;
		baby_poopoo = AssetLoader.baby_poopoo;
		mother = AssetLoader.mother;
		mother1 = AssetLoader.mother1;
		mother2 = AssetLoader.mother2;
		mother3 = AssetLoader.mother3;
		mother4 = AssetLoader.mother4;
		banana = AssetLoader.banana;
		ball = AssetLoader.ball;
		bottle = AssetLoader.bottle;
		diaper = AssetLoader.diaper;
		arms = AssetLoader.arms;
		baby_cries1 = AssetLoader.baby_cries1;
		baby_cries2 = AssetLoader.baby_cries2;
		baby_arms_cries1 = AssetLoader.baby_arms_cries1;
		baby_arms_cries2 = AssetLoader.baby_arms_cries2;
		baby_poopoo_cries1 = AssetLoader.baby_poopoo_cries1;
		baby_poopoo_cries2 = AssetLoader.baby_poopoo_cries2;
		hand = AssetLoader.hand;
		hand_diaper = AssetLoader.hand_diaper;
		hand_bottle = AssetLoader.hand_bottle;
		hand_arms = AssetLoader.hand_arms;
		hand_banana = AssetLoader.hand_banana;
		hand_ball = AssetLoader.hand_ball;
		hand_cigbox = AssetLoader.hand_cigbox;
		hand_superrattle = AssetLoader.hand_superrattle;
		LFQlogo = AssetLoader.LFQlogo;
		playButtonUp = AssetLoader.playButtonUp;
		playButtonDown = AssetLoader.playButtonDown;
		tryAgainButtonUp = AssetLoader.tryAgainButtonUp;
		tryAgainButtonDown = AssetLoader.tryAgainButtonDown;
		resetUp = AssetLoader.resetUp;
		resetDown = AssetLoader.resetDown;
		storeUp = AssetLoader.storeUp;
		storeDown = AssetLoader.storeDown;
		energyUp = AssetLoader.energyUp;
		energyDown = AssetLoader.energyDown;
		cigsUp = AssetLoader.cigarettesUp;
		cigsDown = AssetLoader.cigarettesDown;
		medsUp = AssetLoader.medsUp;
		medsDown = AssetLoader.medsDown;
		cigBoxUp = AssetLoader.cigBoxUp;
		cigBoxDown = AssetLoader.cigBoxDown;
		superRattleUp = AssetLoader.superRattleUp;
		superRattleDown = AssetLoader.superRattleDown;
		backToBabiesUp = AssetLoader.backToBabiesUp;
		backToBabiesDown = AssetLoader.backToBabiesDown;
		play = AssetLoader.play;
		pause = AssetLoader.pause;
		store = AssetLoader.store;
		energy = AssetLoader.energy;
		cigs = AssetLoader.cigs;
		meds = AssetLoader.meds;
		cigbox = AssetLoader.cigbox;
		superrattle = AssetLoader.superrattle;

		temper_bar = AssetLoader.temper_bar;
		temper_rect = AssetLoader.temper_rect;
		mad_mother_logo_flipped = AssetLoader.mad_mother_logo_flipped;
		energy_power = AssetLoader.energy_power;
		motherEatsAnimation = AssetLoader.motherEatsAnimation;
		motherSmokesAnimation = AssetLoader.motherSmokesAnimation;
		EasyUp = AssetLoader.EasyUp;
		MediumUp = AssetLoader.MediumUp;
		DifficultUp = AssetLoader.DifficultUp;
		EasyDown = AssetLoader.EasyDown;
		MediumDown = AssetLoader.MediumDown;
		DifficultDown = AssetLoader.DifficultDown;

		QuitDown = AssetLoader.QuitDown;
		QuitUp = AssetLoader.QuitUp;

		HelpDown = AssetLoader.HelpDown;
		HelpUp = AssetLoader.HelpUp;

		MenuDown = AssetLoader.MenuDown;
		MenuUp = AssetLoader.MenuUp;

		LevToStoreUp = AssetLoader.LevToStoreUp;
		LevToStoreDown = AssetLoader.LevToStoreDown;

		help_background = AssetLoader.help_background;
		level_up_background = AssetLoader.level_up_background;
		rattle = AssetLoader.rattle;
		lock = AssetLoader.lock;
		unlock = AssetLoader.unlock;

		helpLeftUp = AssetLoader.helpLeftUp;
		helpLeftDown = AssetLoader.helpLeftDown;
		helpRightUp = AssetLoader.helpRightUp;
		helpRightDown = AssetLoader.helpRightDown;

		motherOnMeds=AssetLoader.take_meds_ani3;
		motherTakesMeds = AssetLoader.motherTakesMeds;
		arm_ani_feet = AssetLoader.arm_ani_feet;
		babyMovesArms = AssetLoader.babyMovesArms;
		babyBouncesBall = AssetLoader.babyBouncesBall;
		babyPeelsBanana = AssetLoader.babyPeelsBanana;
		babyEatsBanana = AssetLoader.babyEatsBanana;
		babyDrinksBottle = AssetLoader.babyDrinksBottle;
		babyDiaperChanged = AssetLoader.babyDiaperChanged;
		mother_on_couch = AssetLoader.mother_on_couch;
		nullButtonUp = AssetLoader.nullButtonUp;
		nullButtonDown = AssetLoader.nullButtonDown;
		nullButtonDownPink = AssetLoader.nullButtonDownPink;
		levelBar = AssetLoader.levelBar;
		levelMarker = AssetLoader.levelMarker;

	}

	private void initiate_Objects() {
		myMother = myWorld.getMother();
		baby1 = myWorld.getBaby1();
		baby2 = myWorld.getBaby2();
		baby3 = myWorld.getBaby3();
		baby4 = myWorld.getBaby4();
		baby5 = myWorld.getBaby5();
		baby6 = myWorld.getBaby6();
		spebub1 = myWorld.getSpeBub1();
		spebub2 = myWorld.getSpeBub2();
		spebub3 = myWorld.getSpeBub3();
		spebub4 = myWorld.getSpeBub4();
		spebub5 = myWorld.getSpeBub5();
		spebub6 = myWorld.getSpeBub6();
		hand1 = myWorld.getHand();
	}

	public void render(float delta, float runTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batcher.begin();
		TextureRegion[] babyTools = { arms, ball, banana, bottle, diaper };
		batcher.disableBlending();
		if (myWorld.isMenu() == true) {
			batcher.draw(mad_mother_logo_flipped, 0, 0,
					myGameScreen.getGameWidth(), myGameScreen.getGameHeight());
			batcher.enableBlending();
			if (AssetLoader.isMediumUnLocked() == true) {
				which_MediumLock = unlock;
			}
			if (AssetLoader.isDifficultUnLocked() == true) {
				which_DifficultLock = unlock;
			}

			int highest_level = myWorld.getMostNowLevel();
			selected_level = myWorld.getSelectedLevel();
			if (selected_level == 0) {
				selected_level = highest_level;
			}

			batcher.draw(which_Easy_button, 0, 80, 78, 32);
			AssetLoader.bigfont.draw(batcher, "HIGHEST LEVELS:", 90, 55);

			AssetLoader.score_font.draw(batcher,
					"LEVEL " + AssetLoader.getEasyLevel(), 100, 80);
			batcher.draw(which_Medium_button, 0, 112, 78, 32);
			batcher.draw(which_MediumLock, 78, 112, 20, 20);
			AssetLoader.score_font.draw(batcher,
					"LEVEL " + AssetLoader.getMediumLevel(), 100, 112);
			batcher.draw(which_Difficult_button, 0, 144, 78, 32);
			batcher.draw(which_DifficultLock, 78, 144, 20, 20);
			AssetLoader.score_font.draw(batcher,
					"LEVEL " + AssetLoader.getDifficultLevel(), 100, 144);

			batcher.draw(whichNullStartButton, 0, 176, 81, 47);
			AssetLoader.mad_mother_green_font.draw(batcher, "START", 10, 180);
			AssetLoader.mad_mother_green_font.draw(batcher,
					myWorld.getWhichDifficulty(), 10, 192);
			AssetLoader.mad_mother_green_font.draw(batcher, "LEVEL 0", 10, 204);

			if (highest_level != 0) {
				batcher.draw(which_nullContinueButton, 0, 230, 81, 47);
				AssetLoader.mad_mother_green_font.draw(batcher, "CONTINUE", 10,
						234);
				AssetLoader.mad_mother_green_font.draw(batcher,
						myWorld.getWhichDifficulty(), 10, 246);
				AssetLoader.mad_mother_green_font.draw(batcher, "LEVEL "
						+ selected_level, 10, 258);

				float xmark_int = 260 / highest_level;
				float xmax_levelBar = xmark_int * highest_level;
				AssetLoader.mad_mother_green_font.draw(batcher,
						"CHOOSE LEVEL:", 0, 280);

				shapeRenderer.begin(ShapeType.Filled);
				shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f,
						(float) 0.25);

				shapeRenderer.rect(0, 295, xmax_levelBar, 12);

				shapeRenderer.end();

				batcher.draw(levelMarker, 0, 294, 1, 14);
				float x_adj = 0;
				for (int i = 1; i <= highest_level; i++) {
					x_adj += xmark_int;
					if (i == highlighted_level) {
						shapeRenderer.begin(ShapeType.Filled);
						shapeRenderer.setColor(255 / 255.0f, 216 / 255.0f,
								00 / 255.0f, (float) 0.5);
						shapeRenderer.rect(x_adj - xmark_int, 295, xmark_int,
								12);
						shapeRenderer.end();
					}
					batcher.draw(levelMarker, x_adj, 294, 1, 14);
					if (highest_level < 10) {
						AssetLoader.mad_mother_green_font.draw(batcher, "" + i,
								x_adj - (xmark_int / 2), 308);
					}
					if (highest_level >= 10) {
						AssetLoader.mad_mother_green_font.draw(batcher, "0", 0,
								308);
					}
					if (highest_level >= 10 && highest_level < 50 && i % 5 == 0) {
						AssetLoader.mad_mother_green_font.draw(batcher, "" + i,
								x_adj - (xmark_int / 2), 308);
					}
					if (highest_level >= 50 && i % 10 == 0) {
						AssetLoader.mad_mother_green_font.draw(batcher, "" + i,
								x_adj - (xmark_int / 2), 308);
					}
				}
			}

			AssetLoader.smallfont.draw(batcher, "EASY HIGH SCORE="
					+ AssetLoader.getEasyHighScore(), 0, 333);
			AssetLoader.smallfont.draw(batcher, "MEDIUM HIGH SCORE="
					+ AssetLoader.getMediumHighScore(), 0, 358);
			AssetLoader.smallfont.draw(batcher, "DIFFICULT HIGH SCORE="
					+ AssetLoader.getDifficultHighScore(), 0, 383);
			batcher.draw(which_Help_button, 220, 376, 52, 32);
		}
		if (myWorld.isLevelUp() == true || myWorld.isWaitLevelUp()) {
			if (myWorld.getLevelUpX() >= -3) {
				AssetLoader.cheering.play();
			}
			AssetLoader.rattling.stop();
			batcher.draw(level_up_background, myWorld.getLevelUpX(), 0, 492,
					408);
			batcher.enableBlending();
			if (myWorld.getLevelUpX() <= -220) {
				myWorld.setGameState(GameState.LEVELUP);
				AssetLoader.shop_font.draw(batcher, "CONGRATULATIONS!", 0, 0);
				AssetLoader.shop_font.draw(batcher, "YOU'RE AT LEVEL "
						+ myWorld.getLevel(), 0, 20);
				if ((myWorld.getLevel()) % 5 == 1) {
					batcher.draw(
							babyMovesArms.getKeyFrame(runTime),
							88,
							33,
							babyMovesArms.getKeyFrame(runTime).getRegionWidth(),
							babyMovesArms.getKeyFrame(runTime)
									.getRegionHeight());
					batcher.draw(mother_on_couch, 88, 40, 82, 124);

					batcher.draw(arm_ani_feet, 113, 86, 24, 6);
				}
				if ((myWorld.getLevel()) % 5 == 2) {
					batcher.draw(mother_on_couch, 88, 40, 82, 124);
					batcher.draw(babyBouncesBall.getKeyFrame(runTime), 4, 204,
							babyBouncesBall.getKeyFrame(runTime)
									.getRegionWidth(), babyBouncesBall
									.getKeyFrame(runTime).getRegionHeight());
				}
				if ((myWorld.getLevel()) % 5 == 3) {
					batcher.draw(mother_on_couch, 88, 40, 82, 124);
					ctpeel += delta;
					batcher.draw(babyPeelsBanana.getKeyFrame(ctpeel), 4, 204,
							babyPeelsBanana.getKeyFrame(ctpeel)
									.getRegionWidth(), babyPeelsBanana
									.getKeyFrame(ctpeel).getRegionHeight());
					batcher.draw(babyEatsBanana.getKeyFrame(runTime), 4, 204,
							babyEatsBanana.getKeyFrame(runTime)
									.getRegionWidth(), babyEatsBanana
									.getKeyFrame(runTime).getRegionHeight());
				}
				if ((myWorld.getLevel()) % 5 == 4) {
					batcher.draw(mother_on_couch, 88, 40, 82, 124);
					batcher.draw(babyDrinksBottle.getKeyFrame(runTime), 4, 204,
							babyDrinksBottle.getKeyFrame(runTime)
									.getRegionWidth(), babyDrinksBottle
									.getKeyFrame(runTime).getRegionHeight());
				}
				if ((myWorld.getLevel()) % 5 == 0 && myWorld.getLevel() != 0) {

					batcher.draw(babyDiaperChanged.getKeyFrame(runTime), 6,
							238, babyDiaperChanged.getKeyFrame(runTime)
									.getRegionWidth(), babyDiaperChanged
									.getKeyFrame(runTime).getRegionHeight());
				}

				batcher.draw(lev_to_store_up_down, 182, 268, 90, 70);
				batcher.draw(lev_to_babies_up_down, 182, 338, 90, 70);
			}
		}
		if (myWorld.isHelp1() == true) {
			batcher.draw(help_background, 0, 0, 272, 408);
			batcher.enableBlending();
			AssetLoader.font.draw(batcher, "HELP: PAGE 1", 80, 3);

			AssetLoader.shop_font.draw(batcher, "*IF..", 5, 25);// TEACH GIVE
																// ARMS
			batcher.draw(baby_arms_cries1, 35, 25, 30, 30);
			AssetLoader.shop_font.draw(batcher, "..GIVE:", 65, 25);
			batcher.draw(arms, 125, 25, 30, 30);
			AssetLoader.small_shop_font.draw(batcher, "MEANS: baby wants", 160,
					25);
			AssetLoader.small_shop_font.draw(batcher, "to be lifted", 160, 37);

			AssetLoader.shop_font.draw(batcher, "*IF..", 5, 65);// TEACH GIVE
																// BALL
			batcher.draw(baby_ball_all, 35, 65, 30, 30);
			AssetLoader.shop_font.draw(batcher, "..GIVE:", 65, 65);
			batcher.draw(ball, 125, 65, 30, 30);
			AssetLoader.small_shop_font.draw(batcher, "MEANS: baby wants", 160,
					65);
			AssetLoader.small_shop_font.draw(batcher, "a ball", 160, 77);

			AssetLoader.shop_font.draw(batcher, "*IF..", 5, 105);// TEACH GIVE
																	// BANANA
			batcher.draw(baby_banana_all, 35, 105, 30, 30);
			AssetLoader.shop_font.draw(batcher, "..GIVE:", 65, 105);
			batcher.draw(banana, 125, 105, 30, 30);
			AssetLoader.small_shop_font.draw(batcher, "MEANS: baby wants", 160,
					105);
			AssetLoader.small_shop_font.draw(batcher, "a banana", 160, 112);

			AssetLoader.shop_font.draw(batcher, "*IF..", 5, 145);// TEACH GIVE
																	// BOTTLE
			batcher.draw(baby_bottle_all, 35, 145, 30, 30);
			AssetLoader.shop_font.draw(batcher, "..GIVE:", 65, 145);
			batcher.draw(bottle, 125, 145, 30, 30);
			AssetLoader.small_shop_font.draw(batcher,
					"MEANS: baby wants the bottle", 160, 145);
			AssetLoader.small_shop_font.draw(batcher, "the bottle", 160, 157);

			AssetLoader.shop_font.draw(batcher, "*IF..", 5, 185);// TEACH GIVE
																	// DIAPER
			batcher.draw(baby_poopoo_cries1, 35, 185, 30, 30);
			AssetLoader.shop_font.draw(batcher, "..GIVE:", 65, 185);
			batcher.draw(diaper, 125, 185, 30, 30);
			AssetLoader.small_shop_font.draw(batcher,
					"MEANS: baby needs a diaper change", 160, 185);
			AssetLoader.small_shop_font.draw(batcher, "a diaper change", 160,
					197);

			AssetLoader.shop_font.draw(batcher, "*MOTHER LOSES TEMPER IF ", 5,
					225);
			AssetLoader.shop_font.draw(batcher, "4 OR MORE BABIES CRY", 5, 245);

			AssetLoader.shop_font.draw(batcher, "*PLAY/PAUSE IS", 5, 275);
			AssetLoader.shop_font.draw(batcher, "ON TOP RIGHT", 5, 295);
			batcher.draw(pause, 207, 275, 20, 20);
			batcher.draw(play, 240, 275, 20, 20);

			batcher.draw(which_Menu_button, 0, 315, 50, 50);
			batcher.draw(which_help_right, 85, 340, 25, 25);
			AssetLoader.shop_font.draw(batcher, "TO PAGE 2", 115, 340);
		}
		if (myWorld.isHelp2() == true) {
			batcher.draw(help_background, 0, 0, 272, 408);
			batcher.enableBlending();
			AssetLoader.font.draw(batcher, "HELP: PAGE 2", 80, 3);

			AssetLoader.shop_font.draw(batcher, "*LEVEL UP FOR EVERY", 5, 25);
			AssetLoader.shop_font.draw(batcher, "20 BABIES SAVED.", 5, 45);
			
			AssetLoader.shop_font.draw(batcher, "*BABIES CRY FASTER ", 5, 70);
			AssetLoader.shop_font.draw(batcher, "ON EACH LEVEL UP.", 5, 90);
			
			AssetLoader.shop_font.draw(batcher, "*IF THE MOTHER IS LIKE THIS:",
					5, 115);// TEACH MAMA
			// ENERGIZED
			batcher.draw(energy_power, 235, 105, 30, 30);
			batcher.draw(mother, 235, 105, 30, 30);
			AssetLoader.shop_font.draw(batcher, "..SHE IS ON AN ENERGY DRINK",
					5, 135);
			AssetLoader.shop_font.draw(batcher, "AND WILL KEEP HER TEMPER", 5,
					155);
			AssetLoader.shop_font
					.draw(batcher, "FOR 3 SECONDS IF MORE", 5, 175);
			AssetLoader.shop_font.draw(batcher, "THAN 4 BABIES CRY.", 5, 195);
			
			
			AssetLoader.shop_font.draw(batcher, "*IF THE MOTHER IS LIKE THIS:",
					5, 220);// TEACH MAMA
			// ON MEDICATION
			batcher.draw(motherOnMeds, 235, 220, 30, 30);
			AssetLoader.shop_font.draw(batcher, "..SHE IS ON MEDICATION",
					5, 240);
			AssetLoader.shop_font.draw(batcher, "AND WILL KEEP HER TEMPER", 5,
					260);
			AssetLoader.shop_font
					.draw(batcher, "FOR 10 SECONDS IF MORE", 5, 280);
			AssetLoader.shop_font.draw(batcher, "THAN 4 BABIES CRY.", 5, 300);

			batcher.draw(which_Menu_button, 0, 315, 50, 50);
			batcher.draw(which_help_left, 55, 340, 25, 25);
			batcher.draw(which_help_right, 85, 340, 25, 25);
			AssetLoader.shop_font.draw(batcher, "TO PAGE 1 OR 3", 115, 340);

		}
		if (myWorld.isHelp3() == true) {
			batcher.draw(help_background, 0, 0, 272, 408);
			batcher.enableBlending();
			AssetLoader.font.draw(batcher, "HELP: PAGE 3", 80, 3);

			AssetLoader.shop_font.draw(batcher, "*5 BABIES SAVED FAST WILL", 5,
					25);
			AssetLoader.shop_font.draw(batcher, "GIVE A RANDOM RATTLE", 5, 45);
			batcher.draw(rattle, 237, 45, 20, 20);
			AssetLoader.shop_font.draw(batcher, " THAT KEEPS A BABY ", 5, 65);
			AssetLoader.shop_font.draw(batcher, "HAPPY FOR 10 SECONDS.", 5, 85);
			AssetLoader.shop_font
					.draw(batcher, "*A HIGH SCORE OF 100 ON", 5, 110);
			AssetLoader.shop_font.draw(batcher, "EASY UNLOCKS MEDIUM AND", 5,
					130);
			AssetLoader.shop_font.draw(batcher, "UNLOCKS A CIGARETTE BOX ", 5,
					150);
			batcher.draw(cigbox, 237, 150, 20, 20);
			AssetLoader.shop_font.draw(batcher, "IN THE STORE.", 5, 170);
			AssetLoader.shop_font.draw(batcher, "*A HIGH SCORE OF 100 ON",
					5, 200);
			AssetLoader.shop_font
					.draw(batcher, "MEDIUM UNLOCKS DIFFICULT", 5, 220);
			AssetLoader.shop_font.draw(batcher, "AND UNLOCKS A SUPER-", 5,
					240);
			batcher.draw(superrattle, 237, 240, 20, 20);
			AssetLoader.shop_font.draw(batcher, "RATTLE IN THE STORE.", 5, 260);

			batcher.draw(which_Menu_button, 0, 315, 50, 50);
			batcher.draw(which_help_left, 55, 340, 25, 25);
			AssetLoader.shop_font.draw(batcher, "TO PAGE 2", 82, 340);
		}
		if (myWorld.isRunning() == true) {
			if (myWorld.getPlay() == true) {
				play_pause = play;
			} else {
				play_pause = pause;
			}
			ctpeel = 0;
			batcher.draw(background, 0, 0, 272, 408);
			batcher.enableBlending();

			batcher.draw(energy, 0, 0, 30, 30);
			AssetLoader.shop_font.draw(batcher, "X" + myWorld.getEnergy(), 0,
					30);
			batcher.draw(cigs, 40, 0, 30, 30);
			AssetLoader.shop_font
					.draw(batcher, "X" + myWorld.getCigs(), 40, 30);
			batcher.draw(meds, 80, 0, 30, 30);
			AssetLoader.shop_font
					.draw(batcher, "X" + myWorld.getMeds(), 80, 30);

			int length_number_crying = ("" + myWorld.getNumberBabiesCrying())
					.length();
			AssetLoader.crying_font.draw(batcher,
					"" + myWorld.getNumberBabiesCrying() + " crying", 115, 0);
			batcher.draw(temper_bar, 115, 20, 75, 29);
			AssetLoader.crying_font.draw(batcher, "LEVEL " + myWorld.getLevel()
					+ " " + myWorld.getWhichDifficulty().toUpperCase(), 0, 50);
			double startTemperRectX = 117 + (59 * myWorld.getTemper() / 1000);
			double temperRectWidth = 59 - (59 * myWorld.getTemper() / 1000);
			batcher.draw(temper_rect, (int) startTemperRectX, 31,
					(int) temperRectWidth, 7);
			batcher.draw(reset_up_down, myGameScreen.getGameWidth() - 70, 0,
					32, 24);
			batcher.draw(play_pause, myGameScreen.getGameWidth() - 38, 0, 38,
					30);

			int length = 80;
			AssetLoader.shop_font.draw(batcher, "score:" + myWorld.getScore(),
					myGameScreen.getGameWidth() - length, 38);
			AssetLoader.shop_font.draw(batcher, "cash:" + myWorld.getCash(),
					myGameScreen.getGameWidth() - length, 52);

			batcher.draw(store_up_down, myGameScreen.getGameWidth() - 32, 66,
					32, 24);
			batcher.draw(which_Quit_button, myGameScreen.getGameWidth() - 32,
					98, 32, 24);

			batcher.draw(babyTools[randomBabyToolsList[0]], 0, 89, 30, 29);
			batcher.draw(babyTools[randomBabyToolsList[1]], 0, 136, 30, 29);
			batcher.draw(babyTools[randomBabyToolsList[2]], 0, 183, 30, 29);
			batcher.draw(babyTools[randomBabyToolsList[3]], 0, 230, 30, 29);
			batcher.draw(babyTools[randomBabyToolsList[4]], 0, 277, 30, 29);
			batcher.draw(cigbox, 0, 324, 30, 29);
			AssetLoader.shop_font.draw(batcher, "X" + myWorld.getCigBoxes(),
					30, 324);
			batcher.draw(superrattle, 0, 371, 30, 29);
			AssetLoader.shop_font.draw(batcher,
					"X" + myWorld.getSuperRattles(), 30, 371);

			if (myWorld.isRattle() == true) {
				batcher.draw(rattle, myWorld.getRanRatX(),
						myWorld.getRanRatY(), 10, 22);
			}

			if (myWorld.getPlay() == true) {

				if (myWorld.getNextEnergy() == true) {
					batcher.draw(
							energy_power,
							MIDX
									- (myMother.getWhichMotherTexture()
											.getRegionWidth() / 2),
							MIDY
									- (myMother.getWhichMotherTexture()
											.getRegionHeight() / 2),
							myMother.getWhichMotherTexture().getRegionWidth() + 5,
							myMother.getWhichMotherTexture().getRegionHeight() + 5);
				}
				if (which_animation == "none") {
					batcher.draw(myMother.getWhichMotherTexture(), MIDX
							- (myMother.getWhichMotherTexture()
									.getRegionWidth() / 2), MIDY
							- (myMother.getWhichMotherTexture()
									.getRegionHeight() / 2), myMother
							.getWhichMotherTexture().getRegionWidth(), myMother
							.getWhichMotherTexture().getRegionHeight());
				}
				if (which_animation == "eat") {
					batcher.draw(motherEatsAnimation.getKeyFrame(runTime), MIDX
							- (myMother.getWhichMotherTexture()
									.getRegionWidth() / 2), MIDY
							- (myMother.getWhichMotherTexture()
									.getRegionHeight() / 2), myMother
							.getWhichMotherTexture().getRegionWidth(), myMother
							.getWhichMotherTexture().getRegionHeight());
					float animTime = runTime - runTimeStartAnim;
					if (animTime > 2) {
						which_animation = "none";
					}
				}
				if (which_animation == "smoke") {
					batcher.draw(motherSmokesAnimation.getKeyFrame(runTime),
							MIDX
									- (myMother.getWhichMotherTexture()
											.getRegionWidth() / 2), MIDY
									- (myMother.getWhichMotherTexture()
											.getRegionHeight() / 2), myMother
									.getWhichMotherTexture().getRegionWidth(),
							myMother.getWhichMotherTexture().getRegionHeight());
					float animTime = runTime - runTimeStartAnim;
					if (animTime > 2) {
						which_animation = "none";
					}
				}
				if (which_animation == "take_meds") {
					batcher.draw(motherTakesMeds.getKeyFrame(runTime), MIDX
							- (73 / 2), MIDY - (90 / 2), 73, 90);
					if (myWorld.getNextMeds() == false) {
						which_animation = "none";
					}
				}

				batcher.draw(baby1.getWhichBabyPicCries(),
						(float) baby1.getX() - 16, (float) baby1.getY() - 16,
						baby1.getWidth(), baby1.getHeight());
				batcher.draw(baby2.getWhichBabyPicCries(),
						(float) baby2.getX() - 16, (float) baby2.getY() - 16,
						baby2.getWidth(), baby2.getHeight());
				batcher.draw(baby3.getWhichBabyPicCries(),
						(float) baby3.getX() - 16, (float) baby3.getY() - 16,
						baby3.getWidth(), baby3.getHeight());
				batcher.draw(baby4.getWhichBabyPicCries(),
						(float) baby4.getX() - 16, (float) baby4.getY() - 16,
						baby4.getWidth(), baby4.getHeight());
				if (myWorld.getWhichDifficulty().equals("Medium")) {
					batcher.draw(baby5.getWhichBabyPicCries(),
							(float) baby5.getX() - 16,
							(float) baby5.getY() - 16, baby5.getWidth(),
							baby5.getHeight());
				}
				if (myWorld.getWhichDifficulty().equals("Difficult")) {
					batcher.draw(baby5.getWhichBabyPicCries(),
							(float) baby5.getX() - 16,
							(float) baby5.getY() - 16, baby5.getWidth(),
							baby5.getHeight());
					batcher.draw(baby6.getWhichBabyPicCries(),
							(float) baby6.getX() - 16,
							(float) baby6.getY() - 16, baby6.getWidth(),
							baby6.getHeight());
				}

				if (baby1.getWhichBabyPicProblems() != null) {
					batcher.draw(baby1.getWhichBabyPicProblems(),
							(float) spebub1.getX() - 16,
							(float) spebub1.getY() - 44, spebub1.getWidth(),
							spebub1.getHeight());
				}
				if (baby2.getWhichBabyPicProblems() != null) {
					batcher.draw(baby2.getWhichBabyPicProblems(),
							(float) spebub2.getX() - 16,
							(float) spebub2.getY() - 44, spebub2.getWidth(),
							spebub2.getHeight());
				}
				if (baby3.getWhichBabyPicProblems() != null) {
					batcher.draw(baby3.getWhichBabyPicProblems(),
							(float) spebub3.getX() - 16,
							(float) spebub3.getY() - 44, spebub3.getWidth(),
							spebub3.getHeight());
				}
				if (baby4.getWhichBabyPicProblems() != null) {
					batcher.draw(baby4.getWhichBabyPicProblems(),
							(float) spebub4.getX() - 16,
							(float) spebub4.getY() - 44, spebub4.getWidth(),
							spebub4.getHeight());
				}
				if (myWorld.getWhichDifficulty().equals("Medium")) {
					if (baby5.getWhichBabyPicProblems() != null) {
						batcher.draw(baby5.getWhichBabyPicProblems(),
								(float) spebub5.getX() - 16,
								(float) spebub5.getY() - 44,
								spebub5.getWidth(), spebub5.getHeight());
					}
				}
				if (myWorld.getWhichDifficulty().equals("Difficult")) {
					if (baby5.getWhichBabyPicProblems() != null) {
						batcher.draw(baby5.getWhichBabyPicProblems(),
								(float) spebub5.getX() - 16,
								(float) spebub5.getY() - 44,
								spebub5.getWidth(), spebub5.getHeight());
					}
					if (baby6.getWhichBabyPicProblems() != null) {
						batcher.draw(baby6.getWhichBabyPicProblems(),
								(float) spebub6.getX() - 16,
								(float) spebub6.getY() - 44,
								spebub6.getWidth(), spebub6.getHeight());
					}
				}
			}
			batcher.draw(hand1.getWhich(), hand1.getPosx(), hand1.getPosy(),
					22, 30);
			Rectangle hand_rectangle = new Rectangle(hand1.getPosx(),
					hand1.getPosy(), 30, 30);
			Rectangle mother_rectangle = new Rectangle(MIDX
					- (myMother.getWhichMotherTexture().getRegionWidth() / 2),
					MIDY
							- (myMother.getWhichMotherTexture()
									.getRegionHeight() / 2), myMother
							.getWhichMotherTexture().getRegionWidth(), myMother
							.getWhichMotherTexture().getRegionHeight());
			if (hand_rectangle.overlaps(mother_rectangle)
					&& myWorld.getSupplySubtracted() == false) {
				int numenergy = myWorld.getEnergy();
				int numcigs = myWorld.getCigs();
				int nummeds = myWorld.getMeds();
				int num_cigboxes = myWorld.getCigBoxes();

				myWorld.setSupplySubtracted(true);
				if (hand1.getWhichString() == "energy" && numenergy > 0) {
					myWorld.setNextEnergy(true);
					myWorld.subtractEnergy();
					myWorld.resetDrinkTime();
					which_animation = "eat";
					runTimeStartAnim = runTime;
					hand1.restart();

				}
				if (hand1.getWhichString() == "cigs" && numcigs > 0) {
					myWorld.setTemper(100);
					myWorld.subtractCigs();
					which_animation = "smoke";
					runTimeStartAnim = runTime;
					hand1.restart();
				}
				if (hand1.getWhichString() == "meds" && nummeds > 0) {
					myWorld.setNextMeds(true);
					myWorld.subtractMeds();
					myWorld.resetMedsTime();
					which_animation = "take_meds";
					runTimeStartAnim = runTime;
					hand1.restart();
				}
				if (hand1.getWhichString() == "cigbox" && num_cigboxes > 0) {
					myWorld.setTemper(500);
					myWorld.subtractCigBoxes();
					which_animation = "smoke";
					runTimeStartAnim = runTime;
					hand1.restart();
				}

			}
			if (myWorld.getPlay() == false) {
				batcher.draw(whichNullStartPlayButton, 100, 120, 80, 60);
				AssetLoader.mad_mother_green_big_font.draw(batcher, "START!",
						115, 140);
			}

		}
		if (myWorld.isNull() == true || myWorld.isStore() == true) {
			batcher.draw(store, myWorld.getStoreX(), 0, 436, 408);
			batcher.enableBlending();
			if (myWorld.getStoreX() == -164) {
				myWorld.setGameState(GameState.STORE);
				AssetLoader.shop_font.draw(batcher,
						"CASH:$" + myWorld.getCash(), 0, 0);
				batcher.draw(energy_up_down, 0, 15, 65, 52);
				batcher.draw(cigs_up_down, 0, 70, 65, 52);
				batcher.draw(which_cig_box, 0, 123, 65, 52);

				int offsety = 0;
				if (AssetLoader.isMediumUnLocked() == true) {
					batcher.draw(meds_up_down, 0, 176, 65, 52);
					offsety += 53;
				}
				if (AssetLoader.isDifficultUnLocked() == true) {
					batcher.draw(which_super_rattle, 0, 231, 65, 52);
					offsety += 53;
				}
				batcher.draw(back_to_babies_up_down, 171, 278, 101, 91);
				AssetLoader.shop_font.draw(batcher, shop_talk, 90, 35);
				AssetLoader.shop_font.draw(batcher, shop_talk2, 85, 50);
				AssetLoader.shop_font.draw(batcher, shop_talk3, 80, 65);
				AssetLoader.shop_font.draw(batcher, "YOU HAVE", 0,
						176 + offsety);
				AssetLoader.shop_font.draw(batcher, myWorld.getEnergy()
						+ " ENERGY DRINK(S)", 0, 191 + offsety);
				AssetLoader.shop_font.draw(batcher, myWorld.getCigs()
						+ " CIGARETTE(S)", 0, 206 + offsety);
				AssetLoader.shop_font.draw(batcher, myWorld.getCigBoxes()
						+ " CIGARETTE BOX(ES)", 0, 221 + offsety);
				if (AssetLoader.isMediumUnLocked() == true) {
					AssetLoader.shop_font.draw(batcher, myWorld.getMeds()
							+ " MEDICINE", 0, 236 + offsety);
				}
				if (AssetLoader.isDifficultUnLocked() == true) {
					AssetLoader.shop_font.draw(batcher,
							myWorld.getSuperRattles() + " SUPER RATTLES(S)", 0,
							251 + offsety);
				}
			}
		}

		if (myWorld.isGameOver() || myWorld.isHighScore()) {
			drawGameOver();
		}

		batcher.end();
	}

	private void drawGameOver() {
		if (is_first_gameover == true) {
			which_button = tryAgainButtonUp;
		}
		is_first_gameover = false;
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		batcher.draw(mad_mother_logo_flipped, 0, 0,
				myGameScreen.getGameWidth(), myGameScreen.getGameHeight());
		batcher.enableBlending();
		AssetLoader.shadow.draw(batcher, "GAME OVER", 60, 5);
		AssetLoader.font.draw(batcher, "GAME OVER", 60, 3);

		AssetLoader.shadow.draw(batcher, "YOUR SCORE IS " + myWorld.getScore(),
				0, 30);
		AssetLoader.font.draw(batcher, "YOUR SCORE IS " + myWorld.getScore(),
				0, 28);
		int high_score = 0;
		if (myWorld.getWhichDifficulty().equals("Easy")) {
			high_score = AssetLoader.getEasyHighScore();
		}
		if (myWorld.getWhichDifficulty().equals("Medium")) {
			high_score = AssetLoader.getMediumHighScore();
		}
		if (myWorld.getWhichDifficulty().equals("Difficult")) {
			high_score = AssetLoader.getDifficultHighScore();
		}
		AssetLoader.score_font.draw(batcher, myWorld.getWhichDifficulty()
				.toUpperCase() + " HIGH SCORE IS " + high_score, 0, 53);
		batcher.draw(which_tryAgain, 60, 80, 80, 40);
		batcher.draw(which_game_over_to_menu, 0, 355, 50, 50);
		AssetLoader.mother_screaming.stop();
		AssetLoader.baby_cry.stop();
		AssetLoader.rattling.stop();
		}

	public void setWhichButton(String button) {
		if (button.equals("energyUp")) {
			energy_up_down = energyUp;
		}
		if (button.equals("energyDown") && !shop_talk.equals("Thank you.")
				&& !shop_talk.equals("You don't have")) {
			energy_up_down = energyDown;
			shop_talk = "Energy? It keeps your";
			shop_talk2 = "temper under extreme";
			shop_talk3 = "stress for only 3 seconds.";
		}
		if (button.equals("cigsUp")) {
			cigs_up_down = cigsUp;
		}
		if (button.equals("cigsDown") && !shop_talk.equals("Thank you.")
				&& !shop_talk.equals("You don't have")) {
			cigs_up_down = cigsDown;
			shop_talk = "Cigarette?";
			shop_talk2 = "It recovers 10%";
			shop_talk3 = "of your temper.";
		}
		
		if (button.equals("cigBoxUp")) {
			which_cig_box = cigBoxUp;
		}
		if (button.equals("cigBoxDown") && !shop_talk.equals("Thank you.")
				&& !shop_talk.equals("You don't have")) {
			which_cig_box = cigBoxDown;
			shop_talk = "Cigarette box?";
			shop_talk2 = "It recovers 50%";
			shop_talk3 = "of your temper.";
		}
		if (button.equals("medsUp")) {
			meds_up_down = medsUp;
		}
		if (button.equals("medsDown") && !shop_talk.equals("Thank you.")
				&& !shop_talk.equals("You don't have")) {
			meds_up_down = medsDown;
			shop_talk = "Meds? It keeps your";
			shop_talk2 = "temper under extreme";
			shop_talk3 = "stress for 10 seconds.";
		}
		if (button.equals("superRattleDown") && !shop_talk.equals("Thank you.")
				&& !shop_talk.equals("You don't have")) {
			which_super_rattle = superRattleDown;
			shop_talk = "Super Rattle?";
			shop_talk2 = "It will calm the";
			shop_talk3 = "baby for 20 seconds.";
		}
		if (button.equals("superRattleUp")) {
			which_super_rattle = superRattleUp;
		}

		if (button.equals("tryAgainButtonDown")) {
			which_button = tryAgainButtonDown;
		}
		if (button.equals("tryAgainButtonUp")) {
			which_button = tryAgainButtonUp;
		}
		if (button.equals("resetUp")) {
			reset_up_down = resetUp;
		}
		if (button.equals("resetDown")) {
			reset_up_down = resetDown;
		}
		if (button.equals("storeUp")) {
			store_up_down = storeUp;
		}
		if (button.equals("storeDown")) {
			store_up_down = storeDown;
		}


		if (button.equals("backToBabiesUp")) {
			back_to_babies_up_down = backToBabiesUp;
		}
		if (button.equals("backToBabiesDown")) {
			back_to_babies_up_down = backToBabiesDown;
			shop_talk = "OK BYE.";
			shop_talk2 = "";
			shop_talk3 = "";
		}
		if (button.equals("EasyDown")) {
			which_Easy_button = EasyDown;
		}
		if (button.equals("EasyUp")) {
			which_Easy_button = EasyUp;
		}
		if (button.equals("MediumDown")) {
			which_Medium_button = MediumDown;
		}
		if (button.equals("MediumUp")) {
			which_Medium_button = MediumUp;
		}
		if (button.equals("DifficultDown")) {
			which_Difficult_button = DifficultDown;
		}
		if (button.equals("DifficultUp")) {
			which_Difficult_button = DifficultUp;
		}
		if (button.equals("QuitDown")) {
			which_Quit_button = QuitDown;
		}
		if (button.equals("QuitUp")) {
			which_Quit_button = QuitUp;
		}

		if (button.equals("HelpDown")) {
			which_Help_button = HelpDown;
		}
		if (button.equals("HelpUp")) {
			which_Help_button = HelpUp;
		}
		if (button.equals("MenuDown")) {
			which_Menu_button = MenuDown;
		}
		if (button.equals("MenuUp")) {
			which_Menu_button = MenuUp;
		}
		if (button.equals("LevToStoreUp")) {
			lev_to_store_up_down = LevToStoreUp;
		}
		if (button.equals("LevToStoreDown")) {
			lev_to_store_up_down = LevToStoreDown;
		}
		if (button.equals("LevToBabiesUp")) {
			lev_to_babies_up_down = backToBabiesUp;
		}
		if (button.equals("LevToBabiesDown")) {
			lev_to_babies_up_down = backToBabiesDown;
		}

		if (button.equals("helpLeftUp")) {
			which_help_left = helpLeftUp;
		}
		if (button.equals("helpLeftDown")) {
			which_help_left = helpLeftDown;
		}
		if (button.equals("helpRightUp")) {
			which_help_right = helpRightUp;
		}
		if (button.equals("helpRightDown")) {
			which_help_right = helpRightDown;
		}
		if (button.equals("nullStartPlayButtonUp")) {
			whichNullStartPlayButton = nullButtonUp;
		}
		if (button.equals("nullStartPlayButtonDown")) {
			whichNullStartPlayButton = nullButtonDownPink;
		}
		if (button.equals("nullStartButtonUp")) {
			whichNullStartButton = nullButtonUp;
		}
		if (button.equals("nullStartButtonDown")) {
			whichNullStartButton = nullButtonDown;
		}
		if (button.equals("nullContinueButtonUp")) {
			which_nullContinueButton = nullButtonUp;
		}
		if (button.equals("nullContinueButtonDown")) {
			which_nullContinueButton = nullButtonDown;
		}
		if (button.equals("gameOverToMenuUp")) {
			which_game_over_to_menu = MenuUp;
		}
		if (button.equals("gameOverToMenuDown")) {
			which_game_over_to_menu = MenuDown;
		}
		if (button.equals("tryAgainUp")) {
			which_tryAgain = tryAgainButtonUp;
		}
		if (button.equals("tryAgainDown")) {
			which_tryAgain = tryAgainButtonDown;
		}
		if (button.equals("startPlayUp")) {
			whichNullStartPlayButton = nullButtonUp;
		}
		if (button.equals("startPlayDown")) {
			whichNullStartPlayButton = nullButtonDown;
		}

	}

	public void setShopTalk(String talk1, String talk2, String talk3) {
		shop_talk = talk1;
		shop_talk2 = talk2;
		shop_talk3 = talk3;
	}

	public void shuffleBabyTools() {
		int index, temp;
		for (int i = 5 - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = randomBabyToolsList[index];
			randomBabyToolsList[index] = randomBabyToolsList[i];
			randomBabyToolsList[i] = temp;
		}
	}

	public int[] getBabyToolsList() {
		return randomBabyToolsList;
	}

	public void setHighlightedLevel(int new_highlighted_level) {
		highlighted_level = new_highlighted_level;

	}

}
