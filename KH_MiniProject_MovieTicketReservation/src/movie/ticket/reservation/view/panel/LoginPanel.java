package movie.ticket.reservation.view.panel;


import java.awt.*;
import javax.swing.*;

import movie.ticket.reservation.util.MyUtil;
import movie.ticket.reservation.view.MainFrame;

import java.awt.event.*;

//public class LoginPanel extends JFrame implements ActionListener {
public class LoginPanel extends JPanel {
	
	private JTabbedPane tabbedPane;
	private JPanel panel1;
	private JPanel panel2;
//    private boolean wrappedTabs = true;
	private JFrame mainFrame;
	
	public static final int FRAME_X = 500;
	public static final int FRAME_Y = 500;

	public LoginPanel(JFrame f)
	{
		mainFrame = f;
		
		mainFrame.setTitle("로그인" );

		setLayout(new BorderLayout() );

		userLogin();
		guestLogin();

		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("회원", panel1);
		tabbedPane.addTab("비회원", panel2);

		add(tabbedPane, BorderLayout.CENTER );
	}
	
	public void tabbedPaneSelIdx(int idx) {
		tabbedPane.setSelectedIndex(idx);
	}

	public void userLogin()
	{
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
				
				if(id.length() == 0) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
					return;
				} else if (pw.length() == 0) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
					return;
				} else
					JOptionPane.showMessageDialog(null, "로그인 성공!");
			}
			
		});
		
		JButton backbtn = new JButton("뒤로 가기");
		backbtn.setBounds(btn.getX()+btn.getWidth()+30, 360, 100, 50);
		backbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setSize(MainPanel.FRAME_X, MainPanel.FRAME_Y);
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

	public void guestLogin()
	{
		panel2 = new JPanel();
		panel2.setLayout(null);

		JLabel dobLabel = new JLabel("생년월일 : ");
		dobLabel.setBounds(50, 100, 70, 50);
		JTextField dobInput = new JTextField();
		dobInput.setBounds(120, 105, 300, 40);
		
		JLabel phoneLabel = new JLabel("전화번호 : ");
		phoneLabel.setBounds(50, 160, 70, 50);
		JTextField phoneInput = new JTextField();
		phoneInput.setBounds(120, 165, 300, 40);

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
				
				String dob = dobInput.getText();
				String phone = phoneInput.getText();
				String pw = new String(pwInput.getPassword());
				
				if(dob.length() == 0) {
					JOptionPane.showMessageDialog(null, "생년월일을 입력해주세요.");
					return;
				} else if (phone.length() == 0) {
					JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.");
					return;
				} else if(phone.length() != 11) {
					JOptionPane.showMessageDialog(null, "전화번호를 다시 입력해주세요.");
					return;
				} else if(pw.length() == 0) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
					return;
				} else				
				JOptionPane.showMessageDialog(null, "로그인 성공!");
			}
			
		});
		
		JButton backbtn = new JButton("뒤로 가기");
		backbtn.setBounds(btn.getX()+btn.getWidth()+30, 360, 100, 50);
		backbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setSize(MainPanel.FRAME_X, MainPanel.FRAME_Y);
				MyUtil.changePanel(mainFrame, LoginPanel.this, MainFrame.mainPanel);
				MainFrame.loginPanel = new LoginPanel(mainFrame);
			}
		});
		
		panel2.add(dobLabel);
		panel2.add(dobInput);
		panel2.add(phoneLabel);
		panel2.add(phoneInput);
		panel2.add(pwLabel);
		panel2.add(pwInput);
		panel2.add(btn);
		panel2.add(backbtn);
	}



//    public void actionPerformed(ActionEvent evt) {
//	wrappedTabs = !wrappedTabs;
//	if (wrappedTabs) {
//	    tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
//	} else {
//	    tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
//	}
//	tabbedPane.revalidate();
//    }
    

//	public static void main( String args[] )
//	{
//
//		final LoginPanel l = new LoginPanel();
//		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Runnable showFrame = new Runnable() {
//			public void run() {
//			    l.setVisible(true);
//			}
//		};
//		SwingUtilities.invokeLater(showFrame);
//	}
    



}
