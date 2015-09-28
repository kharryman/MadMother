package com.lfq.GameObjects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.lfq.GameWorld.GameRenderer;
import com.lfq.GameWorld.GameWorld;
import com.lfq.Helpers.AssetLoader;
import com.lfq.Helpers.InputHandler;

public class Hand {
	private int posx,posy;
	private Rectangle mother_rectangle,hand_rectangle, rectangle1, rectangle2, rectangle3, rectangle4, rectangle5,energy_rectangle,cigs_rectangle,meds_rectangle,rattle_rectangle,superrattle_rectangle,cigbox_rectangle;
	public Rectangle vbar1,vbar2,vbar3,hbar1,hbar2,hbar3,hbar4,hbar5;
	private TextureRegion which_texture_hand;
	private String which_string_hand;
	private TextureRegion hand, hand_diaper, hand_bottle, hand_arms, hand_banana, hand_ball,hand_energy,hand_cigs,hand_meds,hand_rattle,hand_cigbox,hand_superrattle;
	private GameWorld myWorld;
	private GameRenderer myRenderer;
	private InputHandler ip;
	private Boolean overlap_bar_before;
	private Rectangle[] babyToolsRectangles;
	private int[] randomBabyToolsList={0,1,2,3,4};
	
	private TextureRegion[] hand_objects;
	private String[] which_hand_string_array=new String[]{"arms","ball","banana","bottle","diaper"};
	public Hand(int midPointY,GameWorld world){
		posx=132;
		overlap_bar_before=false;
		myWorld=world;
		myRenderer=myWorld.getRenderer();
		ArrayList<Integer> randomBabyToolsList=new ArrayList<Integer>();
		randomBabyToolsList.add(0);
		randomBabyToolsList.add(1);
		randomBabyToolsList.add(2);
		randomBabyToolsList.add(3);
		randomBabyToolsList.add(4);
		posy=midPointY;
		hand=AssetLoader.hand;
		hand_diaper=AssetLoader.hand_diaper;
		hand_bottle=AssetLoader.hand_bottle;
		hand_arms=AssetLoader.hand_arms;
		hand_banana=AssetLoader.hand_banana;
		hand_ball=AssetLoader.hand_ball;
		hand_energy=AssetLoader.hand_energy;
		hand_cigs=AssetLoader.hand_cigs;
		hand_meds=AssetLoader.hand_meds;
		hand_rattle=AssetLoader.hand_rattle;
		hand_superrattle=AssetLoader.hand_superrattle;
		hand_cigbox=AssetLoader.hand_cigbox;
		which_texture_hand=hand;
		which_string_hand="hand";
		hand_objects=new TextureRegion[]{hand_arms,hand_ball,hand_banana,hand_bottle,hand_diaper};
		rectangle1=new Rectangle(0,89,30,29);
		rectangle2=new Rectangle(0,136,30,29);
		rectangle3=new Rectangle(0,183,30,29);
		rectangle4=new Rectangle(0,230,30,29);
		rectangle5=new Rectangle(0,277,30,29);
		babyToolsRectangles=new Rectangle[]{rectangle1,rectangle2,rectangle3,rectangle4,rectangle5};
		energy_rectangle=new Rectangle(0,0,30,30);
		cigs_rectangle=new Rectangle(40,0,30,30);
		meds_rectangle=new Rectangle(80,0,30,30);
		hand_rectangle=new Rectangle(132,midPointY,30,30);
		cigbox_rectangle=new Rectangle(0,324,30,29);
		superrattle_rectangle=new Rectangle(0,371,30,29);
		
		
		vbar1=new Rectangle(34,0,2,35);
		vbar2=new Rectangle(75,0,2,35);
		
		hbar1=new Rectangle(0,80,35,2);
		hbar2=new Rectangle(0,145,35,2);
		hbar3=new Rectangle(0,209,35,2);
		hbar4=new Rectangle(0,273,35,2);
		hbar5=new Rectangle(0,337,35,2);
	}
	
	public void update(float delta){
		myRenderer=myWorld.getRenderer();
    int[] randomBabyToolsList=myRenderer.getBabyToolsList();
		if (hand_rectangle.overlaps(babyToolsRectangles[0])){which_texture_hand=hand_objects[randomBabyToolsList[0]];which_string_hand=which_hand_string_array[randomBabyToolsList[0]];myWorld.setSupplySubtracted(false);}
		if (hand_rectangle.overlaps(babyToolsRectangles[1])){which_texture_hand=hand_objects[randomBabyToolsList[1]];which_string_hand=which_hand_string_array[randomBabyToolsList[1]];;myWorld.setSupplySubtracted(false);}
		if (hand_rectangle.overlaps(babyToolsRectangles[2])){which_texture_hand=hand_objects[randomBabyToolsList[2]];which_string_hand=which_hand_string_array[randomBabyToolsList[2]];myWorld.setSupplySubtracted(false);}
		if (hand_rectangle.overlaps(babyToolsRectangles[3])){which_texture_hand=hand_objects[randomBabyToolsList[3]];which_string_hand=which_hand_string_array[randomBabyToolsList[3]];myWorld.setSupplySubtracted(false);}
		if (hand_rectangle.overlaps(babyToolsRectangles[4])){which_texture_hand=hand_objects[randomBabyToolsList[4]];which_string_hand=which_hand_string_array[randomBabyToolsList[4]];myWorld.setSupplySubtracted(false);}
		if (hand_rectangle.overlaps(energy_rectangle) && myWorld.getEnergy()>0){which_texture_hand=hand_energy;which_string_hand="energy";myWorld.setSupplySubtracted(false);}
		if (hand_rectangle.overlaps(cigs_rectangle) && myWorld.getCigs()>0){which_texture_hand=hand_cigs;which_string_hand="cigs";myWorld.setSupplySubtracted(false);}
		if (hand_rectangle.overlaps(meds_rectangle) && myWorld.getMeds()>0){which_texture_hand=hand_meds;which_string_hand="meds";myWorld.setSupplySubtracted(false);}
		if (hand_rectangle.overlaps(cigbox_rectangle) && myWorld.getCigBoxes()>0){which_texture_hand=hand_cigbox;which_string_hand="cigbox";myWorld.setSupplySubtracted(false);}
		if (hand_rectangle.overlaps(superrattle_rectangle) && myWorld.getSuperRattles()>0){which_texture_hand=hand_superrattle;which_string_hand="superrattle";myWorld.setSupplySubtracted(false);}
		if (myWorld.isRattle()){
			rattle_rectangle=new Rectangle(myWorld.getRanRatX(),myWorld.getRanRatY(),20,45);
			if (hand_rectangle.overlaps(rattle_rectangle)){
				which_texture_hand=hand_rattle;which_string_hand="rattle";
				myWorld.setIsRattle(false);
			}
		}
	}

	public void onDrag(int x,int y) {
		posx=x;
		posy=y;

        	hand_rectangle.set(posx,posy,30,30);
		return;
	}

	public int getPosx() {
		return posx;
	}

	public int getPosy() {
		return posy;
	}
	
	public Rectangle getHandRectangle(){
		return hand_rectangle;
	}

	public TextureRegion getWhich() {
		return which_texture_hand;
	}
	
	public void restart(){
		which_texture_hand=hand;
		which_string_hand="hand";
	}
	
	public String getWhichString() {
		return which_string_hand;
	}

}
