package priv.wbk.controller;

import java.sql.SQLException;

import priv.wbk.jdbc.DataProcessing;
import priv.wbk.model.User;
import priv.wbk.view.MainFrame;

public class LoginController {
	
	private static User user = null; 
	
	public static User getUser() {
		return user;
	}
	
	public static boolean checkLoginAccess(String username, String password) {
	
		try {
			user = DataProcessing.searchUser(username, password);
			if(user != null) {
				MainFrame mainFrame = new MainFrame(); 
				mainFrame.setVisible(true);
				InitMainFrame.initMainFrame(mainFrame);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
}
