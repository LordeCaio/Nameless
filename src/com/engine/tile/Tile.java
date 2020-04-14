package com.engine.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.engine.world.Camera;

public class Tile {
	
	protected BufferedImage sprite;
	protected int x, y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
