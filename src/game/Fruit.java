package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Fruit extends GameObject{

	Image fruit;
	Random ran = new Random();
	int num;
	int collectCurrent;
	int collectNum = 1;
	BufferedImage[] collectedImage;
	boolean fruit_got = false; 
	



	public Fruit (int x, int y, int width, int height){
		super(x,y,width,height);
		fruit();
		collectedImage = new BufferedImage[25];
		collectCurrent = 0;

	}

	void draw(Graphics g) {
		System.out.println(fruit_got);
		if(fruit_got == false) {
			g.drawImage(fruit, x, y, width, height, null);
		}
		else if(fruit_got == true) {
			g.drawImage(collectedImage[collectCurrent], collisionBox.x, collisionBox.y, width, height, null);
		}
		
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
				fruit = ImageIO.read(new File("src/Pixel Adventure 1/Items/Fruits/strawberry/s2.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	void collected() {

		for (int i = 1; i < 26; i++) {

			try {
				collectedImage[i - 1] = ImageIO.read(new File(
						"src/Pixel Adventure 1/Items/Fruits/collected/Collected (" + collectNum + ").png"));

			} catch (Exception e) {

				e.printStackTrace();
			}

			if (i % 5 == 0) {
				collectNum += 1;
			}
		}
	
}

}
