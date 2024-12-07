package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bomb extends GameObject{
	
	Image bomb;

	public Bomb (int x, int y, int width, int height){
		super(x,y,width,height);
		bomb();
	}
	
	void draw(Graphics g) {
		g.drawImage(bomb, x, y, width, height, null);
	}
	
	void update() {
		super.update();
	}
	
	void bomb() {
		try {
			bomb = ImageIO.read(new File("src/Pixel Adventure 1/Items/bomb.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
