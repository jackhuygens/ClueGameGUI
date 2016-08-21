package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

public class ChoiceView {
	
	private ChoiceController controller;
	
	private JPanel panel, parent;
	private String playerName;
		
	public ChoiceView(JPanel parent){
		this.parent = parent;
		panel = new JPanel();
		panel.setBackground(new Color(236, 242, 248));
		panel.setPreferredSize(new Dimension(140, 300));
		parent.add(panel);
	}
	
	public void setController(ChoiceController controller){
		this.controller = controller;
	}
	
	public void setCurrentPlayer(String playerName){
		this.playerName = playerName;
	}
	
	public void setChoices(String[] choices, String status){

		panel.removeAll();
		
		JLabel playerNameL = new JLabel("<html><center>" + playerName + "</center></html>");
		playerNameL.setPreferredSize(new Dimension(130, 30));
		panel.add(playerNameL);
		
		JLabel statusL = new JLabel("<html><center>" + status + "</center></html>");
		statusL.setPreferredSize(new Dimension(130, 100));
		panel.add(statusL);
		
		for(int i = 0; i < choices.length; i++){
			final int tempI = i;
			JButton b = new JButton(choices[i]);
			b.setPreferredSize(new Dimension(130, 24));
			b.setHorizontalAlignment(SwingConstants.LEFT);
			b.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				 controller.selectChoice(tempI);   
			  }});
			panel.add(b);
			panel.revalidate();
       	 	panel.repaint();
		}
	}
}
