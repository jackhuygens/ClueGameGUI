package UI;

import ClueGame.InputManager;

/**
 * 
 * handles all communication between the viewer and model.
 *  
 */
public class ChoiceController {

	private ChoiceView view;
	private InputManager model;
	
	public ChoiceController(ChoiceView view, InputManager model){
		this.model = model;
		this.view = view;
	}
	
	public void setModel(InputManager model){
		this.model = model;
	}
	
	public void setChoices(String[] choices, String status){
		view.setChoices(choices, status);
	}
	
	public void setCurrentPlayer(String playerName){
		view.setCurrentPlayer(playerName);
	}
	
	public void selectChoice(int no){
		model.activateChoice(no);
	}
}
