package UI;

import java.util.ArrayList;

import ClueGame.*;

public class UIManager {
	
	private ClueGame main;
	private HandController handController;
	
	public UIManager( ClueGame main){
		
		this.main = main;
		
		handController =  new HandController(new HandView(), main.activePlayer);
	}

}
