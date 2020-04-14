package com.engine.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.engine.main.Loop;

public class GAME_OVER {
	
	public int pressEnterFrames = 0; 
	public boolean pressEnterVisible = true;
	
	public void pressEnterAnimation(int timer) {
		pressEnterFrames++;
		if(pressEnterFrames >= timer) {
			pressEnterFrames = 0;
			if(pressEnterVisible) {
				pressEnterVisible = false;
			}else {
				pressEnterVisible = true;
			}
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		pressEnterAnimation(30);
		
		
		//Game_Over BKG
		g2.setColor(new Color(0, 0, 0, 128));
		g2.fillRect(0, 0, Loop.WIDTH, Loop.HEIGHT);
		
		//Normal Text
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD | Font.CENTER_BASELINE, 150));
		g.drawString("GAME OVER", (Loop.WIDTH/2) - (g.getFontMetrics().stringWidth("GAME OVER")/2), (Loop.HEIGHT/2) - 75);
		
		if(pressEnterVisible) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD | Font.CENTER_BASELINE, 75));
			g.drawString("PRESS ENTER TO RESTART", (Loop.WIDTH/2) - (g.getFontMetrics().stringWidth("PRESS ENTER TO RESTART")/2), Loop.HEIGHT - 150);
		}
		
	}

}
