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


public class MainPanel extends JPanel {
	
	public static final int FRAME_X = 450;
	public static final int FRAME_Y = 600;
	
	
	private JFrame mainFrame;
	
	public MainPanel(JFrame f) {
//		MyUtil.init(this, x, y, width, height);
		this.mainFrame = f;
		
		f.setTitle("cgw에 오신걸 환영합니다.");
		f.setSize(FRAME_X, FRAME_Y);
				
		JLabel label = new JLabel();
						
		//화면배경색 지정 바꾸기
		Color c = new Color(242,240,229);
		this.setBackground(c);
			
		//이미지 로고
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc_icon = new GridBagConstraints();
		ImageIcon icon = new ImageIcon("images/logo.png");
		this.setLayout(gbl);
		gbc_icon.gridx = 0;
		gbc_icon.gridx = 0;
		gbc_icon.gridwidth = 2;
//				Image img = icon.getImage();
//				img = img.getScaledInstance(10, 10, Image.SCALE_DEFAULT);
		label.setIcon(icon);
		
		this.add(label, gbc_icon);
//		add(this);
		
		//버튼
		JButton[] btn = new JButton[3];
		GridBagConstraints[] gbc = new GridBagConstraints[3];
		for(int i = 0; i<3; i++) {
			gbc[i] = new GridBagConstraints();
		}
		
		btn[0] = new JButton("회원");	
		gbc[0].gridx = 0;
		gbc[0].gridy = 1;
		gbc[0].insets = new Insets(10,10, 10, 10);
		this.add(btn[0],gbc[0]);
		btn[0].setBackground(Color.red);
		btn[0].setFont(new Font("맑은 고딕",Font.BOLD,11));
		btn[0].setForeground(Color.white);
		btn[0].setFont(btn[0].getFont().deriveFont(25.0f));
		btn[0].setPreferredSize(new Dimension(150, 50));
			
		btn[1] = new JButton("비회원");	
		gbc[1].gridx = 1;
		gbc[1].gridy = 1;
		gbc[1].insets = new Insets(10,10, 10, 10);
		this.add(btn[1],gbc[1]);
		btn[1].setBackground(Color.red);
		btn[1].setFont(new Font("맑은 고딕",Font.BOLD,11));
		btn[1].setForeground(Color.white);
		btn[1].setFont(btn[1].getFont().deriveFont(25.0f));
		btn[1].setPreferredSize(new Dimension(150, 50));
		
		btn[2] = new JButton("회원가입");
		gbc[2].gridx = 0;
		gbc[2].gridy = 2;
		gbc[2].gridwidth = 0;
		gbc[2].insets = new Insets(10,10, 10, 10);
		this.add(btn[2],gbc[2]);
		btn[2].setBackground(Color.red);
		btn[2].setFont(new Font("맑은 고딕",Font.BOLD,11));
		btn[2].setForeground(Color.white);
		btn[2].setFont(btn[2].getFont().deriveFont(25.0f));
		btn[2].setPreferredSize(new Dimension(320, 50));

		
		btn[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "회원입니다.");
				mainFrame.setSize(LoginPanel.FRAME_X, LoginPanel.FRAME_Y);
				MainFrame.loginPanel.tabbedPaneSelIdx(0); //회원 로그인탭으로 전환
				MyUtil.changePanel(mainFrame, MainPanel.this, MainFrame.loginPanel);
			}
		});
		btn[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "비회원입니다.");
				mainFrame.setSize(LoginPanel.FRAME_X, LoginPanel.FRAME_Y);
				MainFrame.loginPanel.tabbedPaneSelIdx(1); //비회원 로그인탭으로 전환
				MyUtil.changePanel(mainFrame, MainPanel.this, MainFrame.loginPanel);
			}
		});
		btn[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "회원가입하겠습니다.");
				mainFrame.setSize(JoinPanel.FRAME_X, JoinPanel.FRAME_Y);
				MyUtil.changePanel(mainFrame, MainPanel.this, MainFrame.joinPanel);
				
			}
		});


		

	}
	
}
