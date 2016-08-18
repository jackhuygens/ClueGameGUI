package UI;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import ClueGame.ClueGame;


public class BoardView extends JPanel{
	
	ClueGame game;
	
	
	public BoardView(ClueGame game){
		this.game = game;
	}
	
	public void paint(Graphics g){
		if (game.players == null){return;}
		g.setColor(Color.cyan);
		g.fillRect(0, 0, 500, 500);
	}
	
	
}