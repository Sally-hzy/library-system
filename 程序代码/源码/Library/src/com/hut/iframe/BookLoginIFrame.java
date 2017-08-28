package com.hut.iframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.hut.Library;
import com.hut.dao.Dao;
import com.hut.model.Operater;
import com.hut.util.CreatecdIcon;
import com.hut.util.MyDocument;

public class BookLoginIFrame extends JFrame {
	
	

	// 创建清空类
	private class BookResetAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			username.setText("");
			password.setText("");

		}
	}

	// 创建登录类
	class BookLoginAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			user = Dao.check(username.getText(), password.getText());   // 从Dao中获取用户名和密码
			if (user.getName() != null) {

				try {

					Library frame = new Library();
					frame.setVisible(true);
					BookLoginIFrame.this.setVisible(false);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "用户名或者密码错误！");
				username.setText("");
				password.setText("");
			}
		}
	}

	private JPasswordField password; 
	private JTextField username;
	private JButton login;
	private JButton reset;
	private static Operater user;

	/**
	 * Create the iframe
	 */
	public BookLoginIFrame() {
		super();
		/*
		 * BorderLayout  是一个布置容器的边界布局，它可以对容器组件进行安排，并调整其大小，使其符合下列五个区域：
		 * 南、北、东、西和中间区域。每个区域最多只能包含一个组件，并通过相应的常量进行标识：NORTH、SOUTH、EAST、WEST 和 CENTER。
		 * 当使用边界布局将一个组件添加到容器中时，要使用这五个常量之一
		 */
		try {
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} 
		
		final BorderLayout borderLayout = new BorderLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borderLayout.setVgap(20);
		getContentPane().setLayout(borderLayout);
		setTitle("图书馆管理系统登录");
		setBounds(100, 100, 285, 194);

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(panel);

		final JPanel panel_2 = new JPanel();
		/*
		 * GridLayout  是一个布局处理器，它以矩形网格形式对容器的组件进行布置。容器被分成大小相等的矩形，一个矩形中放置一个组件
		 */
		final GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setHgap(10);
		gridLayout.setVgap(20);
		panel_2.setLayout(gridLayout);
		panel.add(panel_2);

		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(0, 0));
		label.setMinimumSize(new Dimension(0, 0));
		panel_2.add(label);
		label.setText("用  户  名：");

		username = new JTextField(20);
		username.setPreferredSize(new Dimension(0, 0));
		panel_2.add(username);

		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_1);
		label_1.setText("密      码：");

		/*
		 * 设置登录密码位数
		 */
		password = new JPasswordField(20);
		password.setDocument(new MyDocument(6));
		password.setEchoChar('*');  // 设置密码框的回显字符
		password.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == 10)
					login.doClick();
			}
		});
		panel_2.add(password);

		final JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);

		login = new JButton();
		login.addActionListener(new BookLoginAction());

		login.setText("登录");
		panel_1.add(login);
		reset = new JButton();
		reset.addActionListener(new BookResetAction());

		reset.setText("重置");
		panel_1.add(reset);

		// System.out.println("**************BookLoginIFrame tupianLabel*1");
		final JLabel pictureLabel = new JLabel();
		// System.out.println("**************BookLoginIFrame tupianLabel*2");
		ImageIcon loginIcon = CreatecdIcon.add("login.jpg");
		// System.out.println("**************BookLoginIFrame tupianLabel*3");
		pictureLabel.setIcon(loginIcon);
		pictureLabel.setOpaque(true);
		pictureLabel.setBackground(Color.GREEN);
		pictureLabel.setPreferredSize(new Dimension(260, 60));
		panel.add(pictureLabel, BorderLayout.NORTH);

		setVisible(true);
		setResizable(false);
		// setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

	}

	public static Operater getUser() {
		return user;
	}

	public static void setUser(Operater user) {
		BookLoginIFrame.user = user;
	}

}
