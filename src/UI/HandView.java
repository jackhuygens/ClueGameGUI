package UI;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import ClueGame.Clue;

@SuppressWarnings("serial")
public class HandView {
	
	private JFrame frame;
	private JPanel panel;
	
	protected ArrayList<Clue> hand;
		
	public HandView(JFrame frame){
		
		this.frame = frame;
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(new JLabel("Cards:"));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setPreferredSize(new Dimension(100, 120));
		frame.add(panel, BorderLayout.SOUTH);
		
		
		
	}
	
	public void update() {
		
    }
}
