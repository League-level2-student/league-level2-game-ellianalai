package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
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
	public void actionPerformed(ActionEvent arg0) {
		repaint();		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		drawPlayer(g);
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			System.out.println("up");
			player.up();
			
		}
		
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			player.down();
		}
		
		else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			player.left();
		}
		
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			player.right();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
