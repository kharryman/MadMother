package com.lfq.GameObjects;

public class Speech_Bubble {
	private int width;
	private int height;
	private double theta;
	final private int RADIUSA=64;
	final private int RADIUSB=130;
	final private int MIDX=150;
	final private int MIDY=230;
	public double posx;
	public double posy;
	public Speech_Bubble (double start_posx,double start_posy, int width, int height) {
		this.width = width;
		this.height = height;
		this.theta=theta;
		posx=start_posx;
		posy=start_posy;
		}

	public void update(float delta,double new_posx,double new_posy) {
		posx=new_posx;
		posy=new_posy;
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
