package com.engine.tile;

import java.awt.image.BufferedImage;

import com.engine.graphics.Sprite_Tile;

public class UnSolid_Tile extends Tile{
	
	//UnSolid Tile Paths
	static Sprite_Tile floor01 = new Sprite_Tile("/Tiles/floor01.png");
	
	//UnSolid Tile Objects
	public static BufferedImage Grass01 =  floor01.getSprite(32*0, 32*0, 32, 32);	
	public static BufferedImage Grass02 =  floor01.getSprite(32*1, 32*0, 32, 32);	
	public static BufferedImage Grass03 =  floor01.getSprite(32*2, 32*0, 32, 32);	
	
	
	public UnSolid_Tile(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}
	

}
