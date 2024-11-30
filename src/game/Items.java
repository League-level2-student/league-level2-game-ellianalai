package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Items extends GameObject{
	
	Image apple;
	Image strawberry;
	Image bomb;

	public Items (int x, int y, int width, int height){
		super(x,y,width,height);
		apple();
		strawberry();
		bomb();
	}
	
	void draw(Graphics g) {
		g.drawImage(apple, x, y, width, height, null);
		g.drawImage(strawberry, x, y, width, height, null);
		g.drawImage(bomb, x, y, width, height, null);
	
	}
	
	void update() {
		super.update();
	}
	
	void apple() {
		try {
			apple = ImageIO.read(new File("src/Pixel Adventure 1/Items/Fruits/apple/a.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void strawberry() {
		try {
			strawberry = ImageIO.read(new File("src/Pixel Adventure 1/Items/Fruits/strawberry/s.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void bomb() {
		try {
			bomb = ImageIO.read(new File("src/Pixel Adventure 1/Items/bomb.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
