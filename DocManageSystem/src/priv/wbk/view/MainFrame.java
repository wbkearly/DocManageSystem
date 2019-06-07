package priv.wbk.view;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import priv.wbk.model.User;
import priv.wbk.utils.AddComponentHelper;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane; //内容面板
	private JMenuBar menuBar; //菜单条
	
	//菜单
	private JMenu userManageMenu; //用户管理菜单
	private JMenu docManageMenu; //文档管理菜单
	private JMenu selfInfoManageMenu; //个人信息管理菜单
	
	//用户管理菜单
	private JMenuItem addUserMenuItem; //添加用户菜单项
	private JMenuItem delUserMenuItem; //删除用户菜单项
	private JMenuItem modUserMenuItem; //修改用户菜单项
	
	//文档管理菜单
	private JMenuItem uploadFileMenuItem; //上传文件菜单项
	private JMenuItem downloadFileMenuItem; //下载文件菜单项
	
	//个人信息管理菜单
	private JMenuItem selfInfoModMenuItem; //个人信息修改菜单项
	
	//登录人员
	private User user = null;

	/**
	 * 启动应用
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 创建窗体
	 */
	public MainFrame() {
		
		//设置窗体标题
		this.setTitle("档案管理系统-主界面");
		
		//设置窗体大小
		this.setSize(400, 300);
		
		//设置窗体在屏幕中央
		AddComponentHelper.setFrameInScreenCenter(this);
		
		//启用窗体的关闭按钮
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//设置内容面板
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new CardLayout(0, 0));
		this.setContentPane(contentPane);
		
		//添加菜单条
		menuBar = new JMenuBar();
		AddComponentHelper.addMenuBar(this, menuBar);
		
		//添加菜单
		userManageMenu = new JMenu("用户管理");
		docManageMenu = new JMenu("档案管理");
		selfInfoManageMenu = new JMenu("个人信息管理");
		AddComponentHelper.addMenu(menuBar, userManageMenu);
		AddComponentHelper.addMenu(menuBar, docManageMenu);
		AddComponentHelper.addMenu(menuBar, selfInfoManageMenu);
		
		//添加用户管理菜单项
		addUserMenuItem = new JMenuItem("新增用户");
		delUserMenuItem = new JMenuItem("删除用户"); 
		modUserMenuItem = new JMenuItem("修改用户"); 
		AddComponentHelper.addMenuItem(userManageMenu, addUserMenuItem);
		AddComponentHelper.addMenuItem(userManageMenu, delUserMenuItem);
		AddComponentHelper.addMenuItem(userManageMenu, modUserMenuItem);
		
		//添加文档管理菜单项
		uploadFileMenuItem = new JMenuItem("档案上传");
		downloadFileMenuItem = new JMenuItem("档案下载");
		AddComponentHelper.addMenuItem(docManageMenu, uploadFileMenuItem);
		AddComponentHelper.addMenuItem(docManageMenu, downloadFileMenuItem);
		
		//添加个人信息管理菜单项
		selfInfoModMenuItem = new JMenuItem("信息修改");
		AddComponentHelper.addMenuItem(selfInfoManageMenu, selfInfoModMenuItem);
		
		//为各个菜单项添加事件处理
		addUserMenuItem.addActionListener(new AddUerActionListener());
		delUserMenuItem.addActionListener(new DelUserActionListener());
		modUserMenuItem.addActionListener(new ModUserActionListener());
		uploadFileMenuItem.addActionListener(new UploadFileActionListener());
		downloadFileMenuItem.addActionListener(new DownloadFileActionListener());
		selfInfoModMenuItem.addActionListener(new SelfInfoActionListener());
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public JMenu getUserManageMenu() {
		return userManageMenu;
	}
	
	public JMenuItem getUploadFileMenuItem() {
		return uploadFileMenuItem;
	}

	private class AddUerActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class DelUserActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ModUserActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class UploadFileActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class DownloadFileActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class SelfInfoActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
