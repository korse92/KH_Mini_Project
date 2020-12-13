package movie.ticket.reservation.util;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MyUtil {

	public static void init(JFrame frame, int x, int y, int width, int height) {
		frame.setBounds(x, y, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void changePanel(JFrame f, JPanel current, JPanel next) {
		f.remove(current);
		f.add(next);
		
		//다시 그리기 작업
		f.revalidate();
		f.repaint();
	}
}
