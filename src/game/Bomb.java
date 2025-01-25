package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bomb extends GameObject{
	
	Image bomb;
	int collectCurrent;
	int collectNum = 1;
	BufferedImage[] Exploded;
	boolean isExploded = false;

	public Bomb (int x, int y, int width, int height){
		super(x,y,width,height);
		Exploded = new BufferedImage[16];
		collectCurrent = 0;
		bomb();
		explode();
	}
	
	void draw(Graphics g) {
		if(!isExploded) {
			g.drawImage(bomb, x, y, width, height, null);
		}
		else if(isExploded == true){
			g.drawImage(Exploded[collectCurrent], x, y, width, height, null);
			collectCurrent +=1;
			
		}
		
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
	
	void explode() {
		
		for (int i = 1; i < 17; i++) {

			try {
				Exploded[i - 1] = ImageIO.read(new File("src/Pixel Explosions Free Pack/PNGs/biggerboom/biggerboom" + collectNum + ".png"));

			} catch (Exception e) {

				e.printStackTrace();
			}

			if (i % 4 == 0) {
				collectNum += 1;
			}
		}
	}
}
