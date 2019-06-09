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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
	private JPasswordField add_passwordField; //新增用户密码文本框
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
	private String[][] tableValue;//表格数据,假定最多为100行3列
	private String[] colName = {"用户名", "口令", "角色"}; //列名
	
	//当前用户
	private User currentUser = null;


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
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//设置选项卡面板
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeTabListener());
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
		
		add_passwordField = new JPasswordField();
		AddComponentHelper.setPasswordField(add_passwordField, 130, 80, 160, 30);
		AddComponentHelper.addPasswordField(addUserPanel, add_passwordField);
		
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
	
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	private void initRoleComboBox() {
		User user = null;
		try{
			user = DataProcessing.searchUserByName((String)(mod_usernameComboBox.getSelectedItem()));
			mod_passwordField.setText(user.getPassword());
			mod_roleComboBox.setSelectedItem(user.getClass().getSimpleName());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	private void showUserNameToComboBox() {
		
		try {
			Vector<User> users = DataProcessing.getAllUser();
			mod_usernameComboBoxModel.removeAllElements();
			for(User user:users) {
				mod_usernameComboBoxModel.addElement(user.getName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void showUserInfoToTable() {
	
		try {
			Vector<User> users = DataProcessing.getAllUser();
			tableValue = new String[100][3]; 
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
	
	private class ChangeTabListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			if(tabbedPane.getSelectedIndex() == 1) {
				showUserNameToComboBox();
				initRoleComboBox();
			}
			else if(tabbedPane.getSelectedIndex() == 2) {
				showUserInfoToTable();
			}
		}
		
	}
	private class ModUsernameListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			User user;
			if (e.getStateChange() == ItemEvent.SELECTED) {
				try{
					user = DataProcessing.searchUserByName((String)e.getItem());
					mod_passwordField.setText(user.getPassword());
					mod_roleComboBox.setSelectedItem(user.getClass().getSimpleName());
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
		
	}
	
	private class AddEnsureListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				String name = add_usernameField.getText();
				String password = String.valueOf(add_passwordField.getPassword());
				String role = add_roleComboBox.getSelectedItem().toString();
				if(DataProcessing.insertUser(name, password, role)) {
					JOptionPane.showMessageDialog(addUserPanel, "新增用户成功!");
				} else {
					JOptionPane.showMessageDialog(addUserPanel, "新增用户失败!");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	private class AddCancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
	
	private class ModEnsureListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				String name = mod_usernameComboBox.getSelectedItem().toString();
				String password = String.valueOf(mod_passwordField.getPassword());
				String role = mod_roleComboBox.getSelectedItem().toString();
				if(DataProcessing.updateUser(name, password, role)) {
					JOptionPane.showMessageDialog(modUserPanel, "修改用户成功!");
				} else {
					JOptionPane.showMessageDialog(modUserPanel, "修改用户失败!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	private class ModCancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
	
	private class DelEnsureListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				int selectedRow = del_userTable.getSelectedRow();
				String name = tableValue[selectedRow][0];
				if(DataProcessing.deleteUserByName(name)) {
					showUserInfoToTable();
					JOptionPane.showMessageDialog(delUserPanel, "删除成功!");
				} else {
					JOptionPane.showMessageDialog(delUserPanel, "删除失败!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private class DelCancelListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
