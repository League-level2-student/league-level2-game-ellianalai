package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Apple extends GameObject{
	
	Image apple;
	Image strawberry;

	public Apple (int x, int y, int width, int height){
		super(x,y,width,height);
		apple();

	}

	void draw(Graphics g) {
		g.drawImage(apple, x, y, width, height, null);
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


}
