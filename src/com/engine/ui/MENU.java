package com.engine.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;

import com.engine.data.Data;
import com.engine.data.Save;
import com.engine.main.Loop;

public class MENU {
	
	public String[] options = {"New_Game", "Load_Game", "Exit"};
	public int cur_option = 0;	
	public int maxOptions = options.length - 1;
	
	public int cursorFrames = 0;
	public boolean cursorAnim = false;
	
	public boolean menuUp, menuDown, menuEnter;
	
	public boolean fileExists = false;
	
	public void update() {
		File file = new File("save.txt");
		if(file.exists()) {
			fileExists = true;
		}else {
			fileExists = false;
		}
		
		
		cursorAnimation(15);
		if(menuUp) { //Menu Cursor Up Movement
			menuUp = false;
			cur_option--;
			if(cur_option < 0) {
				cur_option = maxOptions;
			}
		}
		
		if(menuDown) { //Menu Cursor Down Movement
			menuDown = false;
			cur_option++;
			if(cur_option > maxOptions) {
				cur_option = 0;
			}
		}
		
		if(menuEnter) { //Menu Cursor Confirmation3
			menuEnter = false;
			if(options[cur_option] == "New_Game") {
				if(Data.CUR_STATE == Data.GAME_STATE[0]) {
					Data.CUR_LEVEL = 1;
				}
				Data.CUR_STATE = Data.GAME_STATE[2];
				
			}
			else if(options[cur_option] == "Load_Game") {
				file = new File("save.txt");
				if(file.exists()) {				
					String loader = Save.loadFile(0);
					Save.applyValues(loader);
					System.out.println(Data.CUR_LEVEL);
				}
			}
			else if(options[cur_option] == "Exit") {
				System.exit(1);
			}
		}
		
	}
	
	public void cursorAnimation(int timer) {
		cursorFrames++;
		if(cursorFrames >= timer) {
			cursorFrames = 0;
			cursorAnim = !cursorAnim;
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		//Draw the Menu BKG
		g2.setColor(new Color(0, 0, 0, 200));
		g2.fillRect(0, 0, Loop.WIDTH, Loop.HEIGHT);
		
		//Draw Game Title
		
		g.setFont(new Font("Arial", Font.BOLD, 100));
		if(Data.CUR_STATE == Data.GAME_STATE[0]) {
			g.setColor(Color.WHITE);
			g.drawString("NAMELESS", (Loop.WIDTH/2) - g.getFontMetrics().stringWidth("NAMELESS")/2, 150);
		}
		else if(Data.CUR_STATE == Data.GAME_STATE[1]) {
			g.setColor(Color.RED);
			g.drawString("PAUSE", (Loop.WIDTH/2) - g.getFontMetrics().stringWidth("PAUSE")/2, 150);
		}
		//Draw Menu Options
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 50));		
		
		if(Data.CUR_STATE == Data.GAME_STATE[0]) {
			if(options[cur_option] == "New_Game") {
				g.setColor(Color.WHITE);
				if(cursorAnim) {
					g.drawString("> New Game <", (Loop.WIDTH/2) - g.getFontMetrics().stringWidth("> New Game <")/2, 325);
				}else {
					g.drawString(">New Game<", (Loop.WIDTH/2) - g.getFontMetrics().stringWidth(">New Game<")/2, 325);
				}
			}else {
				g.drawString("New Game", (Loop.WIDTH/2) - g.getFontMetrics().stringWidth("New Game")/2, 325);
			}
		}
		else if(Data.CUR_STATE == Data.GAME_STATE[1]) {
			if(options[cur_option] == "New_Game") {
				g.setColor(Color.WHITE);
				if(cursorAnim) {
					g.drawString("> Resume Game <", (Loop.WIDTH/2) - g.getFontMetrics().stringWidth("> Resume Game <")/2, 325);
				}else {
					g.drawString(">Resume Game<", (Loop.WIDTH/2) - g.getFontMetrics().stringWidth(">Resume Game<")/2, 325);
				}
			}else {
				g.drawString("Resume Game", (Loop.WIDTH/2) - g.getFontMetrics().stringWidth("Resume Game")/2, 325);
			}
		}
		
		if(options[cur_option] == "Load_Game") {
			g.setColor(Color.WHITE);
			if(cursorAnim) {
				g.drawString("> Load Game <", (Loop.WIDTH/2) - g.getFontMetrics().stringWidth("> Load Game <")/2, 400);
			}else {
				g.drawString(">Load Game<", (Loop.WIDTH/2) - g.getFontMetrics().stringWidth(">Load Game<")/2, 400);
			}
		}else {
			g.drawString("Load Game", (Loop.WIDTH/2) - g.getFontMetrics().stringWidth("Load Game")/2, 400);
		}
		
		if(options[cur_option] == "Exit") {
			g.setColor(Color.WHITE);
			if(cursorAnim) {
				g.drawString("> Exit <", (Loop.WIDTH/2) - g.getFontMetrics().stringWidth("> Exit <")/2, 475);
			}else {
				g.drawString(">Exit<", (Loop.WIDTH/2) - g.getFontMetrics().stringWidth(">Exit<")/2, 475);
			}
		}else {
			g.drawString("Exit", (Loop.WIDTH/2) - g.getFontMetrics().stringWidth("Exit")/2, 475);
		}
		
	}
}
