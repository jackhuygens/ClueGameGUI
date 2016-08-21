package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import ClueGame.Character;
import ClueGame.Player;

public class MenuManager {
	
	private UIManager main;
	private JFrame frame;
	private ArrayList<JPanel> playerSettings;
	
	public MenuManager(UIManager main, JFrame frame){
		
		this.main = main;
		this.frame = frame;
		
		JMenuBar bar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		JMenu helpMenu = new JMenu("Help");
		fileMenu.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem quit = new JMenuItem("Quit");
		JMenuItem about = new JMenuItem("About");
				
	    quit.setAccelerator(KeyStroke.getKeyStroke("control Q"));
	    about.setAccelerator(KeyStroke.getKeyStroke("control H"));
	    
	    quit.setToolTipText("Quit Cluedo");
	    about.setToolTipText("View more information about Cluedo");
		
		about.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  JOptionPane.showMessageDialog(frame, "Created by Jack Huygens and Alex Ruston (2016)");
			  }});
		
		quit.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  int answer = JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
				  if (answer == JOptionPane.YES_OPTION)
					  System.exit(0);
			  }});
		
		
		fileMenu.add(quit);
		helpMenu.add(about);
		
		bar.add(fileMenu);
		bar.add(helpMenu);
		
		frame.add(bar, BorderLayout.NORTH);
	}
	
	public void setupGame(){
		choosePlayerCount();
	}
	
	public void choosePlayerInfo(int count){
		
		main.main.setupGame(count);
		
		JFrame newGameFrame = new JFrame("New Game");
		newGameFrame.setSize(470,count * 100 + 80);	
		newGameFrame.setVisible(true); 
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(218, 230, 241));
		newGameFrame.add(mainPanel);
		
		playerSettings = new ArrayList<JPanel>();
		for(int i = 1; i <= count; i++){
			JPanel panel = new JPanel();
			playerSettings.add(panel);
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			panel.setBackground(new Color(236, 242, 248));
			panel.setPreferredSize(new Dimension(450, 80));
			mainPanel.add(panel);
		}
		refreshAllPlayerPanels();
		
		JButton b = new JButton("Done");
		b.setPreferredSize(new Dimension(140, 24));
		b.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			  newGameFrame.setVisible(false); 
			  main.main.startGame(); 
		  }});
		mainPanel.add(b);
		
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
	
	public void refreshAllPlayerPanels(){
		for(int i = 0; i < playerSettings.size(); i++){
			refreshPlayerPanel(i);
		}
		
	}
		
	public void refreshPlayerPanel(int index){
		
		Player[] players = main.main.getPlayers();
		
		JPanel panel = playerSettings.get(index);
		panel.removeAll();
		
		final JTextField name = new JTextField(players[index].getName());
		name.setPreferredSize(new Dimension(100, 24));
		name.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        players[index].setName(name.getText());
		      }
		    });
		panel.add(name);
		
		ArrayList<JRadioButton> charNames = new ArrayList<JRadioButton>();
		ArrayList<Boolean> charNamesChosen = new ArrayList<Boolean>();
		ButtonGroup group = new ButtonGroup();
				
	
		for(int i = 0; i < 6; i++){
			
			boolean chosen = false;
			for(int a = 0; a < players.length; a++){
				int val = players[a].getCharacter().getName().ordinal();
				if(val == i)
					chosen = true;
			}
			charNamesChosen.add(chosen);
		}
		
		for(int i = 0; i < 6; i++){
			final int tempI = i;
			boolean active = players[index].getCharacter().getName().ordinal() == i;
			JRadioButton character = new JRadioButton(Character.CharName.values()[i].name(), active);
			character.setEnabled(!charNamesChosen.get(i));
			charNames.add(character);
			group.add(character);
			character.addItemListener(new ItemListener() {
		         public void itemStateChanged(ItemEvent e) {     
			         if(e.getStateChange() == 1){
			        	 players[index].setCharacter(new Character(Character.CharName.values()[tempI]));
			        	 panel.revalidate();
			        	 panel.repaint();
			        	 refreshAllPlayerPanels();
			         }
		         }
		      });
		}

		for(int i = 0; i < 6; i++){
			panel.add(charNames.get(i));
		}
	}
}
