package ClueGame;


import java.util.*;
import ClueGame.Location.LocName;
import ClueGame.Weapon.WeaponType;
import UI.ChoiceController;
import ClueGame.Character.CharName;

public class InputManager {

	//Ideally this class should do nothing but process input and instruct main game class.
	
	private ClueGame game;
	public int state = 0;
	private CharName storeChar = CharName.Miss_Scarlet;
	private WeaponType storeWep = WeaponType.CANDLESTICK;
	private LocName storeLoc = LocName.BALL_ROOM;
	/*
	 * States:
	 * 0 = setup
	 * 1 = player in hallway
	 * 2 = player in room
	 * 3 = suggestion part 1
	 * 4 = suggest part 2
	 * 5 = accusal part 1
	 * 6 = accusal part 2
	 * 7 = accusal part 3
	 * 8 = player in hallway and MOVED
	 * 9 = player in room and MOVED
	 * 
	 */
	public ChoiceController cc;
	
	public InputManager(ClueGame game, ChoiceController cc){
		this.game = game;
		this.cc = cc;
		cc.setModel(this);
	}
		
	
	public void sendChoices(){
		if (state == 1){ //State 1 = Between rooms
			cc.setChoices(new String[]{"Move", "End turn"}, "You are in the hallways");
		}else if (state == 8){ //State 8 = Between rooms and cannot move'
			cc.setChoices(new String[]{"End turn"}, "You are in the hallways");
		}else if (state == 2){ //State 2 = In Room'
			cc.setChoices(new String[]{"Move", "Suggest","Accuse", "End turn"}, "You are in the " + game.activePlayer.getCurrentRoom());
		}else if (state == 3){ //State 3 = Suggestion part 1
			cc.setChoices(new String[]{"Mrs Peacock", "Colonel Mustard","Miss Scarlet", "Mrs White", "Professor Plum", "Reverend Green"}, "Who do you Suggest?");
		}else if (state == 4){ //State 4  = Suggestion part 2
			cc.setChoices(new String[]{"Candlestick", "Dagger","Lead Pipe", "Revolver", "Rope", "Spanner"}, "What weapon do you Suggest?");
		}else if (state == 5){ //State 5  = Accusal part 1
			cc.setChoices(new String[]{"Mrs Peacock", "Colonel Mustard","Miss Scarlet", "Mrs White", "Professor Plum", "Reverend Green"}, "Who do you Suggest?");
		}else if (state == 6){ //State 6  = Accusal part 6
			cc.setChoices(new String[]{"Ball Room", "Billiard Room", "Colonel Mustard", "Conservatory", "Dining Room", "Hall", "Kitchen", "Library", "Lounge", "Study"}, "Who do you Suggest?");
		}else if (state == 7){ //State 6  = Accusal part 6
			cc.setChoices(new String[]{"Candlestick", "Dagger","Lead Pipe", "Revolver", "Rope", "Spanner"}, "What weapon do you Suggest?");
		}
		else {
			cc.setChoices(null, null);
		}
	}
	
	public void activateChoice(int i){
		if (state == 1){ //In hallways
			switch (i){
				case 0:
					moveCommand();
					break;
				case 1:
					game.endTurn();
					break;
					
			}
		}else if (state == 8){
			switch (i){
			case 0:
				game.endTurn();
				break;
			}
		}else if (state == 2){ //In room
			switch (i){
			case 0:
				moveCommand();
				break;
			case 1:
				state = 3; //Set to suggestion part 1
				sendChoices();
				break;
			case 2:
				state = 5; //Set to Accusal part 1
				sendChoices();
				break;
			case 3:
				game.endTurn();
				break;
			}	
		}else if (state == 3){ //Suggestion part 1
			String[] chars = new String[]{"Mrs_Peacock", "Colonel_Mustard","Miss_Scarlet", "Mrs_White", "Professor_Plum", "Reverend_Green"};
			for (CharName c : CharName.values()){
				if (c.toString().equals(chars[i])){
					storeChar = c;
					System.out.println("Stored: " + c.toString());
				}
			}
			
			state++;
			sendChoices();
		}else if (state == 4){ //Suggestion part 1
			String[] weps = new String[]{"CANDLESTICK", "DAGGER","LEADPIPE", "REVOLVER", "ROPE", "SPANNER"};
			for (WeaponType w : WeaponType.values()){
				if (w.toString().equals(weps[i])){
					storeWep = w;
					System.out.println("Stored: " + w.toString());

				}
			}
			state = 2;
			game.makeSuggest(game.activePlayer.getCurrentRoom(), new Weapon(storeWep), new Character(storeChar));
			
		}else if (state == 5){ //Accusal part 1
			String[] chars = new String[]{"Mrs_Peacock", "Colonel_Mustard","Miss_Scarlet", "Mrs_White", "Professor_Plum", "Reverend_Green"};
			for (CharName c : CharName.values()){
				if (c.toString().equals(chars[i])){
					storeChar = c;
					System.out.println("Stored: " + c.toString());
				}
			}
			
			state++;
			sendChoices();
		}else if (state == 6){ //Accusal part 2
			String[] weps = new String[]{"BALL_ROOM", "BILLIARD_ROOM", "COLONEL_MUSTARD", "CONSERVATORY", "DINING_ROOM", "HALL", "KITCHEN", "LIBRARY", "LOUNGE", "STUDY"};
			for (LocName w : LocName.values()){
				if (w.toString().equals(weps[i])){
					storeLoc = w;
					System.out.println("Stored: " + w.toString());

				}
			}
			state++;
			sendChoices();
		}else if (state == 7){ //Accusal part 3
			String[] weps = new String[]{"CANDLESTICK", "DAGGER","LEADPIPE", "REVOLVER", "ROPE", "SPANNER"};
			for (WeaponType w : WeaponType.values()){
				if (w.toString().equals(weps[i])){
					storeWep = w;
					System.out.println("Stored: " + w.toString());

				}
			}
			state = 2;
			game.makeAccusal( new Character(storeChar), new Location(storeLoc), new Weapon(storeWep));
		}
	}
		
		
	
	
	
	/**
	 * 
	 * Rolls the dice and begins the move sequence for the player
	 * 
	 */
	private void moveCommand(){
		
		int canMove = game.rollDice();
		System.out.println("You rolled: " + canMove);
		game.UI.boardController.getView().canMove(true);
		
	}

}










