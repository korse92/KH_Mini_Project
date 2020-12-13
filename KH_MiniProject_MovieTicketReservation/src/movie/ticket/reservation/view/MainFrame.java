package movie.ticket.reservation.view;

import javax.swing.JFrame;

import movie.ticket.reservation.util.MyUtil;
import movie.ticket.reservation.view.panel.JoinPanel;
import movie.ticket.reservation.view.panel.LoginPanel;
import movie.ticket.reservation.view.panel.MainPanel;

public class MainFrame extends JFrame {
	
	public static MainPanel mainPanel;
	public static LoginPanel loginPanel;
	public static JoinPanel joinPanel;
	
	public MainFrame(int x, int y, int width, int height) {
		MyUtil.init(this, x, y, width, height);
		
		mainPanel = new MainPanel(this);
		loginPanel = new LoginPanel(this);
		joinPanel = new JoinPanel(this);		
		
		add(mainPanel);
		
		//화면 가운데 위치 시키기
		setLocationRelativeTo(null);
		
	}

}
