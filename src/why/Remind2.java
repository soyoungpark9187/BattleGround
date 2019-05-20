package why;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.TimerTask;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Remind2 extends TimerTask {
	int reminded;
	JTextArea A;
	Socket socket;
	String ID;
	JScrollPane dd;

	public Remind2(int remind, JTextArea A, Socket socket, String ID, JScrollPane dd) {
		reminded = remind;
		this.dd=dd;
		this.ID = ID;
		this.A = A;
		this.socket = socket;
	}

	@Override
	public void run() {
		if (reminded == 0) {
			// k.setText("TIMES UP");
			A.setText("START");
			PrintWriter pw;
			try {
				pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
				String request = "START:" + ID;
				pw.println(request);

			} catch (IOException e) {
				e.printStackTrace();
			}
			this.cancel();
		} else if (reminded > 0) {
			A.append("" + reminded + "\n");
			dd.getVerticalScrollBar().setValue(dd.getVerticalScrollBar().getMaximum());
			reminded = reminded - 1;
		} else
			return;
	}
}