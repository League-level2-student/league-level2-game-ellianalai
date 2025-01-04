package game;

import javax.swing.*;

public class Game {

	JFrame frame;
	GamePanel panel;
	
//	public static final int WIDTH = 960;
//	public static final int HEIGHT = 600;
	
	public static final int WIDTH = 512;
	public static int HEIGHT = 920;
	
	public Game() {
		frame = new JFrame();
		panel = new GamePanel();
		
	}
	
	void setup() {
		frame.setSize(WIDTH, HEIGHT);
		frame.add(panel);
		frame.addKeyListener(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main (String [] args) {

		Game g = new Game();
		g.setup();
	}

	
	
}
