package why;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class ErrorHandling extends JFrame {
	public ErrorHandling(String str) {
		getContentPane().setBackground(new Color(240, 255, 240));
		
		setAlwaysOnTop(true);
		setTitle("NOTICE");
		setSize(287,191);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		Label system_label = new Label(str);
		system_label.setAlignment(Label.CENTER);
		system_label.setBounds(10, 54, 261, 23);
		getContentPane().add(system_label);
		
		JButton ok_bt = new JButton("»Æ¿Œ");
		ok_bt.setFont(new Font("LG PC", Font.PLAIN, 15));
		ok_bt.setBackground(new Color(255, 255, 255));
		
		ok_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		ok_bt.setBounds(100, 114, 76, 23);
		getContentPane().add(ok_bt);
		setVisible(true);
		
	}

}

