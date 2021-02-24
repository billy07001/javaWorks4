import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Fish{
	
	//�j�p
	int FishSize = (int) (Math.random()*71)+30;
	
	//���y��
	int x,y;
	
	private Random ran = new Random();
	private int r = (int) (Math.random()*3)+1;
	
	//���Ϥ�
	Image fish1[] = {new ImageIcon("src/1.png").getImage(),new ImageIcon("src/2.png").getImage()};
	Image fish2[] = {new ImageIcon("src/3.png").getImage(),new ImageIcon("src/4.png").getImage()};
	Image fish3[] = {new ImageIcon("src/5.png").getImage(),new ImageIcon("src/6.png").getImage()};
	Image fish = null;
	
	public Fish(int x, int y ){
		this.x=x;
		this.y=y;
		fish = getFish();
	}
	
	public int getFishSize() {
		return FishSize;
	}

	public void setFishSize(int fishSize) {
		FishSize = fishSize;
	}


	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getFish() {
		switch(r) {
		case 1:
			fish = fish1[0];
			return fish;
		case 2:
			fish = fish2[0];
			return fish;
		case 3:
			fish = fish3[0];
			return fish;
		}
		
		return null;
	}
	
	public Image switchDir() {
		if (fish == fish1[0])
			{fish = fish1[1];
			return fish;}
		else if (fish == fish2[0])
			{fish = fish2[1];
		    return fish;}
		else if (fish == fish3[0])
			{fish = fish3[1];
		    return fish;}
		return null;
	}
	
	public Image switchDir2() {
		if (fish == fish1[1])
			{fish = fish1[0];
			return fish;}
		else if (fish == fish2[1])
			{fish = fish2[0];
		    return fish;}
		else if (fish == fish3[1])
			{fish = fish3[0];
		    return fish;}
		return null;
	}
	
	

	
	public void draw(Graphics g) {
		g.drawImage(fish, x-(FishSize/2) , y-(FishSize/2) , FishSize , FishSize , null);
	}
}