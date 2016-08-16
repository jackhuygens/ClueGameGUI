package UI;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import ClueGame.Clue;

public class DiceView {
	
	private JPanel panel, parent;
		
	public DiceView(JPanel parent){
		
		this.parent = parent;
		panel = new JPanel();
		panel.add(new JLabel("Dice:"));
		panel.setBackground(Color.GRAY);
		panel.setPreferredSize(new Dimension(80, 40));
		parent.add(panel);
		
	}
	
	public void update() {
		
    }

}
