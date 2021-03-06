package ClueGame;

import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import ClueGame.Character.CharName;
import ClueGame.Location.LocName;
import ClueGame.Weapon.WeaponType;
import UI.*;


public class ClueGame {
	
	//Clue sets
	public List<Weapon> weapons = new ArrayList<Weapon>();
	public List<Character> characters  = new ArrayList<Character>();
	public List<Location> locations = new ArrayList<Location>();
	
	public Solution solution;
	
	public Board board = new Board(this);
	public InputManager input;
	
	public Player[] players;
	int numPly; //Number of players
	public Player activePlayer;
	public UIManager UI;
	
	public boolean playing = true;
	public static boolean useTestingLogic;
	
		
	public ClueGame(boolean generate){
		
		UI = new UIManager(this);
		input = new InputManager(this, UI.ChoiceController);
	}
	
	public void setPlayerCount(int i) {
		numPly = i;
	}
	
	/**
	 * Fills the player array with players ,creates the solution, and deals the
	 * player cards.
	 * 
	 * 
	 * @param playerNo The number of players to create
	 */
	public void setupGame(int playerNo){
		fillClueSets();
			
		//Setting up players.
		numPly = playerNo;
		players = new Player[numPly];
		for (int i = 0; i < numPly; i++){
			players[i] = new Player (i + 1);
			players[i].setCharacter(characters.get(i));
		}
		
		activePlayer = players[0];
		
		solution = generateSolution(); //Make enveloped solution
		shuffleAndFill(); //Deal cards to players
		
	}
	
	/**
	 * Spawns the players, and sets the state in the input manager to active.
	 * 
	 * 
	 */
	public void startGame(){
		for (Player p : players){
			board.spawnPlayer(p);
		}
		input.state = 1;
		UI.ChoiceController.setCurrentPlayer(activePlayer.getName());
		UI.handController.setHand(activePlayer.hand);
		input.sendChoices();
		UI.boardController.getView().repaint();
	}

	public static void main(String[] args) {
		new ClueGame(true);
	}
	
	
	public Player[] getPlayers(){
		return players;
	}
	
	public void endGame(){
		playing = false;
	}
	
	public boolean isEnded(){
		return !playing;
	}
	
	/**
	 * 
	 * End the current players turn and
	 * determines the next player
	 * 
	 */
	public void endTurn(){
		System.out.println("Turn over.");
		int nextPlayer = activePlayer.getNumber();
		while(true){
			if (nextPlayer == numPly)
				nextPlayer = 0;
			if(players[nextPlayer].getActive()){
				activePlayer = players[nextPlayer];
				UI.ChoiceController.setCurrentPlayer(activePlayer.getName());
				UI.handController.setHand(activePlayer.hand);
				if (activePlayer.inRoom()){input.state = 2;} else {input.state = 1;}
				input.sendChoices();
				break;
			}
			nextPlayer++;
		}	
	}
	
	/**
	 * 
	 * Fills the clue arrays with
	 * new instances of the clues.
	 * 
	 */
	public void fillClueSets(){
		weapons.add(new Weapon(WeaponType.CANDLESTICK));
		weapons.add(new Weapon(WeaponType.DAGGER));
		weapons.add(new Weapon(WeaponType.LEADPIPE));
		weapons.add(new Weapon(WeaponType.REVOLVER));
		weapons.add(new Weapon(WeaponType.ROPE));
		weapons.add(new Weapon(WeaponType.SPANNER));
		
		characters.add(new Character(CharName.Mrs_Peacock));
		characters.add(new Character(CharName.Colonel_Mustard));
		characters.add(new Character(CharName.Miss_Scarlet));	
		characters.add(new Character(CharName.Mrs_White));
		characters.add(new Character(CharName.Professor_Plum));
		characters.add(new Character(CharName.The_Reverend_Green));
		
		locations.add(new Location(LocName.BALL_ROOM));//
		locations.add(new Location(LocName.BILLIARD_ROOM));//
		locations.add(new Location(LocName.CONSERVATORY));//
		locations.add(new Location(LocName.DINING_ROOM));//
		locations.add(new Location(LocName.HALL));//
		locations.add(new Location(LocName.KITCHEN));//
		locations.add(new Location(LocName.LIBRARY));
		locations.add(new Location(LocName.LOUNGE));//
		locations.add(new Location(LocName.STUDY));
	}
	
	/**
	 * Takes clues out of the clues array and
	 * generates the enveloped solution for the game.
	 * 
	 * 
	 * @return The Solution object containing the solution clues
	 */
	public Solution generateSolution(){
		Location toAddLoc;
		Character toAddChar;
		Weapon toAddWep;
		
		int rand = new Random().nextInt(characters.size());
		toAddChar = characters.remove(rand);
		
		rand = new Random().nextInt(locations.size());
		toAddLoc = locations.remove(rand);
		
		rand = new Random().nextInt(weapons.size());
		toAddWep = weapons.remove(rand);
		
		//Uncomment for debug
		//System.out.println("The solution is: " + toAddChar.getType() + " " + toAddLoc.getType() + " " + toAddWep.getType());
		return new Solution(toAddChar, toAddLoc , toAddWep);
	}
	
	public Solution generateSolution(Character toAddChar, Location toAddLoc, Weapon toAddWep){
		weapons.remove(0);
		locations.remove(0);
		characters.remove(1);
		return new Solution(toAddChar, toAddLoc , toAddWep);
	}
	
	/**
	 * Shuffles all the clue arrays into one, shuffles it, and
	 * deals them out evenly to the players.
	 * 
	 */
	public void shuffleAndFill(){
		ArrayList<Clue> deck = new ArrayList<Clue>();
		deck.addAll(weapons);
		deck.addAll(characters);
		deck.addAll(locations);
		Collections.shuffle(deck);
		Collections.shuffle(deck); //Shuffle twice to be sure
		
		int playerIndex = 0;
		for (int i = 0; i < deck.size(); i++){
			if (playerIndex > numPly - 1){playerIndex = 0;}
			players[playerIndex].addToHand(deck.get(i));
			playerIndex++;
		}
		
	}
	
	/**
	 * 
	 * Determines whether the player can move to the chosen tile, and if they can,
	 * move them and set the input manager to the correct state. If the dice number becomes zero, the player
	 * can no longer move.
	 * 
	 * @param x The x coordinate on the board to move to
	 * @param y The y coordinate on the board to move to
	 */
	public void beginMove(int x, int y){
		int movedX = activePlayer.getPosition().col - x;
		int movedY = activePlayer.getPosition().row - y;
		
		if (board.leaveRoom(activePlayer, new Coordinate(y,x))){input.state = 8; return;}
		if (Math.abs(movedX) + Math.abs(movedY) > 1){JOptionPane.showMessageDialog(null, "Please click an ADJACENT tile to move to", "Info!", javax.swing.JOptionPane.INFORMATION_MESSAGE); return;}
		
		board.movePlayer(activePlayer, new Coordinate(-movedY,-movedX));
		UI.DiceController.setValue(UI.DiceController.getValue() - 1);
		if(UI.DiceController.getValue() == 0){UI.boardController.getView().canMove(false);}
		if (activePlayer.inRoom()){input.state = 2;} else {input.state = 8;}
		input.sendChoices();
	}
	
	/**
	 * Takes three clue item and makes a cluedo suggestion.
	 * Scans all other players hands (clockwise) to see if their hands contain
	 * any of the suggested clues, and outputs if they do.
	 * 
	 * 
	 * @param loc The suggested Location 
	 * @param wep The suggested Weapon
	 * @param cha The suggested Character
	 */
	public void makeSuggest(LocName loc, Weapon wep, Character cha){
		for (Player p : players){
			if (p.getCharacter().getType().equals(cha.getType())){board.movePlayerToRoom(p, loc);}
		}
		
		System.out.println();
		System.out.println("You are suggesting it was:");
		System.out.println(cha.getType() + " in the "  + loc.name()+ " with the " + wep.getType());
		System.out.println();
		
		int i = activePlayer.getNumber(); //Technically this is the player AFTER active player in terms of array numbering
		try {
			if (!useTestingLogic){TimeUnit.SECONDS.sleep(1);}
			for (int k = 0; k < numPly - 1; k++){
				if (i == numPly){i = 0;}
				for (Clue c : players[i].getHand()){			
					if (wep.getType().equals(c.getType())){JOptionPane.showMessageDialog(null,"Player " + players[i].getNumber() + " has the " + c.getType(), "INFO",javax.swing.JOptionPane.INFORMATION_MESSAGE); return;}
					if (cha.getType().equals(c.getType())){JOptionPane.showMessageDialog(null,"Player " + players[i].getNumber() + " has the " + c.getType(),"INFO",javax.swing.JOptionPane.INFORMATION_MESSAGE);  return;}
					if (loc.name().equals(c.getType())){JOptionPane.showMessageDialog(null,"Player " + players[i].getNumber() + " has the " + c.getType(),"INFO",javax.swing.JOptionPane.INFORMATION_MESSAGE); return;}
				}
				i++;
			}
			
			JOptionPane.showMessageDialog(null, "None of the other players can refute you", "Info!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
		}catch (Exception e){System.out.println("Whoops! Somthing went wrong there");}
	}
	
	/**
	 * Checks to see if paramater clues match the ones in the solution
	 * 
	 * 
	 * @param tryChar The suggested Character
	 * @param tryLoc The suggested Location
	 * @param tryWep The suggested Weapon
	 * @return boolean of whether all three parameters match the solution
	 */
	public boolean tryVictory(Character tryChar, Location tryLoc, Weapon tryWep){
		
		return ((solution.getChar().getType()).equals(tryChar.getType()) 
				&& (solution.getWep().getType()).equals(tryWep.getType())
				&& (solution.getLoc().getType()).equals(tryLoc.getType()));
			
	}
	
	/**
	 * 
	 * Returns a dice roll.
	 * 
	 * @return integer from 1 to 6
	 */
	public int rollDice(){
		int diceValue = new Random().nextInt(6) + 1;
		UI.DiceController.setValue(diceValue);
		return diceValue;
	}

	/**
	 * Takes 3 suggested clues, and checks to see of they match the solution.
	 * If so, then the active player is the winner.
	 * If not, the current player has lost and can no longer take their turn.
	 * 
	 * 
	 * @param cha The accused Character
	 * @param loc The accused Location
	 * @param wep The accused Weapon
	 * @return boolean if whether accusal was correct
	 * @throws InterruptedException 
	 */
	public boolean makeAccusal(Character cha, Location loc, Weapon wep){
		
		System.out.println();
		System.out.println("You are accusing: ");
		System.out.println(cha.getType() + " of commiting the crime in the "  + loc.getType()+ " with the " + wep.getType());
		
		try{
			if (!useTestingLogic){ //Waiting during testing is annoying
				TimeUnit.SECONDS.sleep(1);
				System.out.println("...");
				TimeUnit.SECONDS.sleep(1);
				System.out.println("...");
				TimeUnit.SECONDS.sleep(1);
			}
		
		
			if (tryVictory(cha, loc, wep)){
				System.out.println("Player " + activePlayer.getNumber() + " is the WINNER!"); 
				playing = false;
				return true;
			} else {
				System.out.println("Sorry player " + activePlayer.getNumber() + ", but that wrong. You are OUT!");
				if (!useTestingLogic){TimeUnit.SECONDS.sleep(3);}
				activePlayer.setActive(false);
				return false;
			}
		
		}catch (InterruptedException e){System.out.println("Whoops! Somthing went wrong there " + e); return false;}
	}
}
