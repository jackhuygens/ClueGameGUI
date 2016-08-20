package UI;

import ClueGame.*;

public class BoardController {

	private BoardView view;
	private Board model;
	
	public BoardController(BoardView view, Board model){
		this.model = model;
		this.view = view;
	}
	
	public BoardView getView(){
		return view;
	}
	
	public void update(){
		
	}
}
