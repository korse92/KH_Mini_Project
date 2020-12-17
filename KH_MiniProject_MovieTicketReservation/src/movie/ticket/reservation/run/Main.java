package movie.ticket.reservation.run;

import movie.ticket.reservation.view.MainFrame;
import movie.ticket.reservation.view.panel.SelectionMoviePanel;

public class Main {

	public static void main(String[] args) {
		
		MainFrame f = new MainFrame(0,0,0,0);
		
		f.setSize(SelectionMoviePanel.FRAME_WIDTH, SelectionMoviePanel.FRAME_HEIGHT);
		f.setVisible(true);
		
	}

}
