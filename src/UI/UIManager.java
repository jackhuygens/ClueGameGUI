package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import ClueGame.*;

public class UIManager {
	
	public ClueGame main;
	private JFrame frame;
	
	private MenuManager menu;
	private HandController handController;
	private BoardController boardController;
	private ChoiceController ChoiceController;
	private DiceController DiceController;
	
	public UIManager( ClueGame main){
		
		this.main = main;
		
		// create frame
		frame = new JFrame("Cludo");
		frame.setSize(600,600);
		frame.setLayout(new BorderLayout());
		frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent windowEvent){ System.exit(0); }});    
		
		// create menus
		menu = new MenuManager(this, frame);
		
		// create board panel 
		boardController =  new BoardController(new BoardView(frame), main.board);
		
		// create side bar panel
		JPanel handPanel = new JPanel();
		handPanel.setBackground(new Color(218, 230, 241));
		handPanel.setPreferredSize(new Dimension(150, 100));
		frame.add(handPanel, BorderLayout.WEST);
		
		// create all other components, and store then in the side bar
		
		// start game button:
		JButton b = new JButton("New Game");
		b.setPreferredSize(new Dimension(140, 24));
		b.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			menu.setupGame();  
		  }});
		handPanel.add(b);
		
		// viewers that don't need a controller reference:
		handController =  new HandController(new HandView(handPanel), main.activePlayer);
		DiceController =  new DiceController(new DiceView(handPanel), main.input);
		
		// viewers that DO need a controller reference:
		ChoiceView cv = new ChoiceView(handPanel);
		ChoiceController =  new ChoiceController(cv, main.input);
		cv.setController(ChoiceController);
		
		frame.setVisible(true); 
	}

}
