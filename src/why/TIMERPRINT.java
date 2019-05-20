package why;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class TIMERPRINT extends JFrame {
	public TIMERPRINT(String str) {
		getContentPane().setBackground(Color.WHITE);
		
		setAlwaysOnTop(true);
		setTitle("ROADING");
		setSize(558,301);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src\\roading.gif"));
		lblNewLabel.setBounds(12, 10, 528, 252);
		getContentPane().add(lblNewLabel);
		setVisible(true);
		
	}
}

