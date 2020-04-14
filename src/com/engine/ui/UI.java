package com.engine.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.engine.data.Data;
import com.engine.data.Functions;
import com.engine.graphics.Sprite;

public class UI {
	
	//Life Bar Variables
	public int playerLifeW = 200, playerLifeH = 25;
	public double playerLifeNewW = playerLifeW-4;
	
	public int LifeR = 0, LifeG = 0;
	
	//Ammo Bar Variables
	public int playerAmmoW = 150, playerAmmoH = 25;
	public double playerAmmoNewW = playerAmmoW-4;
	
	
	public void playerLife(Graphics g) {		
		//Smooth FX for Life Bar
		playerLifeNewW = Functions.lerp((float)playerLifeNewW, (float)((playerLifeW-4) + (Data.Player.health - Data.Player.maxHealth) * ((playerLifeW-4)/Data.Player.maxHealth)), (float).1);	
		playerLifeNewW = Functions.clamp(playerLifeNewW, 0, 196);
		
		//Dynamic Color Manager
		LifeR =  (int) ((256/Data.Player.maxHealth) * (Data.Player.maxHealth - Data.Player.health));
		LifeG = (int) ((256/Data.Player.maxHealth) * (Data.Player.health));
		LifeR = Functions.clamp(LifeR, 0, 255);
		LifeG = Functions.clamp(LifeG, 64, 255);
		Color lifeBar = new Color(LifeR, LifeG, 0);
		
		//Draws the Life Bar Frame & Background
		g.setColor(Color.BLACK);
		g.fillRect(15, 15,	playerLifeW, playerLifeH);				
		g.setColor(Color.DARK_GRAY);
		g.fillRect(17, 17,	playerLifeW - 4, playerLifeH - 4);
		
		//Draws a Dynamic Colorful Life Bar		
		g.setColor(lifeBar.brighter());
		g.fillRect(17, 17,	(int)(playerLifeNewW+0.1), playerLifeH - 4);		
		
		//Draws LifeBar count
		String lifeCount = Data.Player.health+" / "+Data.Player.maxHealth;
		int lifeCountX = (int) (g.getFontMetrics().stringWidth(lifeCount)/1.2) ;
		int lifeTextX = 15+(playerLifeW/2);
		int lifeTextY = 17 + 20 - 2;
		
		g.setFont(new Font("Arial", Font.BOLD | Font.CENTER_BASELINE, 20));
		Sprite.drawStringOutline(lifeCount, lifeTextX - (lifeCountX), lifeTextY, Color.WHITE, Color.BLACK, g);

	}
	
	public void playerAmmo(Graphics g) {
		//Smooth FX for Ammo Bar		
		playerAmmoNewW = Functions.lerp((float)playerAmmoNewW, (float)((playerAmmoW-4) + ((double)Data.Player.ammo - (double)Data.Player.maxAmmo) * ((playerAmmoW-4)/(double)Data.Player.maxAmmo)), (float).1);
		playerAmmoNewW = Functions.clamp(playerAmmoNewW, 0, 146);
		
		//Draws the Life Bar Frame & Background
		g.setColor(Color.BLACK);
		g.fillRect(15, 45,	playerAmmoW, playerAmmoH);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(17, 47,	playerAmmoW - 4, playerAmmoH - 4);	
	
		//Draws a Dynamic Colorful Life Bar
		g.setColor(Color.YELLOW);
		g.fillRect(17, 47, (int)(playerAmmoNewW+0.1), playerAmmoH - 4);
		
		//Draws AmmoBar Count
		String ammoCount = Data.Player.ammo+" / "+Data.Player.maxAmmo;		
		int ammoCountW = g.getFontMetrics().stringWidth(ammoCount)/2;		
		int ammoTextX = 15+(playerAmmoW/2);
		int ammoTextY = 47 + 20 - 2;	
		
		g.setFont(new Font("Arial", Font.BOLD | Font.CENTER_BASELINE , 20));
		Sprite.drawStringOutline(ammoCount, ammoTextX - (ammoCountW), ammoTextY, Color.WHITE, Color.BLACK, g);			

	}
	
	public void render(Graphics g) {
		playerLife(g);
		playerAmmo(g);
	}	


}
