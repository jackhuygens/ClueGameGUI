package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import ClueGame.*;

public class UIManager {
	
	public ClueGame main;
	private JFrame frame;
	
	public MenuManager menu;
	public HandController handController;
	public BoardController boardController;
	public ChoiceController ChoiceController;
	public DiceController DiceController;
	
	public UIManager( ClueGame main){
		
		this.main = main;
		
		// create frame
		frame = new JFrame("Cludo");
		frame.setSize(800,800);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent windowEvent){ 
			  	int answer = JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
			  	if (answer == JOptionPane.YES_OPTION)
			  		System.exit(0);
			  }});    
		
		// create menus
		menu = new MenuManager(this, frame);
		
		// create board panel 
		BoardView view = new BoardView(main, frame);
		view.setBackground(Color.GRAY);
		frame.add(view, BorderLayout.CENTER);
		boardController =  new BoardController(view, main.board);
		
		// create side bar panel
		JPanel handPanel = new JPanel();
		handPanel.setBackground(new Color(218, 230, 241));
		handPanel.setPreferredSize(new Dimension(150, 100));
		frame.add(handPanel, BorderLayout.WEST);
		
		// create all other components, and store then in the side bar
		
		// start game button:
		final JButton b = new JButton("New Game");
		b.setToolTipText("Start a new game of Cluedo");
		b.setPreferredSize(new Dimension(140, 24));
		b.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			  b.setEnabled(false);
			  menu.setupGame();  
		  }});
		handPanel.add(b);
		
		
		DiceController =  new DiceController(new DiceView(handPanel), main.input);
		
		ChoiceView cv = new ChoiceView(handPanel);
		ChoiceController =  new ChoiceController(cv, main.input);
		cv.setController(ChoiceController);
		
		handController =  new HandController(new HandView(handPanel), main.activePlayer);
		
		frame.setVisible(true); 
	}

}
