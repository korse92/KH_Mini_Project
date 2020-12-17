package movie.ticket.reservation.view.panel;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import movie.ticket.reservation.model.vo.Member;
import movie.ticket.reservation.model.vo.NonMember;
import movie.ticket.reservation.util.MyUtil;
import movie.ticket.reservation.view.MainFrame;

public class LoginPanel extends JPanel {

	private JTabbedPane tabbedPane;
	private JPanel panel1;
	private JPanel panel2;
	private JFrame mainFrame;

	public static final int FRAME_WIDTH = 500;
	public static final int FRAME_HEIGHT = 500;

	private String[] yearArr;
	private String[] monthArr;
	private String[] dayArr;

	public LoginPanel(JFrame f) {
		mainFrame = f;

		mainFrame.setTitle("로그인");

		setLayout(new BorderLayout());

		userLogin();
		guestLogin();

		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("회원", panel1);
		tabbedPane.addTab("비회원", panel2);

		add(tabbedPane, BorderLayout.CENTER);
	}

	public void tabbedPaneSelIdx(int idx) {
		tabbedPane.setSelectedIndex(idx);
	}

	public void userLogin() {
		panel1 = new JPanel();
		panel1.setLayout(null);

		JLabel idLabel = new JLabel("아이디 : ");
		idLabel.setBounds(50, 100, 70, 50);
		JTextField idInput = new JTextField();
		idInput.setBounds(120, 105, 300, 40);

		JLabel pwLabel = new JLabel("패스워드 : ");
		pwLabel.setBounds(50, 160, 70, 50);
		JPasswordField pwInput = new JPasswordField();
		pwInput.setBounds(120, 165, 300, 40);
		pwInput.setEchoChar('*');

		JButton btn = new JButton("로그인");
		btn.setBounds(135, 360, 100, 50);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String id = idInput.getText();
				String pw = new String(pwInput.getPassword());

				if (id.length() == 0) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
					return;
				} else if (pw.length() == 0) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
					return;
				} else {

					Member[] users = MainFrame.rc.getUsers();
					for (int i = 0; i < users.length; i++) {

						if (users[i] == null) {
							break;
						}

						if (users[i].getId().equals(id) && users[i].getPassword().equals(pw)) {
							JOptionPane.showMessageDialog(null, "로그인 성공!");
							MainFrame.rc.login(users[i]);
							// 여기 다음에 화면을 ReservationMain 패널로 바꿔주는 코드 넣어줘야함.
							mainFrame.setSize(ReservationMainPanel.FRAME_WIDTH, ReservationMainPanel.FRAME_HEIGHT);
							MyUtil.changePanel(mainFrame, LoginPanel.this, MainFrame.reservationMain);
							MainFrame.loginPanel = new LoginPanel(mainFrame);

						} else {
							JOptionPane.showMessageDialog(null, "아이디/비밀번호가 일치하지 않습니다.");
							return;
						}
					}
				}
			}

		});

		JButton backbtn = new JButton("뒤로 가기");
		backbtn.setBounds(btn.getX() + btn.getWidth() + 30, 360, 100, 50);
		backbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setSize(MainPanel.FRAME_WIDTH, MainPanel.FRAME_HIGHT);
				MyUtil.changePanel(mainFrame, LoginPanel.this, MainFrame.mainPanel);
				MainFrame.loginPanel = new LoginPanel(mainFrame);
			}
		});

		panel1.add(idLabel);
		panel1.add(idInput);
		panel1.add(pwLabel);
		panel1.add(pwInput);
		panel1.add(btn);
		panel1.add(backbtn);

	}

	public void guestLogin() {
		panel2 = new JPanel();
		panel2.setLayout(null);

		JLabel dobLabel = new JLabel("생년월일 : ");
		dobLabel.setBounds(50, 100, 70, 50);

		createBirthText();

		JComboBox<String> dobYear = new JComboBox<>();
		dobYear.setModel(new DefaultComboBoxModel<String>(yearArr));
		dobYear.setBounds(120, 105, 100, 40);

		JComboBox<String> dobMonth = new JComboBox<>();
		dobMonth.setModel(new DefaultComboBoxModel<String>(monthArr));
		dobMonth.setBounds(230, 105, 90, 40);

		JComboBox<String> dobDay = new JComboBox<>();
		dobDay.setModel(new DefaultComboBoxModel<String>(dayArr));
		dobDay.setBounds(330, 105, 90, 40);

		JLabel phoneLabel = new JLabel("전화번호 : ");
		phoneLabel.setBounds(50, 160, 70, 50);
		JTextField phoneInput = new JTextField();
		phoneInput.setBounds(120, 165, 300, 40);

		JLabel lblNewLabel = new JLabel("ex) 01012345678 (하이픈('-') 없이 입력)");
		lblNewLabel.setForeground(SystemColor.activeCaption);
		lblNewLabel.setBounds(192, 202, 250, 22);
		panel2.add(lblNewLabel);

		JLabel pwLabel = new JLabel("패스워드 : ");
		pwLabel.setBounds(50, 220, 70, 50);
		JPasswordField pwInput = new JPasswordField();
		pwInput.setBounds(120, 225, 300, 40);
		pwInput.setEchoChar('*');

		JButton btn = new JButton("로그인");
		btn.setBounds(135, 360, 100, 50);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String year = dobYear.getSelectedItem().toString();
				String month = dobMonth.getSelectedItem().toString();
				String date = dobDay.getSelectedItem().toString();

				String phone = phoneInput.getText();
				String pw = new String(pwInput.getPassword());

				if (year == "year" || month == "month" || date == "date") {
					JOptionPane.showMessageDialog(null, "생년월일을 입력해주세요.");
					return;
				} else if ((phone.length() == 0) || (phone.length() != 11)) {
					JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.");
					return;
				} else if (pw.length() == 0) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
					return;
				} else {

					NonMember[] noUsers = MainFrame.rc.getNoUsers();

					for (int i = 0; i < noUsers.length; i++) {

						if(noUsers[i] == null) {
							Calendar birth = Calendar.getInstance();
							birth.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(date));
							NonMember user = MainFrame.rc.register2(birth, phone, pw);
							MainFrame.rc.login(user);
							JOptionPane.showMessageDialog(null, "새로 오신것을 환영합니다!\n로그인 성공!");
							break;
						} else if (noUsers[i].getPhoneNum().equals(phone) && noUsers[i].getPassword().equals(pw)) {
							JOptionPane.showMessageDialog(null, "로그인 성공!");
							MainFrame.rc.login(noUsers[i]);
							// 여기 다음에 화면을 ReservationMain 패널로 바꿔주는 코드 넣어줘야함.
							break;
						}						
					}
					// 여기 다음에 화면을 ReservationMain 패널로 바꿔주는 코드 넣어줘야함.
					mainFrame.setSize(ReservationMainPanel.FRAME_WIDTH, ReservationMainPanel.FRAME_HEIGHT);
					MyUtil.changePanel(mainFrame, LoginPanel.this, MainFrame.reservationMain);
					MainFrame.loginPanel = new LoginPanel(mainFrame);

				}

			}

		});

		JButton backbtn = new JButton("뒤로 가기");
		backbtn.setBounds(btn.getX() + btn.getWidth() + 30, 360, 100, 50);
		backbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setSize(MainPanel.FRAME_WIDTH, MainPanel.FRAME_HIGHT);
				MyUtil.changePanel(mainFrame, LoginPanel.this, MainFrame.mainPanel);
				MainFrame.loginPanel = new LoginPanel(mainFrame);
			}
		});

		panel2.add(dobLabel);
		panel2.add(dobYear);
		panel2.add(dobMonth);
		panel2.add(dobDay);
		panel2.add(phoneLabel);
		panel2.add(phoneInput);
		panel2.add(pwLabel);
		panel2.add(pwInput);
		panel2.add(btn);
		panel2.add(backbtn);
	}

	private void createBirthText() {

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);

		int firstYear = cal.get(Calendar.YEAR);
		int nowYear = Calendar.getInstance().get(Calendar.YEAR);

		// 0번지에 year,month,day 넣기위해 배열인덱스 +1
		yearArr = new String[nowYear - firstYear + 2]; // 1970~현재년도까지 개수를 위해 +1
		monthArr = new String[13];
		dayArr = new String[32];

		yearArr[0] = "year";
		monthArr[0] = "month";
		dayArr[0] = "day";

		for (int i = 1; i < yearArr.length; i++) {
			yearArr[i] = String.valueOf(nowYear - i + 1);
		}

		for (int i = 1; i < monthArr.length; i++) {
			monthArr[i] = String.valueOf(i);
		}

		for (int i = 1; i < dayArr.length; i++) {
			dayArr[i] = String.valueOf(i);
		}

	}

}
