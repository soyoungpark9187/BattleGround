package RANKS;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Rank {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rank window = new Rank();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Rank() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(240, 255, 240));
		frame.setSize(800,600);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		panel.setBounds(76, 15, 271, 214);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("RANKING");
		lblNewLabel.setFont(new Font("배달의민족 주아", Font.PLAIN, 46));
		lblNewLabel.setBounds(307, 35, 209, 100);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\박상현\\Desktop\\myfile\\rank.png"));
		label.setBounds(73, 163, 568, 319);
		panel.add(label);
		
		JLabel lblst = new JLabel("1st player");
		lblst.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		lblst.setBounds(168, 150, 104, 21);
		panel.add(lblst);
		
		JLabel label_1 = new JLabel("2nd player");
		label_1.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		label_1.setBounds(280, 202, 104, 21);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("3rd player");
		label_2.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		label_2.setBounds(396, 242, 104, 21);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("4th player");
		label_3.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		label_3.setBounds(514, 270, 104, 21);
		panel.add(label_3);
	}
}
