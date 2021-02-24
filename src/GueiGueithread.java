import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GueiGueithread implements Runnable{

	//圖片位置
	private int xpos;
	private int ypos;
	
	//方向
	private int incX;
	
	//圖片大小
	private int h;
	private int w;
	
	//邊框大小
	
	private Graphics g;
	private int sleepTime;
	private SecureRandom generator = new SecureRandom();
	private Random ran = new Random();
	private int chdir; 
	private int ggSpeedx = (int) (Math.random()*2)+1;
	Image gg1[] = {new ImageIcon("src/turtle.png").getImage(),new ImageIcon("src/turtle2.png").getImage()};

	GueiGuei gg;
	
	public GueiGueithread(GueiGuei gg , int x, int y) {
       this.gg = gg;
       this.xpos = x;
       this.ypos = y;
	}

	public void run() {
		
		sleepTime = (int) (Math.random()*25)+16;
		int r = ran.nextInt(2);
		while(true) {
			
			//烏龜往下掉
			if (ypos != 550) {
				ypos++;
				gg.setY(ypos);
			}			
			else {
				
			//烏龜往右走
			  if (gg.gg == gg1[0]) {
					if (xpos >= 950) {
						   ggSpeedx = -ggSpeedx;
						   gg.gg = gg1[1];
							}
					else if (xpos <= 0) {
						   ggSpeedx = -ggSpeedx;
						   gg.gg = gg1[0];
					   }
					moveR();}
			  
			//烏龜往左走
			  else if (gg.gg == gg1[1])	{	

					if (xpos >= 950) {
						   ggSpeedx = -ggSpeedx;
						   gg.gg = gg1[1];
							}
					else if (xpos <= 0) {
						   ggSpeedx = -ggSpeedx;
						   gg.gg = gg1[0];
					   }
					moveL();
			  }
				
			}
			
		try {
			Thread.sleep(sleepTime);
		}
		catch(InterruptedException exception) {			
			exception.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	}
	
	public void moveR() {
		xpos+=ggSpeedx;
		gg.setX(xpos);

	}
	public void moveL() {
		xpos-=ggSpeedx;
		gg.setX(xpos);

	}

	
	}
	
	


