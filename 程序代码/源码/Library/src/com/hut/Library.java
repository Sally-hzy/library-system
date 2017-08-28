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
 * 主窗体
 */
public class Library extends JFrame{
	
	private Font font;
	
	/*
	 * JDesktopPane 用于创建多文档界面或虚拟桌面的容器。
	 * 用户可创建 JInternalFrame 对象并将其添加到 JDesktopPane。
	 * JDesktopPane 扩展了 JLayeredPane，以管理可能的重叠内部窗体。它还维护了对 DesktopManager 实例的引用，
	 * 这是由 UI 类为当前的外观 (L&F) 所设置的
	 */
	private  static final JDesktopPane DESKTOP_PANE=new JDesktopPane();
	
	
	/*
	 * *********************主函数******************
	 */
	public static void main(String[] args) {
		
	
		
		 /* UIManager  此类跟踪当前的外观及其默认设置。按下列方式选择默认外观类： 
		*1 如果系统属性 swing.defaultlaf 为非 null，则将其用作默认外观类名称。 
		*2 如果 Properties 文件 swing.properties 存在且包含键 swing.defaultlaf，则将其值用作默认外观类名称。swing.properties 的位置可能随 Java 平台的实现而不同。在 Sun 的实现中，这将驻留在 &java.home>/lib/swing.properties 中。有关更详细的信息，请参阅您正在使用的实现的发行说明。 
		*3 否则，请使用 Java look and feel。 
    	 *我们管理下列三个级别的默认设置：用户默认、外观默认、系统默认。
		 */
		
		try {
			//System.out.println("******************Library  main  1***********");
			
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			//System.out.println("***********************Library main 2*************");
			//登录窗口
			new BookLoginIFrame();
			//System.out.println("************************Library main 3***********");
		
		} catch (Exception ex) {
			// TODO 自动生成的 catch 块
			ex.printStackTrace();
		} 
	}
	
	
	
	//添加登陆子窗体
	public static void addIFrame(JInternalFrame iframe){
		DESKTOP_PANE.add(iframe);
	}
	
	
	public Library(){
		super();
		font=new Font("宋体", Font.BOLD, 20);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setSize(800,600);
		//setLocationByPlatform(true);
		setTitle("图书管理系统");
		
		//调用创建菜单栏的方法
	 JMenuBar menuBar=createMenu();
	 setJMenuBar(menuBar);
	 
	 //调用工具栏的方法
	 JToolBar toolBar=createToolBar();
	 getContentPane().add(toolBar, BorderLayout.NORTH);
	 
	 final JLabel label=new JLabel();
	 label.setBounds(0, 0, 0, 0);
	 label.setIcon(null);//窗体背景为空
	 
	 
	 // 对DESKTOP_PANE添加匿名内部类 设置界面
	 DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
		 public void componentResized(final ComponentEvent e){
			 /*
			  * Dimension  类封装单个对象中组件的宽度和高度（精确到整数）。该类与组件的某个属性关联
			  */
			 Dimension size=e.getComponent().getSize();
			 label.setSize(e.getComponent().getSize());
			 label.setText("<html><img width=" + size.width + " height="+ size.height + " src='"+ this.getClass().getResource("/backImg.jpg")+ "'></html>");
		 }
		 
	});
	 
	 DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));
	 getContentPane().add(DESKTOP_PANE);
	 
	}
	
	//创建工具栏
	private JToolBar createToolBar(){
		JToolBar toolBar=new JToolBar();
		toolBar.setFloatable(false);
		/*
		 * BevelBorder  实现简单的双线斜面边框
		 */
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		//创建添加图书按钮
		JButton bookAddButton=new JButton(MenuActions.BOOK_ADD);
		
		//创建添加菜单栏图标
		ImageIcon icon=new ImageIcon(Library.class.getResource("/bookAddtb.jpg"));
		bookAddButton.setIcon(icon);
		bookAddButton.setHideActionText(true);
		
		toolBar.add(bookAddButton);
		
		//在工具栏中添加图书修改与删除图标
		JButton bookModiAndDelButton=new JButton("bookModiAndDelButton");
		//创建图标方法
		ImageIcon bookmodiicon=CreatecdIcon.add("bookModiAndDeltb.jpg");
		bookModiAndDelButton.setIcon(bookmodiicon);
		bookModiAndDelButton.setHideActionText(true);
		
		toolBar.add(bookModiAndDelButton);
		
		//在工具栏中添加图书种类添加图标
		JButton bookTypeAddButton=new JButton(MenuActions.BOOKTYPE_ADD);
		ImageIcon bookTypeAddicon=CreatecdIcon.add("bookTypeAddtb.jpg");
		bookTypeAddButton.setIcon(bookTypeAddicon);
		bookTypeAddButton.setHideActionText(true);
		
		toolBar.add(bookTypeAddButton);
		
		//在工具栏中添加图书借阅图标
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
	

	//创建菜单栏
	private JMenuBar createMenu() {
		/*
		 * JMenuBar 菜单栏的实现
		 */
		JMenuBar menuBar=new JMenuBar();
		
		//初始化新书订购管理菜单
		/*
		 * JMenu 菜单的该实现是一个包含 JMenuItem 的弹出窗口，用户选择 JMenuBar 上的项时会显示该 JMenuItem
		 */
		JMenu bookOrderMenu=new JMenu();
		bookOrderMenu.setIcon(CreatecdIcon.add("xsdgcd.jpg"));
		bookOrderMenu.add(MenuActions.NEWBOOK_ORDER);
		bookOrderMenu.add(MenuActions.NEWBOOK_CHECK_ACCEPT);

		
		//初始化基础数据维护菜单
		JMenu baseMenu=new JMenu();
		baseMenu.setIcon(CreatecdIcon.add("jcsjcd.jpg"));
		{
			JMenu readerManagerMItem = new JMenu("读者信息管理");
			readerManagerMItem.add(MenuActions.READER_ADD);
			readerManagerMItem.add(MenuActions.READER_MODIFY);

			JMenu bookTypeManageMItem = new JMenu("图书类别管理");
			bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);
			bookTypeManageMItem.add(MenuActions.BOOKTYPE_MODIFY);

			JMenu menu = new JMenu("图书信息管理");
			menu.add(MenuActions.BOOK_ADD);
			menu.add(MenuActions.BOOK_MODIFY);

			baseMenu.add(readerManagerMItem);
			baseMenu.add(bookTypeManageMItem);
			baseMenu.add(menu);
			baseMenu.addSeparator();
			baseMenu.add(MenuActions.EXIT);
		}
		
		//借阅管理
		JMenu borrowManageMenu = new JMenu(); 
		borrowManageMenu.setIcon(CreatecdIcon.add("jyglcd.jpg"));
		
		borrowManageMenu.add(MenuActions.BORROW); // 借阅
		borrowManageMenu.add(MenuActions.GIVE_BACK); // 归还
		borrowManageMenu.add(MenuActions.BOOK_SEARCH); // 搜索

		 // 系统维护
		JMenu sysManageMenu = new JMenu();
		sysManageMenu.setIcon(CreatecdIcon.add("jcwhcd.jpg"));
		
		// 用户管理
		JMenu userManageMItem = new JMenu("用户管理"); 
		userManageMItem.add(MenuActions.USER_ADD);
		userManageMItem.add(MenuActions.USER_MODIFY);
		sysManageMenu.add(MenuActions.MODIFY_PASSWORD);
		
		sysManageMenu.add(userManageMItem);

		// 添加基础数据维护菜单到菜单栏
		menuBar.add(baseMenu); 
		
		// 添加新书订购管理菜单到菜单栏
		menuBar.add(bookOrderMenu); 
		
		// 添加借阅管理菜单到菜单栏
		menuBar.add(borrowManageMenu); 
		
		// 添加系统维护菜单到菜单栏
		menuBar.add(sysManageMenu); 
		
		return menuBar;
		
	}
	
	
	
	

}
