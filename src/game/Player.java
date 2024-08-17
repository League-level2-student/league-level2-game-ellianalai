package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{

	int move;
	
	public Player(int x, int y, int width, int height) {
		super(x,y,width,height);
		move = 100;
		System.out.println("hi");
	}


	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);

	}

	public void up() {
		y-=move;
		super.update();
	}

	public void down() {
		y+=move;
		super.update();
	}

	public void left() {
		x-=move;
		super.update();
	}

	public void right() {
		x+=move;
		super.update();
	}


}
