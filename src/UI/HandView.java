package UI;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import ClueGame.Clue;

@SuppressWarnings("serial")
public class HandView extends JPanel{
	
	protected ArrayList<Clue> hand;
		
	public HandView(){
		add(new JLabel("AA"));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D gg = (Graphics2D) g;
		gg.setColor(Color.BLUE);
		
		int x = 0;
		for (Clue c : hand){
	        gg.fill(new Rectangle(x, 0, 10, 10));
	        x += 20;
		}
    }
}
