package why;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Rank {
	
	String[] ID = new String[4];
	String[] SCORE = new String[4];
	String[] temp = new String[100];
   JFrame frame;
   String r;
   public Rank(String rank) {
      r=rank;
      temp = r.split(":");
      
      for(int i=0;i<4;i++)
      {
    	  
    	  ID[i] = temp[2*i+1];
    	  SCORE[i] = temp[2*i+2];
      }

      initialize();
      
   }

   private void initialize() {
      frame = new JFrame();
      frame.setBackground(new Color(240, 255, 240));
      frame.setSize(800,600);
      frame.setResizable(false);
      frame.addWindowListener(new WindowAdapter() {
		   public void windowClosing(WindowEvent evt) {
		    System.exit(0);
		   }
		  });
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
      label.setIcon(new ImageIcon("src\\rank.png"));
      label.setBounds(73, 163, 568, 319);
      panel.add(label);
      
      JLabel lblst = new JLabel(ID[0]);
      lblst.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
      lblst.setBounds(168, 150, 104, 21);
      panel.add(lblst);
      
      JLabel label_1 = new JLabel(ID[1]);
      label_1.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
      label_1.setBounds(280, 202, 104, 21);
      panel.add(label_1);
      
      JLabel label_2 = new JLabel(ID[2]);
      label_2.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
      label_2.setBounds(396, 242, 104, 21);
      panel.add(label_2);
      
      JLabel label_3 = new JLabel(ID[3]);
      label_3.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
      label_3.setBounds(514, 270, 104, 21);
      panel.add(label_3);
      frame.setVisible(true);
   }
}