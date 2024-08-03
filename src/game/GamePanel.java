package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{
	Player player;
	Timer timer;
	
	GamePanel(){
		player = new Player(200,200,100,100);
		timer = new Timer(1000/60, this);
		timer.start();
		
	}
	public void drawPlayer(Graphics g) {
		player.draw(g);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		drawPlayer(g);
		
	}
	
	
}
