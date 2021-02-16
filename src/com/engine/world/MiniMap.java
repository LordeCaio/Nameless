package com.engine.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.engine.data.Data;
import com.engine.entities.Enemy;
import com.engine.main.Loop;
import com.engine.tile.Solid_Tile;
import com.engine.tile.UnSolid_Tile;

public class MiniMap {
	
	public static BufferedImage miniMap = new BufferedImage(World.WIDTH, World.HEIGHT, BufferedImage.TYPE_INT_RGB);
	public static int[] miniMapPixels;
	
	public static void miniMapRender(Graphics g) {
		miniMapPixels = ((DataBufferInt)miniMap.getRaster().getDataBuffer()).getData();
		g.drawImage(miniMap, 0, Loop.HEIGHT - (World.HEIGHT*8), World.WIDTH*8, World.HEIGHT*8, null);
		
		for(int xx = 0; xx < World.WIDTH; xx++) {
			for(int yy = 0; yy < World.WIDTH; yy++) {
				if(World.tiles[xx + (yy * World.WIDTH)] instanceof Solid_Tile) {
					miniMapPixels[xx + (yy * World.WIDTH)] = 0xFF61615E;
				}
				else if(World.tiles[xx + (yy * World.WIDTH)] instanceof UnSolid_Tile) {
					miniMapPixels[xx + (yy * World.WIDTH)] = 0x40914E;
				} 
			}
		}
		
		int playerX = (Data.Player.getX() + Data.Player.getFootMask('x'))  /World.TILE_SIZE;
		int playerY = (Data.Player.getY() + Data.Player.getFootMask('y'))  /World.TILE_SIZE;		
		miniMapPixels[playerX + (playerY * World.WIDTH)] = 0xFFFA1900;
		
		for(int i = 0; i < Data.Enemy.size(); i++) {
			Enemy e = Data.Enemy.get(i);			
			miniMapPixels[(e.getX() / World.TILE_SIZE) +  (e.getY() / World.TILE_SIZE * World.WIDTH)] = 0xFF9A30A2;
		}
		
	}

}
