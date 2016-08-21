package UI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

import ClueGame.ClueGame;
import ClueGame.Player;


public class BoardView extends JPanel implements MouseListener{
	
	ClueGame game;
	Frame frame;
	public Point pressed = null;
	boolean moving = false;
	
	public BoardView(ClueGame game, Frame frame){
		this.game = game;
		this.frame = frame;
		addMouseListener(this);
	}
	
	/**
	 * 
	 * Sets the boolean value for whether to act on mouse clicks on the board (essentially if the player can
	 * move or not)
	 * 
	 * @param canmove
	 */
	public void canMove(boolean canmove){
		moving = canmove;
	}
	
	/**
	 * 
	 * Paints the board and the players
	 * 
	 * 
	 */
	public void paint(Graphics g){
		final int[][] tiles =  new int[][] {
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
			{4,4,4,4,4,4,4,4,4,4,3,1,1,1,1,3,4,4,4,4,4,4,4,4,4,4},
			{4,1,1,1,1,1,1,4,0,0,0,1,1,1,1,0,0,0,4,1,1,1,1,1,1,4},
			{4,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,4},
			{4,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,4},
			{4,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,0,0,2,1,1,1,1,1,4},
			{4,1,1,1,1,1,1,0,0,2,1,1,1,1,1,1,2,0,0,0,1,1,1,1,4,4},
			{4,4,1,1,1,2,1,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,3,4},
			{4,0,0,0,0,0,0,0,0,1,2,1,1,1,1,2,1,0,0,0,0,0,0,0,4,4},
			{4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,4},
			{4,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1,1,1,1,1,4},
			{4,1,1,1,1,1,1,1,1,0,0,4,4,4,4,4,0,0,0,1,1,1,1,1,1,4},
			{4,1,1,1,1,1,1,1,1,0,0,4,4,4,4,4,0,0,0,1,1,1,1,1,1,4},
			{4,1,1,1,1,1,1,1,2,0,0,4,4,4,4,4,0,0,0,1,1,1,1,2,1,4},
			{4,1,1,1,1,1,1,1,1,0,0,4,4,4,4,4,0,0,0,0,0,0,0,0,4,4},
			{4,1,1,1,1,1,1,1,1,0,0,4,4,4,4,4,0,0,0,1,1,2,1,1,4,4},
			{4,1,1,1,1,1,1,2,1,0,0,4,4,4,4,4,0,0,1,1,1,1,1,1,1,4},
			{4,4,0,0,0,0,0,0,0,0,0,4,4,4,4,4,0,0,2,1,1,1,1,1,1,4},
			{4,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,4},
			{4,4,0,0,0,0,0,0,0,0,1,1,2,2,1,1,0,0,0,1,1,1,1,1,4,4},
			{4,1,1,1,1,1,1,2,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,3,4},
			{4,1,1,1,1,1,1,1,0,0,1,1,1,1,1,2,0,0,0,0,0,0,0,0,4,4},
			{4,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0,2,1,1,1,1,1,1,4},
			{4,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,4},
			{4,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,4},
			{4,1,1,1,1,1,1,4,3,4,4,1,1,1,1,4,4,0,4,1,1,1,1,1,1,4},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4}
		};
		
		if (game.players == null){return;}//Dont paint if game is not set up yet
		super.paintComponent(g);
		
		int x = 0;
		int y = 0;
		int size = 30;
		
		if (frame.getSize().height > frame.getSize().width){size = frame.getSize().height / 35;} else {size = frame.getSize().width / 35;}
			
		
		
		for (int i = 0; i < tiles.length; i++){
			x = 0;
			for (int k = 0; k < tiles[i].length; k++){
				int t = tiles[i][k];
				
				
				switch (t){
					case 4:
						g.setColor(Color.black);
						g.fillRect(x, y, size, size);
						break;
					case 0:
						g.setColor(new Color(114,74,34));
						g.fillRect(x, y, size, size);
						g.setColor(new Color(126,90,54));
						g.drawRect(x, y, size, size);
						break;
					case 1:
						g.setColor(Color.gray);
						g.fillRect(x, y, size, size);
						break;
					case 2:
						g.setColor(Color.lightGray);
						g.fillRect(x, y, size, size);
						break;
				}
				
				//Act on mouse inputs
				if(pressed != null && new Rectangle(x, y, size, size).contains(pressed) && moving){
					g.setColor(Color.black);
					g.fillRect(x + 2, y + 2, size - 4, size - 4);
					game.beginMove((k - 1), (i - 1));
				}
				
				x += size;
			}
			y += size;
		}
		
		//Paint players
		for (Player p : game.players){ //REMEMBER! Added an extra outer layer of tiles, so player coordinates need to be shifted by 1
			
			switch (p.getCharacter().getName()){
				case Miss_Scarlet:
					g.setColor(Color.RED);
					break;
				case Mrs_White:
					g.setColor(Color.WHITE);
					break;
				case Mrs_Peacock:
					g.setColor(Color.blue);
					break;
				case Colonel_Mustard:
					g.setColor(new Color(246,200,7));
					break;
				case The_Reverend_Green:
					g.setColor(Color.green);
					break;
				case Professor_Plum:
					g.setColor(new Color(98,4,197));
					break;
				
			}
			
			g.fillOval((p.getPosition().col + 1) * size + 1, (p.getPosition().row + 1) * size + 1, size - 3  , size - 3 );
			g.setColor(Color.BLACK);
			g.drawOval((p.getPosition().col + 1) * size + 1, (p.getPosition().row + 1) * size + 1, size - 3, size - 3);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		pressed = arg0.getPoint();
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}