package UI;

import ClueGame.*;

public class DiceController {

	private DiceView view;
	private InputManager model;
	
	public DiceController(DiceView view, InputManager model){
		this.model = model;
		this.view = view;
	}
	
	public void setValue(int val){
		view.setDice(val);
	}
}
