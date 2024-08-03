package game;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	int x;
	int y;
	int width;
	int height;

	public Player(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}




	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);

	}

	void up() {
		y-=1;
	}

	void down() {
		y+=1;
	}

	void left() {
		x-=1;
	}

	void right() {
		x+=1;
	}


}
