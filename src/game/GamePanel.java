package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	Player player;
	Timer timer;
	Image blue;
	int x;
	int y;

	GamePanel(){
		player = new Player(200,200,70,70);
		timer = new Timer(1000/60, this);
		timer.start();
		background();

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
		
		for(int i = 0; i<135; i++) {
			x = (i*64)%960;
			y = ((int)(i*64)/960) * 64;
			g.drawImage(blue, x, y, 64, 64, null);
		}
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

	void loadImage(String imageFile) {
		System.out.println(imageFile);
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Image not found");
			}
			needImage = false;
		}
	}
	
	void background() {
		try {
			blue = ImageIO.read(new File ("src/Pixel Adventure 1/Background/Blue.png"));
		}

		catch (Exception e) {

		}
	}


}
