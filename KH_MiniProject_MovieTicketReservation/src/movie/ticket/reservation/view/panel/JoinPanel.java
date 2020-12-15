package movie.ticket.reservation.view.panel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import movie.ticket.reservation.model.vo.Member;
import movie.ticket.reservation.util.MyUtil;
import movie.ticket.reservation.view.MainFrame;


public class JoinPanel extends JPanel {

	/**
	 * createBirthText(); 에서 사용할 문자열 배열
	 */
	private String[] yearArr;
	private String[] monthArr;
	private String[] dayArr;
	
	private JFrame mainFrame;
	
	public static final int FRAME_X = 500;
	public static final int FRAME_Y = 500;
	
	public JoinPanel(JFrame f) {
		this.mainFrame = f;

		mainFrame.setTitle("회원가입");
				
		setLayout(null);	
		
		JLabel nameLabel = new JLabel("이름 : ");
		nameLabel.setBounds(50, 40, 70, 50);
		JTextField nameInput = new JTextField();
		nameInput.setBounds(120, 45, 300, 40);
		
		JLabel dobLabel = new JLabel("생년월일 : ");
		dobLabel.setBounds(50, 100, 70, 50);
		
		
		createBirthText();
		
		JComboBox<String> cmb1 = new JComboBox<>();
		cmb1.setModel(new DefaultComboBoxModel<String>(yearArr));
		cmb1.setBounds(120, 105, 100, 40);
		
		JComboBox<String> cmb2 = new JComboBox<>();
		cmb2.setModel(new DefaultComboBoxModel<String>(monthArr));
		cmb2.setBounds(230, 105, 90, 40);
		
		JComboBox<String> cmb3 = new JComboBox<>();
		cmb3.setModel(new DefaultComboBoxModel<String>(dayArr));
		cmb3.setBounds(330, 105, 90, 40);

		JLabel phoneLabel = new JLabel("전화번호 : ");
		phoneLabel.setBounds(50, 160, 70, 50);
		JTextField phoneInput1 = new JTextField();
		phoneInput1.setBounds(120, 165, 90, 40);
		JLabel label1 = new JLabel("-");
		label1.setBounds(215, 160, 50, 50);
		JTextField phoneInput2 = new JTextField();
		phoneInput2.setBounds(225, 165, 90, 40);
		JLabel label2 = new JLabel("-");
		label2.setBounds(315, 160, 50, 50);
		JTextField phoneInput3 = new JTextField();
		phoneInput3.setBounds(325, 165, 90, 40);

		
		JLabel idLabel = new JLabel("아이디 : ");
		idLabel.setBounds(50, 220, 70, 50);
		JTextField idInput = new JTextField();
		idInput.setBounds(120, 225, 300, 40);
		
		JLabel pwLabel = new JLabel("패스워드 : ");
		pwLabel.setBounds(50, 280, 70, 50);
		JPasswordField pwInput = new JPasswordField();
		pwInput.setBounds(120, 285, 300, 40);
		pwInput.setEchoChar('*');
				
		JButton btn = new JButton("가입");
		btn.setBounds(135, 360, 100, 50);
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = null;
				boolean isOk = false;

//				BufferedWriter bw = new BufferedWriter(new FileWriter("회원명단.txt",true));
//				BufferedReader br = new BufferedReader(new FileReader("회원명단.txt"));

				String name = nameInput.getText();
				String y = cmb1.getSelectedItem().toString();
				String m = cmb2.getSelectedItem().toString();
				String d = cmb3.getSelectedItem().toString();
				String phone1 = phoneInput1.getText();
				String phone2 = phoneInput2.getText();
				String phone3 = phoneInput3.getText();

				String id = idInput.getText();
				String pw = new String(pwInput.getPassword());
				
				String phoneNum = phone1 + phone2 + phone3;

				if (name.length() == 0) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
					return;
				} else if (y == "year" || m == "month" || d == "date") {
					JOptionPane.showMessageDialog(null, "생년월일을 입력해주세요.");

					return;
				} else if (phone1.length() == 0 || phone2.length() == 0 || phone3.length() == 0) {
					JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.");

					return;
				} else if(phoneNum.length() != 11){
					JOptionPane.showMessageDialog(null, "11자리 전화번호를 입력해주세요.");

					return;
				} else if (id.length() == 0) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");

					return;
				} else if (pw.length() == 0) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");

					return;
				} else {

					Member[] users = MainFrame.rc.getUsers();

					for (int i = 0; i < users.length; i++) {
						
						if(users[i] == null)
							break;
						
						if (users[i].getId().equals(id)) {
							JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");
							return;
						}
					}
				}			

				Calendar birth = Calendar.getInstance();
				birth.set(Integer.parseInt(y), Integer.parseInt(m) - 1, Integer.parseInt(d));

				MainFrame.rc.register(name, birth, phoneNum, id, pw);

				JOptionPane.showMessageDialog(null, "회원가입 되었습니다.");
				
				mainFrame.setSize(MainPanel.FRAME_WIDTH, MainPanel.FRAME_HIGHT);
				MyUtil.changePanel(mainFrame, JoinPanel.this, MainFrame.mainPanel);
				MainFrame.joinPanel = new JoinPanel(mainFrame);

			}

		});
		
		JButton backbtn = new JButton("뒤로 가기");
		backbtn.setBounds(btn.getX()+btn.getWidth()+30, 360, 100, 50);
		backbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setSize(MainPanel.FRAME_WIDTH, MainPanel.FRAME_HIGHT);
				MyUtil.changePanel(mainFrame, JoinPanel.this, MainFrame.mainPanel);
				MainFrame.joinPanel = new JoinPanel(mainFrame);
			}
		});
		
		add(nameLabel);
		add(nameInput);
		add(dobLabel);
		add(cmb1);
		add(cmb2);
		add(cmb3);
		add(phoneLabel);
		add(phoneInput1);
		add(label1);
		add(phoneInput2);
		add(label2);
		add(phoneInput3);
		add(idLabel);
		add(idInput);
		add(pwLabel);
		add(pwInput);
		
		add(btn);
		add(backbtn);
		
	}
	
	private void createBirthText() {
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
				
		int firstYear = cal.get(Calendar.YEAR);
		int nowYear = Calendar.getInstance().get(Calendar.YEAR);
		
		//0번지에 year,month,day 넣기위해 배열인덱스 +1
		yearArr = new String[nowYear-firstYear+2]; //1970~현재년도까지 개수를 위해 +1
		monthArr = new String[13];
		dayArr = new String[32];
		
		yearArr[0] = "year";
		monthArr[0] = "month";
		dayArr[0] = "day";
		
		for(int i = 1; i < yearArr.length; i++) {
			yearArr[i] = String.valueOf(nowYear-i+1);
		}
		
		for(int i = 1; i < monthArr.length; i++) {
			monthArr[i] = String.valueOf(i);
		}
		
		for(int i = 1; i < dayArr.length; i++) {
			dayArr[i] = String.valueOf(i);
		}
		
	}
	
}
