package why;

import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Remind3 extends TimerTask {
	double reminded;
	JFrame f;
	private TIMERPRINT Ax;

	public Remind3(double reminded, JFrame f, TIMERPRINT Ax) {
		this.reminded = reminded;
		this.f = f;
		this.Ax = Ax;
	}

	@Override
	public void run() {
		if (reminded == 0) {
			
			
			Ax.setVisible(false);
			f.setVisible(true);
			this.cancel();
		} else if (reminded > 0) {

			reminded--;
			
		} else
			return;
	}
}