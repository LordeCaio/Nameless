package com.engine.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.engine.world.Camera;

public class Loop extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	//<THREAD CREATION>//
	private boolean isRunning;
	private Thread thread;
	public static int frames;
	//<THREAD CREATION>//	
	
	//<FRAME CREATION>//
	public static JFrame frame;
	public static int WIDTH = 1280;
	public static int HEIGHT = 720;
	
	public static BufferedImage layer;	
	//<FRAME CREATION>//
	
	//ICON CHANGE//
	public static Image gameIcon;
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	//ICON CHANGE//
	
	public static Game game;	
	
	public Loop() {
		initFrame();
		game = new Game();
		addKeyListener(Game.input);

	}
	
	public void initFrame() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		frame = new JFrame("ENGINE");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();		
		try {
			gameIcon = ImageIO.read(getClass().getResource("/icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	
		frame.setIconImage(gameIcon);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);		
	}
	
	public void update() {
		game.update();
	}
	
	public void render() {
		//<BUFFERSTRATEGY INITIALIZATION>//
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		//<BUFFERSTRATEGY INITIALIZATION>//
		Graphics g = layer.getGraphics();		
		g.setColor(Color.PINK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//<IN IMAGE DRAW>//
		
		game.inRender(g);
		
		//<IN IMAGE DRAW>//
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*Camera.wZoom, HEIGHT*Camera.hZoom, null);		
		//<OUT IMAGE DRAW>//
		
		game.outRender(g);

		//<OUT IMAGE DRAW>//
		bs.show();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();		
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				update();
				render();
				frames++;
				delta--;
			}			
			if(System.currentTimeMillis() - timer >= 1000) {
				Debug.FPS = frames;
				//System.out.println("FPS: "+frames);
				frames = 0;
				timer += 1000;
			}			
		}		
		stop();		
	}

}
