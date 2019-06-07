package priv.wbk.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import priv.wbk.jdbc.DataProcessing;
import priv.wbk.model.User;
import priv.wbk.utils.AddComponentHelper;

public class UserFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane; //选项卡
	private JPanel addUserPanel; //添加用户
	private JPanel delUserPanel; //删除用户
	private JPanel modUserPanel; //修改用户
	
	//添加用户面板
	private JLabel add_usernameLabel; //新增用户名标签
	private JLabel add_passwordLabel; //新增用户密码标签
	private JLabel add_roleLabel; //新增用户身份标签
	private JTextField add_usernameField; //新增用户名文本框
	private JPasswordField add_PasswordField; //新增用户密码文本框
	private JComboBox<String> add_roleComboBox; //新增用户身份下拉框
	private JButton add_ensureButton; //新增用户确认按钮
	private JButton add_cancelButton; //新增用户取消按钮
	private DefaultComboBoxModel<String> add_roleComboBoxModel = 
			new DefaultComboBoxModel<String>(
			new String[] {"", "Administrator", "Operator", "Browser"}); //添加用户名下拉框数据模型

	//修改用户面板
	private JLabel mod_usernameLabel; //修改用户名标签
	private JLabel mod_passwordLabel; //修改用户密码标签
	private JLabel mod_roleLabel; //修改用户身份标签
	private JComboBox<String> mod_usernameComboBox; //修改用户名下拉框
	private JPasswordField mod_passwordField; //修改用户密码框
	private JComboBox<String> mod_roleComboBox; //修改用户身份下拉框
	private JButton mod_ensureButton; //修改用户身份确认按钮
	private JButton mod_cancelButton; //修改用户身份取消按钮
	private DefaultComboBoxModel<String> mod_roleComboBoxModel = 
			new DefaultComboBoxModel<String>(
			new String[] {"", "Administrator", "Operator", "Browser"}); //修改用户名下拉框数据模型
	private DefaultComboBoxModel<String> mod_usernameComboBoxModel; //修改用户名数据模型
	
	//删除用户面板
	private JScrollPane scrollPane; //滚动面板
	private JTable del_userTable; //删除用户表格
	private JButton del_ensureButton; //删除用户确认按钮
	private JButton del_cancelButton; //删除用户取消按钮
	private DefaultTableModel tableModel; //删除用户表格模型
	private String[][] tableValue = new String[100][3]; //表格数据,假定最多为100行3列
	private String[] colName = {"用户名", "口令", "角色"}; //列名
	
	/**
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame();
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
	public UserFrame() {
	
		//设置窗体标题
		this.setTitle("档案管理系统-用户管理");
		
		//设置窗体大小
		this.setSize(400, 300);
		
		//设置窗体在屏幕中央
		AddComponentHelper.setFrameInScreenCenter(this);
		
		//启用窗体的关闭按钮
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//设置选项卡面板
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		AddComponentHelper.addTabbedPane(this, tabbedPane);
		
		//添加新增用户选项卡
		addUserPanel = new JPanel();
		AddComponentHelper.addTab(tabbedPane, addUserPanel, "新增用户");
		addUserPanel.setLayout(null);
		
		//添加修改用户选项卡
		modUserPanel = new JPanel();
		AddComponentHelper.addTab(tabbedPane, modUserPanel, "修改用户");
		modUserPanel.setLayout(null);
		
		//添加删除用户选项卡
		delUserPanel = new JPanel();
		AddComponentHelper.addTab(tabbedPane, delUserPanel, "删除用户");
		delUserPanel.setLayout(null);
		
		//给新增用户面板添加组件
		add_usernameLabel = new JLabel("用户名:");
		AddComponentHelper.setLabel(add_usernameLabel, 70, 40, 70, 30);
		AddComponentHelper.addLabel(addUserPanel, add_usernameLabel);
		
		add_usernameField = new JTextField();
		AddComponentHelper.setTextField(add_usernameField, 130, 40, 160, 30, 10);
		AddComponentHelper.addTextField(addUserPanel, add_usernameField);
		
		add_passwordLabel = new JLabel("密码:");
		AddComponentHelper.setLabel(add_passwordLabel, 70, 80, 70, 30);
		AddComponentHelper.addLabel(addUserPanel, add_passwordLabel);
		
		add_PasswordField = new JPasswordField();
		AddComponentHelper.setPasswordField(add_PasswordField, 130, 80, 160, 30);
		AddComponentHelper.addPasswordField(addUserPanel, add_PasswordField);
		
		add_roleLabel = new JLabel("身份:");
		AddComponentHelper.setLabel(add_roleLabel, 70, 120, 70, 30);
		AddComponentHelper.addLabel(addUserPanel, add_roleLabel);
		
		add_roleComboBox = new JComboBox<String>();
		AddComponentHelper.setComboBox(add_roleComboBox, add_roleComboBoxModel, 130, 120, 160, 30);
		AddComponentHelper.addComboBox(addUserPanel, add_roleComboBox);
		
		add_ensureButton = new JButton("确定");
		AddComponentHelper.setButton(add_ensureButton, 70, 160, 70, 20);
		AddComponentHelper.addButton(addUserPanel, add_ensureButton);
		add_ensureButton.addActionListener(new AddEnsureListener());
		
		add_cancelButton = new JButton("取消");
		AddComponentHelper.setButton(add_cancelButton, 220, 160, 70, 20);
		AddComponentHelper.addButton(addUserPanel, add_cancelButton);
		add_cancelButton.addActionListener(new AddCancelListener());
		
		//给修改用户面板添加组件
		mod_usernameLabel = new JLabel("用户名:");
		AddComponentHelper.setLabel(mod_usernameLabel, 70, 40, 70, 30);
		AddComponentHelper.addLabel(modUserPanel, mod_usernameLabel);
		
		mod_usernameComboBox = new JComboBox<String>();
		mod_usernameComboBoxModel = new DefaultComboBoxModel<String>();
		mod_usernameComboBox.addItemListener(new ModUsernameListener());
		addUserNameToComboBox(); //TODO
		AddComponentHelper.setComboBox(mod_usernameComboBox, mod_usernameComboBoxModel, 130, 40, 160, 30);
		AddComponentHelper.addComboBox(modUserPanel, mod_usernameComboBox);
		
		mod_passwordLabel = new JLabel("密码:");
		AddComponentHelper.setLabel(mod_passwordLabel, 70, 80, 70, 30);
		AddComponentHelper.addLabel(modUserPanel, mod_passwordLabel);
		
		mod_passwordField = new JPasswordField();
		AddComponentHelper.setPasswordField(mod_passwordField, 130, 80, 160, 30);
		AddComponentHelper.addPasswordField(modUserPanel, mod_passwordField);
		
		mod_roleLabel = new JLabel("身份:");
		AddComponentHelper.setLabel(mod_roleLabel, 70, 120, 70, 30);
		AddComponentHelper.addLabel(modUserPanel, mod_roleLabel);
		
		mod_roleComboBox = new JComboBox<String>();
		AddComponentHelper.setComboBox(mod_roleComboBox, mod_roleComboBoxModel, 130, 120, 160, 30);
		initRoleComboBox();
		AddComponentHelper.addComboBox(modUserPanel, mod_roleComboBox);
		
		mod_ensureButton = new JButton("确定");
		AddComponentHelper.setButton(mod_ensureButton, 70, 160, 70, 20);
		AddComponentHelper.addButton(modUserPanel, mod_ensureButton);
		mod_ensureButton.addActionListener(new ModEnsureListener());
		
		mod_cancelButton = new JButton("取消");
		AddComponentHelper.setButton(mod_cancelButton, 220, 160, 70, 20);
		AddComponentHelper.addButton(modUserPanel, mod_cancelButton);
		mod_cancelButton.addActionListener(new ModCancelListener());
		
		//给删除用户面板添加组件
		scrollPane = new JScrollPane();		
		AddComponentHelper.setScrollerPane(scrollPane, 0, 0, 400, 200);
		AddComponentHelper.addScrollPane(delUserPanel, scrollPane);
		
		del_userTable = new JTable();
		del_userTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
		del_userTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		addUserToTable(); //TODO
		scrollPane.setViewportView(del_userTable);
		
		del_ensureButton = new JButton("确定");
		AddComponentHelper.setButton(del_ensureButton, 70, 200, 70, 20);
		AddComponentHelper.addButton(delUserPanel, del_ensureButton);
		del_ensureButton.addActionListener(new DelEnsureListener());
		
		del_cancelButton = new JButton("取消");
		AddComponentHelper.setButton(del_cancelButton, 220, 200, 70, 20);
		AddComponentHelper.addButton(delUserPanel, del_cancelButton);
		del_cancelButton.addActionListener(new DelCancelListener());
		
	}
	
	private void initRoleComboBox() {
		User user = null;
		try{
			user = DataProcessing.searchUser((String)(mod_usernameComboBox.getSelectedItem()));
			mod_passwordField.setText(user.getPassword());
			mod_roleComboBox.setSelectedItem(user.getClass().getSimpleName());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	private void addUserNameToComboBox() {
		
		try {
			Vector<User> users = DataProcessing.getAllUser();
			for(User user:users) {
				mod_usernameComboBoxModel.addElement(user.getName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void addUserToTable() {
	
		try {
			Vector<User> users = DataProcessing.getAllUser();
			int row = 0;
			for(User user:users) {
				tableValue[row][0] = user.getName();
				tableValue[row][1] = user.getPassword();
				tableValue[row][2] = user.getRole();
				row++;
			}
			tableModel = new DefaultTableModel(tableValue, colName);
			AddComponentHelper.setTable(del_userTable, tableModel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private class ModUsernameListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			User user;
			if (e.getStateChange() == ItemEvent.SELECTED) {
				try{
					user = DataProcessing.searchUser((String)e.getItem());
					mod_passwordField.setText(user.getPassword());
					System.out.println(user.getClass().getSimpleName());
					mod_roleComboBox.setSelectedItem(user.getClass().getSimpleName());
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
		
	}
	
	private class AddEnsureListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
	
	private class AddCancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ModEnsureListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	private class ModCancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class DelEnsureListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class DelCancelListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
