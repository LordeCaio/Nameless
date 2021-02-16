package com.engine.entities.ENTITY;

import java.awt.image.BufferedImage;

import com.engine.entities.Entity;

public class Solid extends Entity{

	public Solid(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		this.setCollMask(0, 0, 32, 32);
		this.solid = true;
		
	}
}
