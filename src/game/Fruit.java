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
//		apple();
//		strawberry();

	}

	void draw(Graphics g) {

		
//		g.drawImage(apple, x, y, width, height, null);
//		g.drawImage(strawberry, x, y, width, height, null);
		g.drawImage(fruit, x, y, width, height, null);
	}

	void update() {
		super.update();
	}	

	void fruit() {
		num = ran.nextInt(2);
		
		if(num == 0) {
			System.out.println("hi");
			try {
				fruit = ImageIO.read(new File("src/Pixel Adventure 1/Items/Fruits/apple/a.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else if (num == 1) {
			System.out.println("bye");
			try {
				fruit = ImageIO.read(new File("src/Pixel Adventure 1/Items/Fruits/strawberry/s.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	//	void apple() {
	//		try {
	//			apple = ImageIO.read(new File("src/Pixel Adventure 1/Items/Fruits/apple/a.png"));
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		}
	//	}
	//	
	//	void strawberry() {
	//		try {
	//			strawberry = ImageIO.read(new File("src/Pixel Adventure 1/Items/Fruits/strawberry/s.png"));
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		}
	//
	//	}
}
