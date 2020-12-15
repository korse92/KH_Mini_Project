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

public class BookingDetailPayPanel extends JPanel {
	private JFrame mainFrame;
	Color color = new Color(242,240,229);
	
	public static final int FRAME_WIDTH = 485;
	public static final int FRAME_HEIGHT = 690;
	
	public BookingDetailPayPanel(JFrame f) {
		this.mainFrame = f;
		f.setTitle("결제");	
		
		f.setBounds(100, 100, 485, 690);
//		f.setResizable(true);
		setLayout(null);
		
//		JPanel contetnPane = new JPanel();
//		f.getContentPane().setLayout(null);
		


		
		//Title panel
		JPanel t1 = new JPanel();
		t1.setBounds(0, 0, 482, 50);
		t1.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		t1.setBackground(color);
		JLabel l1 = new JLabel("예매정보");
		l1.setForeground(Color.GRAY);
		l1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		t1.add(l1);
		
		///Title panel
		JPanel t2 = new JPanel();
		t2.setBounds(0, 350, 482, 50);
		t2.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		t2.setBackground(color);
		JLabel l2 = new JLabel("결제");
		l2.setForeground(Color.GRAY);
		l2.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		t2.add(l2);
		
		add(t1);
		add(t2);
		
		
		//예약내역 panel
		JPanel rsv = new JPanel();
		rsv.setBounds(0, 45, 482, 308);
		rsv.setBackground(color);
		rsv.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		rsv.setLayout(null);
		add(rsv);
		
		JLabel rsvL1 = new JLabel("영화명");
		rsvL1.setBounds(200, 50, 100, 30);
		rsvL1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		rsv.add(rsvL1);
		
		JLabel rsvL2 = new JLabel("러닝타임");
		rsvL2.setBounds(200, 80, 100, 30);
		rsvL2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		rsv.add(rsvL2);
		
		JLabel rsvL3 = new JLabel("극장");
		rsvL3.setBounds(200, 110, 100, 30);
		rsvL3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		rsv.add(rsvL3);
		
		JLabel rsvL4 = new JLabel("상영관");
		rsvL4.setBounds(200, 140, 100, 30);
		rsvL4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		rsv.add(rsvL4);
		
		JLabel rsvL5 = new JLabel("일시");
		rsvL5.setBounds(200, 170, 100, 30);
		rsvL5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		rsv.add(rsvL5);
		
		JLabel rsvL6 = new JLabel("인원");
		rsvL6.setBounds(200, 200, 100, 30);
		rsvL6.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		rsv.add(rsvL6);
		
		JLabel rsvL7 = new JLabel("좌석");
		rsvL7.setBounds(200, 230, 100, 30);
		rsvL7.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		rsv.add(rsvL7);
		
		
		
		
		//결제 panel
		JPanel pay = new JPanel();
		pay.setBounds(0, 400, 482, 253);
		pay.setBackground(color);
		pay.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		pay.setLayout(null);
//		top.add(pay);
		add(pay);
		
		JLabel payL1 = new JLabel("결제방법");
		payL1.setBounds(100, 50, 100, 50);
		payL1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		pay.add(payL1);
		
		//결제방법 선택
		JRadioButton cardBtn = new JRadioButton("카드");
		cardBtn.setBounds(200, 50, 80, 50);
		cardBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		cardBtn.setBackground(color);
		pay.add(cardBtn);
		JRadioButton cashBtn = new JRadioButton("현금");
		cashBtn.setBounds(300, 50, 80, 50);
		cashBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		cashBtn.setBackground(color);
		pay.add(cashBtn);
		
		//결제방법 버튼 그룹핑
		ButtonGroup group = new ButtonGroup();
		group.add(cardBtn);
		group.add(cashBtn);
		
		
		JLabel payL2 = new JLabel("결제금액");
		payL2.setBounds(100, 80, 100, 50);
		payL2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		pay.add(payL2);
		
		
		//결제버튼
		JButton payBtn = new JButton("결제");
		payBtn.setBounds(170, 170, 150, 50);
		payBtn.setForeground(color.white);
		payBtn.setBackground(new Color(255, 0, 0));
		payBtn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		pay.add(payBtn);
		
		payBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.");
				mainFrame.setSize(MovieTicketViewPanel.FRAME_WIDTH, MovieTicketViewPanel.FRAME_HEIGHT);
				MyUtil.changePanel(mainFrame, BookingDetailPayPanel.this, MainFrame.movieTicketView);
//				MainFrame.movieTicketView = new MovieTicketViewPanel(mainFrame);
				
			}
		});

		

		
		
	}
	
	
}
