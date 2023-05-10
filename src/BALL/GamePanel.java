package BALL;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Obj.Player;

public class GamePanel extends JPanel implements Runnable {
	
	final int originalTileSize = 16; //16X16 pixel
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //48 pixel
	final int maxScreenCOl = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCOl; //768
	final int screenHeight = tileSize * maxScreenRow; //576 pixels
	
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler(); 
	Thread gameThread;
	Player player = new Player(this, keyH);
	//set default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed=4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void startGameThread() {
		gameThread = new Thread(this); // gamepanel to 
		gameThread.start();
	}
	
	public void run() {
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime()+drawInterval;
		
		while(gameThread!=null) {
			 
			//update the info as character position
			update();
			
			// draw the screen with updated thing
			repaint();
			

			
			try {
				
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime /= 1000000;
				
				if(remainingTime<0) remainingTime = 0;
				
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void update() { //왼쪽 위가 0,0
		player.update();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;
		player.draw(g2);

		g2.dispose();
	}
	
	
	
}
