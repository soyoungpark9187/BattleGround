package server;

import java.io.IOException;

import java.awt.*;
import java.io.PrintWriter;

import java.net.InetAddress;

import java.net.InetSocketAddress;

import java.net.ServerSocket;

import java.net.Socket;

import java.util.ArrayList;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ChatServer extends JFrame {
	public static final int PORT = 60000;
	public static final int max = 4;
	public static int i = 0;
	public boolean Switch;
	private JTextField textField;
	public Integer Avd;
	private ArrayList<Integer> SCORE;
	int score[][] = new int[10][25];
	private ArrayList<Integer> round;
	
	public ChatServer() {
		for(int j=0;j<10;j++) {
		for(int i=0;i<25;i++) {
			score[j][i] = (int)((Math.random()*10)) * 10 ;
			}
		}
		round = new ArrayList<Integer>();
		Switch = true;
		setTitle("SERVER");

		SCORE = new ArrayList<Integer>();
		SCORE.add(0);
		SCORE.add(0);
		SCORE.add(0);
		SCORE.add(0);
		getContentPane().setBackground(new Color(240, 255, 240));
		getContentPane().setSize(100, 100);
		setBackground(new Color(240, 255, 240));
		getContentPane().setLayout(null);
		setSize(450, 407);
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(12, 10, 410, 272);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				setVisible(false);
				System.exit(0);
			}
		});

		JTextArea textPane = new JTextArea();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		setVisible(true);

		textPane.append("SERVER ON\n");

		JButton btnNewButton = new JButton("CLOSE SERVER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				System.exit(0);
			}
		});
		btnNewButton.setBounds(12, 318, 410, 40);
		getContentPane().add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(12, 281, 318, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("ENTER");

		btnNewButton_1.setBounds(325, 280, 97, 23);
		getContentPane().add(btnNewButton_1);
		SERVER_START(textPane, btnNewButton_1, textField, scrollPane);
	}

	private void SERVER_START(JTextArea A, JButton B, JTextField C, JScrollPane D) {
		Avd = 0;
		ServerSocket serverSocket = null;

		List<PrintWriter> listWriters = new ArrayList<PrintWriter>();
		List<String> nicknames = new ArrayList<String>();
		List<String> players = new ArrayList<String>();

		ChatToPlayer X = new ChatToPlayer(listWriters, nicknames, A, B, C, D);
		try {

			// 1. 서버 소켓 생성

			serverSocket = new ServerSocket();

			// 2. 바인딩
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			String IP = "127.0.0.1";
			serverSocket.bind(new InetSocketAddress(IP, PORT));
			A.append(IP + ":" + PORT + " 대기중..\n");
			X.start();

			// 3. 요청 대기

			while (true) {

				Socket socket = serverSocket.accept();

				if (listWriters.size()<4)
					new ChatServerProcessThread(socket, listWriters, nicknames, A, D, Avd, players, SCORE, score ,round).start();

				
					
			}
		}

		catch (IOException e) {

			e.printStackTrace();
		}

		finally {

			try {

				if (serverSocket != null && !serverSocket.isClosed()) {

					serverSocket.close();
				}
			} catch (IOException e) {

				e.printStackTrace();

			}

		}
	}

	public static void main(String[] args) {

		new ChatServer();
	}

	private static void consoleLog(String log) {

		System.out.println("[server " + Thread.currentThread().getId() + "] " + log);

	}
}