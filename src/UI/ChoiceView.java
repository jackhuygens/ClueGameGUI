package UI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChoiceView {
	
	private JPanel panel, parent;
	
	public ChoiceView(JPanel parent){
		
		this.parent = parent;
		panel = new JPanel();
		panel.add(new JLabel("Choice:"));
		panel.setBackground(Color.GRAY);
		panel.setPreferredSize(new Dimension(120, 150));
		parent.add(panel);
		
	}
	
	public void update() {
		
    }
}
