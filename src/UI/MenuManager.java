package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;

public class MenuManager {
	
	private JFrame frame;
	private ArrayList<JPanel> playerSettings;
	
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
	
	public void setupGame(){
		
		JFrame newGameFrame = new JFrame("New Game");
		newGameFrame.setSize(400,400);
		//frame.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(218, 230, 241));
		panel.setPreferredSize(new Dimension(150, 100));
		newGameFrame.add(panel);

		newGameFrame.setVisible(true); 
		
		choosePlayerCount();
	}
	
	public void choosePlayerCount(){
		
		JFrame playerCountFrame = new JFrame("Choose players");
		//playerCountFrame.setLayout(new GridLayout(1,2));
		playerCountFrame.setSize(200,100);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(218, 230, 241));
		panel.setPreferredSize(new Dimension(190, 35));
		playerCountFrame.add(panel);
		
		panel.add(new JLabel("      How many players?      "));
		
		final DefaultComboBoxModel<String> players = new DefaultComboBoxModel<String>();
		for(int i = 1; i <= 6; i++)
			players.addElement("" + i);
	    final JComboBox<String> playersCombo = new JComboBox<String>(players);    
	    playersCombo.setSelectedIndex(0);
	    panel.add(playersCombo);
		
		JButton b = new JButton("Ok");
		//b.setPreferredSize(new Dimension(45, 24));
		b.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			//FIXME  this
			 //controller >> rollDice, maybe?   
		  }});
		panel.add(b);
		
		playerCountFrame.setVisible(true); 
	}
	
	public void refreshPlayerSettings(){
		
	}
}
