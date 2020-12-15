package movie.ticket.reservation.view.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import movie.ticket.reservation.util.MyUtil;
import movie.ticket.reservation.view.MainFrame;

public class ReservationMainPanel extends JPanel {

	private JFrame mainFrame;

	public static final int FRAME_WIDTH = 500;
	public static final int FRAME_HEIGHT = 500;

	public ReservationMainPanel(JFrame f) {
		this.mainFrame = f;
		f.setTitle("cgw에 오신걸 환영합니다.");
//		setLayout(null);

		JPanel panel = new JPanel();
		JLabel label = new JLabel();

		// 화면 가운데 위치 시키기
//		setLocationRelativeTo(null);

		// 화면배경색 지정 바꾸기
		Color c = new Color(242, 240, 229);
		panel.setBackground(c);

		// 이미지 로고
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc_icon = new GridBagConstraints();
		ImageIcon icon = new ImageIcon("images/logo.png");
		panel.setLayout(gbl);
		gbc_icon.gridx = 0;
		gbc_icon.gridx = 0;
		gbc_icon.gridwidth = 2;
//				Image img = icon.getImage();
//				img = img.getScaledInstance(10, 10, Image.SCALE_DEFAULT);
		label.setIcon(icon);

		panel.add(label, gbc_icon);
		add(panel);

		// 버튼
		JButton[] btn = new JButton[2];
		GridBagConstraints[] gbc = new GridBagConstraints[2];
		for (int i = 0; i < 2; i++) {
			gbc[i] = new GridBagConstraints();
		}
		btn[0] = new JButton("영화 예매");
		gbc[0].gridx = 0;
		gbc[0].gridy = 1;
		gbc[0].insets = new Insets(10, 10, 10, 10);
		panel.add(btn[0], gbc[0]);
		btn[0].setBackground(Color.red);
		btn[0].setFont(new Font("맑은 고딕", Font.BOLD, 11));
		btn[0].setForeground(Color.white);
		btn[0].setFont(btn[0].getFont().deriveFont(25.0f));
		btn[0].setPreferredSize(new Dimension(320, 50));

		btn[1] = new JButton("영화 예매 확인");
		gbc[1].gridx = 0;
		gbc[1].gridy = 2;
		gbc[1].gridwidth = 0;
		gbc[1].insets = new Insets(10, 10, 10, 10);
		panel.add(btn[1], gbc[1]);
		btn[1].setBackground(Color.red);
		btn[1].setFont(new Font("맑은 고딕", Font.BOLD, 11));
		btn[1].setForeground(Color.white);
		btn[1].setFont(btn[1].getFont().deriveFont(25.0f));
		btn[1].setPreferredSize(new Dimension(320, 50));

//		mainFrame.setContentPane(panel);

		btn[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "영화예매하겠습니다.");
				mainFrame.setSize(SelectionMoviePanel.FRAME_WIDTH, SelectionMoviePanel.FRAME_HEIGHT);
				MyUtil.changePanel(mainFrame, ReservationMainPanel.this, MainFrame.selectionMovie);
				MainFrame.reservationMain = new ReservationMainPanel(mainFrame);

			}
		});
		btn[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "영화예매확인하겠습니다.");
				mainFrame.setSize(MovieTicketViewPanel.FRAME_WIDTH, MovieTicketViewPanel.FRAME_HEIGHT);
				MyUtil.changePanel(mainFrame, ReservationMainPanel.this, MainFrame.movieTicketView);
			}
		});

	}

}
