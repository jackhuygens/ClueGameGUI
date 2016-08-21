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
		panel.setPreferredSize(new Dimension(140, 230));
		parent.add(panel);	

	}
	
	public void setHand(ArrayList<Clue> hand) {
		
		panel.removeAll();
		JLabel j = new JLabel("Cards:");
		j.setPreferredSize(new Dimension(140, 16));
		j.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(j);
		for(int i = 0; i < hand.size(); i++){

			String card = hand.get(i).getType();
			card = card.replace('_', ' ');
			card = card.toLowerCase();
			JLabel l = new JLabel("<html><center>" + card + "</center></html>");
			Font oldFont = l.getFont();
			//l.setFont(new Font(oldFont.getName(), Font.PLAIN, 8));
			l.setPreferredSize(new Dimension(130, 12));
			panel.add(l);
		}
    }
}
