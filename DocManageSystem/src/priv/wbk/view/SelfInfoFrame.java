package priv.wbk.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import priv.wbk.jdbc.DataProcessing;
import priv.wbk.model.User;
import priv.wbk.utils.AddComponentHelper;

public class SelfInfoFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane; //内容面板
	
	private JLabel usernameLabel; //用户名标签
	private JLabel oldPwdLabel; //原密码标签
	private JLabel newPwdLabel; //新密码标签
	private JLabel confirmNewPwdLabel; //确认新口令标签
	private JLabel roleLabel; //角色标签
	
	private JTextField usernameField; //用户名文本框
	private JPasswordField oldPwdField; //原密码 密码框
	private JPasswordField newPwdField; //新密码 密码框
	private JPasswordField confirmNewPwdField; //确认新密码 密码框
	private JTextField roleField; //角色文本框

	private JButton ensureButton; //确认按钮
	private JButton cancelButton; //取消按钮
	
	private User currentUser = null;

	/**
	 * 启动应用程序
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelfInfoFrame frame = new SelfInfoFrame();
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
	public SelfInfoFrame() {
		
		//设置窗体标题
		this.setTitle("档案管理系统-个人信息管理");
		
		//设置窗体大小
		this.setSize(400, 300);
		
		//设置窗体在屏幕中央
		AddComponentHelper.setFrameInScreenCenter(this);
		
		//启用窗体的关闭按钮
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//设置内容面板
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		this.setContentPane(contentPane);
		
		usernameLabel = new JLabel("用户名:");
		AddComponentHelper.setLabel(usernameLabel, 70, 20, 70, 30);
		AddComponentHelper.addLabel(contentPane, usernameLabel);
		
		usernameField = new JTextField();
		AddComponentHelper.setTextField(usernameField, 140, 20, 160, 30, 10);
		AddComponentHelper.addTextField(contentPane, usernameField);
		
		oldPwdLabel = new JLabel("原密码:");
		AddComponentHelper.setLabel(oldPwdLabel, 70, 60, 70, 30);
		AddComponentHelper.addLabel(contentPane, oldPwdLabel);
		
		oldPwdField = new JPasswordField();
		AddComponentHelper.setPasswordField(oldPwdField, 140, 60, 160, 30);
		AddComponentHelper.addPasswordField(contentPane, oldPwdField);
		
		newPwdLabel = new JLabel("新密码:");
		AddComponentHelper.setLabel(newPwdLabel, 70, 100, 70, 30);
		AddComponentHelper.addLabel(contentPane, newPwdLabel);
		
		newPwdField = new JPasswordField();
		AddComponentHelper.setPasswordField(newPwdField, 140, 100, 160, 30);
		AddComponentHelper.addPasswordField(contentPane, newPwdField);
		
		confirmNewPwdLabel = new JLabel("确认新密码:");
		AddComponentHelper.setLabel(confirmNewPwdLabel, 70, 140, 70, 30);
		AddComponentHelper.addLabel(contentPane, confirmNewPwdLabel);
		
		confirmNewPwdField = new JPasswordField();
		AddComponentHelper.setPasswordField(confirmNewPwdField, 140, 140, 160, 30);
		AddComponentHelper.addPasswordField(contentPane, confirmNewPwdField);
		
		roleLabel = new JLabel("身份:");
		AddComponentHelper.setLabel(roleLabel, 70, 180, 70, 30);
		AddComponentHelper.addLabel(contentPane, roleLabel);
		
		roleField = new JTextField();
		AddComponentHelper.setTextField(roleField, 140, 180, 160, 30, 10);
		AddComponentHelper.addTextField(contentPane, roleField);
		
		ensureButton = new JButton("确定");
		AddComponentHelper.setButton(ensureButton, 70, 220, 70, 20);
		AddComponentHelper.addButton(contentPane, ensureButton);
		ensureButton.addActionListener(new EnsureListener());
		
		cancelButton = new JButton("取消");
		AddComponentHelper.setButton(cancelButton, 220, 220, 70, 20);
		AddComponentHelper.addButton(contentPane, cancelButton);
		cancelButton.addActionListener(new CancelListener());
		
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}


	public JTextField getUsernameField() {
		return usernameField;
	}
	
	public JTextField getRoleField() {
		return roleField;
	}
	
	private class EnsureListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String name = usernameField.getText();
			String role = roleField.getText();
			String oldPwd = String.valueOf(oldPwdField.getPassword());
			String newPwd = String.valueOf(newPwdField.getPassword());
			String confirmNewPwd = String.valueOf(confirmNewPwdField.getPassword());
			if(!oldPwd.equals(currentUser.getPassword())) {
				JOptionPane.showMessageDialog(contentPane, "原密码不正确!");
			}
			else {
				if(newPwd.equals("")) {
					JOptionPane.showMessageDialog(contentPane, "新密码不能为空!");
				}
				else {
					if(!confirmNewPwd.equals(newPwd)) {
						JOptionPane.showMessageDialog(contentPane, "两次密码不一致!");
					}
					else {
						try {
							if(DataProcessing.updateUser(name, newPwd, role)) {
								JOptionPane.showMessageDialog(contentPane, "修改信息成功!");
							}
							else {
								JOptionPane.showMessageDialog(contentPane, "修改信息失败!");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
	}
	
	private class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
}
