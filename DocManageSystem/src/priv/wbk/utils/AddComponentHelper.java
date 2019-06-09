package priv.wbk.utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AddComponentHelper {
	
	/**
	 * 设置窗体在屏幕中央
	 * @param frame 要设置的窗体
	 */
	public static void setFrameInScreenCenter(JFrame frame) {
		
		//获取屏幕高和宽
		Toolkit toolkit= frame.getToolkit();
		Dimension dimension=toolkit.getScreenSize();
		int screenHeight=dimension.height;
		int screenWidth=dimension.width;
		
		//获取窗体本身高和宽
		int frame_Height = frame.getHeight();
		int frame_Width = frame.getWidth();
		
		//设置窗体左上角坐标的位置
		frame.setLocation((screenWidth-frame_Width)/2, (screenHeight-frame_Height)/2);
	}
	
	
	/**
	 * 设置标签大小及宽高
	 * @param label 标签
	 * @param x 标签左上角横坐标
	 * @param y 标签左上角纵坐标
	 * @param width 标签的宽度
	 * @param height 标签的高度
	 */
	public static void setLabel(JLabel label, int x, int y, int width, int height) {
		
		//设置Label字体种类、样式及大小
		label.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
		
		//设置JLabel位置及宽高
		label.setBounds(x, y, width, height);
	}
	
	/**
	 * 添加标签到内容面板
	 * @param contentPane 要添加标签的内容面板
	 * @param label 要添加的标签
	 */
	public static void addLabel(JPanel contentPane, JLabel label) {
		
		contentPane.add(label);
	}
	
	/**
	 * 设置文本框的位置及宽高
	 * @param textField 要设置的文本框
	 * @param x 文本框左上角横坐标
	 * @param y 文本框左上角纵坐标
	 * @param width 文本框宽度
	 * @param height 文本框高度
	 * @param columns TODO
	 */
	public static void setTextField(JTextField textField, int x, int y, int width, int height, int columns) {
		
		textField.setBounds(x, y, width, height);
		textField.setColumns(columns);
	}
	
	/**
	 * 添加文本框到内容面板
	 * @param contentPane 要添加文本框的内容面板
	 * @param textField 要添加的文本框
	 */
	public static void addTextField(JPanel contentPane, JTextField textField) {
		
		contentPane.add(textField);
	}

	/**
	 * 设置密码框
	 * @param passwordField 要设置的密码框
	 * @param x 密码框左上角横坐标
	 * @param y 密码框左上角纵坐标
	 * @param width 密码框宽度
	 * @param height 密码框高度
	 */
	public static void setPasswordField(JPasswordField passwordField, int x, int y, int width, int height) {
		
		passwordField.setBounds(x, y, width, height);
	}
	
	/**
	 * 添加密码框到内容面板
	 * @param contentPane 要添加密码框的内容面板
	 * @param passwordField 要添加的密码框
	 */
	public static void addPasswordField(JPanel contentPane, JPasswordField passwordField) {
		
		contentPane.add(passwordField);
	}
	
	/**
	 * 设置按钮的位置及宽高
	 * @param button 要设置的按钮
	 * @param x 按钮左上角横坐标
	 * @param y 按钮左上角纵坐标
	 * @param width 按钮宽度
	 * @param height 按钮高度
	 */
	public static void setButton(JButton button, int x, int y, int width, int height) {
		
		//设置JButton字体种类及大小
		button.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
		
		//设置JButton字体位置及宽高
		button.setBounds(x, y, width, height);
	}
	
	/**
	 * 添加按钮到内容面板
	 * @param contentPane 要添加按钮的内容面板
	 * @param button 要添加的按钮
	 */
	public static void addButton(JPanel contentPane, JButton button) {
		
		contentPane.add(button);
	}
	
	/**
	 * 添加菜单条
	 * @param frame 要添加菜单条的窗体
	 * @param menuBar 菜单条
	 */
	public static void addMenuBar(JFrame frame, JMenuBar menuBar) {
		
		frame.setJMenuBar(menuBar);
	}
	
	/**
	 * 添加菜单
	 * @param menuBar 要添加菜单的菜单条
	 * @param menu 要添加的菜单
	 */
	public static void addMenu(JMenuBar menuBar, JMenu menu) {
		
		//设置菜单字体
		menu.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
		
		//添加菜单
		menuBar.add(menu);
	}
	
	/**
	 * 添加菜单项
	 * @param menu 要添加菜单项的菜单
	 * @param menuItem 要添加的菜单项
	 */
	public static void addMenuItem(JMenu menu, JMenuItem menuItem) {
		
		//设置菜单项字体
		menuItem.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
		
		//添加菜单项
		menu.add(menuItem);
	}
	
	/**
	 * 添加选项卡
	 * @param frame 要添加选项卡的窗体
	 * @param tabbedPane 添加的选项卡
	 */
	public static void addTabbedPane(JFrame frame, JTabbedPane tabbedPane) {
		
		//设置选项面板字体
		tabbedPane.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
		
		//添加选项面板
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}
	
	/**
	 * 添加选项面板
	 * @param tabbedPane 要添加选项面板的选项卡组件
	 * @param panel 要添加的选项面板
	 * @param title 选项面板标题
	 */
	public static void addTab(JTabbedPane tabbedPane, JPanel panel, String title) {
		
		tabbedPane.addTab(title, null, panel, null);
	}
	
	/**
	 * 设置下拉框数据，位置及宽高
	 * @param comboBox 要设置的下拉框
	 * @param comboBoxModel 要设置的下拉框里的数据
	 * @param x 下拉框左上角横坐标
	 * @param y 下拉框左上角纵坐标
	 * @param width 下拉框宽度
	 * @param height 下拉框高度
	 */
	public static void setComboBox(JComboBox<String> comboBox, DefaultComboBoxModel<String> comboBoxModel, int x, int y, int width, int height) {
		
		comboBox.setModel(comboBoxModel);	
		comboBox.setBounds(x, y, width, height);
	}
	
	/**
	 * 添加下拉框到面板
	 * @param panel 要添加下拉框的面板
	 * @param comboBox 要添加的下拉框
	 */
	public static void addComboBox(JPanel panel, JComboBox<String> comboBox) {
		
		panel.add(comboBox);
	}
	
	/**
	 * 设置滚动容器的位置及宽高
	 * @param scrollPane 滚动容器
	 * @param x 容器左上角横坐标
	 * @param y 容器左上角纵坐标
	 * @param width 容器宽
	 * @param height 容器高
	 */
	public static void setScrollerPane(JScrollPane scrollPane, int x, int y, int width, int height) {
		
		scrollPane.setBounds(x, y, width, height);
	}
	
	/**
	 * 添加滚动容器
	 * @param panel 要添加滚动容器的面板
	 * @param scrollPane 要添加的滚动容器
	 */
	public static void addScrollPane(JPanel panel, JScrollPane scrollPane) {
		
		panel.add(scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * 根据表格模型设置表格
	 * @param table 要创建的表格
	 * @param tableModel 依据的表格模型
	 */
	public static void setTable(JTable table, DefaultTableModel tableModel) {
		
		table.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
		table.setModel(tableModel);
		table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	}
	
	/**
	 * 设置文本域位置及宽高
	 * @param textArea 文本域
	 * @param x 文本域左上角横坐标
	 * @param y 文本域左上角纵坐标
	 * @param width 文本域宽度
	 * @param height 文本域高度
	 */
	public static void setTextArea(JTextArea textArea, int x, int y, int width, int height) {
		
		//设置文本域字体
		textArea.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
		
		//设置文本域位置及大小
		textArea.setBounds(x, y, width, height);
	}
	
}
