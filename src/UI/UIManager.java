package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import ClueGame.*;

public class UIManager {
	
	private ClueGame main;
	private JFrame frame;
	
	private HandController handController;
	private BoardController boardController;
	private ChoiceController ChoiceController;
	private DiceController DiceController;
	
	public UIManager( ClueGame main){
		
		this.main = main;
		
		// create frame
		frame = new JFrame("Java SWING Examples");
		frame.setSize(600,600);
		frame.setTitle("Cluedo");
		frame.setLayout(new BorderLayout());
		frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent windowEvent){ System.exit(0); }});    
		
		// create menus
		new MenuManager(frame);
		
		// create board panel 
		boardController =  new BoardController(new BoardView(frame), main.board);
		
		// create side bar panel
		JPanel handPanel = new JPanel();
		handPanel.setBackground(Color.LIGHT_GRAY);
		handPanel.setPreferredSize(new Dimension(150, 100));
		frame.add(handPanel, BorderLayout.WEST);
		
		// create all other components, and store then in the side bar
		handController =  new HandController(new HandView(handPanel), main.activePlayer);
		ChoiceController =  new ChoiceController(new ChoiceView(handPanel), main.input);
		DiceController =  new DiceController(new DiceView(handPanel), main.input);
		
		frame.setVisible(true); 
	}

}
