package com.lfq.GameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.lfq.GameWorld.GameWorld;
import com.lfq.GameWorld.GameWorld.GameState;
import com.lfq.Helpers.AssetLoader;

public class Mother {
	private int width, height;
	public GameWorld myWorld;
	private TextureRegion mother, mother1, mother2, mother3,mother4;
	public TextureRegion which_mother_texture;
	public String which_mother_string;
	private int number_babies_crying;
	private Baby babies;

	public Mother(int width, int height, GameWorld myWorld) {
		this.myWorld = myWorld;
		this.width = width;
		this.height = height;
		mother = AssetLoader.mother;
		mother1 = AssetLoader.mother1;
		mother2 = AssetLoader.mother2;
		mother3 = AssetLoader.mother3;
		mother4 = AssetLoader.mother4;
		which_mother_string = "mother";
		which_mother_texture = mother;
	}

	public void update(float delta) {
		number_babies_crying = myWorld.getNumberBabiesCrying();
		if (number_babies_crying ==0) {
			which_mother_texture = mother;
		}
		if (number_babies_crying ==1) {
			which_mother_texture = mother1;
		}
		if (number_babies_crying == 2) {
			which_mother_texture = mother2;
		}
		if (number_babies_crying == 3) {
			which_mother_texture = mother3;
		}
		if (number_babies_crying == 4) {
			which_mother_texture = mother4;
		}


	}

	public TextureRegion getWhichMotherTexture() {
		return which_mother_texture;
	}
	
	public void onDrag() {

	}

}
