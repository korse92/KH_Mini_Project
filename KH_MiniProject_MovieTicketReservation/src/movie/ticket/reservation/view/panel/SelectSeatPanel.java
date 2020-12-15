package movie.ticket.reservation.view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import movie.ticket.reservation.util.MyUtil;
import movie.ticket.reservation.view.MainFrame;

public class SelectSeatPanel extends JPanel {
	
	private JFrame mainFrame;
	private Color color = new Color(242,240,229);
	
	private boolean[] seat = new boolean[100]; //실프로그램에서는 스크린객체에서 배열주소값을 넘겨받아야 함.
	
	private JButton[] adultBtnArr = new JButton[10];
	private JButton[] teenBtnArr = new JButton[10];
	
	private JTextArea mySeat;
	
	private int selectedSeat = 0;
	
	private int adultNum = 0;
	private int teenNum = 0;
	
	private int sum = 0;
	
	private final int COLUMN_MAX = 10; //좌석 최대 행 개수 -> 좌석그릴때, 선택한 좌석배열 번호찾을때 사용
	private final int SELECT_MAX = 8; //최대 인원 제한을 상수로 선언하였음

	private JPanel seatPanel;
	private JPanel top;
	

	
	public static final int FRAME_WIDTH = 1086;
	public static final int FRAME_HEIGHT = 800;
	
	public SelectSeatPanel(MainFrame f) {
		
		this.mainFrame = f;
		
		mainFrame.setTitle("좌석 예약");
		f.setSize(1086, 800);
		
		this.setBackground(color);
		
		setLayout(null);
		
		top = new JPanel();
		top.setSize(1086, 800);
		
		//인원 선택
		JPanel ppl = new JPanel();
		ppl.setBounds(0, 0, 1086, 200);
		ppl.setBackground(color);
		ppl.setLayout(null);
//		getContentPane().add(ppl);
		
		
		//일반, 청소년 label
		JLabel adult = new JLabel("일반");
		adult.setBounds(50, 50, 100, 30);
		adult.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		ppl.add(adult);
		JLabel teen = new JLabel("청소년");
		teen.setBounds(50, 120, 100, 30);
		teen.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		ppl.add(teen);
		
		//인원 선택 버튼 (일반)
		adultBtnArr = new JButton[9];
		
		for(int i = 0; i < adultBtnArr.length; i++) {

			adultBtnArr[i] = new JButton("" + i);
			adultBtnArr[i].setSize(50, 50);
			adultBtnArr[i].setLocation(200 + i * 70, 40);
			adultBtnArr[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			adultBtnArr[i].setBackground(color);

			adultBtnArr[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JButton adultBtn = (JButton) e.getSource();

					for (int i = 0; i < adultBtnArr.length; i++) {
						if (adultBtnArr[i] == adultBtn)
							adultBtnArr[i].setBackground(Color.pink);							
						else						
							adultBtnArr[i].setBackground(color);
					}

					adultNum = Integer.parseInt(adultBtn.getText());
					
					//여기서부터
					if (adultNum + teenNum > SELECT_MAX) {
						JOptionPane.showMessageDialog(null, "최대인원 수인 " + SELECT_MAX + "명을 넘었습니다.");
					}

					sum = adultNum + teenNum;
				}
			});

			ppl.add(adultBtnArr[i]);
			
		}
		
		
		


		
		// 인원 선택 버튼 (청소년)
		teenBtnArr = new JButton[9];

		for (int i = 0; i < teenBtnArr.length; i++) {

			teenBtnArr[i] = new JButton("" + i);
			teenBtnArr[i].setSize(50, 50);
			teenBtnArr[i].setLocation(200 + i * 70, 110);
			teenBtnArr[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			teenBtnArr[i].setBackground(color);

			teenBtnArr[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JButton teenBtn = (JButton) e.getSource();

					for (int i = 0; i < teenBtnArr.length; i++) {
						if (teenBtnArr[i] == teenBtn)
							teenBtnArr[i].setBackground(Color.pink);
						else
							teenBtnArr[i].setBackground(color);
					}

					teenNum = Integer.parseInt(teenBtn.getText());

					if (adultNum + teenNum > 8) {
						JOptionPane.showMessageDialog(null, "최대인원 수인 " + SELECT_MAX + "명을 넘었습니다.");
					}

					sum = adultNum + teenNum;
				}
			});

			ppl.add(teenBtnArr[i]);

		}
		
		
		//좌석 선택 + Screen panel
		JPanel seatBackground = new JPanel();
		seatBackground.setLayout(null);
		seatBackground.setBounds(0, 200, 1086, 600);
		seatBackground.setBackground(color);
		add(seatBackground);

		//Screen panel
		JPanel screen = new JPanel();
		screen.setBounds(0, 0, 800, 50);
		screen.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		screen.setBackground(Color.DARK_GRAY);
		seatBackground.add(screen);
		
		//Screen label
		JLabel screenL = new JLabel("SCREEN");
		screenL.setForeground(Color.GRAY);
		screenL.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		screen.add(screenL);
		
		
		//좌석배열
		seatPanel = new JPanel();
		seatPanel.setLayout(null);
		seatPanel.setLocation(20, 60);
		
		drawSeat();		
		
		seatBackground.add(seatPanel);	
		
		//좌석 표시
		//선택좌석
		JButton choiceBtn = new JButton();
		choiceBtn.setBounds(850, 50, 20, 20);
		choiceBtn.setBackground(Color.green);
		seatBackground.add(choiceBtn);
		JLabel choiceL = new JLabel("선택");
		choiceL.setBounds(880, 40, 40, 40);
		choiceL.setForeground(Color.GRAY);
		choiceL.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		seatBackground.add(choiceL);
		
		//예매완료좌석
		JButton soldBtn = new JButton();
		soldBtn.setBounds(850, 80, 20, 20);
		soldBtn.setBackground(Color.DARK_GRAY);
		seatBackground.add(soldBtn);
		JLabel soldL = new JLabel("예매완료");
		soldL.setBounds(880, 70, 70, 40);
		soldL.setForeground(Color.GRAY);
		soldL.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		seatBackground.add(soldL);
		
		//선택불가좌석
		JButton unableBtn = new JButton();
		unableBtn.setBounds(850, 110, 20, 20);
		unableBtn.setBackground(Color.RED);
		seatBackground.add(unableBtn);
		JLabel unableL = new JLabel("선택불가");
		unableL.setBounds(880, 100, 70, 40);
		unableL.setForeground(Color.GRAY);
		unableL.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		seatBackground.add(unableL);
		
		
		
		//선택좌석 출력 label
		JLabel mySeatL = new JLabel("선택 좌석");
		mySeatL.setBounds(880, 150, 100, 50);
		mySeatL.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		seatBackground.add(mySeatL);
		
		//선택좌석 출력
		mySeat = new JTextArea();
		mySeat.setBounds(840, 200, 180, 200);
//		mySeat.setEditable(false);
		mySeat.setBackground(color);
		mySeat.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		seatBackground.add(mySeat);
		mySeat.setText("");		
		
		//예매버튼
		JButton payBtn = new JButton("예매");
		payBtn.setBounds(850, 450, 150, 50);
		payBtn.setForeground(color.white);
		payBtn.setBackground(new Color(255, 0, 0));
		payBtn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		seatBackground.add(payBtn);
		
		payBtn.addActionListener(new ActionListener() {
			int result;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedSeat < sum || selectedSeat == 0) {
					JOptionPane.showMessageDialog(null, "좌석을 선택해 주세요.");
					return;
				}
				else if(result == JOptionPane.NO_OPTION)
					return;	
				else if (selectedSeat == sum) {
					result = JOptionPane.showConfirmDialog(null, "결제 하시겠습니까?", "", JOptionPane.YES_NO_OPTION);
					
					if (result == JOptionPane.CLOSED_OPTION) {					
						return;
					}
					else if (result == JOptionPane.YES_OPTION) {

						String[] s = mySeat.getText().split("\n");
						for (int i = 0; i < s.length; i++) {
													
							//버튼 문자열의 첫번재 문자를 반환(정수로 계산을 위해서 int형 변수에 받았음)
							int seatColumn = s[i].charAt(0); //선택한 좌석이 몇행인지 알 수 있다.
							String seatRowStr = s[i].substring(1); //버튼 문자열의 1번인덱스부터 끝까지 반환 -> 선택한 좌석이 몇열인지 알수 있다.
							int seatRow = Integer.parseInt(seatRowStr); //계산을 위해 정수로 변환
							
							
							//A는 아스키코드 값이 65라서 A에서 -1뺀 값인 64를 빼주면 A~Z행이 각각 숫자로 몇번째 행인지 알수 있다.
							seatColumn -= 'A'-1; 
							
							//선택한 좌석에 전체시트를 통틀어 몇번째 시트인지 계산해준다.
							//(현재 행-1)*전체열갯수 +현재행번호
							int numOfSeat = (seatColumn-1)*COLUMN_MAX+seatRow; //필드에 상수로 최대열의 갯수를 선언해두었음
							
							System.out.println("s : " + s[i]);
							System.out.println(numOfSeat);
							
							seat[numOfSeat-1] = true; //배열 인덱스로 사용						

						}
						
						JOptionPane.showMessageDialog(null, "예약이 완료되었습니다!");
						
						//확인을 위해 시트 다시 그리기 작업
						drawSeat();
						revalidate();
						repaint();
						//이 아래에 다음 패널로 바꿔주는 코드 작성
						mainFrame.setSize(BookingDetailPayPanel.FRAME_WIDTH, BookingDetailPayPanel.FRAME_HEIGHT);
						MyUtil.changePanel(mainFrame, SelectSeatPanel.this, MainFrame.bookingDetailPayPanel);
						MainFrame.bookingDetailPayPanel = new BookingDetailPayPanel(mainFrame);

					} else
						return;

				}
				
			}
		});
		
		add(top.add(ppl));
		add(top.add(seatBackground));		
	}
	
	public void drawSeat() {
		
		seatPanel.removeAll();
		
		int seatPanelWidth = 0;
		int seatPanelHeight = 0;
		
		ImageIcon seatIcon = new ImageIcon("images/seatIcon.png");
		Image seatImg = seatIcon.getImage();
		
		int column = 0; //행
		int row = 0;//열
		
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
	}
	

	
	public class seatBtnActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(sum);
			
			if(e.getSource() instanceof JButton) {
			
				JButton btn = (JButton) e.getSource();
				String seatName = btn.getText();

				if (btn.getBackground().equals(Color.green)) {
					btn.setBackground(color);
					selectedSeat--;
					String cancelSeat = mySeat.getText();
					mySeat.setText(cancelSeat.replaceAll(btn.getText()+ "\n", ""));
					
				} else {
					if(selectedSeat < sum && selectedSeat >= 0 && sum <= SELECT_MAX) {
						btn.setBackground(Color.green);
						selectedSeat++;
						mySeat.append(seatName + "\n");
						
					} else if (sum == 0) {
						JOptionPane.showMessageDialog(null, "인원수를 먼저 선택해주세요.");
					} else if(sum > SELECT_MAX) {						
						JOptionPane.showMessageDialog(null, "최대인원 수인 " + SELECT_MAX + "명을 넘었습니다.\n 인원을 다시 선택해주세요");						
					}
					else {
						JOptionPane.showMessageDialog(null, "선택 인원수를 초과할 수 없습니다.");
					}	
				}			
			}
		}
		
	}

}
