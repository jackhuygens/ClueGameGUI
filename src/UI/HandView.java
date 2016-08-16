package UI;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import ClueGame.Clue;
import ClueGame.Weapon;
import ClueGame.Weapon.WeaponType;

public class HandView {
	
	private JPanel panel, parent;
	
	protected ArrayList<Clue> hand;
		
	public HandView(JPanel parent){
			
		this.parent = parent;
		panel = new JPanel();
		panel.setBackground(new Color(236, 242, 248));
		panel.setPreferredSize(new Dimension(140, 130));
		parent.add(panel);	
		
		//FIXME delete this
		ArrayList<Clue> hand = new ArrayList<Clue>();
		hand.add(new Weapon(WeaponType.REVOLVER));
		hand.add(new Weapon(WeaponType.REVOLVER));
		hand.add(new Weapon(WeaponType.REVOLVER));
		setHand(hand);
	}
	
	public void setHand(ArrayList<Clue> hand) {
		
		panel.removeAll();
		JLabel j = new JLabel("Cards:");
		j.setPreferredSize(new Dimension(140, 20));
		j.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(j);
		for(int i = 0; i < hand.size(); i++){
			//FIXME use images!
			JButton b = new JButton("");
			b.setPreferredSize(new Dimension(40, 80));
			panel.add(b);
		}
    }
}
