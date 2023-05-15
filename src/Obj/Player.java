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
		x = 200;
		y=250;
		speed = 3;
		direction = "up";
	}
	public void getPlayerImage() {
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/player/성대사랑.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		if(keyH.upPressed == true || keyH.downPressed ==true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed==true) {
				direction = "up";
			}
			else if(keyH.downPressed==true) {
				direction = "down";
			}
			else if (keyH.leftPressed==true) {
				direction = "left";
			}
			else if (keyH.rightPressed==true) {
				direction = "right";
			}
		
			collisionOn = false;
			gP.cDetector.checkTile(this);
		
			if(collisionOn==false) {
				switch(direction){
				case "up":
					y -= speed;
					break;
				case "down":
					y += speed;
					break;
				case "left":
					x -= speed;
					break;
				case "right":
					x += speed;
					break;
				}	
			}
		}
	}
	public void draw(Graphics2D g2) {
		BufferedImage im = image;
		g2.drawImage(im, x, y, gP.tileSize*2/3, gP.tileSize*2/3, null);
		
	}
}
