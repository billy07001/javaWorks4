import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class GueiGuei{
	
	//大小
	int ggSize = (int) (Math.random()*41)+60;
	//座標
	int x,y;
	
	private Random ran = new Random();
	private int r = (int) (Math.random()*2)+1;
	
	//圖片
	Image gg1[] = {new ImageIcon("src/turtle.png").getImage(),new ImageIcon("src/turtle2.png").getImage()};
	Image gg = null;
	
	public GueiGuei(int x, int y ){
		this.x=x;
		this.y=y;
		gg = getGueiGuei();
	}




	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getGueiGuei() {
		switch(r) {
		case 1:
			gg = gg1[0];
			return gg;
		case 2:
			gg = gg1[1];
			return gg;
		}
		
		return null;
	}
	
	public Image switchDir() {

			gg = gg1[1];
			return gg;

	}
	
	public Image switchDir2() {
			gg = gg1[0];
			return gg;

	}
	
	

	
	public void draw(Graphics g) {
		g.drawImage(gg, x-(ggSize/2) , y-(ggSize/2) , ggSize , ggSize , null);
	}
}
