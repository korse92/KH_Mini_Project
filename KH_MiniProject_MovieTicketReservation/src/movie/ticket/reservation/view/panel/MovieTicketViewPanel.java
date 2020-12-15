package movie.ticket.reservation.view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import movie.ticket.reservation.util.MyUtil;
import movie.ticket.reservation.view.MainFrame;

public class MovieTicketViewPanel extends JPanel {
	private JFrame mainFrame;
	public static final int FRAME_WIDTH = 450;
	public static final int FRAME_HEIGHT = 470;
	Color color = new Color(242,240,229);
	
	public MovieTicketViewPanel(JFrame f) {
		this.mainFrame = f;
		f.setTitle("티켓");
		setLayout(null); //얘는 없으면 화면이 안뜸..
//		f.setBounds(100, 100, 450, 470);
//		f.setResizable(true);
//		JPanel contetnPane = new JPanel();
//		f.getContentPane().setLayout(null);
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.getContentPane().setLayout(null);
//		f.setVisible(true);
		
		//예매번호 panel
		JPanel t1 = new JPanel();
		t1.setBounds(0, 0, 430, 50);
		t1.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		t1.setBackground(color);

		JLabel l1 = new JLabel("예매번호");
		l1.setForeground(Color.GRAY);
		l1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		t1.add(l1);
		
		add(t1);
		
		
		//티켓 panel
		JPanel ticket = new JPanel();
		ticket.setBounds(0, 45, 430, 380);
		ticket.setBackground(color);
		ticket.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		ticket.setLayout(null);
		add(ticket);
		
		JLabel ticketL1 = new JLabel("영화명");
		ticketL1.setBounds(258, 51, 100, 30);
		ticketL1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ticket.add(ticketL1);
		
		JLabel ticketL2 = new JLabel("관람등급");
		ticketL2.setBounds(170, 51, 100, 30);
		ticketL2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ticket.add(ticketL2);
		
		JLabel ticketL3 = new JLabel("극장");
		ticketL3.setBounds(170, 110, 100, 30);
		ticketL3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ticket.add(ticketL3);
		
		JLabel ticketL4 = new JLabel("상영관");
		ticketL4.setBounds(170, 140, 100, 30);
		ticketL4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ticket.add(ticketL4);
		
		JLabel ticketL5 = new JLabel("일시");
		ticketL5.setBounds(170, 170, 100, 30);
		ticketL5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ticket.add(ticketL5);
		
		JLabel ticketL6 = new JLabel("인원");
		ticketL6.setBounds(170, 200, 100, 30);
		ticketL6.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ticket.add(ticketL6);
		
		JLabel ticketL7 = new JLabel("좌석");
		ticketL7.setBounds(170, 230, 100, 30);
		ticketL7.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ticket.add(ticketL7);
		
		
		//티켓출력 버튼
		JButton printBtn = new JButton("티켓출력");
		printBtn.setBounds(30, 280, 360, 70);
		printBtn.setForeground(color.white);
		printBtn.setBackground(new Color(255, 0, 0));
		printBtn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		ticket.add(printBtn);
		
		
		printBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "출력완료!");
				mainFrame.setSize(ReservationMainPanel.FRAME_WIDTH , ReservationMainPanel.FRAME_HEIGHT);
				MyUtil.changePanel(mainFrame, MovieTicketViewPanel.this, MainFrame.reservationMain);
			}
		});

		
		
	}
	


}
