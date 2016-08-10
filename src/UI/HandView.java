package UI;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class HandView extends JPanel{
		
	public HandView(){
		add(new JLabel("AA"));
	}
	
	@Override
	protected void paintComponent(Graphics g) {

        Graphics2D gg = (Graphics2D) g;
        gg.setColor(Color.BLUE);
        gg.fill(new Rectangle(0, 0, 10, 10));

    }
}
