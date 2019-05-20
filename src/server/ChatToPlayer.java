package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatToPlayer extends Thread {
	private String nickname = null;
	private JTextArea X;
	JButton B;
	List<PrintWriter> listWriters = null;
	List<String> nicknames = null;
	JTextField C;
	JScrollPane D;

	public ChatToPlayer(List<PrintWriter> listWriters, List<String> nicknames, JTextArea X, JButton B, JTextField C,
			JScrollPane D) {
		this.C = C;
		this.D = D;
		this.X = X;
		this.B = B;
		this.listWriters = listWriters;
		this.nicknames = nicknames;
	}

	public void run() {
		B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = C.getText();
				if (!temp.isEmpty()) {
					broadcast("관리자 : " + temp);
					X.append("관리자 :" + temp + "\n");
					D.getVerticalScrollBar().setValue(D.getVerticalScrollBar().getMaximum());
				}
				C.setText("");
			}
		});

		C.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) { // 채팅 enter로 입력 가능
				char c;
				c = e.getKeyChar();
				if (c == '\n') {
					String temp = C.getText();
					if (!temp.isEmpty()) {
						broadcast("관리자 : " + temp);
						X.append("관리자 :" + temp + "\n");
						D.getVerticalScrollBar().setValue(D.getVerticalScrollBar().getMaximum());
					}
					C.setText("");
				}
			}
		});

	}

	private void broadcast(String data) {

		synchronized (listWriters) {

			for (PrintWriter writer : listWriters) {

				writer.println(data);

				writer.flush();

			}

		}

	}

}
