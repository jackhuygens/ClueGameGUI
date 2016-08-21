package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

/**
 * 
 * handles all aspects of drawing the choice panel to the UI.
 *  
 */
public class ChoiceView {
	
	private ChoiceController controller;
	
	private JPanel panel, parent;
	private String playerName;
		
	public ChoiceView(JPanel parent){
		this.parent = parent;
		panel = new JPanel();
		panel.setBackground(new Color(236, 242, 248));
		panel.setPreferredSize(new Dimension(140, 330));
		parent.add(panel);
	}
	
	public void setController(ChoiceController controller){
		this.controller = controller;
	}
	
	public void setCurrentPlayer(String playerName){
		this.playerName = playerName;
	}
	
	/**
	 * 
	 * update and re-draw the choice panel to reflect new options for the player.
	 *  
	 * @param choices string array representing each option the player has 
	 * @param status message to display above choices
	 */
	
	public void setChoices(String[] choices, String status){

		panel.removeAll();
		
		JLabel playerNameL = new JLabel(playerName);
		panel.add(playerNameL);
		
		JLabel statusL = new JLabel("<html>" + status + "</html>");
		statusL.setPreferredSize(new Dimension(130, 70));
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
