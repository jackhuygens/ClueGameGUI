package UI;

import ClueGame.InputManager;

public class DiceController {

	private DiceView view;
	private InputManager model;
	
	public DiceController(DiceView view, InputManager model){
		this.model = model;
		this.view = view;
	}
	
	public void update(){
		//view.hand = model.getHand();
	}
}
