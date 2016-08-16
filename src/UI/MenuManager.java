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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

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
		choosePlayerCount();
	}
	
	public void choosePlayerInfo(int count){
		
		JFrame newGameFrame = new JFrame("New Game");
		newGameFrame.setSize(400,count * 100 + 30);	
		newGameFrame.setVisible(true); 
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(218, 230, 241));
		newGameFrame.add(mainPanel);
		
		//frame.setLayout(new GridLayout(1, count));
		
		for(int i = 1; i <= count; i++){
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			panel.setBackground(new Color(236, 242, 248));
			panel.setPreferredSize(new Dimension(390, 80));
			mainPanel.add(panel);
		}

		newGameFrame.setVisible(true); 
	}
	
	public void choosePlayerCount(){
		
		JFrame playerCountFrame = new JFrame("Choose players");
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
		b.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			  choosePlayerInfo(playersCombo.getSelectedIndex()+1);
			  playerCountFrame.setVisible(false); 
		  }});
		panel.add(b);
		
		playerCountFrame.setVisible(true); 
	}
	
	public void refreshPlayerSettings(){
		
	}
}
