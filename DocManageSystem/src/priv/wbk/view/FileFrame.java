package priv.wbk.view;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import priv.wbk.jdbc.DataProcessing;
import priv.wbk.model.Doc;
import priv.wbk.model.User;
import priv.wbk.utils.AddComponentHelper;

public class FileFrame extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane; //选项卡
	private JPanel uploadPanel; //上传面板
	private JPanel downloadPanel; //下载面板
	
	//上传面板
	private JLabel docIdLabel; //档案号标签
	private JLabel docDescribeLabel; //档案描述标签
	private JLabel docNameLabel; //档案名标签
	private JTextField docIdField; //档案号文本框
	private JScrollPane uploadScrollPane; //可滚动面板
	private JTextArea docDescribeTextArea; //档案号描述文本域
	private JTextField docNameTextField; //档案名文本框
	private JButton fileOpenButton; //打开文件按钮
	private JButton uploadButton; //上传按钮
	private JButton uploadCancelButton; //上传取消按钮
	
	//下载面板
	private JScrollPane downloadScrollPane; //可滚动面板
	private JTable docTable; //文件表格
	private JButton downloadButton; //下载按钮
	private JButton downloadCancelButton; //取消下载按钮
	private DefaultTableModel tableModel; //下载文件表格模型
	private String[][] tableValue = new String[100][5]; //表格数据,假定最多为100行
	private String[] colName = {"档案号", "创建者", "时间", "文件名", "描述"}; //列名
	
	private String uploadPath = "D:\\OOP\\uploadFile\\";
	private User currentUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileFrame frame = new FileFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FileFrame() {
		
		//设置窗体标题
		this.setTitle("档案管理系统-档案管理");
		
		//设置窗体大小
		this.setSize(400, 300);
		
		//设置窗体在屏幕中央
		AddComponentHelper.setFrameInScreenCenter(this);
		
		//启用窗体的关闭按钮
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//设置选项卡面板
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		AddComponentHelper.addTabbedPane(this, tabbedPane);
		tabbedPane.addChangeListener(new TabChangeListener());
		
		//添加文件上传选项卡
		uploadPanel = new JPanel();
		AddComponentHelper.addTab(tabbedPane, uploadPanel, "文件上传");
		uploadPanel.setLayout(null);
		
		//添加文件下载选项卡
		downloadPanel = new JPanel();
		AddComponentHelper.addTab(tabbedPane, downloadPanel, "文件下载");
		downloadPanel.setLayout(null);
		
		//添加组件到文件上传面板
		
		docIdLabel = new JLabel("档案号:");
		AddComponentHelper.setLabel(docIdLabel, 30, 30, 60, 20);
		AddComponentHelper.addLabel(uploadPanel, docIdLabel);
		
		docDescribeLabel = new JLabel("档案描述:");
		AddComponentHelper.setLabel(docDescribeLabel, 30, 70, 60, 20);
		AddComponentHelper.addLabel(uploadPanel, docDescribeLabel);
		
		docNameLabel = new JLabel("档案名:");
		AddComponentHelper.setLabel(docNameLabel, 30, 150, 70, 20);
		AddComponentHelper.addLabel(uploadPanel, docNameLabel);
		
		docIdField = new JTextField();
		AddComponentHelper.setTextField(docIdField, 120, 30, 130, 20, 10);
		AddComponentHelper.addTextField(uploadPanel, docIdField);
		
		uploadScrollPane = new JScrollPane();
		AddComponentHelper.setScrollerPane(uploadScrollPane, 120, 70, 130, 60);
		AddComponentHelper.addScrollPane(uploadPanel, uploadScrollPane);
		
		docDescribeTextArea = new JTextArea();
		AddComponentHelper.setTextArea(docDescribeTextArea, 120, 70, 130, 60);
		uploadScrollPane.setViewportView(docDescribeTextArea);
		
		docNameTextField = new JTextField();
		AddComponentHelper.setTextField(docNameTextField, 120, 150, 130, 20, 10);
		AddComponentHelper.addTextField(uploadPanel, docNameTextField);
		
		fileOpenButton = new JButton("打开");
		AddComponentHelper.setButton(fileOpenButton, 250, 150, 70, 20);
		AddComponentHelper.addButton(uploadPanel, fileOpenButton);
		fileOpenButton.addActionListener(new OpenFileListener());
		
		uploadButton = new JButton("上传");
		AddComponentHelper.setButton(uploadButton, 100, 190, 70, 20);
		AddComponentHelper.addButton(uploadPanel, uploadButton);
		uploadButton.addActionListener(new UploadListener());
		
		uploadCancelButton = new JButton("取消");
		AddComponentHelper.setButton(uploadCancelButton, 190, 190, 70, 20);
		AddComponentHelper.addButton(uploadPanel, uploadCancelButton);
		uploadCancelButton.addActionListener(new UploadCancelListener());
		
		//添加组件到文件下载面板
		
		downloadScrollPane = new JScrollPane();
		AddComponentHelper.setScrollerPane(downloadScrollPane, 10, 10, 360, 130);
		AddComponentHelper.addScrollPane(downloadPanel, downloadScrollPane);
		
		docTable = new JTable();
		docTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
		docTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		downloadScrollPane.setViewportView(docTable);
		
		downloadButton = new JButton("下载");
		AddComponentHelper.setButton(downloadButton, 100, 190, 70, 20);
		AddComponentHelper.addButton(downloadPanel, downloadButton);
		downloadButton.addActionListener(new DownloadListener());
		
		downloadCancelButton = new JButton("取消");
		AddComponentHelper.setButton(downloadCancelButton, 190, 190, 70, 20);
		AddComponentHelper.addButton(downloadPanel, downloadCancelButton);
		downloadCancelButton.addActionListener(new DownloadCancelListener());
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public JPanel getUploadPanel() {
		return uploadPanel;
	}

	public JPanel getDownloadPanel() {
		return downloadPanel;
	}
	
	private void showFileToTable() {
		
		try {
			Vector<Doc> docs = DataProcessing.getAllDocs();
			int row = 0;
			for(Doc doc:docs) {
				tableValue[row][0] = doc.getID();
				tableValue[row][1] = doc.getCreator();
				tableValue[row][2] = doc.getTimestamp().toString();
				tableValue[row][3] = doc.getFilename();
				tableValue[row][4] = doc.getDescription();
				row++;
			}
			tableModel = new DefaultTableModel(tableValue, colName);
			AddComponentHelper.setTable(docTable, tableModel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	
	private class TabChangeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			if(tabbedPane.getSelectedIndex() == 1) {
				showFileToTable();
			}
		}
		
	}

	private class OpenFileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JFileChooser fileChooser = new JFileChooser(".");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.showOpenDialog(new Frame());
			docNameTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
			
		}
		
	}
	
	private class UploadListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			String sourceFileName = docNameTextField.getText();
			String ID = docIdField.getText();
			String description = docDescribeTextArea.getText();
			byte[] buffer = new byte[1024];
			
			// 获取当前文件
			File tempFile = new File(sourceFileName);
			// 获取当前文件名
			String fileName = tempFile.getName();
			try {
			    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(tempFile));
			    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadPath + fileName));
				while (true) {
				    int byteRead = bufferedInputStream.read(buffer);
				    if (byteRead == -1)
				        break;
				    bufferedOutputStream.write(buffer, 0, byteRead);
				}
				bufferedInputStream.close();
				bufferedOutputStream.close();
				String creator = currentUser.getName();
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				if (!DataProcessing.insertDoc(ID, creator, timestamp, description, fileName)) {
				    tempFile = new File(uploadPath + fileName);
			    } else {
			    	JOptionPane.showMessageDialog(uploadPanel, "文件上传成功!");
			    }
			} catch (FileNotFoundException e) {
			    JOptionPane.showMessageDialog(uploadPanel, "文件不存在或路径名错误!");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(uploadPanel, "向数据库写入档案信息出错!");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(uploadPanel, "读写文件出错!");
			}

		}
		
	}
	
	private class UploadCancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
	
	private class DownloadListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			int selectedRow = docTable.getSelectedRow();
			JFileChooser fileChooser = new JFileChooser(".");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.showSaveDialog(new Frame());
			String downloadPath = fileChooser.getSelectedFile().getAbsolutePath();
					
			byte[] buffer = new byte[1024];
	        String fileId = tableValue[selectedRow][0];
	        String fileName;
	        String tempFileName;
	        String targetFileName;
	        try {
	            Doc doc = DataProcessing.searchDocById(fileId);
	            if (doc != null) {
	                fileName = doc.getFilename();
	                tempFileName = uploadPath + fileName; 
	                targetFileName = downloadPath + "//" + fileName;
	                File tempFile = new File(tempFileName);
	                File targetFile = new File(targetFileName);
	                FileInputStream fileInputStream;
	                FileOutputStream fileOutputStream;
	                fileInputStream = new FileInputStream(tempFile);
	                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
	                fileOutputStream = new FileOutputStream(targetFile);
	                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
	                while (true) {
	                    int byteRead = bufferedInputStream.read(buffer);
	                    if (byteRead == -1)
	                        break;
	                    bufferedOutputStream.write(buffer, 0, byteRead);
	                }
	                bufferedInputStream.close();
	                bufferedOutputStream.close();
	                JOptionPane.showMessageDialog(downloadPanel, "文件下载成功!");
	            } else {
	                JOptionPane.showMessageDialog(downloadPanel, "未找到档案号为" + fileId + "的文件!");
	            }
	        } catch (SQLException e) {
	        	JOptionPane.showMessageDialog(downloadPanel, "数据库查找文件失败!");
	        } catch (FileNotFoundException e) {
	        	JOptionPane.showMessageDialog(downloadPanel,"文件不存在或路径名错误!");
	        } catch (IOException e) {
	        	JOptionPane.showMessageDialog(downloadPanel,"读写文件错误,文件下载失败!");
	        }
		}
		
	}
	
	private class DownloadCancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	
	}

}
