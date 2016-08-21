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
	 * 
	 * 
	 */
	public ChoiceController cc;
	
	public InputManager(ClueGame game, ChoiceController cc){
		this.game = game;
		this.cc = cc;
		cc.setModel(this);
	}
	
	/**
	 * 
	 * Deals with all a players input for their turn.
	 * 
	 * @param cannotMove Boolean for whether the player is allowed to move.
	 */
	public void processInput(boolean cannotMove){
			
	}
	
	
	public void sendChoices(){
		if (state == 1){ //State 1 = Between rooms
			cc.setChoices(new String[]{"Move", "End turn"}, "You are not in a room");
		}
		else if (state == 2){ //State 2 = In Room
			cc.setChoices(new String[]{"Move", "Suggest","Accuse", "End turn"}, "You are in a room");
		}else if (state == 3){ //State 3 = Suggestion part 1
			cc.setChoices(new String[]{"Mrs Peacock", "Colonel Mustard","Miss Scarlet", "Mrs White", "Professor Plum", "Reverend Green"}, "Who do you Suggest?");
		}else if (state == 4){ //State 4  = Suggestion part 2
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
				makeAccusal();
				break;
			case 3:
				game.endTurn();
				break;
			}	
		}else if (state == 3){ //Suggestion part 1
			String[] chars = new String[]{"Mrs Peacock", "Colonel Mustard","Miss Scarlet", "Mrs White", "Professor Plum", "Reverend Green"};
			for (CharName c : CharName.values()){
				if (c.toString().equals(chars[i])){
					storeChar = c;
				}
			}
			
			state++;
			sendChoices();
		}else if (state == 4){ //Suggestion part 1
			String[] weps = new String[]{"Candlestick", "Dagger","Lead Pipe", "Revolver", "Rope", "Spanner"};
			for (WeaponType w : WeaponType.values()){
				if (w.toString().equals(weps[i])){
					storeWep = w;
				}
			}
			state = 2;
			game.makeSuggest(game.activePlayer.getCurrentRoom(), new Weapon(storeWep), new Character(storeChar));
		}
	}
		
		
	
	
	/**
	 * 
	 * Calls the active player to print the clues in
	 * their hand.
	 * 
	 */
	private void printHand(){
		System.out.println();
		System.out.println("You have the following clues: ");
		game.activePlayer.printHand();
		System.out.println();
	}
	
	/**
	 * 
	 * Takes input for making a suggestion and calls the makeSuggest method
	 * in ClueGameGUI with those inputs.
	 * 
	 * 
	 */
	
	
	/**
	 * 
	 * Takes input for a player making an accusal and calls
	 * the makeAccusal method in ClueGameGUI
	 * 
	 * 
	 */
	private void makeAccusal(){
		Location loc = new Location(LocName.BALL_ROOM); //Defaults for initialization
		Weapon wep = new Weapon(WeaponType.CANDLESTICK); 
		Character cha = new Character(CharName.Mrs_Peacock); 

		
		System.out.println("Which character do you Accuse?");
		int choice = getActionFromList(new String[] {
				"Colonel Mustard",
				"Miss Scarlet",
				"Mrs Peacock",
				"Professor Plum",
				"Mrs White",
				"The Reverend Green"});
		
		switch (choice){
		case 0:
			cha = new Character(CharName.Colonel_Mustard);
			break;
		case 1:
			cha = new Character(CharName.Miss_Scarlet);
			break;
		case 2:
			cha = new Character(CharName.Mrs_Peacock);
			break;
		case 3:
			cha = new Character(CharName.Professor_Plum);
			break;
		case 4:
			cha = new Character(CharName.Mrs_White);
			break;
		case 5:
			cha = new Character(CharName.The_Reverend_Green);
			break;
		}
		
		System.out.println("Which room do you Accuse?");
		choice = getActionFromList(new String[] {
				"Ball Room",
				"Billiard Room",
				"Conservatory",
				"Dining Room",
				"Hall",
				"Kitchen",
				"Library",
				"Lounge",
				"Study"});
		switch (choice){
		case 0:
			loc  = new Location(LocName.BALL_ROOM);
			break;
		case 1:
			loc  = new Location(LocName.BILLIARD_ROOM);
			break;
		case 2:
			loc  = new Location(LocName.CONSERVATORY);
			break;
		case 3:
			loc  = new Location(LocName.DINING_ROOM);
			break;
		case 4:
			loc  = new Location(LocName.HALL);
			break;
		case 5:
			loc  = new Location(LocName.KITCHEN);
			break;
		case 6:
			loc  = new Location(LocName.LIBRARY);
			break;
		case 7:
			loc  = new Location(LocName.LOUNGE);
			break;
		case 8:
			loc  = new Location(LocName.STUDY);
			break;
		
		
		}
		
		System.out.println("Which weapon do you Accuse?");
		choice = getActionFromList(new String[] {
				"Candlestick",
				"Dagger",
				"Lead pipe",
				"Revolver",
				"Rope",
				"Spanner"});
		switch (choice){
		case 0:
			wep = new Weapon(WeaponType.CANDLESTICK);
			break;
		
		case 1:
			wep = new Weapon(WeaponType.DAGGER);
			break;
		case 2:
			wep = new Weapon(WeaponType.LEADPIPE);
			break;
		case 3:
			wep = new Weapon(WeaponType.REVOLVER);
			break;
		case 4:
			wep = new Weapon(WeaponType.ROPE);
			break;
		case 5:
			wep = new Weapon(WeaponType.SPANNER);
			break;
		}
		
		game.makeAccusal(cha, loc, wep);
	}
	
	/**
	 * 
	 * Takes in the input for players moving and calls the board to move the player.
	 * 
	 */
	private void moveCommand(){
		
		int canMove = game.rollDice();
		System.out.println("You rolled: " + canMove);
		
		
	}
	
	/**
	 * 
	 * Asks for number of players
	 * 
	 * @return number of players
	 */
	public int getPlayerCount(){
		System.out.println("How many players do you want?");
		return getActionFromList(new String[] {
				"", // will only show numbers, just like we want!
				"",
				"",
				"",
				"",
				"" }) + 1;	
	}
	
	/**
	 * 
	 * Takes an integer (0-3) for the player moving 
	 * and returns the corresponding correct board coordinate.
	 * 
	 * @param input input for movement direction.
	 * @return Coordinate of resulting movement.
	 */
	private Coordinate convertInputToVector(int input){
		switch(input){
		case 0:
			return Board.UP;
		case 1:
			return Board.DOWN;
		case 2:
			return Board.RIGHT;
		case 3:
			return Board.LEFT;
		}
		
		return null;
	}
	
	/**
	 * 
	 * Takes a list of actions the player
	 * can take and asks for the user to input a number corresponding to the
	 * list.
	 * 
	 * 
	 * @param list The list of actions
	 * @return The integer corresponding to the action the player selects.
	 */
	private int getActionFromList(String[] list){
		for (int i = 1; i <= list.length; i++){	
			System.out.println(i + " : " + list[i-1]);
		}
		while(true){
			System.out.println("please enter the number of your choice : ");
			
			//try{
				//int res = Integer.parseInt(input);
				//if(res <= list.length)
				//	return res-1;
			//}
			//catch (NumberFormatException e){
			//	continue;
			//}
		}
	}	
}










