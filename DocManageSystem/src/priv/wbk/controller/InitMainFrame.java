package priv.wbk.controller;
import priv.wbk.view.MainFrame;

public class InitMainFrame {

	public static void initMainFrame(MainFrame mainFrame) {
			
		mainFrame.setUser(LoginController.getUser());
		if(!mainFrame.getUser().getRole().equalsIgnoreCase("Administrator")) {
			mainFrame.getUserManageMenu().setEnabled(false);
		}
		if(!mainFrame.getUser().getRole().equalsIgnoreCase("Operator")) {
			mainFrame.getUploadFileMenuItem().setEnabled(false);
		}
	}
}
