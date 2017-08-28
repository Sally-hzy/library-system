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
	
	

	// ���������
	private class BookResetAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			username.setText("");
			password.setText("");

		}
	}

	// ������¼��
	class BookLoginAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			user = Dao.check(username.getText(), password.getText());   // ��Dao�л�ȡ�û���������
			if (user.getName() != null) {

				try {

					Library frame = new Library();
					frame.setVisible(true);
					BookLoginIFrame.this.setVisible(false);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "�û��������������");
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
		 * BorderLayout  ��һ�����������ı߽粼�֣������Զ�����������а��ţ����������С��ʹ����������������
		 * �ϡ��������������м�����ÿ���������ֻ�ܰ���һ���������ͨ����Ӧ�ĳ������б�ʶ��NORTH��SOUTH��EAST��WEST �� CENTER��
		 * ��ʹ�ñ߽粼�ֽ�һ�������ӵ�������ʱ��Ҫʹ�����������֮һ
		 */
		try {
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
		} catch (Exception e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		} 
		
		final BorderLayout borderLayout = new BorderLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borderLayout.setVgap(20);
		getContentPane().setLayout(borderLayout);
		setTitle("ͼ��ݹ���ϵͳ��¼");
		setBounds(100, 100, 285, 194);

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(panel);

		final JPanel panel_2 = new JPanel();
		/*
		 * GridLayout  ��һ�����ִ����������Ծ���������ʽ��������������в��á��������ֳɴ�С��ȵľ��Σ�һ�������з���һ�����
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
		label.setText("��  ��  ����");

		username = new JTextField(20);
		username.setPreferredSize(new Dimension(0, 0));
		panel_2.add(username);

		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_1);
		label_1.setText("��      �룺");

		/*
		 * ���õ�¼����λ��
		 */
		password = new JPasswordField(20);
		password.setDocument(new MyDocument(6));
		password.setEchoChar('*');  // ���������Ļ����ַ�
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

		login.setText("��¼");
		panel_1.add(login);
		reset = new JButton();
		reset.addActionListener(new BookResetAction());

		reset.setText("����");
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
