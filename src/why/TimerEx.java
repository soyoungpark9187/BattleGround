package why;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class TimerEx{

   JTextField k;
   JTextArea l;
   JFrame f;

	public TimerEx(double TIMES, JTextField k, Socket socket, JTextField txtChat) throws InterruptedException{  // a : timer field  b : Áú¹®area     s : score area  
		this.k=k;
	    Timer timer= new Timer(true);
	    TimerTask task= new Remind(TIMES,k, socket, txtChat);
	    timer.scheduleAtFixedRate(task,1000, 1000);
	 }
	
	public TimerEx(int TIME, JTextArea A, Socket socket, String ID, JScrollPane p) throws InterruptedException{
		Timer timer = new Timer(true);
		TimerTask task = new Remind2(TIME, A, socket, ID,p);
		timer.scheduleAtFixedRate(task, 1000, 1000);
	}
	
	public TimerEx(JFrame f, int TIME)
	{
		TIMERPRINT AT = new TIMERPRINT("");
		this.f=f;
		f.setVisible(false);
		Timer timer=new Timer(true);
		TimerTask task=new Remind3(TIME, f, AT);
		timer.scheduleAtFixedRate(task, 1000, 1000);
		
	}
}