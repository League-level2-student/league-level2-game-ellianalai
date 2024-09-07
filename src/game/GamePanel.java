package game;

import java.awt.Color;
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
		player = new Player(200,200,70,70);
		timer = new Timer(1000/60, this);
		timer.start();

	}
	public void drawPlayer(Graphics g) {
		player.draw(g);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		player.update();
		repaint();		
	}


	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		drawPlayer(g);

	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_SPACE) {
			player.isJumping = true;
			player.yspeed = -20;

		}

		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			player.isFalling = true;
		}

		else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			player.direction = 1;
			player.isLeft = true;
			player.isWalking = true;
		}

		else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			player.direction = 2;
			player.isLeft = false;
			player.isWalking = true;
		}

	}
	@Override
	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			player.isFalling = false;
		}

		else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			player.isWalking = false;
		}

		else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			player.isWalking = false;
		}




	}


}
