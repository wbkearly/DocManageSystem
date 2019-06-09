package priv.wbk.controller;

import java.sql.SQLException;

import priv.wbk.jdbc.DataProcessing;
import priv.wbk.model.User;

public class LoginController {
	
	private static User loginUser = null; 
	
	public static User getUser() {
		return loginUser;
	}
	
	public static boolean checkLoginAccess(String username, String password) {
	
		try {
			loginUser = DataProcessing.searchUser(username, password);
			if(loginUser != null) {
				@SuppressWarnings("unused")
				MainFrameController mainFrameController = new MainFrameController(loginUser);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
