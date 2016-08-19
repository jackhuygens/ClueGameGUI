package UI;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import ClueGame.ClueGame;
import ClueGame.Player;


public class BoardView extends JPanel{
	
	ClueGame game;
	
	
	public BoardView(ClueGame game){
		this.game = game;
	}
	
	public void paint(Graphics g){
		if (game.players == null){return;}
		g.setColor(Color.cyan);
		g.fillRect(0, 0, 500, 500);
		
		g.setColor(Color.red);
		for (Player p : game.players){
			g.fillOval(p.getPosition().col * 20, p.getPosition().row * 20, 20, 20);
		}
	}
	
	
}