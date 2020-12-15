package test;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import movie.ticket.reservation.util.MyUtil;

public class Test extends JFrame {
	
	boolean[] seat = new boolean[100];
	Color color = new Color(242,240,229);
//	JFrame mainFrame;
	
	{
		seat[0] = true;
		seat[1] = true;
	}
	
	public static void main(String[] args) {
		new Test(0, 0, 1000, 1000).setVisible(true);
	}
	
	public Test(int x, int y, int width, int height) {
//		mainFrame = f;
//		setLayout(null);
//		setSize(1100, 500);
//		f.setSize(getWidth(), getHeight());
//		setBackground(new Color(242,240,229));
//		
//		JPanel moviePane = new JPanel();
//		moviePane.setLayout(new FlowLayout(FlowLayout.LEFT));
//		moviePane.setBounds(0, 0, getWidth()/4, getHeight());
//		moviePane.setBackground(new Color(0,0,255));		
//		
//		JList<String> movieJList = new JList<String>(MainFrame.rv.getMovieList());
//		movieJList.setFixedCellWidth(moviePane.getWidth()-30);
//		
//		JScrollPane scrollPaneMovie = new JScrollPane(movieJList);
//		moviePane.add(scrollPaneMovie);
//		
//		add(moviePane);
		MyUtil.init(this, 0, 0, 1000, 1000);	
		getContentPane().setLayout(null);
		setBackground(Color.BLUE);
		
		JPanel seatPanel = new JPanel();
		seatPanel.setLayout(null);
		seatPanel.setLocation(0, 0);
		
		int seatPanelWidth = 0;
		int seatPanelHeight = 0;
		
		ImageIcon seatIcon = new ImageIcon("images/seatIcon.png");
		Image seatImg = seatIcon.getImage();
		
		int column = 0; //행
		int row = 0;//열
		final int COLUMN_MAX = 10; //좌석 최대 행 개수
		char rowChar = 'A';
		for(int i = 0; i < seat.length; i++) {				
				//예약된자리 버튼 만들고
				JButton btn = new JButton(""+ rowChar +(column+1));
				btn.setSize(70, 40);
				btn.setLocation(column++*btn.getWidth(), row*btn.getHeight());
				btn.setEnabled(!seat[i]); //setEnabled -> true면 컴포넌트 활성화, false면 비활성화이므로
				//seat[i] true면 예약된 자리라서 버튼 비활성화, false면 빈자리니까 버튼 활성화 시켜줘야함
				
				seatImg = seatImg.getScaledInstance(btn.getWidth(), btn.getHeight(), Image.SCALE_DEFAULT);
				btn.setBackground(color);
				btn.setIcon(new ImageIcon(seatImg));
				btn.setHorizontalTextPosition(JButton.CENTER);
				btn.setVerticalTextPosition(JButton.CENTER);
				btn.addActionListener(new seatBtnActionListener());
				
				if(i < 1) { //딱한번만 버튼너비/높이로 전체 패널 너비/높이 계산
					seatPanelWidth = btn.getWidth()*10;
					seatPanelHeight = btn.getHeight()*seat.length/10;
				}
				
				seatPanel.add(btn);
							
			if(column >= COLUMN_MAX) {
				row++;
				column = 0;
				rowChar++;
			}
		}
		
		seatPanel.setSize(seatPanelWidth, seatPanelHeight);
		getContentPane().add(seatPanel);

	}
	
	public class seatBtnActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			if(btn.getBackground().equals(Color.WHITE) )
				btn.setBackground(Color.YELLOW);
			else if(btn.getBackground().equals(Color.YELLOW))
				btn.setBackground(Color.WHITE);
			
		}
		
	}
}
