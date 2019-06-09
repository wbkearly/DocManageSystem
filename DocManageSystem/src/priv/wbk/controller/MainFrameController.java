package priv.wbk.controller;
import priv.wbk.model.User;
import priv.wbk.view.FileFrame;
import priv.wbk.view.MainFrame;
import priv.wbk.view.SelfInfoFrame;
import priv.wbk.view.UserFrame;

public class MainFrameController {

	private MainFrame mainFrame; //主窗口
	private User currentUser = null; //主窗口当前用户
	
	/**
	 * 构造函数
	 * @param currentUser 当前用户
	 */
	public MainFrameController(User currentUser) {
		
		this.currentUser = currentUser;
		mainFrame = new MainFrame();
		initMainFrame();
	}
	
	/**
	 * 初始化主界面
	 */
	private void initMainFrame() {

		mainFrame.setCurrentUser(currentUser);
		if(!mainFrame.getCurrentUser().getRole().equalsIgnoreCase("Administrator")) {
			mainFrame.getUserManageMenu().setEnabled(false);
		}
		if(!mainFrame.getCurrentUser().getRole().equalsIgnoreCase("Operator")) {
			mainFrame.getUploadFileMenuItem().setEnabled(false);
		}
		mainFrame.setVisible(true);
	}
	
	/**
	 * 用户界面
	 * @param currentUser 当前用户
	 * @param index 0:新增用户 1:删除用户  2:修改用户
	 */
	public static void goUserFrameByIndex(User currentUser, int index) {
		
		UserFrame userFrame = new UserFrame();
		userFrame.setCurrentUser(currentUser);
		userFrame.getTabbedPane().setSelectedIndex(index);
		userFrame.setVisible(true);
	}
	
	/**
	 * 进入文件管理界面
	 * @param currentUser 当前用户
	 * @param index 0:文件上传  1:文件下载
	 */
	public static void goFileFrameById(User currentUser, int index) {
		
		FileFrame fileFrame = new FileFrame();
		fileFrame.setCurrentUser(currentUser);
		if(!fileFrame.getCurrentUser().getRole().equalsIgnoreCase("Operator")) {
			fileFrame.getTabbedPane().setEnabledAt(0, false);
		}
		
		fileFrame.getTabbedPane().setSelectedIndex(index);
		fileFrame.setVisible(true);
	}
	
	/**
	 * 个人信息修改
	 * @param currentUser 当前用户
	 */
	public static void goSelfInfoFrame(User currentUser) {
		
		SelfInfoFrame selfInfoFrame = new SelfInfoFrame();
		selfInfoFrame.setCurrentUser(currentUser);
		selfInfoFrame.setVisible(true);
		selfInfoFrame.getUsernameField().setText(currentUser.getName());
		selfInfoFrame.getUsernameField().setEditable(false);
		selfInfoFrame.getRoleField().setText(currentUser.getRole());
		selfInfoFrame.getRoleField().setEditable(false);
	}
	
}
