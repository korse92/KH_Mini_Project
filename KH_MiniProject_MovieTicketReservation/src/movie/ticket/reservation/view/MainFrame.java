package movie.ticket.reservation.view;

import javax.swing.JFrame;

import movie.ticket.reservation.controller.ReservationController;
import movie.ticket.reservation.util.MyUtil;
import movie.ticket.reservation.view.panel.BookingDetailPayPanel;
import movie.ticket.reservation.view.panel.JoinPanel;
import movie.ticket.reservation.view.panel.LoginPanel;
import movie.ticket.reservation.view.panel.MainPanel;
import movie.ticket.reservation.view.panel.MovieTicketViewPanel;
import movie.ticket.reservation.view.panel.ReservationMainPanel;
import movie.ticket.reservation.view.panel.SelectSeatPanel;
import movie.ticket.reservation.view.panel.SelectionMoviePanel;

public class MainFrame extends JFrame {
	
	
	public static ReservationView rv = new ReservationView();
	public static ReservationController rc = new ReservationController();
	
	public static MainPanel mainPanel;
	public static LoginPanel loginPanel;
	public static JoinPanel joinPanel;
	public static SelectionMoviePanel selectionMovie;
	public static BookingDetailPayPanel bookingDetailPayPanel;
	public static MovieTicketViewPanel movieTicketView;
	public static ReservationMainPanel reservationMain;
	public static SelectSeatPanel selectSeatPanel;
	
	
	
	public MainFrame(int x, int y, int width, int height) {
		
		MyUtil.init(this, x, y, width, height);

		mainPanel = new MainPanel(this); //메인
		joinPanel = new JoinPanel(this); //회원가입
		loginPanel = new LoginPanel(this); //로그인
		reservationMain = new ReservationMainPanel(this); //예매 메인
		selectionMovie = new SelectionMoviePanel(this); //영화선택
		selectSeatPanel = new SelectSeatPanel(this); //좌석선택
		bookingDetailPayPanel = new BookingDetailPayPanel(this); //결제
		movieTicketView = new MovieTicketViewPanel(this); //영화티켓확인

		add(mainPanel);//메인//
//		add(joinPanel);//회원가입//
//		add(selectionMovie);//영화선택
//		add(bookingDetailPayPanel);//결제창/
//		add(movieTicketView);//영화티켓//
//		add(reservationMain);//영화예매/확인창//
//		add(selectSeatPanel);//좌석/
		
		//화면 가운데 위치 시키기
		setLocationRelativeTo(null);
		
	}

}
