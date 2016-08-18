package UI;

import ClueGame.InputManager;

public class ChoiceController {

	private ChoiceView view;
	private InputManager model;
	
	public ChoiceController(ChoiceView view, InputManager model){
		this.model = model;
		this.view = view;
	}
	
	public void setChoices(String[] choices, String status){
		view.setChoices(choices, status);
	}
	
	public void selectChoice(int no){
		//FIXME this
		model.activateChoice(no);
	}
}
