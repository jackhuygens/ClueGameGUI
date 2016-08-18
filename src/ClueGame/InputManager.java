package ClueGame;


import java.util.*;
import ClueGame.Location.LocName;
import ClueGame.Weapon.WeaponType;
import UI.ChoiceController;
import ClueGame.Character.CharName;

public class InputManager {

	//Ideally this class should do nothing but process input and instruct main game class.
	
	private ClueGame game;
	private int state = 0;
	public ChoiceController cc = game.UI.ChoiceController;
	
	public InputManager(ClueGame game){
		this.game = game;
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
		}
		else {
			cc.setChoices(null, null);
		}
	}
	
	public void activateChoice(int i){
		if (state == 1){
			switch (i){
				case 0:
					moveCommand();
					break;
				case 1:
					game.endTurn();
					break;
					
			}
		}else if (state == 2){
			switch (i){
			case 0:
				moveCommand();
				break;
			case 1:
				makeSuggestion();
				break;
			case 2:
				makeAccusal();
				break;
			case 3:
				game.endTurn();
				break;
				
		}
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
	private void makeSuggestion(){
		
		LocName loc = game.activePlayer.getCurrentRoom();
		Weapon wep = new Weapon(WeaponType.CANDLESTICK); //Defaults for initialization
		Character cha = new Character(CharName.Mrs_Peacock); 
		
		System.out.println("Which weapon do you suggest?");
		int choice = getActionFromList(new String[] {
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
		
		System.out.println("Which character do you suggest?");
		choice = getActionFromList(new String[] {
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
		
		game.makeSuggest(loc, wep, cha);
		
	}
	
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
		if(ClueGame.useTestingLogic)
			canMove = 100;
		System.out.println("You rolled: " + canMove);
		
		while(true){
			
			int choice;
			
			if(game.activePlayer.inRoom()){
				System.out.println("You are in a room, which door do you wish to leave from?");
				choice = getActionFromList(new String[] {
						"North",
						"South",
						"East",
						"West",
						"Stay in this room" });
			}
			else{
				System.out.println("Choose a direction to move");
				choice = getActionFromList(new String[] {
						"North",
						"South",
						"East",
						"West",
						"Stop moving" });
			}
			
			if(choice == 4)
				return;
			
			if (game.board.movePlayer(game.activePlayer, convertInputToVector(choice))){
				System.out.println("--------------");
				System.out.println(game.board.renderBoard());
				System.out.println("--------------");
				System.out.println("You moved. Moves remaining : " + (canMove-1));
				if(game.activePlayer.inRoom()){
					System.out.println("you have entered the " + game.activePlayer.getCurrentRoom() + "!");
					processInput(true);
					return;
				}
				canMove--;
			} else {
				System.out.println("You cannot move here!");
			}	 
			
			if(canMove == 0){
				return;
			}	
		}
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










