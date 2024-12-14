package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Fruit extends GameObject{

	Image fruit;
	Random ran = new Random();
	int num;



	public Fruit (int x, int y, int width, int height){
		super(x,y,width,height);
		fruit();

	}

	void draw(Graphics g) {
		g.drawImage(fruit, x, y, width, height, null);
//		g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}

	void update() {
		super.update();
	}	
	
	void fruit() {
		num = ran.nextInt(2);
		
		if(num == 0) {
			try {
				fruit = ImageIO.read(new File("src/Pixel Adventure 1/Items/Fruits/apple/a2.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else if (num == 1) {
			try {
				fruit = ImageIO.read(new File("src/Pixel Adventure 1/Items/Fruits/strawberry/s.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
