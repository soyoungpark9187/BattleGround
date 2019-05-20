package why;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;

public class login_main extends JFrame {

	public login_main() {
		setResizable(false);
		
		getContentPane().setBackground(new Color(240, 255, 240));
		login_available start;

		setTitle("로그인");
		setSize(287, 176);
		setLocationRelativeTo(null);
		setVisible(true);

		getContentPane().setLayout(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				setVisible(false);
				System.exit(0);
			}
		});
		Label ID_label = new Label("ID");
		ID_label.setFont(new Font("LG PC", Font.PLAIN, 15));
		ID_label.setBounds(25, 22, 39, 23);
		getContentPane().add(ID_label);

		TextField ID_text = new TextField();
		ID_text.setBounds(85, 22, 174, 23);
		ID_text.setVisible(true);
		getContentPane().add(ID_text);

		Label PW_label = new Label("PW");
		PW_label.setFont(new Font("LG PC", Font.PLAIN, 15));
		PW_label.setBounds(25, 60, 44, 23);
		getContentPane().add(PW_label);

		JPasswordField PW_text = new JPasswordField();
		PW_text.setBounds(85, 60, 174, 23);
		PW_text.setVisible(true);
		getContentPane().add(PW_text);
		PW_text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) { // enter 입력시 submit 버튼과 같음
				char c;
				c = e.getKeyChar();
				if (c == '\n') {
					new login_available(ID_text.getText(), PW_text.getText());
					setVisible(login_available.suc);
				}

			}
		});

		Button login_bt = new Button("로그인");
		login_bt.setFont(new Font("LG PC", Font.PLAIN, 15));
		login_bt.setBackground(new Color(255, 255, 255));
		login_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new login_available(ID_text.getText(), PW_text.getText());
				setVisible(login_available.suc);
			}
		});

		login_bt.setBounds(10, 105, 111, 23);
		getContentPane().add(login_bt);
		Button register_bt = new Button("회원가입");
		register_bt.setBackground(Color.WHITE);
		register_bt.setFont(new Font("LG PC", Font.PLAIN, 15));
		register_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Register();
			}
		});
		register_bt.setBounds(148, 105, 111, 23);
		getContentPane().add(register_bt);

	}

	public static void main(String[] args) {
		new login_main();

	}
}
