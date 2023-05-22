package Obj;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import BALL.GamePanel;

public class Enemy extends obj {
   GamePanel gp;
   public int actioncounter;
   public Boolean flag;		// true 인경우 speed (+)
   
   public Enemy (GamePanel gp,String iconname,int X,int Y) {
      this.gp = gp;
      actioncounter = 0;
      flag=true;
      setDefaultValue(X,Y);
      getEnemyImage(iconname);
   }
   
   public void setDefaultValue(int X,int Y) {
      x = X*48;
      y = Y*48;
      speed = 10;   
   }
   
   public void getEnemyImage(String iconname) {
	   String imageloc = "/player/" + iconname + ".png";
		try{
			image = ImageIO.read(getClass().getResourceAsStream(imageloc));
		}catch(IOException e) {
			e.printStackTrace();
		}
   }
   
   public void Xupdate() {
      
      if(x<0) {
    	  x=0;
    	  this.flag=true;
      }
      
      else if(x>28*48) {
    	  x=28*48;
    	  this.flag=false;
      }
      
      //while(x>=0 && x<=28*32) {
      else {
	      if(this.flag==true)
			  x = x+speed;
		  else
			  x = x-speed;
      }
      //}
     
   }
   
   public void Yupdate() {
	      
	      if(y<0) {
	    	  y=0;
	    	  this.flag=true;
	      }
	      
	      else if(y>16*48) {
	    	  y=16*48;
	    	  this.flag=false;
	      }
	      
	      //while(x>=0 && x<=28*32) {
	      else {
		      if(this.flag==true)
				  y = y+speed;
			  else
				  y = y-speed;
	      }
	      //}
	     
	   }
   
   public void draw(Graphics2D g2) {
      BufferedImage im = image;
      g2.drawImage(im, x, y, gp.tileSize*2/3, gp.tileSize*2/3, null);
      
   }
 
}