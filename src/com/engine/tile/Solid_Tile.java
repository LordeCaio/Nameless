package com.engine.tile;

import java.awt.image.BufferedImage;

import com.engine.graphics.Sprite_Tile;

public class Solid_Tile extends Tile{
	
	//Solid Tile Paths
	static Sprite_Tile Wall01 = new Sprite_Tile("/Tiles/wall01.png");
	
	//Solid Tile Objects
	public static BufferedImage Rock01 =  Wall01.getSprite(0, 0, 32, 32);
	
	
	public Solid_Tile(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}

		
}
