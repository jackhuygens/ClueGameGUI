package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import ClueGame.Clue;

public class DiceView {
	
	private JPanel panel, parent;
		
	public DiceView(JPanel parent){
		
		this.parent = parent;
		panel = new JPanel();
		
		panel.setBackground(new Color(236, 242, 248));
		panel.setPreferredSize(new Dimension(140, 55));
		parent.add(panel);
		
		//FIXME delete this
		setDice(5);
		
	}
	
	public void setDice(int val){
		
		panel.removeAll();
		
		panel.add(new JLabel("Dice:  "));
		JButton b = new JButton("" + val);
		b.setPreferredSize(new Dimension(45, 45));
		b.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			//FIXME  this
			 //controller >> rollDice, maybe?   
		  }});
		panel.add(b);
	}
}
