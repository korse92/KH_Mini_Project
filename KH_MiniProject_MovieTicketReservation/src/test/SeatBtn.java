package test;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import test.Test.seatBtnActionListener;

public class SeatBtn extends JFrame {
	
	boolean[] seat = new boolean[100]; //클래스 멤버변수로 선언해주세여
	Color color = new Color(242,240,229);	
	
	public SeatBtn() {
		
		//여기 코드는 좌석선택화면 클래스에 복붙하셔서 쓰면됩니다. 
		JPanel seatPanel = new JPanel();
		seatPanel.setLayout(null);
		seatPanel.setLocation(0, 0);
		
		int seatPanelWidth = 0;
		int seatPanelHeight = 0;
		
		ImageIcon seatIcon = new ImageIcon("images/seatIcon.png"); //같이 첨부된 아이콘 이미지는 프로젝트 폴더밑에 images 만드셔서 넣어주세요
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
		getContentPane().add(seatPanel); //원하는 곳에다 패널 추가
		
	}
	
	//이건 내부클래스로 선언해주세요
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
