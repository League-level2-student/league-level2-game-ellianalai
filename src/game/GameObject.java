package game;

import java.awt.Rectangle;

public class GameObject {
	
	int x;
	int y;
	int width;
	int height;
	int speed = 0;
	Rectangle rect;
	
	public GameObject(int x,int y, int width,int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect = new Rectangle(x,y,width,height);
		
		
	}
	
	void update() {
		rect.setBounds(x, y, width, height);
	}

}
