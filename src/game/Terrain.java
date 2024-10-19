package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Terrain extends GameObject{
	
	Image grass;

	public Terrain(int x, int y, int width, int height) {
		super(x, y, width, height);
		blocks();
	}
	
	void draw(Graphics g) {
		g.drawImage(grass, x, y, width, height, null);
		g.drawRect(x, y, width, height);
	}
	
	void blocks() {
		try {
			grass = ImageIO.read(new File("src/Pixel Adventure 1/Terrain/Grass.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	void update() {
		collisionBox.setBounds(x, y, width, height/2);
	}

}
