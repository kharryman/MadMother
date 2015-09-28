package com.lfq.Helpers;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static TextureRegion energy_power;
	public static Texture texture, logoTexture,store_texture,animation;
	public static TextureRegion logo, LFQlogo, help_background, level_up_background,background,store, bottle, diaper, banana, ball, arms;
	public static TextureRegion baby, baby_poopoo, baby_bottle, baby_banana,
			baby_ball, baby_arms_up,baby_bottle_all, baby_banana_all,baby_ball_all;
	public static TextureRegion mother_anim1,mother_anim2,mother_smokes_anim,mother, mother1,mother2, mother3, mother4,mad_mother_logo,mad_mother_logo_flipped;
	public static Animation babyAnimation;
	public static TextureRegion hand;
	public static TextureRegion baby_cries1, baby_cries2, baby_arms_cries1,
			baby_arms_cries2, baby_poopoo_cries1, baby_poopoo_cries2,baby_rattle,baby_superrattle;
	public static TextureRegion hand_diaper, hand_bottle, hand_arms,
			hand_banana, hand_ball,hand_energy,hand_cigs,hand_meds,hand_rattle,hand_superrattle,hand_cigbox;
	private Random random;
	private int rannum;
	private int[] baby_pic_inds = new int[10];
	public TextureRegion baby_cries[];
	public static BitmapFont font, score_font,shadow, whiteFont,shop_font,small_shop_font,crying_font,mad_mother_green_font,mad_mother_green_big_font,bigfont,smallfont;
	private static Preferences prefs;
	public static Sound baby_cry,level_up,rattling,cheering,mother_screaming;
	public static TextureRegion energy,cigs,meds,superrattle,cigbox,temper_bar,temper_rect;
	public static TextureRegion playButtonUp,playButtonDown,tryAgainButtonUp,tryAgainButtonDown,retry,resetUp,resetDown,play,pause,storeUp,storeDown;
	public static TextureRegion backToBabiesUp,backToBabiesDown,energyUp,energyDown,cigarettesUp,cigarettesDown,medsUp,medsDown,cigBoxUp,cigBoxDown,superRattleUp,superRattleDown;
	public static TextureRegion EasyUp, MediumUp, DifficultUp, EasyDown, MediumDown, DifficultDown;
	public static TextureRegion QuitUp,QuitDown,HelpUp,HelpDown,MenuUp,MenuDown;
	public static TextureRegion rattle,lock,unlock;
	
	public static TextureRegion arm_ani_feet,arm_ani1,arm_ani2,arm_ani3,arm_ani4,arm_ani5,arm_ani6,arm_ani7;//arm animation
	public static TextureRegion ball_ani1,ball_ani2,ball_ani3,ball_ani4,ball_ani5,ball_ani6,ball_ani7,ball_ani8;//ball animation
	public static TextureRegion banana_ani1,banana_ani2,banana_ani3,banana_ani4,banana_ani5,banana_ani6,banana_ani7,banana_ani8,banana_ani9,banana_ani10;//banana animation
	public static TextureRegion bottle_ani1,bottle_ani2,bottle_ani3,bottle_ani4;//bottle animation
	public static TextureRegion diaper_ani1,diaper_ani2,diaper_ani3,diaper_ani4;//diaper animation
	
	public static TextureRegion take_meds_ani1,take_meds_ani2,take_meds_ani3;
	
	public static Animation babyMovesArms,babyBouncesBall,babyPeelsBanana,babyEatsBanana,babyDrinksBottle,babyDiaperChanged,motherTakesMeds;
	
	public static TextureRegion mother_on_couch,null_image,helpLeftUp,helpLeftDown,helpRightUp,helpRightDown;
	
	public static TextureRegion nullButtonUp,nullButtonDown,nullButtonDownPink;
	
	
	public static TextureRegion LevToStoreUp, LevToStoreDown;
	
	public static Animation motherEatsAnimation,motherSmokesAnimation;
	
	public static TextureRegion levelBar,levelMarker;

	public static void load() {

		texture = new Texture(Gdx.files.internal("data/mad_mother_pic.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		store_texture = new Texture(Gdx.files.internal("data/mad_mother_store.png"));
		store_texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
			
		animation = new Texture(Gdx.files.internal("data/animation.png"));
		animation.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		store=new TextureRegion(store_texture, 0, 0, 435, 408);
		store.flip(false, true);
		LFQlogo = new TextureRegion(texture, 284, 304, 270, 114);
		background = new TextureRegion(texture, 0, 0, 272, 408);
		background.flip(false, true);
		
		backToBabiesUp=new TextureRegion(store_texture,721,281,131,91);
		backToBabiesUp.flip(false, true);
		backToBabiesDown=new TextureRegion(store_texture,448,181,131,91);
		backToBabiesDown.flip(false, true);
		
		MenuUp=new TextureRegion(store_texture,584,181,131,91);
		MenuUp.flip(false, true);
		MenuDown=new TextureRegion(store_texture,584,87,131,91);
		MenuDown.flip(false, true);
		
		rattle=new TextureRegion(texture,918,154,40,89);
		rattle.flip(false, true);
		
		lock=new TextureRegion(texture,918,90,40,53);
		lock.flip(false, true);
		unlock=new TextureRegion(texture,912,13,53,53);
		unlock.flip(false, true);
		
		energy=new TextureRegion(store_texture,531,9,18,44);
		energy.flip(false, true);
		energyUp=new TextureRegion(store_texture,165,15,65,52);
		energyUp.flip(false, true);
		energyDown=new TextureRegion(store_texture,448,9,65,52);
		energyDown.flip(false, true);
		
		cigs=new TextureRegion(store_texture,531,66,37,40);
		cigs.flip(false, true);
		cigarettesUp=new TextureRegion(store_texture,165,70,65,52);
		cigarettesUp.flip(false, true);
		cigarettesDown=new TextureRegion(store_texture,448,66,65,52);
		cigarettesDown.flip(false, true);
		
		meds=new TextureRegion(store_texture,531,124,23,38);
		meds.flip(false, true);
		medsUp=new TextureRegion(store_texture,798,10,65,52);
		medsUp.flip(false, true);
		medsDown=new TextureRegion(store_texture,448,124,65,52);
		medsDown.flip(false, true);
		
		cigbox=new TextureRegion(store_texture,691,14,34,34);
		cigbox.flip(false,true);
		cigBoxUp=new TextureRegion(store_texture,868,9,65,52);
		cigBoxUp.flip(false, true);
		cigBoxDown=new TextureRegion(store_texture,868,64,65,52);
		cigBoxDown.flip(false, true);
		
		superrattle=new TextureRegion(store_texture,655,13,26,35);
		superrattle.flip(false, true);
		superRattleUp=new TextureRegion(store_texture,938,9,65,52);
		superRattleUp.flip(false, true);
		superRattleDown=new TextureRegion(store_texture,938,64,65,52);
		superRattleDown.flip(false, true);
		
		temper_bar=new TextureRegion(store_texture,565,11,75,29);
		temper_bar.flip(false, true);
		temper_rect=new TextureRegion(store_texture,567,47,59,7);
		temper_rect.flip(false, true);
		
		playButtonUp = new TextureRegion(texture, 296, 264, 58, 32);
		playButtonDown = new TextureRegion(texture, 354, 264, 58, 32);
		playButtonUp.flip(false, true);
		playButtonDown.flip(false, true);
		
		tryAgainButtonUp = new TextureRegion(texture, 412, 264, 100, 32);
		tryAgainButtonDown = new TextureRegion(texture, 512, 264, 100, 32);
		tryAgainButtonUp.flip(false, true);
		tryAgainButtonDown.flip(false, true);
		
		resetUp = new TextureRegion(texture, 618, 270, 48, 32);
		resetUp.flip(false, true);
		resetDown = new TextureRegion(texture, 667, 270, 48, 32);
		resetDown.flip(false, true);
		
		storeUp = new TextureRegion(texture, 716, 270, 48, 32);
		storeUp.flip(false, true);
		storeDown = new TextureRegion(texture, 765, 270, 48, 32);
		storeDown.flip(false, true);
		
		
		EasyUp = new TextureRegion(texture, 412, 301, 78, 32);
		EasyUp.flip(false, true);
		EasyDown = new TextureRegion(texture, 493, 301, 78, 32);
		EasyDown.flip(false, true);
		
		MediumUp = new TextureRegion(texture, 412, 340, 78, 32);
		MediumUp.flip(false, true);
		MediumDown = new TextureRegion(texture, 493, 340, 78, 32);
		MediumDown.flip(false, true);
		
		DifficultUp = new TextureRegion(texture, 412, 381, 78, 32);
		DifficultUp.flip(false, true);
		DifficultDown = new TextureRegion(texture, 493, 381, 78, 32);
		DifficultDown.flip(false, true);
		
		QuitUp = new TextureRegion(texture, 814, 270, 40, 32);
		QuitUp.flip(false, true);
		QuitDown = new TextureRegion(texture, 855, 270, 40, 32);
		QuitDown.flip(false, true);
		
		HelpUp = new TextureRegion(texture, 412, 419, 52, 32);
		HelpUp.flip(false, true);
		HelpDown = new TextureRegion(texture, 493, 419, 52, 32);
		HelpDown.flip(false, true);
		
		help_background=new TextureRegion(texture, 971, 90, 20, 20);
		level_up_background=new TextureRegion(texture, 7, 525, 492, 408);
		level_up_background.flip(false, true);
		
		LevToStoreUp = new TextureRegion(store_texture, 721, 181, 131, 91);
		LevToStoreUp.flip(false, true);
		LevToStoreDown = new TextureRegion(store_texture, 721, 87, 131, 91);
		LevToStoreDown.flip(false, true);
        
        play=new TextureRegion(texture, 678, 343, 27, 27);
        play.flip(false, true);
        pause=new TextureRegion(texture, 678, 306, 27, 27);
        pause.flip(false, true);
        
        mother_anim1= new TextureRegion(texture, 15, 429, 82, 92);
        mother_anim1.flip(false, true);
        mother_anim2= new TextureRegion(texture, 114, 429, 82, 92);
        mother_anim2.flip(false, true);
        mother_smokes_anim= new TextureRegion(texture, 215, 430, 82, 92);
        mother_smokes_anim.flip(false, true);
		
		
		mother = new TextureRegion(texture, 280, 156, 82, 92);
		mother.flip(false, true);
		mother1 = new TextureRegion(texture, 384, 156, 54, 92);
		mother1.flip(false, true);
		mother2 = new TextureRegion(texture, 588, 316, 50, 92);
		mother2.flip(false, true);
		mother3 = new TextureRegion(texture, 478, 156, 46, 92);
		mother3.flip(false, true);
		mother4 = new TextureRegion(texture, 556, 140, 68, 108);
		mad_mother_logo=new TextureRegion(texture, 509, 509, 512, 512);
		mad_mother_logo_flipped=new TextureRegion(texture, 509, 509, 512, 512);
		mad_mother_logo_flipped.flip(false,true);
		mother4.flip(false, true);		
		baby = new TextureRegion(texture, 274, 2, 28, 52);
		baby.flip(false, true);
		baby_poopoo = new TextureRegion(texture, 308, 2, 28, 52);
		baby_poopoo.flip(false, true);
		baby_bottle = new TextureRegion(texture, 352, 62, 44, 32);
		baby_bottle.flip(false, true);
		baby_banana = new TextureRegion(texture, 300, 62, 44, 32);
		baby_banana.flip(false, true);
		baby_ball = new TextureRegion(texture, 404, 62, 44, 32);
		baby_ball.flip(false, true);
		
		baby_bottle_all = new TextureRegion(texture, 352, 62, 44, 86);
		baby_bottle_all.flip(false, true);
		baby_banana_all = new TextureRegion(texture, 300, 62, 44, 86);
		baby_banana_all.flip(false, true);
		baby_ball_all = new TextureRegion(texture, 404, 62, 44, 86);
		baby_ball_all.flip(false, true);
		
		baby_arms_up = new TextureRegion(texture, 336, 2, 40, 52);
		baby_arms_up.flip(false, true);
		bottle = new TextureRegion(texture, 432, 12, 14, 30);
		bottle.flip(false, true);
		diaper = new TextureRegion(texture, 382, 14, 36, 20);
		diaper.flip(false, true);
		banana = new TextureRegion(texture, 464, 14, 50, 24);
		banana.flip(false, true);
		ball = new TextureRegion(texture, 474, 62, 30, 30);
		ball.flip(false, true);
		arms = new TextureRegion(texture, 526, 14, 36, 34);
		arms.flip(false, true);
		baby_cries1 = new TextureRegion(texture, 520, 58, 36, 54);
		baby_cries1.flip(false, true);
		baby_cries2 = new TextureRegion(texture, 566, 58, 36, 54);
		baby_cries2.flip(false, true);
		baby_arms_cries1 = new TextureRegion(texture, 608, 60, 40, 54);
		baby_arms_cries1.flip(false, true);
		baby_arms_cries2 = new TextureRegion(texture, 658, 60, 40, 54);
		baby_arms_cries2.flip(false, true);
		baby_rattle=new TextureRegion(texture, 709, 35, 44, 79);
		baby_rattle.flip(false,true);
		baby_superrattle=new TextureRegion(texture, 773, 31, 49, 84);
		baby_superrattle.flip(false, true);
		baby_poopoo_cries1 = new TextureRegion(texture, 566, 0, 36, 54);
		baby_poopoo_cries1.flip(false, true);
		baby_poopoo_cries2 = new TextureRegion(texture, 608, 0, 36, 54);
		baby_poopoo_cries2.flip(false, true);
		hand = new TextureRegion(texture, 634, 182, 22, 30);
		hand.flip(false, true);
		hand_diaper = new TextureRegion(texture, 638, 234, 34, 34);
		hand_diaper.flip(false, true);
		hand_bottle = new TextureRegion(texture, 686, 224, 22, 44);
		hand_bottle.flip(false, true);
		hand_arms = new TextureRegion(texture, 718, 220, 36, 48);
		hand_arms.flip(false, true);
		hand_banana = new TextureRegion(texture, 762, 230, 50, 34);
		hand_banana.flip(false, true);
		hand_ball = new TextureRegion(texture, 824, 224, 30, 44);
		hand_ball.flip(false, true);
		hand_energy = new TextureRegion(texture, 720, 315, 22, 47);
		hand_energy.flip(false, true);
		hand_cigs = new TextureRegion(texture, 762, 317, 37, 40);
		hand_cigs.flip(false, true);
		hand_meds = new TextureRegion(texture, 814, 317, 24, 52);
		hand_meds.flip(false, true);
		hand_rattle= new TextureRegion(texture, 862, 215, 25, 53);
		hand_rattle.flip(false, true);
		hand_superrattle = new TextureRegion(texture, 863, 315, 26, 57);
		hand_superrattle.flip(false, true);
		hand_cigbox = new TextureRegion(texture, 901, 316, 37, 53);
		hand_cigbox.flip(false, true);
		
		energy_power=new TextureRegion(texture, 296, 309, 84, 91);
		energy_power.flip(false, true);
		
		nullButtonUp=new TextureRegion(texture, 308, 416, 81, 47);
		nullButtonDown=new TextureRegion(texture, 308, 470, 81, 47);
		nullButtonDownPink=new TextureRegion(texture, 396, 470, 81, 47);
				
		arm_ani_feet=new TextureRegion(animation,42,67,24,6);//arm animation
		arm_ani_feet.flip(false, true);
		arm_ani1=new TextureRegion(animation,19,8,45,53);
		arm_ani1.flip(false, true);
		arm_ani2=new TextureRegion(animation,87,8,46,53);
		arm_ani2.flip(false, true);
		arm_ani3=new TextureRegion(animation,161,6,51,53);
		arm_ani3.flip(false, true);
		arm_ani4=new TextureRegion(animation,227,5,55,53);
		arm_ani4.flip(false, true);
		arm_ani5=new TextureRegion(animation,304,6,61,53);
		arm_ani5.flip(false, true);
		arm_ani6=new TextureRegion(animation,378,7,64,53);
		arm_ani6.flip(false, true);
		arm_ani7=new TextureRegion(animation,445,7,67,53);
		arm_ani7.flip(false, true);
		
		ball_ani1=new TextureRegion(animation,32,74,38,90);//ball animation
		ball_ani1.flip(false, true);
		ball_ani2=new TextureRegion(animation,75,74,38,90);
		ball_ani2.flip(false, true);
		ball_ani3=new TextureRegion(animation,118,74,38,90);
		ball_ani3.flip(false, true);
		ball_ani4=new TextureRegion(animation,161,74,38,90);
		ball_ani4.flip(false, true);
		ball_ani5=new TextureRegion(animation,204,74,38,90);
		ball_ani5.flip(false, true);
		ball_ani6=new TextureRegion(animation,247,74,38,90);
		ball_ani6.flip(false, true);
		ball_ani7=new TextureRegion(animation,290,74,38,90);
		ball_ani7.flip(false, true);
		ball_ani8=new TextureRegion(animation,334,74,38,90);
		ball_ani8.flip(false, true);
		
		banana_ani1=new TextureRegion(animation,37,181,26,60);//banana animation
		banana_ani1.flip(false, true);
		banana_ani2=new TextureRegion(animation,78,181,26,60);
		banana_ani2.flip(false, true);
		banana_ani3=new TextureRegion(animation,118,181,26,60);
		banana_ani3.flip(false, true);
		banana_ani4=new TextureRegion(animation,157,181,26,60);
		banana_ani4.flip(false, true);
		banana_ani5=new TextureRegion(animation,195,181,26,60);
		banana_ani5.flip(false, true);
		banana_ani6=new TextureRegion(animation,236,181,26,60);
		banana_ani6.flip(false, true);
		banana_ani7=new TextureRegion(animation,276,181,26,60);
		banana_ani7.flip(false, true);
		banana_ani8=new TextureRegion(animation,314,181,26,60);
		banana_ani8.flip(false, true);
		banana_ani9=new TextureRegion(animation,348,181,26,60);
		banana_ani9.flip(false, true);
		banana_ani10=new TextureRegion(animation,381,181,26,60);
		banana_ani10.flip(false, true);
		
		bottle_ani1=new TextureRegion(animation,34,265,39,52);//bottle animation
		bottle_ani1.flip(false, true);
		bottle_ani2=new TextureRegion(animation,82,265,38,52);
		bottle_ani2.flip(false, true);
		bottle_ani3=new TextureRegion(animation,129,265,41,52);
		bottle_ani3.flip(false, true);
		bottle_ani4=new TextureRegion(animation,176,265,42,52);
		bottle_ani4.flip(false, true);
		
		diaper_ani1=new TextureRegion(animation,50,335,58,139);//diaper animation
		diaper_ani1.flip(false, true);
		diaper_ani2=new TextureRegion(animation,136,335,58,140);
		diaper_ani2.flip(false, true);
		diaper_ani3=new TextureRegion(animation,212,334,58,140);
		diaper_ani3.flip(false, true);
		diaper_ani4=new TextureRegion(animation,284,334,58,140);
		diaper_ani4.flip(false, true);
		
		take_meds_ani1=new TextureRegion(animation,45,498,79,92);
		take_meds_ani1.flip(false, true);
		take_meds_ani2=new TextureRegion(animation,132,496,64,94);
		take_meds_ani2.flip(false, true);
		take_meds_ani3=new TextureRegion(animation,206,500,73,90);
		take_meds_ani3.flip(false, true);
		
		mother_on_couch=new TextureRegion(texture,651,378,82,124);
		mother_on_couch.flip(false, true);
		
		null_image=new TextureRegion(texture,710,188,10,10);
		
		levelBar=new TextureRegion(texture,749,388,146,12);
		levelBar.flip(false,true);
		levelMarker=new TextureRegion(texture,749,405,1,14);
		levelMarker.flip(false,true);
		
		helpLeftUp=new TextureRegion(store_texture,868,130,28,28);
		helpLeftDown=new TextureRegion(store_texture,868,161,28,28);
		helpRightUp=new TextureRegion(store_texture,904,130,28,28);
		helpRightDown=new TextureRegion(store_texture,904,161,28,28);
		
		TextureRegion[] mother_takes_meds={take_meds_ani1,take_meds_ani2,take_meds_ani3};
		motherTakesMeds=new Animation(0.30f,mother_takes_meds);
		motherTakesMeds.setPlayMode(Animation.NORMAL);
		
		TextureRegion[] mother_eats = {mother,mother_anim1, mother_anim2};
		motherEatsAnimation = new Animation(0.30f, mother_eats);
		motherEatsAnimation.setPlayMode(Animation.LOOP_PINGPONG);
		
		TextureRegion[] mother_smokes = {mother,mother_anim1, mother_anim2,mother_smokes_anim};
		motherSmokesAnimation = new Animation(0.30f, mother_smokes);
		motherSmokesAnimation.setPlayMode(Animation.LOOP_PINGPONG);
		
		TextureRegion[] baby_moves_arms={arm_ani1,arm_ani2,arm_ani3,arm_ani4,arm_ani5,arm_ani6,arm_ani7};
		babyMovesArms = new Animation(0.30f, baby_moves_arms);
		babyMovesArms.setPlayMode(Animation.LOOP_PINGPONG);
		
		TextureRegion[] baby_bounces_ball={ball_ani1,ball_ani2,ball_ani3,ball_ani4,ball_ani5,ball_ani6,ball_ani7,ball_ani7,ball_ani8};
		babyBouncesBall = new Animation(0.30f, baby_bounces_ball);
		babyBouncesBall.setPlayMode(Animation.LOOP_PINGPONG);
		
		TextureRegion[] baby_peels_banana={banana_ani1,banana_ani2,banana_ani3,banana_ani4,banana_ani5,null_image};
		TextureRegion[] baby_eats_banana={banana_ani6,banana_ani7,banana_ani8,banana_ani9,banana_ani10};
		babyPeelsBanana= new Animation(0.30f, baby_peels_banana);
		babyPeelsBanana.setPlayMode(Animation.NORMAL);
		babyEatsBanana = new Animation(0.30f, baby_eats_banana);
		babyEatsBanana.setPlayMode(Animation.LOOP_PINGPONG);
		
		TextureRegion[] baby_drinks_bottle={bottle_ani1,bottle_ani2,bottle_ani3,bottle_ani4};
		babyDrinksBottle = new Animation(0.30f, baby_drinks_bottle);
		babyDrinksBottle.setPlayMode(Animation.LOOP_PINGPONG);
		
		TextureRegion[] baby_diaper_changed={diaper_ani1,diaper_ani2,diaper_ani3,diaper_ani4};
		babyDiaperChanged = new Animation(0.30f, baby_diaper_changed);
		babyDiaperChanged.setPlayMode(Animation.LOOP_PINGPONG);
				
		int[] baby_pic_inds = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font.setScale(0.3f, -0.3f);
		
		bigfont = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		bigfont.setScale(0.325f, -0.325f);
		
		score_font=new BitmapFont(Gdx.files.internal("data/text.fnt"));
		score_font.setScale(0.25f, -0.25f);
		
		smallfont=new BitmapFont(Gdx.files.internal("data/text.fnt"));
		smallfont.setScale(0.20f, -0.20f);

		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.setScale(.30f, -.30f);
		
		shop_font=new BitmapFont(Gdx.files.internal("data/mad_mother.fnt"));
		shop_font.setScale(0.5f,-0.5f);
		
		small_shop_font=new BitmapFont(Gdx.files.internal("data/mad_mother.fnt"));
		small_shop_font.setScale(0.36f,-0.36f);
		
		crying_font=new BitmapFont(Gdx.files.internal("data/mad_mother.fnt"));
		crying_font.setScale(0.5f,-0.5f);
		
		mad_mother_green_font=new BitmapFont(Gdx.files.internal("data/mad_mother_green.fnt"));
		mad_mother_green_font.setScale(0.4f,-0.4f);
		
		mad_mother_green_big_font=new BitmapFont(Gdx.files.internal("data/mad_mother_green.fnt"));
		mad_mother_green_big_font.setScale(0.5f,-0.5f);
		
		baby_cry = Gdx.audio.newSound(Gdx.files.internal("data/leslie_crying1_2.wav"));
		level_up = Gdx.audio.newSound(Gdx.files.internal("data/Powerup49.wav"));
		rattling= Gdx.audio.newSound(Gdx.files.internal("data/Rattle.wav"));
		cheering= Gdx.audio.newSound(Gdx.files.internal("data/Kids Cheering.wav"));
		mother_screaming= Gdx.audio.newSound(Gdx.files.internal("data/mother screaming.wav"));

		// Create (or retrieve existing) preferences file
		prefs = Gdx.app.getPreferences("Mad-Mother");

		if (!prefs.contains("easyHighScore")) {
			prefs.putInteger("easyHighScore", 0);
		}
		if (!prefs.contains("mediumHighScore")) {
			prefs.putInteger("mediumHighScore", 0);
		}
		if (!prefs.contains("difficultHighScore")) {
			prefs.putInteger("difficultHighScore", 0);
		}
		if (!prefs.contains("isMediumUnLocked")) {
			prefs.putBoolean("isMediumUnLocked", false);
		}
		if (!prefs.contains("isDifficultUnLocked")) {
			prefs.putBoolean("isDifficultUnLocked", false);
		}
		if (!prefs.contains("easyLevel")) {
			prefs.putInteger("easyLevel", 0);
		}
		if (!prefs.contains("mediumLevel")) {
			prefs.putInteger("mediumLevel", 0);
		}
		if (!prefs.contains("difficultLevel")) {
			prefs.putInteger("difficultLevel", 0);
		}
	}

	public static void dispose() {
		texture.dispose();
		font.dispose();
		shadow.dispose();

	}
	
	public static void setEasyHighScore(int val) {
	    prefs.putInteger("easyHighScore", val);
	    prefs.flush();
	}
	
	public static void setMediumHighScore(int val) {
	    prefs.putInteger("mediumHighScore", val);
	    prefs.flush();
	}
	
	public static void setDifficultHighScore(int val) {
	    prefs.putInteger("difficultHighScore", val);
	    prefs.flush();
	}
	
	
	public static int getEasyHighScore() {
		return prefs.getInteger("easyHighScore");
	}
	
	public static int getMediumHighScore() {
	    return prefs.getInteger("mediumHighScore");
	}
	
	public static int getDifficultHighScore() {
	    return prefs.getInteger("difficultHighScore");
	}
	
	public static int getEasyLevel() {
	    return prefs.getInteger("easyLevel");
	}
	
	public static int getMediumLevel() {
	    return prefs.getInteger("mediumLevel");
	}
	
	public static int getDifficultLevel() {
	    return prefs.getInteger("difficultLevel");
	}
	
	
	
	public static Boolean isMediumUnLocked(){
		return prefs.getBoolean("isMediumUnLocked");
	}
	public static Boolean isDifficultUnLocked(){
		return prefs.getBoolean("isDifficultUnLocked");
	}
	
	public static void setMediumUnlocked() {
		prefs.putBoolean("isMediumUnLocked", true);
	    prefs.flush();
	}
	
	public static void setDifficultUnlocked() {
	    prefs.putBoolean("isDifficultUnLocked", true);
	    prefs.flush();
	}
	
	

}
