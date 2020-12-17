package movie.ticket.reservation.view.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import movie.ticket.reservation.comp.ScreenNameAscending;
import movie.ticket.reservation.model.vo.Screen;
import movie.ticket.reservation.view.MainFrame;

public class SelectionMoviePanel extends JPanel {

	public static final int FRAME_WIDTH = 1400;
	public static final int FRAME_HEIGHT = 645;

	JButton[] dayBtnArr;

	Color paneColor = new Color(216, 209, 175);

	private JTextField textField;
	private JTextField movieTextField;
	private JTextField theaterTextField;
//	private JList list;
//	private JList mlist;
	private JList theaterList;

	private JButton theaterBtn1;
	private JButton theaterBtn2;
	private JButton theaterBtn3;
	private JButton theaterBtn4;
	private JButton theaterBtn5;
	private JButton theaterBtn6;
	private JButton theaterBtn7;
	private JButton theaterBtn8;
	private JButton theaterBtn9;
	private JButton theaterBtn10;
	private JScrollPane scrollPane;
	private JPanel theaterPanel;

	private JSpinner yearSpinner;
	private JComboBox<Integer> monthComboBox;
	JPanel dayPanel;
	GridLayout dayPaneLayout;

	SpinnerListModel yearListModel;
	DefaultComboBoxModel<Integer> monthComboBoxModel;

	// 영화관 리스트
	String[] seoul = { "강남", "강변", "건대입구", "동대문", "명동", "신촌", "압구정", "홍대" }; // 서울리스트
	String[] gyeonggi = { "광교", "김포", "동탄", "부천", "서현", "수원", "용인", "판교" }; // 경기리스트
	String[] incheon = { "부평", "송도", "인천", "청라" }; // 인천리스트
	String[] gangwon = { "강릉", "원주", "춘천" }; // 강원리스트
	String[] chungcheong = { "논산", "당진", "대전", "서산", "세종", "천안", "청주" }; // 충청리스트
	String[] daegu = { "대구수성", "대구월성", "대구칠곡", "대구한일" }; // 대구리스트
	String[] busan = { "남포", "서면", "센텀시티", "울산삼산", "해운대" }; // 부산리스트
	String[] gyeongsang = { "거제", "김해", "마산", "안동", "창원", "포항" }; // 경상리스트
	String[] jeolla = { "광양", "광주", "군산", "나주", "목포", "여수", "전주" }; // 전라리스트
	String[] jeju = { "제주" }; // 제주리스트

	private Integer[] yearArr;
	private Integer[] monthArr;
	private Integer[] dayArr;

	private JPanel timeTablePanel;
	JScrollPane timeTableJPanel;

	

	private JFrame mainFrame;

//	ReservationController rc = new ReservationController();

	/**
	 * Create the application.
	 */
	public SelectionMoviePanel(JFrame f) {
		this.mainFrame = f;
		mainFrame.setTitle("예매");

		f.setBackground(new Color(153, 153, 153));

//		JPanel panel = new JPanel();
//		panel.setBackground(new Color(204, 204, 204));
//		add(panel, BorderLayout.CENTER);

		/**
		 * 영화패널
		 */
		setLayout(null);
		JPanel moviePanel = new JPanel();
		moviePanel.setBackground(new Color(242, 240, 229));
		moviePanel.setBounds(0, 0, 268, 606);
		add(moviePanel);
		moviePanel.setLayout(null);

		JPanel paneMovielTitle = new JPanel();
		paneMovielTitle.setBounds(0, 0, 270, 47);
		paneMovielTitle.setBackground(new Color(51, 51, 51));
		moviePanel.add(paneMovielTitle);
		paneMovielTitle.setLayout(null);

		JLabel movieTitle = new JLabel("영화");
		movieTitle.setHorizontalAlignment(SwingConstants.CENTER);
		movieTitle.setForeground(new Color(255, 255, 255));
		movieTitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		movieTitle.setBounds(0, 0, 270, 47);
		paneMovielTitle.add(movieTitle);

		// 영화 list담기
		String[] movies = { "영화1", "영화2", "영화3", "영화2", "영화2", "영화3", "영화2", "영화3", "영화2", "영화3", "영화2", "영화3", "영화2",
				"영화3", "영화2", "영화3", "영화2", "영화3", "영화2", "영화3", "영화2", "영화2", "영화3", "영화2", "영화3", "영화2", "영화3", "영화2",
				"영화3", "영화2", "영화3", "영화2", "영화3", "영화2", "영화3", "영화2", "영화3" };

		JList movieList;

		movieList = new JList(MainFrame.rc.getMovieList());
		movieList.setForeground(new Color(51, 51, 51));
		movieList.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
		movieList.setBackground(new Color(242, 240, 229));
		movieList.setBounds(17, 85, 234, 490);

		scrollPane = new JScrollPane(movieList);
		scrollPane.setBounds(17, 62, 234, 467);
		scrollPane.setBackground(new Color(255, 255, 255));

		movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		movieList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// 선택영화
				String selection = (String) movieList.getSelectedValue();
				// 선택영화 텍스트필드에 기록
				textField.setText(selection);

				theaterPanel.remove(theaterList);

				theaterList = new JList(MainFrame.rc.getTheaterList((String) movieList.getSelectedValue()));
				theaterList.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
				theaterList.setBackground(new Color(242, 240, 229));
				theaterList.setBounds(151, 62, 100, 448);
				theaterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				theaterPanel.add(theaterList);
				theaterList.addListSelectionListener(new TheaterListSelListener());

				revalidate();
				repaint();

			}
		});

//		moviePanel.add(movieTextField);
		moviePanel.add(scrollPane);

		/**
		 * 극장패널
		 */
		theaterPanel = new JPanel();
		theaterPanel.setBackground(new Color(242, 240, 229));
		theaterPanel.setBounds(269, 0, 268, 606);
		add(theaterPanel);
		theaterPanel.setLayout(null);

		JPanel panelTheaterTitle = new JPanel();
		panelTheaterTitle.setBounds(0, 0, 270, 47);
		panelTheaterTitle.setLayout(null);
		panelTheaterTitle.setBackground(new Color(51, 51, 51));
		theaterPanel.add(panelTheaterTitle);

		JLabel theaterTitle = new JLabel("극장");
		theaterTitle.setHorizontalAlignment(SwingConstants.CENTER);
		theaterTitle.setForeground(Color.WHITE);
		theaterTitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		theaterTitle.setBounds(0, 0, 270, 47);
		panelTheaterTitle.add(theaterTitle);

		///////////////////////////////////////

		theaterBtn1 = new JButton("서울");
		theaterBtn1.setBackground(Color.GRAY);
		theaterBtn1.setBounds(10, 62, 103, 31);
		theaterBtn1.setFont(new Font("굴림", Font.BOLD, 18));
		theaterBtn1.setForeground(new Color(64, 64, 64));

		theaterBtn2 = new JButton("경기");
		theaterBtn2.setBackground(Color.GRAY);
		theaterBtn2.setBounds(10, 100, 103, 31);
		theaterBtn2.setFont(new Font("굴림", Font.BOLD, 18));
		theaterBtn2.setForeground(new Color(64, 64, 64));

		theaterBtn3 = new JButton("인천");
		theaterBtn3.setBackground(Color.GRAY);
		theaterBtn3.setBounds(10, 138, 103, 31);
		theaterBtn3.setFont(new Font("굴림", Font.BOLD, 18));
		theaterBtn3.setForeground(new Color(64, 64, 64));

		theaterBtn4 = new JButton("강원");
		theaterBtn4.setBackground(Color.GRAY);
		theaterBtn4.setBounds(10, 176, 103, 31);
		theaterBtn4.setFont(new Font("굴림", Font.BOLD, 18));
		theaterBtn4.setForeground(new Color(64, 64, 64));

		theaterBtn5 = new JButton("충청");
		theaterBtn5.setBackground(Color.GRAY);
		theaterBtn5.setBounds(10, 214, 103, 31);
		theaterBtn5.setFont(new Font("굴림", Font.BOLD, 18));
		theaterBtn5.setForeground(new Color(64, 64, 64));

		theaterBtn6 = new JButton("대구");
		theaterBtn6.setBackground(Color.GRAY);
		theaterBtn6.setBounds(10, 252, 103, 31);
		theaterBtn6.setFont(new Font("굴림", Font.BOLD, 18));
		theaterBtn6.setForeground(new Color(64, 64, 64));

		theaterBtn7 = new JButton("부산");
		theaterBtn7.setBackground(Color.GRAY);
		theaterBtn7.setBounds(10, 290, 103, 31);
		theaterBtn7.setFont(new Font("굴림", Font.BOLD, 18));
		theaterBtn7.setForeground(new Color(64, 64, 64));

		theaterBtn8 = new JButton("경상");
		theaterBtn8.setBackground(Color.GRAY);
		theaterBtn8.setBounds(10, 328, 103, 31);
		theaterBtn8.setFont(new Font("굴림", Font.BOLD, 18));
		theaterBtn8.setForeground(new Color(64, 64, 64));

		theaterBtn9 = new JButton("전라");
		theaterBtn9.setBackground(Color.GRAY);
		theaterBtn9.setBounds(10, 366, 103, 31);
		theaterBtn9.setFont(new Font("굴림", Font.BOLD, 18));
		theaterBtn9.setForeground(new Color(64, 64, 64));

		theaterBtn10 = new JButton("제주");
		theaterBtn10.setBackground(Color.GRAY);
		theaterBtn10.setBounds(10, 404, 103, 31);
		theaterBtn10.setFont(new Font("굴림", Font.BOLD, 18));
		theaterBtn10.setForeground(new Color(64, 64, 64));

		theaterList = new JList();
		theaterList.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
		theaterList.setBackground(new Color(242, 240, 229));
		theaterList.setBounds(151, 62, 100, 448);
		theaterList.addListSelectionListener(new TheaterListSelListener());

//		theaterBtn1.addActionListener(new TheaterBtnActionListener());
//		theaterBtn2.addActionListener(new TheaterBtnActionListener()); 
//		theaterBtn3.addActionListener(new TheaterBtnActionListener());
//		theaterBtn4.addActionListener(new TheaterBtnActionListener());
//		theaterBtn5.addActionListener(new TheaterBtnActionListener());
//		theaterBtn6.addActionListener(new TheaterBtnActionListener()); 	
//		theaterBtn7.addActionListener(new TheaterBtnActionListener()); 
//		theaterBtn8.addActionListener(new TheaterBtnActionListener()); 
//		theaterBtn9.addActionListener(new TheaterBtnActionListener()); 
//		theaterBtn10.addActionListener(new TheaterBtnActionListener());

//		JLabel choiceTheater = new JLabel("극장 : ");
//		choiceTheater.setFont(new Font("맑은 고딕", Font.BOLD, 18));
//		choiceTheater.setBounds(17, 554, 78, 22);
//
//		theaterTextField = new JTextField();
//		theaterTextField.setForeground(new Color(255, 255, 255));
//		theaterTextField.setBackground(new Color(51, 51, 51));
//		theaterTextField.setFont(new Font("맑은 고딕", Font.BOLD, 18));
//		theaterTextField.setBounds(95, 554, 156, 28);
//		theaterTextField.setColumns(10);
//		theaterTextField.setEditable(false);

		theaterPanel.add(theaterBtn1);
		theaterPanel.add(theaterBtn2);
		theaterPanel.add(theaterBtn3);
		theaterPanel.add(theaterBtn4);
		theaterPanel.add(theaterBtn5);
		theaterPanel.add(theaterBtn6);
		theaterPanel.add(theaterBtn7);
		theaterPanel.add(theaterBtn8);
		theaterPanel.add(theaterBtn9);
		theaterPanel.add(theaterBtn10);
		theaterPanel.add(theaterList);
//		theaterPanel.add(choiceTheater);
//		theaterPanel.add(theaterTextField);
		
		textField = new JTextField();
		textField.setBounds(95, 551, 156, 28);
		theaterPanel.add(textField);
		textField.setForeground(new Color(255, 255, 255));
		textField.setBackground(new Color(51, 51, 51));
		textField.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		textField.setColumns(10);
		textField.setEditable(false);
		
		JLabel choiceMovie = new JLabel("choice :");
		choiceMovie.setBounds(10, 551, 78, 25);
		theaterPanel.add(choiceMovie);
		choiceMovie.setFont(new Font("맑은 고딕", Font.BOLD, 18));

		/**
		 * 날짜패널
		 */
		JPanel datePanel = new JPanel();
		datePanel.setBackground(new Color(242, 240, 229));
		datePanel.setBounds(538, 0, 400, 606); // 초기값 191
//		DateMovie.setLayout(null);
		add(datePanel);

		JPanel panelDateTitle = new JPanel();
		panelDateTitle.setLayout(null);
		panelDateTitle.setBackground(new Color(51, 51, 51));

		JLabel dateTitle = new JLabel("날짜");
		dateTitle.setHorizontalAlignment(SwingConstants.CENTER);
		dateTitle.setForeground(Color.WHITE);
		dateTitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		dateTitle.setBounds(0, 0, datePanel.getWidth(), 47);
		panelDateTitle.add(dateTitle);

		// 달력 전체 패널
		JPanel calendarPane = new JPanel();
		calendarPane.setLayout(null);
		calendarPane.setBackground(new Color(242, 240, 229));
		datePanel.add(calendarPane);

		GroupLayout gl_DateMovie = new GroupLayout(datePanel);
		gl_DateMovie.setHorizontalGroup(gl_DateMovie.createParallelGroup(Alignment.LEADING)
				.addComponent(panelDateTitle, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
				.addGroup(gl_DateMovie.createSequentialGroup().addGap(17)
						.addComponent(calendarPane, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE).addGap(17)));
		gl_DateMovie.setVerticalGroup(gl_DateMovie.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DateMovie.createSequentialGroup()
						.addComponent(panelDateTitle, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(calendarPane, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(84, Short.MAX_VALUE)));
		datePanel.setLayout(gl_DateMovie);

		yearSpinner = new JSpinner();
		yearSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int year = (Integer) yearSpinner.getValue();
				monthArr = Arrays.stream(MainFrame.rc.getTagetMonth(year)).boxed().toArray(Integer[]::new);
				monthComboBoxModel = new DefaultComboBoxModel<>(monthArr);
				monthComboBox.setModel(monthComboBoxModel);
			}
		});

		monthComboBox = new JComboBox();

		dayPanel = new JPanel();
		dayPaneLayout = new GridLayout(6, 7);

		dayPanel.setLayout(dayPaneLayout);
		dayPanel.setBackground(new Color(242, 240, 229));
		dayPanel.setBounds(0, 39, datePanel.getWidth() - 40, datePanel.getHeight() - 450);

		yearSpinner.setBounds(0, dayPanel.getY() - 38, dayPanel.getWidth() / 3, 30);
		monthComboBox.setBounds(yearSpinner.getWidth() + yearSpinner.getX() + 3, yearSpinner.getY(),
				dayPanel.getWidth() / 3 * 2 - 4, 30);

//		monthComboBox.addActionListener(new monthComboBoxActionListener());
		monthComboBox.addItemListener(new monthComboBoxActionListener());

		calendarPane.add(yearSpinner);
		calendarPane.add(monthComboBox);

		calendarPane.add(dayPanel);

		// JCalendar를 이용해 달력 만들기
//		JCalendar calendar = new JCalendar();
//		calendar.getDayChooser().setWeekdayForeground(Color.BLACK);
//		calendar.getDayChooser().getDayPanel().setForeground(Color.GRAY);
//		calendar.getDayChooser().getDayPanel().setBackground(new Color(242, 240, 229));

		/**
		 * 시간패널
		 */
		JPanel timePanel = new JPanel();

		timePanel.setBackground(new Color(242, 240, 229));
		int widthTimePane = FRAME_WIDTH - datePanel.getX() - datePanel.getWidth() - 20;
		timePanel.setBounds(datePanel.getX() + datePanel.getWidth() + 1, 0, widthTimePane, 606);
		add(timePanel);
		timePanel.setLayout(null);

		JPanel panelTimeTitle = new JPanel();
		panelTimeTitle.setBounds(0, 0, widthTimePane, 47);
		panelTimeTitle.setLayout(null);
		panelTimeTitle.setBackground(new Color(51, 51, 51));
		timePanel.add(panelTimeTitle);

		JLabel timeTitle = new JLabel("시간");
		timeTitle.setHorizontalAlignment(SwingConstants.CENTER);
		timeTitle.setForeground(Color.WHITE);
		timeTitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		timeTitle.setBounds(0, 0, widthTimePane, 47);
		panelTimeTitle.add(timeTitle);

		timeTablePanel = new JPanel();
		timeTablePanel.setLayout(null);
		timeTablePanel.setBackground(new Color(242, 240, 229));

		int timeTableY = panelTimeTitle.getHeight() + panelTimeTitle.getY();
		int timeTaBleWidth = timePanel.getWidth();
		int timeTableHeight = timePanel.getHeight() - panelTimeTitle.getHeight();

		Dimension size = new Dimension();// 사이즈를 지정하기 위한 객체 생성
		size.setSize(timeTaBleWidth+500, timeTableHeight+500);// 객체의 사이즈를 지정
		timeTablePanel.setPreferredSize(size);// 사이즈 정보를 가지고 있는 객체를 이용해 패널의 사이즈 지정

		timeTableJPanel = new JScrollPane(timeTablePanel); // timeTablePanel이 화면에서 보이는거임 (스크롤은 배경)
		timeTableJPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		timeTableJPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		timeTableJPanel.setBackground(new Color(242, 240, 229));
		timeTableJPanel.setBounds(0, timeTableY, timeTaBleWidth, timeTableHeight);

		timePanel.add(timeTableJPanel);
		int topPannelWidth = timePanel.getWidth() + datePanel.getWidth() + theaterPanel.getWidth()
				+ moviePanel.getWidth() + 20;
		int topPannelHeight = timePanel.getHeight() + panelTimeTitle.getHeight() - 7;

		setBounds(100, 100, topPannelWidth, topPannelHeight);

	}

	public class DayOfCalendarBtnActionListener implements ActionListener {
		

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			
			timeTablePanel.removeAll();

			int x = 0;
			int y = 0;

			for (int i = 0; i < dayBtnArr.length; i++) {
				if (dayBtnArr[i].isEnabled()) {
					if (dayBtnArr[i] == btn)
						dayBtnArr[i].setBackground(Color.pink);
					else
						dayBtnArr[i].setBackground(new Color(216, 209, 175));
				}
			}

			int year = (Integer) yearSpinner.getValue();
			int month = (Integer) monthComboBox.getSelectedItem();
			int day = Integer.parseInt(btn.getText());

			HashMap<Screen, HashMap<Calendar, boolean[]>> targetScreen = MainFrame.rc.getScreenOfMonthDay(year, month, day);
			
			ArrayList<Screen> targetScreenKey = new ArrayList<>(targetScreen.keySet());
			Collections.sort(targetScreenKey, new ScreenNameAscending()); //스크린 이름순 정렬
			
			for (Screen s : targetScreenKey) {
				HashMap<Calendar, boolean[]> targetTime = targetScreen.get(s);
				if (!targetTime.isEmpty()) {
					
					x = 0;
					CustumTimePane prePane = null;

					ArrayList<Calendar> targetTimeKey = new ArrayList<>(targetTime.keySet());
					Collections.sort(targetTimeKey);
					
					for (Calendar c : targetTimeKey) {
						
//						System.out.println(s.getScreenName()); // UI에 사용할 스크린 이름
						CustumTimePane timePane = new CustumTimePane(s.getScreenName());
						timePane.setTargetTime(c);

						JTextArea timeTextArea = new JTextArea();
						timeTextArea.setEditable(false);
						timeTextArea.setBackground(paneColor);
						String str = c.get(Calendar.HOUR_OF_DAY) + "시 " + c.get(Calendar.MINUTE) + "분 상영\n";

						boolean[] seat = targetTime.get(c);
						timePane.setSeat(seat);

						int emptySeatNum = 0;
						for (int i = 0; i < seat.length; i++)
							if (seat[i] == false)
								emptySeatNum++;
						str += "전체 좌석 수 : " + s.getTotalSeat() + "\n";
						str += "예약된 좌석 수 : " + (s.getTotalSeat() - emptySeatNum) + "\n";
						str += "남은 좌석수 : " + emptySeatNum + "\n";

//						System.out.println(str);

						timeTextArea.setText(str);
						timePane.add(timeTextArea);

						JButton selectBtn = new JButton("선택");
						selectBtn.addActionListener(new SelectTimeBtnActionListener());
						timePane.add(selectBtn);

						if (prePane != null) { //맨 첫패널이 생길때 좌표값을 계산하지 않기 위한 조건
							// (직전패널의 X좌표) + (직전패널의 너비) + (패널사이의 간격값 지정)
							x = prePane.getX() + prePane.getWidth() + 10; // X 좌표 계산
						}

						timePane.setBounds(x, y, 150, 150);
//						System.out.println("x : " + x);
//						System.out.println("y : " + y);

						timeTablePanel.add(timePane); 
						
						prePane = timePane; // 첫패널을 패널에 추가하고 난 다음에 직전 패널 객체정보 저장 
					}
					//가로축 패널을 다 그리고 난 다음에 Y값 계산을 해준다.
					//직전패널의 Y값
					y = prePane.getY() + prePane.getHeight() + 10; //Y좌표값 계산
				}
			}
			//패널이 모두 추가되면 다시 그려주기 작업
			revalidate();
			repaint();
		}

	}
	
	public class SelectTimeBtnActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
//			mainFrame.setSize(SelectSeatPanel.FRAME_WIDTH, SelectSeatPanel.FRAME_HEIGHT);
//			MyUtil.changePanel(mainFrame, SelectionMoviePanel.this, MainFrame.selectSeatPanel);
			JButton selectBtn = (JButton)e.getSource();
			CustumTimePane timePane = (CustumTimePane)selectBtn.getParent();
			
			Component[] timePaneComArr =timePane.getComponents();
			
			String startTime = null;
			
			for(Component c : timePaneComArr)
				if(c instanceof JTextArea) {
					String[] textAreaText = ((JTextArea) c).getText().split("\n");
					System.out.println(Arrays.toString(textAreaText));
					startTime = textAreaText[0].trim(); //공백제거했으니 substring으로 가져오면됨
			}
			
			String screenName = timePane.getScreenName();
			String[] hourStr = startTime.split("시");
			String[] minuteStr = hourStr[1].split("분");
//			int hour = Integer.parseInt(hourStr[0]);
//			int minute = Integer.parseInt(minuteStr[0]);
			
			System.out.println(hourStr[0]);
			System.out.println(minuteStr[1]);
			
			
			
			
		}
		
	}

	public class CustumTimePane extends JPanel {

		private Calendar targetTime;
		boolean[] seat;
		String screenName;

		public CustumTimePane(String title) {
//			setLayout(null);
			setBackground(paneColor);
			setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(title),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			screenName = title;
		}

		public Calendar getTargetTime() {
			return targetTime;
		}

		public boolean[] getSeat() {
			return seat;
		}

		public String getScreenName() {
			return screenName;
		}

		public void setTargetTime(Calendar targetTime) {
			this.targetTime = targetTime;
		}

		public void setSeat(boolean[] seat) {
			this.seat = seat;
		}

		public void setScreenName(String screenName) {
			this.screenName = screenName;
		}

	}

	public class monthComboBoxActionListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			dayPanel.removeAll();

			Calendar cal = Calendar.getInstance();
			int selectYear = (Integer) yearSpinner.getValue();
			int selectMonth = (Integer) monthComboBox.getSelectedItem() - 1; // 캘린더에서 월은 0부터 시작하니까
			cal.set(selectYear, selectMonth, 1);

			int startDOW = cal.get(Calendar.DAY_OF_WEEK); // 해당월 첫일의 요일 (일요일 1부터 토요일 7까지)
			int endDayOfMon = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 그 달의 마지막날
			int calText = 1;

			dayBtnArr = new JButton[endDayOfMon];

			int[] dayArr = MainFrame.rc.getTargetDay(selectMonth);

			for (int i = 0; i < dayPaneLayout.getRows(); i++) {
				for (int j = 0; j < dayPaneLayout.getColumns(); j++) {
					if (i < 1) {
						char dow = 'a'; // 요일 문자
						switch (j) {
						case 0:
							dow = '일';
							break;
						case 1:
							dow = '월';
							break;
						case 2:
							dow = '화';
							break;
						case 3:
							dow = '수';
							break;
						case 4:
							dow = '목';
							break;
						case 5:
							dow = '금';
							break;
						case 6:
							dow = '토';
							break;
						}
						JLabel dowLabel = new JLabel("" + dow);
						dowLabel.setHorizontalAlignment(SwingConstants.CENTER);
						dowLabel.setOpaque(true);
						if (j == 0 || j == 6) {
							dowLabel.setBackground(Color.RED);
						} else {
							dowLabel.setBackground(Color.GRAY);
						}

						dayPanel.add(dowLabel);

					} else {
						JButton dayBtn = new JButton();
						dayBtn.setFont(new Font("맑은 고딕", Font.BOLD, 13));
						dayBtn.setBackground(new Color(242, 240, 229));
						dayBtn.setEnabled(false); // 아래에서 날짜에 해당하는것만 Enable해줄 예정

						if (i == 1) { // 달력 첫번째줄일경우
							if (j >= startDOW - 1) {
								dayBtn.setText(String.valueOf(calText++));
								dayBtnArr[calText - 2] = dayBtn;
							}
						} else {
							if (calText <= endDayOfMon) {
								dayBtn.setText(String.valueOf(calText++));
								dayBtnArr[calText - 2] = dayBtn;
							}
						}

						dayPanel.add(dayBtn);

						for (int day : dayArr) {
							String dayStr = String.valueOf(day);
							if (dayStr.equals(dayBtn.getText())) {
								dayBtn.setEnabled(true);
								dayBtn.setBackground(new Color(216, 209, 175));
								dayBtn.addActionListener(new DayOfCalendarBtnActionListener());
							}

						}
					}
				}
			}

			revalidate();
			repaint();

		}

	}

	// 극장리스트 선택 액션리스너
	public class TheaterListSelListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// 선택영화
			String theaterName = (String) theaterList.getSelectedValue();
			// 선택영화 텍스트필드에 기록
//			theaterTextField.setText(theaterName);
			

			yearArr = Arrays.stream(MainFrame.rc.getTargetYear(theaterName)).boxed().toArray(Integer[]::new);
			yearListModel = new SpinnerListModel(yearArr);
			yearSpinner.setModel(yearListModel);

			int year = (Integer) yearSpinner.getValue();
			monthArr = Arrays.stream(MainFrame.rc.getTagetMonth(year)).boxed().toArray(Integer[]::new);
			monthComboBoxModel = new DefaultComboBoxModel<>(monthArr);
			monthComboBox.setModel(monthComboBoxModel);

			new monthComboBoxActionListener().itemStateChanged(null);
		}

	}

	// 극장버튼 액션리스너
	public class TheaterBtnActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			theaterPanel.remove(theaterList);
			theaterList = new JList();

			JButton btn = (JButton) e.getSource();

			switch (btn.getText()) {
			case "서울":
				theaterList = new JList(seoul);
				break;
			case "경기":
				theaterList = new JList(gyeonggi);
				break;
			case "인천":
				theaterList = new JList(incheon);
				break;
			case "강원":
				theaterList = new JList(gangwon);
				break;
			case "충청":
				theaterList = new JList(chungcheong);
				break;
			case "대구":
				theaterList = new JList(daegu);
				break;
			case "부산":
				theaterList = new JList(busan);
				break;
			case "경상":
				theaterList = new JList(gyeongsang);
				break;
			case "전라":
				theaterList = new JList(jeolla);
				break;
			case "제주":
				theaterList = new JList(jeju);
				break;

			}

			theaterList.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
			theaterList.setBackground(new Color(242, 240, 229));
			theaterList.setBounds(151, 62, 100, 448);
			theaterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			theaterList.addListSelectionListener(new TheaterListSelListener());

			revalidate();
			repaint();

			theaterPanel.add(theaterList);

		}
	}

}
