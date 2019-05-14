import java.util.*;

public class DataProcessing {

	static Hashtable<String, User> users;

	static {
		users = new Hashtable<>();
		users.put("jack", new Operator("jack","123","operator"));
		users.put("rose", new Browser("rose","123","browser"));
		users.put("kate", new Administrator("kate","123","administrator"));		
	}

	/**
	 * 在Hashtable中查找是否存在姓名为name的用户
	 * @param name 用户姓名
	 * @return 返回是否存在姓名为name的用户
	 */
	public static User searchUser(String name){
		if (users.containsKey(name)) {
			return users.get(name);			
		}
		return null;
	}

	/**
	 * 登录时使用，根据用户名和密码查找用户
	 * @param name 用户姓名
	 * @param password 用户密码
	 * @return 如果找到了用户名为name,密码为password的用户，则返回该用户，否则返回null
	 */
	public static User search(String name, String password){
		if (users.containsKey(name)) {
			User temp =users.get(name);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;
	}

	/**
	 * 在Hashtable中查找所有用户相关信息
	 * @return 返回Hashtable中存储的所有用户信息
	 */
	public static Enumeration<User> getAllUser(){

		final Enumeration<User> e = users.elements();
		return e;
	}

	/**
	 * 更新用户信息，修改用户时使用
	 * @param name 用户姓名
	 * @param password 用户密码
	 * @param role 用户身份
	 * @return 返回是否更新成功
	 */
	public static boolean update(String name, String password, String role){
		User user;
		if (users.containsKey(name)) {
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}else
			return false;
	}

	/**
	 * 向Hashtable中插入用户，在管理员新增用户时使用
	 * @param name 用户姓名
	 * @param password 用户密码
	 * @param role 用户身份
	 * @return 是否插入成功
	 */
	public static boolean insert(String name, String password, String role){
		User user;
		if (users.containsKey(name))
			return false;
		else{
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}
	}

	/**
	 * 在Hashtable中删除用户姓名为name的用户信息
	 * @param name 用户姓名
	 * @return 是否删除成功
	 */
	public static boolean delete(String name){
				
		if (users.containsKey(name)){
			users.remove(name);
			return true;
		}else
			return false;
		
	}

}
