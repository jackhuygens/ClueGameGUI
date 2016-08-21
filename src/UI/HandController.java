package UI;

import java.util.ArrayList;

import ClueGame.*;

public class HandController {
	
	private HandView view;
	private Player model;
	
	public HandController(HandView view, Player model){
		this.model = model;
		this.view = view;
	}	
	
	public void setHand(ArrayList<Clue> hand) {
		view.setHand(hand);
	}
}
