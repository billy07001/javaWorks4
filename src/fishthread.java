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

public class fishthread implements Runnable{

	//圖片
	private Image fish1 = new ImageIcon("src/1.png").getImage();
	//圖片位置
	private int xpos;
	private int ypos;
	
	//圖片大小
	private int h;
	private int w;
	
	//邊框大小
	private int maxH;
	private int maxW;
	
	private Graphics g;
	private int sleepTime;
	private SecureRandom generator = new SecureRandom();
	private Random ran = new Random();
	private int chdir; 
	private int FishSpeedx = (int) (Math.random()*2)+1;
	private int FishSpeedy = (int) (Math.random()*2)+1;
	private int chspeed = (int) (Math.random()*21)+20;
	private int timer;
	Fish f;
	
	public fishthread(Fish f , int x, int y) {
       this.f = f;
       this.xpos = x;
       this.ypos = y;
	}

	public void run() {
		
		sleepTime = (int) (Math.random()*25)+16;
		int r = ran.nextInt(4);
		while(true) {
			switch(r) {
			//魚往第一象限游
			case 0 :
				if (xpos <= 950 && xpos >= 0 && ypos >=0 && ypos <= 575) {
				f.switchDir2();
				FirQ();
				}
				
				else {
					r = 2;
					xpos-=FishSpeedx;
					ypos+=FishSpeedy;
					f.setX(xpos);
					f.setY(ypos);
				}
				break;
				
		    //魚往第二象限游
			case 1 :
				if (xpos <= 950 && xpos >= 0 && ypos >= 0 && ypos <= 575) {
					f.switchDir();
					SecQ();
				}
					else{
					r = 3;
					xpos+=FishSpeedx;
					ypos+=FishSpeedy;
					f.setX(xpos);
					f.setY(ypos);
				}
				break;
			//魚往第三象限游	
			case 2 :				
				if (xpos <= 950 && xpos >= 0 && ypos >= 0 && ypos <= 575) {
					f.switchDir();
					ThrQ();
				}
					else{
					r = 0;
					xpos+=FishSpeedx;
					ypos-=FishSpeedy;
					f.setX(xpos);
					f.setY(ypos);
				}
				break;
			
			//魚往第四象限游
			case 3 :				
				if (xpos <= 950 && xpos >= 0 && ypos >= 0 && ypos <= 575) {
					f.switchDir2();
					ForQ();
				}
					else {
					r = 1;
					xpos-=FishSpeedx;
					ypos-=FishSpeedy;
					f.setX(xpos);
					f.setY(ypos);
				}
				break;
			}
			if (timer %chspeed == 0) {
				r = ran.nextInt(4);
				chspeed = (int) (Math.random()*21)+20;
			}
			
			if (xpos >= 950 || xpos <= 0 || ypos <= 0 || ypos >= 575) {
				r = ran.nextInt(4);
			}
			timer++;
		try {
			Thread.sleep(sleepTime);
		}
		catch(InterruptedException exception) {			
			exception.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	}
	
	public void FirQ() {
		xpos+=FishSpeedx;
		ypos-=FishSpeedy;
		f.setX(xpos);
		f.setY(ypos);
		FishSpeedx = (int) (Math.random()*2)+1;
		FishSpeedy = (int) (Math.random()*2)+1;
	}
	public void SecQ() {
		xpos-=FishSpeedx;
		ypos-=FishSpeedy;
		f.setX(xpos);
		f.setY(ypos);
		FishSpeedx = (int) (Math.random()*2)+1;
		FishSpeedy = (int) (Math.random()*2)+1;
	}
	public void ThrQ() {
		xpos-=FishSpeedx;
		ypos+=FishSpeedy;
		f.setX(xpos);
		f.setY(ypos);
		FishSpeedx = (int) (Math.random()*2)+1;
		FishSpeedy = (int) (Math.random()*2)+1;
	}
	public void ForQ() {
		xpos+=FishSpeedx;
		ypos+=FishSpeedy;
		f.setX(xpos);
		f.setY(ypos);
		FishSpeedx = (int) (Math.random()*2)+1;
		FishSpeedy = (int) (Math.random()*2)+1;
	}
	
	}
	
	


