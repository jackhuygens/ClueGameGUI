package UI;

import ClueGame.*;

/**
 * 
 * handles all communication between the viewer and model.
 *  
 */
public class DiceController {

	private DiceView view;
	private InputManager model;
	int value = 0;
	
	public DiceController(DiceView view, InputManager model){
		this.model = model;
		this.view = view;
	}
	
	public void setValue(int val){
		view.setDice(val);
		value = val;
	}
	
	public int getValue(){
		return value;
	}
}
