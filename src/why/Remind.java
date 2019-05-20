package why;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.TimerTask;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Remind extends TimerTask {
	double reminded;
	Socket socket;
	JTextField k;
	JTextArea s;
	JTextArea l;
	double ad;
	JTextField txtChat;

	public Remind(double remind, JTextField k, Socket socket, JTextField txtChat) {
		reminded = remind;
		this.txtChat = txtChat;
		this.k = k;
		this.socket = socket;
	}

	@Override
   public void run() {
      if(reminded==0) {
    	  k.setText("TIMES UP");
    	 
    	  if(txtChat.isEnabled())
    	  {
          PrintWriter pw;

          try {

             pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);


             String request = "TIMESUP:" + "\r\n";

             pw.println(request);
             pw.flush();



          }

          catch (IOException e) {
             new ErrorHandling("sendMessage ERROR");
             e.printStackTrace();

          }
    	  }
          
    	  this.cancel();
      }
      else if(reminded>0) {

      reminded = reminded - 1;
      String X =String.format("%.0f",reminded);
      k.setText(X);
      }
      else return;
   }
}