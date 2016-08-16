package UI;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import ClueGame.Clue;

public class HandView {
	
	private JPanel panel, parent;
	
	protected ArrayList<Clue> hand;
		
	public HandView(JPanel parent){
			
		this.parent = parent;
		panel = new JPanel();
		panel.add(new JLabel("Cards:"));
		panel.setBackground(Color.GRAY);
		panel.setPreferredSize(new Dimension(120, 80));
		parent.add(panel);		
		
	}
	
	public void update() {
		
    }
}
