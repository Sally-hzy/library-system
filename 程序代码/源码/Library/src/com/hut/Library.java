package com.hut;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import com.hut.iframe.BookLoginIFrame;
import com.hut.util.CreatecdIcon;
import com.hut.MenuActions;

/*
 * ������
 */
public class Library extends JFrame{
	
	private Font font;
	
	/*
	 * JDesktopPane ���ڴ������ĵ���������������������
	 * �û��ɴ��� JInternalFrame ���󲢽�����ӵ� JDesktopPane��
	 * JDesktopPane ��չ�� JLayeredPane���Թ�����ܵ��ص��ڲ����塣����ά���˶� DesktopManager ʵ�������ã�
	 * ������ UI ��Ϊ��ǰ����� (L&F) �����õ�
	 */
	private  static final JDesktopPane DESKTOP_PANE=new JDesktopPane();
	
	
	/*
	 * *********************������******************
	 */
	public static void main(String[] args) {
		
	
		
		 /* UIManager  ������ٵ�ǰ����ۼ���Ĭ�����á������з�ʽѡ��Ĭ������ࣺ 
		*1 ���ϵͳ���� swing.defaultlaf Ϊ�� null����������Ĭ����������ơ� 
		*2 ��� Properties �ļ� swing.properties �����Ұ����� swing.defaultlaf������ֵ����Ĭ����������ơ�swing.properties ��λ�ÿ����� Java ƽ̨��ʵ�ֶ���ͬ���� Sun ��ʵ���У��⽫פ���� &java.home>/lib/swing.properties �С��йظ���ϸ����Ϣ�������������ʹ�õ�ʵ�ֵķ���˵���� 
		*3 ������ʹ�� Java look and feel�� 
    	 *���ǹ����������������Ĭ�����ã��û�Ĭ�ϡ����Ĭ�ϡ�ϵͳĬ�ϡ�
		 */
		
		try {
			//System.out.println("******************Library  main  1***********");
			
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			//System.out.println("***********************Library main 2*************");
			//��¼����
			new BookLoginIFrame();
			//System.out.println("************************Library main 3***********");
		
		} catch (Exception ex) {
			// TODO �Զ����ɵ� catch ��
			ex.printStackTrace();
		} 
	}
	
	
	
	//��ӵ�½�Ӵ���
	public static void addIFrame(JInternalFrame iframe){
		DESKTOP_PANE.add(iframe);
	}
	
	
	public Library(){
		super();
		font=new Font("����", Font.BOLD, 20);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setSize(800,600);
		//setLocationByPlatform(true);
		setTitle("ͼ�����ϵͳ");
		
		//���ô����˵����ķ���
	 JMenuBar menuBar=createMenu();
	 setJMenuBar(menuBar);
	 
	 //���ù������ķ���
	 JToolBar toolBar=createToolBar();
	 getContentPane().add(toolBar, BorderLayout.NORTH);
	 
	 final JLabel label=new JLabel();
	 label.setBounds(0, 0, 0, 0);
	 label.setIcon(null);//���屳��Ϊ��
	 
	 
	 // ��DESKTOP_PANE��������ڲ��� ���ý���
	 DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
		 public void componentResized(final ComponentEvent e){
			 /*
			  * Dimension  ���װ��������������Ŀ�Ⱥ͸߶ȣ���ȷ���������������������ĳ�����Թ���
			  */
			 Dimension size=e.getComponent().getSize();
			 label.setSize(e.getComponent().getSize());
			 label.setText("<html><img width=" + size.width + " height="+ size.height + " src='"+ this.getClass().getResource("/backImg.jpg")+ "'></html>");
		 }
		 
	});
	 
	 DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));
	 getContentPane().add(DESKTOP_PANE);
	 
	}
	
	//����������
	private JToolBar createToolBar(){
		JToolBar toolBar=new JToolBar();
		toolBar.setFloatable(false);
		/*
		 * BevelBorder  ʵ�ּ򵥵�˫��б��߿�
		 */
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		//�������ͼ�鰴ť
		JButton bookAddButton=new JButton(MenuActions.BOOK_ADD);
		
		//������Ӳ˵���ͼ��
		ImageIcon icon=new ImageIcon(Library.class.getResource("/bookAddtb.jpg"));
		bookAddButton.setIcon(icon);
		bookAddButton.setHideActionText(true);
		
		toolBar.add(bookAddButton);
		
		//�ڹ����������ͼ���޸���ɾ��ͼ��
		JButton bookModiAndDelButton=new JButton("bookModiAndDelButton");
		//����ͼ�귽��
		ImageIcon bookmodiicon=CreatecdIcon.add("bookModiAndDeltb.jpg");
		bookModiAndDelButton.setIcon(bookmodiicon);
		bookModiAndDelButton.setHideActionText(true);
		
		toolBar.add(bookModiAndDelButton);
		
		//�ڹ����������ͼ���������ͼ��
		JButton bookTypeAddButton=new JButton(MenuActions.BOOKTYPE_ADD);
		ImageIcon bookTypeAddicon=CreatecdIcon.add("bookTypeAddtb.jpg");
		bookTypeAddButton.setIcon(bookTypeAddicon);
		bookTypeAddButton.setHideActionText(true);
		
		toolBar.add(bookTypeAddButton);
		
		//�ڹ����������ͼ�����ͼ��
		JButton bookBorrowButton=new JButton(MenuActions.BORROW);
		ImageIcon bookBorrowicon=CreatecdIcon.add("bookBorrowtb.jpg");
		bookBorrowButton.setIcon(bookBorrowicon);
		bookBorrowButton.setHideActionText(true);
		
		toolBar.add(bookBorrowButton);

		//
		JButton bookOrderButton=new JButton(MenuActions.NEWBOOK_ORDER);
		ImageIcon bookOrdericon=CreatecdIcon.add("bookOrdertb.jpg");
		bookOrderButton.setIcon(bookOrdericon);
		bookOrderButton.setHideActionText(true);
		
		toolBar.add(bookOrderButton);
		
		
		//
		JButton bookCheckButton=new JButton(MenuActions.NEWBOOK_CHECK_ACCEPT);
		ImageIcon bookCheckicon=CreatecdIcon.add("newbookChecktb.jpg");
		bookCheckButton.setIcon(bookCheckicon);
		bookCheckButton.setHideActionText(true);
		
		toolBar.add(bookCheckButton);
		
		
		//
		JButton readerAddButton=new JButton(MenuActions.READER_ADD);
		ImageIcon readerAddicon=CreatecdIcon.add("readerAddtb.jpg");
		readerAddButton.setIcon(readerAddicon);
		readerAddButton.setHideActionText(true);
		
		toolBar.add(readerAddButton);
		
		
		//
		JButton readerModiAndDelButton=new JButton(MenuActions.READER_MODIFY);
		ImageIcon readerModiAndDelicon=CreatecdIcon.add("readerModiAndDeltb.jpg");
		readerModiAndDelButton.setIcon(readerModiAndDelicon);
		readerModiAndDelButton.setHideActionText(true);
		
		toolBar.add(readerModiAndDelButton);
		
		//
		JButton ExitButton=new JButton(MenuActions.EXIT);
		ImageIcon Exiticon=CreatecdIcon.add("exittb.jpg");
		ExitButton.setIcon(Exiticon);
		ExitButton.setHideActionText(true);
		
		toolBar.add(ExitButton);
		
		
		return toolBar;
		
		
	}
	

	//�����˵���
	private JMenuBar createMenu() {
		/*
		 * JMenuBar �˵�����ʵ��
		 */
		JMenuBar menuBar=new JMenuBar();
		
		//��ʼ�����鶩������˵�
		/*
		 * JMenu �˵��ĸ�ʵ����һ������ JMenuItem �ĵ������ڣ��û�ѡ�� JMenuBar �ϵ���ʱ����ʾ�� JMenuItem
		 */
		JMenu bookOrderMenu=new JMenu();
		bookOrderMenu.setIcon(CreatecdIcon.add("xsdgcd.jpg"));
		bookOrderMenu.add(MenuActions.NEWBOOK_ORDER);
		bookOrderMenu.add(MenuActions.NEWBOOK_CHECK_ACCEPT);

		
		//��ʼ����������ά���˵�
		JMenu baseMenu=new JMenu();
		baseMenu.setIcon(CreatecdIcon.add("jcsjcd.jpg"));
		{
			JMenu readerManagerMItem = new JMenu("������Ϣ����");
			readerManagerMItem.add(MenuActions.READER_ADD);
			readerManagerMItem.add(MenuActions.READER_MODIFY);

			JMenu bookTypeManageMItem = new JMenu("ͼ��������");
			bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);
			bookTypeManageMItem.add(MenuActions.BOOKTYPE_MODIFY);

			JMenu menu = new JMenu("ͼ����Ϣ����");
			menu.add(MenuActions.BOOK_ADD);
			menu.add(MenuActions.BOOK_MODIFY);

			baseMenu.add(readerManagerMItem);
			baseMenu.add(bookTypeManageMItem);
			baseMenu.add(menu);
			baseMenu.addSeparator();
			baseMenu.add(MenuActions.EXIT);
		}
		
		//���Ĺ���
		JMenu borrowManageMenu = new JMenu(); 
		borrowManageMenu.setIcon(CreatecdIcon.add("jyglcd.jpg"));
		
		borrowManageMenu.add(MenuActions.BORROW); // ����
		borrowManageMenu.add(MenuActions.GIVE_BACK); // �黹
		borrowManageMenu.add(MenuActions.BOOK_SEARCH); // ����

		 // ϵͳά��
		JMenu sysManageMenu = new JMenu();
		sysManageMenu.setIcon(CreatecdIcon.add("jcwhcd.jpg"));
		
		// �û�����
		JMenu userManageMItem = new JMenu("�û�����"); 
		userManageMItem.add(MenuActions.USER_ADD);
		userManageMItem.add(MenuActions.USER_MODIFY);
		sysManageMenu.add(MenuActions.MODIFY_PASSWORD);
		
		sysManageMenu.add(userManageMItem);

		// ��ӻ�������ά���˵����˵���
		menuBar.add(baseMenu); 
		
		// ������鶩������˵����˵���
		menuBar.add(bookOrderMenu); 
		
		// ��ӽ��Ĺ���˵����˵���
		menuBar.add(borrowManageMenu); 
		
		// ���ϵͳά���˵����˵���
		menuBar.add(sysManageMenu); 
		
		return menuBar;
		
	}
	
	
	
	

}
