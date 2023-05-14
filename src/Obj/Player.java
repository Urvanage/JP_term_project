package Obj;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import BALL.GamePanel;
import BALL.KeyHandler;

public class Player extends obj {
	GamePanel gP;
	KeyHandler keyH;
	
	public Player (GamePanel gP, KeyHandler keyH) {
		this.gP = gP;
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		x = 100;
		y=100;
		speed = 4;
	}
	public void getPlayerImage() {
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/player/성대사랑.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		if(keyH.upPressed==true) {
			y -= speed;
		}
		else if(keyH.downPressed==true) {
			y += speed;
		}
		else if (keyH.leftPressed==true) {
			x -= speed;
		}
		else if (keyH.rightPressed==true) {
			x += speed;
		}
	}
	public void draw(Graphics2D g2) {
		BufferedImage im = image;
		g2.drawImage(im, x, y, gP.tileSize*2/3, gP.tileSize*2/3, null);
		
	}
}
