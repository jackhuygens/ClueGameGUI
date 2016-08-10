package UI;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


public class BoardView {
	
	private JFrame frame;
	private JPanel panel;
	
	public BoardView(JFrame frame){
		this.frame = frame;
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		frame.add(panel, BorderLayout.CENTER);
	}
	
}