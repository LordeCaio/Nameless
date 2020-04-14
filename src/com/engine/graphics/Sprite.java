package com.engine.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Sprite extends Graphics{
		
	public static BufferedImage tint(BufferedImage sprite, float r, float g, float b, float a) {
		BufferedImage tintedSprite = new BufferedImage(sprite.getWidth(), sprite.getHeight(), BufferedImage.TRANSLUCENT);		
		if(r > 1f) {r /= 255.0f;} 
		if(g > 1f) {g /= 255.0f;}
		if(b > 1f) {b /= 255.0f;}
		if(a > 1f) {a /= 255.0f;}		
		Graphics2D graphics = tintedSprite.createGraphics();
		graphics.drawImage(sprite, 0, 0, null);
		graphics.dispose();		
		for (int i = 0; i < tintedSprite.getWidth(); i++) {
			for (int j = 0; j < tintedSprite.getHeight(); j++) {
				int ax = tintedSprite.getColorModel().getAlpha(tintedSprite.getRaster().
				getDataElements(i, j, null));
				int rx = tintedSprite.getColorModel().getRed(tintedSprite.getRaster().
				getDataElements(i, j, null));
				int gx = tintedSprite.getColorModel().getGreen(tintedSprite.getRaster().
				getDataElements(i, j, null));
				int bx = tintedSprite.getColorModel().getBlue(tintedSprite.getRaster().
				getDataElements(i, j, null));
				rx *= r;
				gx *= g;
				bx *= b;
				ax *= a;
				tintedSprite.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx));
			}		
		}
		return tintedSprite;
	}
	
	public static boolean mirror(BufferedImage sprite, int x, int y ,int width, int height, Graphics g) {
		return g.drawImage(sprite, x+width, y, -width, height, null);
	}
	
	public static boolean flip(BufferedImage sprite, int x, int y ,int width, int height, Graphics g) {
		return g.drawImage(sprite, x+width, y, -width, height, null);
	}

	public static void drawStringOutline(String str, int x, int y, Color c_text, Color c_outline, Graphics g) {
		g.setColor(c_outline);		
		g.drawString(str, x+1, y-1);
		g.drawString(str, x+1, y);
		g.drawString(str, x+1, y+1);
		g.drawString(str, x, y+1);
		g.drawString(str, x-1, y+1);
		g.drawString(str, x-1, y);
		g.drawString(str, x-1, y-1);
		g.drawString(str, x, y-1);
		
		g.setColor(c_text);
		g.drawString(str, x, y);
		
	}

}