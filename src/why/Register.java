package why;

import javax.swing.*;


import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Register extends JFrame {
	
	
	boolean able = false;
	
	public Register() {
		getContentPane().setBackground(new Color(240, 255, 240));
		addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				setVisible(false);
			    System.exit(0);
			   }
			  });
		setTitle("회원가입");
		setSize(360,200);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		getContentPane().setLayout(null);
		
		Label ID_label = new Label("ID");
		ID_label.setFont(new Font("LG PC", Font.PLAIN, 15));
		ID_label.setBounds(30, 25, 26, 23);
		getContentPane().add(ID_label);
		
		TextField ID_text = new TextField();
		ID_text.setBounds(85, 25, 150, 23);
		getContentPane().add(ID_text);
		
		Label PW = new Label("PW");
		PW.setFont(new Font("LG PC", Font.PLAIN, 15));
		PW.setBounds(30, 75, 33, 23);
		getContentPane().add(PW);
		
		JPasswordField PW_text = new JPasswordField();
		PW_text.setBounds(85, 75, 150, 23);
		getContentPane().add(PW_text);
		
		Label PW2_label = new Label("재확인");
		PW2_label.setFont(new Font("LG PC", Font.PLAIN, 15));
		PW2_label.setBounds(30, 125, 49, 23);
		getContentPane().add(PW2_label);
		
		JPasswordField PW2_text = new JPasswordField();
		PW2_text.setBounds(85, 125, 150, 23);
		getContentPane().add(PW2_text);
		
		Button check_bt = new Button("중복확인");
		check_bt.setFont(new Font("LG PC", Font.PLAIN, 15));
		check_bt.setBackground(new Color(255, 255, 255));
		check_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = ID_text.getText();
				 id_check check = new id_check(id);
				 able = check.check;
			}
		});
		check_bt.setBounds(260, 25, 76, 23);
		getContentPane().add(check_bt);
		
		Button cancle_bt = new Button("취소");
		cancle_bt.setFont(new Font("LG PC", Font.PLAIN, 15));
		cancle_bt.setBackground(new Color(255, 255, 255));
		cancle_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new login_main();
				setVisible(false);
			}
		});
		cancle_bt.setBounds(260, 125, 76, 23);
		getContentPane().add(cancle_bt);
		
		Button register_bt = new Button("회원가입");
		register_bt.setFont(new Font("LG PC", Font.PLAIN, 15));
		register_bt.setBackground(new Color(255, 255, 255));
		register_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = ID_text.getText();
				String pw = PW_text.getText();
				String pw1 = PW2_text.getText();
				
				
				
				if (id.equals("") || pw.equals(""))
					new Print("모든 정보를 입력하세요.");
				else if(able == false)
					new Print("아이디 중복하기를 확인해주세요.");
				else if (!pw.equals(pw1))
					new Print("비밀번호가 일치하지 않습니다.");
				else {
					new Input_Database(id, pw);
					new login_main();
					setVisible(false);
				}
			}
		});
		register_bt.setBounds(260, 75, 76, 23);
		getContentPane().add(register_bt);
		
	}
}

