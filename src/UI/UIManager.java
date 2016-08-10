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
	
	public UIManager( ClueGame main){
		
		this.main = main;
		
		frame = new JFrame("Java SWING Examples");
		frame.setSize(600,600);
		frame.setTitle("Cluedo");
		frame.setLayout(new BorderLayout());

		frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent windowEvent){ System.exit(0); }});    
		
		new MenuManager(frame);
		handController =  new HandController(new HandView(frame), main.activePlayer);
		boardController =  new BoardController(new BoardView(frame), main.board);
		
		frame.setVisible(true); 
	}

}
