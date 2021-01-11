package minigame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ResultDialog extends JDialog{
	public JButton okButton;
	private JLabel result_label;
	
	public ResultDialog (JFrame frame) {
		super(frame, "Result", true);
		okButton = new JButton("OK");
//		okButton.setPreferredSize(new Dimension(20, 10));
		
		result_label= new JLabel();
		result_label.setHorizontalAlignment(JLabel.CENTER);
		
		setLayout(new BorderLayout());	
		add(result_label, BorderLayout.NORTH);
		add(okButton, BorderLayout.SOUTH);
		setBounds(100,100,200,100);
		

	}	
	
	public void setResult(int result) {
		switch(result) {
		case 0:		//fail
		case 2:		//draw
			this.result_label.setText("Fail");
			break;		
		case 1:		//win
			this.result_label.setText("Success");
			break;
		}
		
	}
	
}