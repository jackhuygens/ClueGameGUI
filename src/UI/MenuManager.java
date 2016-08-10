package UI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class MenuManager {
	
	private JFrame frame;
	
	public MenuManager(JFrame frame){
		
		this.frame = frame;
		
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		fileMenu.add(new JMenuItem("Quit"));
		helpMenu.add(new JMenuItem("About"));
		
		bar.add(fileMenu);
		bar.add(helpMenu);
		
		frame.add(bar, BorderLayout.NORTH);
	}
}
