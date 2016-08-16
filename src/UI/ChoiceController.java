package UI;

import ClueGame.InputManager;

public class ChoiceController {

	private ChoiceView view;
	private InputManager model;
	
	public ChoiceController(ChoiceView view, InputManager model){
		this.model = model;
		this.view = view;
	}
	
	public void update(){
		//FIXME  this
		//view.hand = model.getHand();
	}
	
	public void selectChoice(int no){
		//FIXME this
		// inputmanager >> selectChoice()
	}
}
