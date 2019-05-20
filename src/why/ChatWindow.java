package why;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import why.TimerEx;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class ChatWindow extends JComponent {
   public int A = 0; // 임시 문제 어레이 번호
   public int score = 0; // 표시되는 score
   private JTextField txtTimer; // timer textfield
   public int TIME_FLAG = 0;
   public String CHAT = ""; // chatting 저장 string
   private JTextArea txtQ; // 질문 text area
   private JTextArea textField_2; // 체팅 area
   private JTextField txtChat; // chating 적는 field
   private TextField textField;
   private JScrollPane scrollpanel; // chatting textfield scroll
   private SoundsOfMusic AS;
   private JScrollPane scrollPane;
   private JButton button[] = new JButton[25];
   private int MYNUM;
   private String[] PLAYERNAME;
   public boolean flag = true;
   int owner[] = { 0, };// grid 소유자 저장
   int p1 = 0, p2 = 0, p3 = 0, p4 = 0;// player들의 점수
   // 각자 저장위치에 따라서 수정해야 함
   private int select = -1;
   int op = 0;

   JLabel lblNewLabel = new JLabel("");// 수정필요

   static ImageIcon white = new ImageIcon("src\\white.PNG");
   static ImageIcon red = new ImageIcon("src\\red.PNG");
   static ImageIcon green = new ImageIcon("src\\green.PNG");
   static ImageIcon blue = new ImageIcon("src\\blue.PNG");
   static ImageIcon yellow = new ImageIcon("src\\yellow.PNG");
   static ImageIcon myColor;

   private String name;
   private JFrame frame;
   private JPanel pannel;
   private JPanel panel_1;
   private Button buttonSend;
   private TextArea textArea;
   private Socket socket;
   private JPanel pan;

   public ChatWindow(String name, Socket socket, int MYNUM) {
	   PLAYERNAME = new String[4];
      myColor = red;
      this.MYNUM = MYNUM;
      this.name = name;
      AS = new SoundsOfMusic("src\\13.wav");
      frame = new JFrame(name);
      frame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent evt) {
            PrintWriter pw;
            try {
               pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8),
                     true);
               String request = "quit:ID";
               pw.println(request);
               textField.requestFocus();
            } catch (IOException e) {
               e.printStackTrace();
               new ErrorHandling("sendMessage ERROR");
            }
            setVisible(false);
            System.exit(0);
         }
      });

      frame.setBackground(new Color(240, 255, 240));
      frame.setSize(1188, 615);
      frame.setResizable(true);

      panel_1 = new JPanel();

      pannel = new JPanel();
      pannel.setBackground(new Color(240, 255, 240));
      pannel.setBounds(0, 0, 500, 600);
      frame.getContentPane().add(pannel);
      pannel.setLayout(null);

      lblNewLabel.setForeground(new Color(0, 0, 0));
      lblNewLabel.setFont(new Font("1훈연필습격 R", Font.BOLD, 17));
      lblNewLabel.setBounds(17, 15, 466, 95);
      pannel.add(lblNewLabel);

      // 기존
      buttonSend = new Button("Send");
      textField = new TextField();
      textArea = new TextArea(30, 80);

      this.socket = socket;

      JButton btnSelected = new JButton("SELECT");
      btnSelected.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { // select 버튼
                                             // ***********************************************************************************8

            if (select == -1) {
               new Print("error");
            } else {
               sendbutton(socket, select + ":" + MYNUM);
               select = -1;
               op = 0;
            }
         }
      });
      btnSelected.setFont(new Font("1훈연필습격 R", Font.PLAIN, 18));
      btnSelected.setBackground(new Color(255, 250, 205));
      btnSelected.setBounds(197, 452, 125, 29);
      pannel.add(btnSelected);
/*
      button[0] = new JButton("New button");
      button[0].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 0;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[1] = new JButton("New button");
      button[1].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 1;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[2] = new JButton("New button");
      button[2].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 2;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[3] = new JButton("New button");
      button[3].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 3;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[4] = new JButton("New button");
      button[4].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 4;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[5] = new JButton("New button");
      button[5].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 5;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[6] = new JButton("New button");
      button[6].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 6;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[7] = new JButton("New button");
      button[7].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 7;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[8] = new JButton("New button");
      button[8].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 8;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[9] = new JButton("New button");
      button[9].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 9;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[10] = new JButton("New button");
      button[10].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 10;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[11] = new JButton("New button");
      button[11].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 11;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[12] = new JButton("New button");
      button[12].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 12;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[13] = new JButton("New button");
      button[13].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 13;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[14] = new JButton("New button");
      button[14].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 14;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[15] = new JButton("New button");
      button[15].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 15;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[16] = new JButton("New button");
      button[16].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 16;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[17] = new JButton("New button");
      button[17].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 17;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[18] = new JButton("New button");
      button[18].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 18;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[19] = new JButton("New button");
      button[19].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 19;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[20] = new JButton("New button");
      button[20].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 20;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[21] = new JButton("New button");
      button[21].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 21;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[22] = new JButton("New button");
      button[22].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 22;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[23] = new JButton("New button");
      button[23].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 23;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });

      button[24] = new JButton("New button");
      button[24].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (op == 1) {
               select = 24;
               for (int i = 0; i < 25; i++) {
                  button[i].setIcon(white);
               }
               button[select].setIcon(myColor);
            }
         }
      });
*/
      for (int i = 0; i < 25; i++) { // *****************************************************
         int x=i;
         button[i] = new JButton("New button");
         button[i].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
               if (op == 1) {
                  select = x;
                  for (int d = 0; d < 25; d++) {
                     if(button[d].isEnabled())
                        button[d].setIcon(white);
                  }
                  button[select].setIcon(myColor);
               }
            }
         });
         
         button[i].setBounds(130 + 55 * (i % 5), 114 + 55 * (i / 5), 50, 50);
         pannel.add(button[i]);
         button[i].setIcon(white);
      } // 흰 바탕으로 초기화

      panel_1.setBackground(new Color(240, 255, 240));
      panel_1.setBounds(501, 0, 699, 600);
      frame.getContentPane().add(panel_1);
      // frame.add(BorderLayout.EAST,panel_1);// 게임 창
      panel_1.setLayout(null);

      JLabel newLabel = new JLabel("");
      newLabel.setHorizontalAlignment(SwingConstants.CENTER);
      newLabel.setBackground(Color.WHITE);
      newLabel.setBounds(42, 15, 476, 476);
      pannel.add(newLabel);

      txtTimer = new JTextField();
      txtTimer.setHorizontalAlignment(SwingConstants.CENTER);
      txtTimer.setFont(new Font("LG PC", Font.BOLD, 25));
      txtTimer.setEditable(false);
      txtTimer.setText("0");
      txtTimer.setBounds(940, 12, 216, 39);
      panel_1.add(txtTimer);
      txtTimer.setColumns(10);

      txtQ = new JTextArea(); // 질문 area
      txtQ.setToolTipText("");
      txtQ.setEditable(false);
      txtQ.setBounds(568, 70, 588, 331);
      panel_1.add(txtQ);
      txtQ.setColumns(10);

      scrollPane = new JScrollPane(); // 채팅 스크롤
      scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      scrollPane.setBounds(568, 411, 588, 105);
      panel_1.add(scrollPane);

      textField_2 = new JTextArea();
      textField_2.setForeground(UIManager.getColor("Button.darkShadow"));
      scrollPane.setViewportView(textField_2);
      textField_2.setBackground(Color.WHITE);
      textField_2.setEditable(false);
      textField_2.setColumns(100);

      txtChat = new JTextField();
      txtChat.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {// 채팅 enter로 입력 가능
            char c;
            c = e.getKeyChar();
            if (c == '\n') {
               String temp = txtChat.getText();

               sendMessage(socket, temp);
               txtChat.setText("");

               /*
                * int position = textField_2.getText().length();
                * textField_2.setCaretPosition(position);
                * txtChat.setText("");
                */
            }
         }
      });
      txtChat.setBounds(568, 527, 476, 21);
      panel_1.add(txtChat);
      txtChat.setColumns(10);

      JButton btnNewButton_2 = new JButton("ENTER");
      btnNewButton_2.setFont(new Font("1훈연필습격 R", Font.PLAIN, 18));
      btnNewButton_2.setBackground(new Color(255, 250, 205));
      btnNewButton_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String temp = txtChat.getText();
            
            if (temp.startsWith("/")) {
               textField.setText("");

               if (temp.substring(1).equalsIgnoreCase("off") && flag == true) {
                  textArea.append("MUSIC OFF\n");
                  flag = false;
                  AS.wavEnd();
               }

               else if (temp.substring(1).equalsIgnoreCase("off") && flag == false) {
                  textArea.append("Music is already off...\n");
               } else if (temp.substring(1).equalsIgnoreCase("on") && flag == false) {
                  textArea.append("MUSIC ON\n");
                  flag = true;
                  AS = new SoundsOfMusic("src\\13.wav");
               } else if (temp.substring(1).equalsIgnoreCase("on") && flag == true) {
                  textArea.append("Music is already playing...\n");
               } else {
                  textArea.append("UNDEFINED CONSOLELOG\n");
               }
               
            } else if(!temp.isEmpty()) {
               sendMessage(socket, temp);
               txtChat.setText("");
            }
            scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
         }
      });
      btnNewButton_2.setBounds(1055, 526, 101, 23);
      panel_1.add(btnNewButton_2);
      frame.setVisible(true);

      new ChatClientReceiveThread(socket).start();

   }

   public void show() {
      // Button
      buttonSend.setBackground(Color.GRAY);
      buttonSend.setForeground(Color.WHITE);
      buttonSend.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {
            sendMessage(socket, txtChat.getText());
            txtChat.setText("");
         }
      });

      // Textfield
      textField.setColumns(80);
      textField.addKeyListener(new KeyAdapter() {
         public void keyReleased(KeyEvent e) {
            char keyCode = e.getKeyChar();
            if (keyCode == KeyEvent.VK_ENTER) {
               String temp = txtChat.getText();

               if (temp.startsWith("/")) {
                  textField.setText("");

                  if (temp.substring(1).equalsIgnoreCase("off") && flag == true) {
                     textArea.append("MUSIC OFF\n");
                     flag = false;
                     AS.wavEnd();
                  }

                  else if (temp.substring(1).equalsIgnoreCase("off") && flag == false) {
                     textArea.append("Music is already off...\n");
                  } else if (temp.substring(1).equalsIgnoreCase("on") && flag == false) {
                     textArea.append("MUSIC ON\n");
                     flag = true;
                     AS = new SoundsOfMusic("src\\13.wav");
                  } else if (temp.substring(1).equalsIgnoreCase("on") && flag == true) {
                     textArea.append("Music is already playing...\n");
                  } else {
                     textArea.append("UNDEFINED CONSOLELOG\n");
                  }
                  
               } else {
                  sendMessage(socket, temp);
                  txtChat.setText("");
               }
               scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
            }
         }
      });

      // frame.add(BorderLayout.CENTER,pannel);
      // Pannel
      panel_1.setBackground(Color.LIGHT_GRAY);
      // textField.setBounds(0,570,400,20);
      // buttonSend.setBounds(420,580, 10, 10);
      panel_1.add(textField);
      panel_1.add(buttonSend);
      // frame.getContentPane().add(buttonSend);
      frame.getContentPane().add(BorderLayout.SOUTH, panel_1);

      // frame.getContentPane().add(textField);
      // pan.setLayout(null);

      // TextArea
      textArea.setEditable(false);
      frame.getContentPane().add(BorderLayout.SOUTH, textArea);
      // textArea.setBounds(0, 540, 400, 20);
      // pannel.add(textArea);
      // frame.getContentPane().add(textArea);

      // Frame

      frame.addWindowListener(new WindowAdapter() {

         public void windowClosing(WindowEvent e) {

            PrintWriter pw;

            try {

               pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8),
                     true);

               String request = "quit\r\n";

               pw.println(request);
               pw.flush();

               System.exit(0);

            }

            catch (IOException e1) {

               e1.printStackTrace();

            }

         }

      });

      frame.setVisible(true);

      frame.pack();

   }

   // 쓰레드를 만들어서 대화를 보내기

   private void sendMessage(Socket socket, String A) {

      PrintWriter pw;

      try {

         pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

         String message = A;

         String request = "Answer:" + message + "\r\n";

         pw.println(request);
         pw.flush();

         txtChat.requestFocus();

      }

      catch (IOException e) {
         new ErrorHandling("sendMessage ERROR");
         e.printStackTrace();

      }

   }

   private void sendbutton(Socket socket, String A) {

      PrintWriter pw;

      try {

         pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

         String message = A;

         String request = "Button:" + message + "\r\n";

         pw.println(request);
         pw.flush();

         txtChat.requestFocus();

      }

      catch (IOException e) {
         new ErrorHandling("sendMessage ERROR");
         e.printStackTrace();

      }

   }

   private class ChatClientReceiveThread extends Thread {

      Socket socket = null;

      ChatClientReceiveThread(Socket socket) {

         this.socket = socket;

      }

      public void run() {

         try {

            BufferedReader br = new BufferedReader(
                  new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            while (true) { // 서버에서 받은 스트링
                        // 처리****************************************************************************8

               String msg = br.readLine();

               String token[] = msg.split(":");

               if (token[0].equals("ANSWER")) {
                  txtQ.setText("");
                  for (int i = 1; i < token.length; i++) {
                      txtQ.append(token[i] + "\n");
                   }
                  
                  try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO 자동 생성된 catch 블록
					e1.printStackTrace();
				}
                  
                  try {
					new TimerEx(15,txtTimer, socket, txtChat);
				} catch (InterruptedException e) {
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				}
                 
                  txtChat.enable(true);
               }
               else if(token[0].equals("format"))
               {
            	   for(int i=0;i<4;i++)
            	    PLAYERNAME[i] = token[i+1];
            	   lblNewLabel.setText(
                           "                     "+PLAYERNAME[0]+" : " + p1 + " "+PLAYERNAME[1]+" : " + p2 + " "+PLAYERNAME[2]+" : " + p3 + " "+PLAYERNAME[3]+" : " + p4);
               }

               else if (token[0].equals("Correct")) {
                  txtQ.append(msg + "\n");

               }

               else if (token[0].equals("yourright")) {
                  op = 1;
                  txtChat.enable(false);
               }

               else if (token[0].equals("player")) {
                  p1 = Integer.parseInt(token[1]);
                  p2 = Integer.parseInt(token[2]);
                  p3 = Integer.parseInt(token[3]);
                  p4 = Integer.parseInt(token[4]);
                  lblNewLabel.setText(
                        "                     "+PLAYERNAME[0]+" : " + p1 + " "+PLAYERNAME[1]+" : " + p2 + " "+PLAYERNAME[2]+" : " + p3 + " "+PLAYERNAME[3]+" : " + p4);
               }

               else if (token[0].equals("score")) {
                  button[Integer.parseInt(token[1])].setIcon(null);
                  button[Integer.parseInt(token[1])].setText(token[2].toString());
                  button[Integer.parseInt(token[1])].setEnabled(false);

               }
               else if(token[0].equals("ENDGAME"))
               {
                  new Rank(msg);
                  
               }

               else if (token[0].equals("start2")) {
                  new TimerEx(frame, 1);
               }

               else  {
                  textField_2.append(msg);

                  textField_2.append("\n");
                  scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
               }

            }

         }

         catch (IOException e) {
            new ErrorHandling("Chat recieve Error");
            e.printStackTrace();

         }

      }

   }

}