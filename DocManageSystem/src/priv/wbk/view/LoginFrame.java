package priv.wbk.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import priv.wbk.controller.LoginController;
import priv.wbk.utils.AddComponentHelper;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane; //内容面板
	private JLabel usernameLabel; //用户名标签
	private JLabel passwordLabel; //密码标签
	private JTextField usernameField; //用户名文本框
	private JPasswordField passwordFiled; //密码框
	private JButton loginButton; //登录按钮
	private JButton cancelButton; //取消按钮
	
	/**
	 * 启动应用
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
	
		//设置窗体标题
		this.setTitle("档案管理系统-登录");
		
		//设置窗体大小
		this.setSize(380, 200);
		
		//设置窗体在屏幕中央
		AddComponentHelper.setFrameInScreenCenter(this);
		
		//启用窗体的关闭按钮
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//设置窗体在其它窗体之上
		this.setAlwaysOnTop(true);
		
		//设置内容面板
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		this.setContentPane(contentPane);
		
		//添加用户名标签组件
		usernameLabel = new JLabel("用户名:");
		AddComponentHelper.setLabel(usernameLabel, 60, 30, 70, 20);
		AddComponentHelper.addLabel(contentPane, usernameLabel);
		
		//添加用户名文本框组件
		usernameField = new JTextField();
		AddComponentHelper.setTextField(usernameField, 120, 30, 150, 20, 10);
		AddComponentHelper.addTextField(contentPane, usernameField);
		
		//添加密码标签组件
		passwordLabel = new JLabel("密码:");
		AddComponentHelper.setLabel(passwordLabel, 60, 60, 70, 20);
		AddComponentHelper.addLabel(contentPane, passwordLabel);
		
		//添加密码框组件
		passwordFiled = new JPasswordField();
		AddComponentHelper.setPasswordField(passwordFiled, 120, 60, 150, 20);
		AddComponentHelper.addPasswordField(contentPane, passwordFiled);
		
		//添加登录按钮组件
		loginButton = new JButton("登录");
		AddComponentHelper.setButton(loginButton, 100, 120, 70, 20);
		AddComponentHelper.addButton(contentPane, loginButton);
		loginButton.addActionListener(new LoginActionListener());
		
		//添加取消按钮组件
		cancelButton = new JButton("取消");
		AddComponentHelper.setButton(cancelButton, 180, 120, 70, 20);
		AddComponentHelper.addButton(contentPane, cancelButton);
		cancelButton.addActionListener(new CancelActionListener());
		
	}
	
	public JPasswordField getPasswordField() {
		return passwordFiled;
	}

	public JTextField getUsernameField() {
		return usernameField;
	}
	
	/**
	 * 处理登录按钮点击事件类
	 * @author wbk
	 */
	private class LoginActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = usernameField.getText();
			String password = passwordFiled.getPassword().toString();
			if(!LoginController.checkLoginAccess(username, password)) {
				JOptionPane.showMessageDialog(contentPane, "登录失败!");
			} else {
				dispose();
			}
		}
		
	}
	
	/**
	 * 处理取消按钮事件类
	 * @author wbk
	 */
	private class CancelActionListener implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(WindowConstants.EXIT_ON_CLOSE);
		}
	}
	
}
