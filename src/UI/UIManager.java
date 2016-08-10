package UI;

import java.util.ArrayList;

import ClueGame.*;

public class UIManager {
	
	private ClueGame main;
	private HandController handController;
	private BoardController boardController;
	
	public UIManager( ClueGame main){
		
		this.main = main;
		
		handController =  new HandController(new HandView(), main.activePlayer);
		boardController =  new BoardController(new BoardView(), main.board);
		
	}

}
