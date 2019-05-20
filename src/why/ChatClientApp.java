package why;

import java.util.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;
import java.awt.Component;


public class ChatClientApp extends JFrame{
  private static final String SERVER_IP = "127.0.0.1";//수정해줘야 실행됨.
  private static final int SERVER_PORT = 60000;
  private String ID="";
  public boolean flag = true;
  private JTextArea textArea;
  private int player_num;
  private Socket socket;
  private JScrollPane scrollPane;
  private JTextField textField;
  private JTextArea[] A = new JTextArea[4]; 
  private SoundsOfMusic AS;
  private boolean FLAG;
  private int MYNUM;

  
  public ChatClientApp(String pid) {
  	setResizable(false);
	  
	FLAG = true;	
	ID=pid;  
    String name = pid;
    
    getContentPane().setBackground(new Color(240, 255, 240));
	setBackground(new Color(240, 255, 240));
	
    setTitle("USER : "+ name);
	setSize(385,291);
	setLocationRelativeTo(null);
	getContentPane().setLayout(null);
	addWindowListener(new WindowAdapter() {
		   public void windowClosing(WindowEvent evt) {
			   PrintWriter pw;
			    try {
			      pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
			      String request = "quit:ID";
			      pw.println(request);
			      textField.requestFocus();
			    }
			    catch (IOException e) {
			      e.printStackTrace();
			      new ErrorHandling("sendMessage ERROR");
			    }
			setVisible(false);
		    System.exit(0);
		   }
		  });
	JPanel player1 = new JPanel();
	player1.setBorder(new LineBorder(new Color(154, 205, 50), 3));
	player1.setBackground(new Color(255, 255, 255));
	player1.setBounds(12, 10, 174, 60);
	getContentPane().add(player1);
	player1.setLayout(null);
	
	
	
	JPanel player2 = new JPanel();
	player2.setBackground(new Color(255, 255, 255));
	player2.setBorder(new LineBorder(new Color(154, 205, 50), 3));
	player2.setLayout(null);
	player2.setBounds(198, 10, 165, 60);
	getContentPane().add(player2);
	
	
	JPanel player3 = new JPanel();
	player3.setBackground(new Color(255, 255, 255));
	player3.setBorder(new LineBorder(new Color(154, 205, 50), 3));
	player3.setLayout(null);
	player3.setBounds(12, 80, 174, 60);
	getContentPane().add(player3);
	
	
	
	JPanel player4 = new JPanel();
	player4.setBackground(new Color(255, 255, 255));
	player4.setBorder(new LineBorder(new Color(154, 205, 50), 3));
	player4.setLayout(null);
	player4.setBounds(198, 80, 165, 60);
	getContentPane().add(player4);
	

	for(int v=0;v<4;v++)
	{
	//*******************
			A[v]= new JTextArea("EMPTY");
			A[v].setEditable(false);
			A[v].setFont(new Font("LG PC", Font.PLAIN, 15));
	}
	
	A[0].setBounds(10, 10, 154, 40);
	player1.add(A[0]);
	A[1].setBounds(10, 10, 145, 40);
	player2.add(A[1]);
	A[2].setBounds(10, 10, 154, 40);
	player3.add(A[2]);
	A[3].setBounds(10, 10, 133, 40);
	player4.add(A[3]);

	getContentPane().add(player1);
	getContentPane().add(player2);
	getContentPane().add(player3);
	getContentPane().add(player4);
	
	
	JPanel chat = new JPanel();
	chat.setBackground(new Color(255, 255, 255));
	chat.setBounds(13, 146, 351, 103);
	getContentPane().add(chat);
	chat.setLayout(null);
	
	
	//******************************
	textArea = new JTextArea();
	textArea.setWrapStyleWord(true);
	textArea.setEditable(false);
	textArea.setBounds(0, 0, 351, 82);
	textArea.setLineWrap(true);
	
	
	scrollPane = new JScrollPane(); // 채팅 스크롤
      scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      scrollPane.setBounds(0, 0, 351, 82);
     
      scrollPane.setViewportView(textArea);
      chat.add(scrollPane);

    //******************************
	textField = new JTextField();
	textField.setBounds(0, 82, 265, 21);
	chat.add(textField);
	textField.setColumns(10);
	 textField.addKeyListener(new KeyAdapter() {
	      	@Override
	      	public void keyTyped(KeyEvent e) { // 채팅 enter로 입력 가능
	      		char c;
	      		c=e.getKeyChar();
	      		if(c=='\n')
	      		{
	      			String temp =textField.getText();
	      			
	      			if(temp.startsWith("/"))
	      			{
	      				textField.setText("");
	      				
	      				if(temp.substring(1).equalsIgnoreCase("off")&& flag==true)
	      				{
	      					textArea.append("MUSIC OFF\n");
	      					flag=false;
	      					AS.wavEnd();
	      				}
	      				
	      				else if(temp.substring(1).equalsIgnoreCase("off")&& flag==false)
	      				{
	      					textArea.append("Music is already off...\n");
	      				}
	      				else if(temp.substring(1).equalsIgnoreCase("on")&&flag==false)
	      				{
	      					textArea.append("MUSIC ON\n");
	      					flag=true;
	      					AS = new SoundsOfMusic("src\\13.wav");
	      				}
	      				else if(temp.substring(1).equalsIgnoreCase("on")&&flag==true)
	      				{
	      					textArea.append("Music is already playing...\n");
	      				}
	      				else
	      				{
	      					textArea.append("UNDEFINED CONSOLELOG\n");
	      				}
	      				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	      			}
	      			else
	      				if(!temp.isEmpty())
	      					sendMessage(socket,  temp);
	      		}
	      	}
	      });
	
	JButton btnNewButton = new JButton("ENTER");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String temp =textField.getText();
  			
  			if(temp.startsWith("/"))
  			{
  				textField.setText("");
  				
  				if(temp.substring(1).equalsIgnoreCase("off")&& flag==true)
  				{
  					textArea.append("MUSIC OFF\n");
  					flag=false;
  					AS.wavEnd();
  				}
  				else if(temp.substring(1).equalsIgnoreCase("off")&& flag==false)
  				{
  					textArea.append("Music is already off...\n");
  				}
  				else if(temp.substring(1).equalsIgnoreCase("on")&&flag==false)
  				{
  					textArea.append("MUSIC ON\n");
  					flag=true;
  					AS = new SoundsOfMusic("src\\13.wav");
  				}
  				else if(temp.substring(1).equalsIgnoreCase("on")&&flag==true)
  				{
  					textArea.append("Music is already playing...\n");
  				}
  				else
  				{
  					textArea.append("UNDEFINED CONSOLELOG\n");
  				}
  				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
  				
  			}
  			else
  				if(!temp.isEmpty())
  					sendMessage(socket,  temp);
		}
	});
	btnNewButton.setBounds(265, 81, 86, 23);
	chat.add(btnNewButton);
	
	
	ImageIcon a =new ImageIcon("src\\roading2.gif");
	
	this.socket = new Socket();

    try {
        this.socket.connect( new InetSocketAddress(SERVER_IP, SERVER_PORT) );
        AS = new SoundsOfMusic("src\\13.wav");
        ChatClientReceiveThread TH =new ChatClientReceiveThread(this.socket);
        TH.start();

        player_num =TH.mynum;
        A[player_num].setText(ID);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        String request = "join:" + name + "\r\n";
        pw.println(request);
      }
      catch (IOException e) {
        e.printStackTrace();
       new ErrorHandling("SERVER CLOSED");
      }
	setVisible(true);
  }
  
  private void sendMessage(Socket socket, String string) {
	    PrintWriter pw;
	    try {
	      pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
	      String request = "message: " + string + "\r\n";
	      pw.println(request);
	      textField.requestFocus();
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	      new ErrorHandling("sendMessage ERROR");
	    }
	  }
private class ChatClientReceiveThread extends Thread{
	    Socket socket = null;
	    int mynum;
	    ChatClientReceiveThread(Socket socket){
	      this.socket = socket;
	    }
	    

	    public void run() {
	      try {
	        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
	        while(true) {

	          String msg = br.readLine();
	          String token[] = msg.split(":");
	          boolean checker = true;
	          if(token[0].equals("!"))
	          {
	        	  
	            for(int i =0; i<4;i++)// player@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	            {
	            	if(token[i+1].equals("EMPTY"))checker=false;
	            		
	            	if(token[i+1].equals(ID))mynum = i;

	            	A[i].setText(token[i+1]);
	            }
	            
	            if(checker) // CHECKER*************************************************
	            {
	            	try {
						TimerEx countdown = new TimerEx(3,textArea, socket, ID, scrollPane);
					} catch (InterruptedException e) {
						// TODO 자동 생성된 catch 블록
						e.printStackTrace();
					}
	            }
	          }
	          else if(token[0].equals("START")&&token[1].equals("CLIENT"))
	          {
	        	  if(flag == true)
	        		  AS.wavEnd();
	        	  getContentPane().setVisible(false);
	        	  break;
	        	 
	          }else if(token[0].equals("INDEX"))
				{
					MYNUM = Integer.parseInt(token[1]);
				}
	          else{
	          textArea.append(msg+"\n");
	      		textField.setText("");
	      		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	      		}
	        }
	        setVisible(false);
	        new ChatWindow(ID, socket,MYNUM);
	      }
	      catch (IOException e) {
	        e.printStackTrace();
	        new ErrorHandling("Chat recieve Error");
	      }
	    }
	  }
  private static void consoleLog(String log) {

    System.out.println(log);

  }
}