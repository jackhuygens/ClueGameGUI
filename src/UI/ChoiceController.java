package UI;

import ClueGame.InputManager;

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
		//FIXME this
		model.activateChoice(no);
	}
}
