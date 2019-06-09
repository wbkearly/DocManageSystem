package priv.wbk.jdbc;

import priv.wbk.model.*;
import priv.wbk.utils.Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class DataProcessing {

    private  static DBConfig dbConfig = null;
    private static Connection conn = null;
    static {
        dbConfig = new DBConfig();
        try {
            Class.forName(dbConfig.getDriver()); //加载驱动
        } catch(ClassNotFoundException e) {
            System.out.println("没有找到jdbc驱动类!");
        }
    }
    
    // 单例模式返回数据库连接对象
 	public static Connection getConnection() throws SQLException {
 		if(conn == null) {
 			conn = DriverManager.getConnection(
 					dbConfig.getUrl(), 
 					dbConfig.getUserName(), 
 					dbConfig.getPassword());
 			return conn;
 		}
 		return conn;
 	}

    /**
     * 在数据库中查找用户名为name的用户，管理员使用该方法
     * @param name 要查找用户的姓名
     * @return 返回用户信息
     * @throws SQLException 抛出数据库查找异常
     */
    public static User searchUserByName(String name) throws SQLException {
       
    	Connection connection = getConnection();
    	User user = null;
    	PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        
        preparedStatement = connection.prepareStatement(SQLConstant.SEARCH_USER_BY_NAME);
        preparedStatement.setObject(1, name);
        resultset = preparedStatement.executeQuery();
        if(resultset.next()) {
        	String password = resultset.getString("password");
        	String role = resultset.getString("role");
        	if(role.equalsIgnoreCase("Administrator")) {
        		user = new Administrator(name, password, "Administrator");
        	}
        	else if(role.equalsIgnoreCase("Operator")) {
        		user = new Operator(name, password, role);
        	}
        	else {
        		user = new Browser(name, password, role);
        	}
        }

    	Common.free(connection, preparedStatement, resultset);
        return user;
    }

    /**
     * 普通用户登录时查找信息
     * @param name 用户名
     * @param password 密码
     * @return 用户信息
     * @throws SQLException 数据库查找异常
     */
    public static User searchUser(String name, String password) throws SQLException {
    	
    	Connection connection = getConnection();
    	User user = null;
    	PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        
        preparedStatement = connection.prepareStatement(SQLConstant.SEARCH_USER);
        preparedStatement.setObject(1, name);
        preparedStatement.setObject(2, password);
        resultset = preparedStatement.executeQuery();
        if(resultset.next()) {
        	String role = resultset.getString("role");
        	if(role.equalsIgnoreCase("Administrator")) {
        		user = new Administrator(name, password, "Administrator");
        	}
        	else if(role.equalsIgnoreCase("Operator")) {
        		user = new Operator(name, password, role);
        	}
        	else {
        		user = new Browser(name, password, role);
        	}
        }

    	Common.free(connection, preparedStatement, resultset);
        return user;
    }
    
    /**
     * 获取数据库中所有用户信息
     * @return 所有用户信息
     * @throws SQLException 数据库访问异常
     */
    public static Vector<User> getAllUser() throws SQLException {
    	
    	Vector<User> users = new Vector<>(); //存储所有用户信息
    	Connection connection = getConnection(); //获取数据库连接
    	PreparedStatement preparedStatement = null;
    	ResultSet resultset = null; //返回结果集
    	
    	//执行查询语句
    	preparedStatement = connection.prepareStatement(SQLConstant.SEARCH_ALL_USERS);
    	resultset = preparedStatement.executeQuery();
    	
    	while(resultset.next()) {
    		
    		String name = resultset.getString("username");
    		String role = resultset.getString("role");
        	String password = resultset.getString("password");
    		User user = null;

    		if(role.equalsIgnoreCase("Administrator")) {
        		user = new Administrator(name, password, "Administrator");
        	}
        	else if(role.equalsIgnoreCase("Operator")) {
        		user = new Operator(name, password, role);
        	}
        	else {
        		user = new Browser(name, password, role);
        	}
    		users.add(user);
    	}

    	Common.free(connection, preparedStatement, resultset);
        return users;
    }

    /**
     * 根据用户名删除用户
     * @param name 要删除的用户名
     * @return 是否删除成功
     * @throws SQLException 数据库访问异常
     */
    public static boolean deleteUserByName(String name) throws SQLException {
    	
    	Connection connection = getConnection();
    	PreparedStatement preparedStatement = null;
    	int resultset = 0;
    	preparedStatement = connection.prepareStatement(SQLConstant.DELETE_USER);
    	preparedStatement.setObject(1, name);
    	resultset = preparedStatement.executeUpdate();

    	Common.free(connection, preparedStatement, null);
    	
    	if(resultset != 0) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * 更新用户信息
     * @param name 用户名
     * @param password 密码
     * @param role 身份
     * @return 是否更新成功
     * @throws SQLException 数据库访问异常
     */
    public static boolean updateUser(String name, String password, String role) throws SQLException {
    	
    	Connection connection = getConnection();
    	PreparedStatement preparedStatement = null;
    	int resultset = 0;
    	preparedStatement = connection.prepareStatement(SQLConstant.UPDATE_USER);
    	preparedStatement.setObject(1, name);
    	preparedStatement.setObject(2, password);
    	preparedStatement.setObject(3, role);
    	preparedStatement.setObject(4, name);
    	resultset = preparedStatement.executeUpdate();
    	Common.free(connection, preparedStatement, null);
    	if(resultset != 0) {
    		return true;
    	}
    	return false;
    }

    /**
     * 根据用户名信息来进行对用户信息的插入或更新
     *
     * @param name     用户姓名
     * @param password 用户密码
     * @param role     用户身份
     * @return 返回是否插入或更新成功
     * @throws SQLException 数据库访问异常
     */
    public static boolean insertUser(String name, String password, String role) throws SQLException {
    	
    	Connection connection = getConnection();
    	PreparedStatement preparedStatement = null;
    	int resultset = 0;
    	
    	preparedStatement = connection.prepareStatement(SQLConstant.INSERT_USER);
    	
    	//设置参数
    	preparedStatement.setObject(1, name);
    	preparedStatement.setObject(2, password);
    	preparedStatement.setObject(3, role);
    	
    	//返回更新的记录条数
    	resultset = preparedStatement.executeUpdate();
    	
    	Common.free(connection, preparedStatement, null);
    	if(resultset != 0) {
    		return true;
    	}
    	return false;
    }

    /**
     * 根据id查找文档信息
     * @param ID 档案号
     * @return 档案信息
     * @throws SQLException 数据访问异常
     */
    public static Doc searchDocById(String ID) throws SQLException {
    	
    	Connection connection = getConnection();
    	Doc doc = null;
    	PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        
        preparedStatement = connection.prepareStatement(SQLConstant.SEARCH_DOC_BY_ID);
        preparedStatement.setObject(1, ID);
        resultset = preparedStatement.executeQuery();
        if(resultset.next()) {
        	String id = resultset.getString("id");
        	String creator = resultset.getString("creator");
        	Timestamp timestamp = resultset.getTimestamp("timestamp");
        	String description = resultset.getString("description");
        	String filename = resultset.getString("filename");
        	doc = new Doc(id, creator, timestamp, description, filename);
        }
        Common.free(connection, preparedStatement, resultset);
        return doc;
    }

    /**
     * 获取所有文档信息
     * @return 所有档案信息
     * @throws SQLException 数据库访问异常
     */
    public static Vector<Doc> getAllDocs() throws SQLException {
    	
    	Vector<Doc> docs = new Vector<>();
    	Connection connection = getConnection();
    	PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        
        preparedStatement = connection.prepareStatement(SQLConstant.SEARCH_ALL_DOCS);
        resultset = preparedStatement.executeQuery();
        while(resultset.next()) {
        	Doc doc = null;
        	String id = resultset.getString("id");
        	String creator = resultset.getString("creator");
        	Timestamp timestamp = resultset.getTimestamp("timestamp");
        	String description = resultset.getString("description");
        	String filename = resultset.getString("filename");
        	doc = new Doc(id, creator, timestamp, description, filename);
        	docs.add(doc);
        }
        Common.free(connection, preparedStatement, resultset);
        return docs;
    }

    /**
     * 新增档案
     * @param ID 档案号
     * @param creator 档案创建者
     * @param timestamp 档案创建时间
     * @param description 档案描述
     * @param filename 档案名
     * @return 档案信息
     * @throws SQLException 数据库访问异常
     */
    public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException {
        
    	Connection connection = getConnection();
    	PreparedStatement preparedStatement = null;
    	int resultset = 0;
    	
    	preparedStatement = connection.prepareStatement(SQLConstant.INSERT_DOC);
    	
    	//设置参数
    	preparedStatement.setObject(1, ID);
    	preparedStatement.setObject(2, creator);
    	preparedStatement.setObject(3, timestamp);
    	preparedStatement.setObject(4, description);
    	preparedStatement.setObject(5, filename);
    	
    	//返回更新的记录条数
    	resultset = preparedStatement.executeUpdate();
    	
    	Common.free(connection, preparedStatement, null);
    	if(resultset != 0) {
    		return true;
    	}
    	return false;

    }
    
    /**
     * 根据文档id来查询文档信息
     * @param ID 档案号
     * @return 要查询的文档信息
     * @throws SQLException 数据库访问异常
     */
    public static boolean deleteDocById(String ID) throws SQLException {
    	Connection connection = getConnection();
    	PreparedStatement preparedStatement = null;
    	int resultset = 0;
    	preparedStatement = connection.prepareStatement(SQLConstant.DELETE_DOC);
    	preparedStatement.setObject(1, ID);
    	resultset = preparedStatement.executeUpdate();

    	Common.free(connection, preparedStatement, null);
    	
    	if(resultset != 0) {
    		return true;
    	}
    	return false;

    }

    /**
     * 更新文档信息
     * @param ID 文档ID
     * @param creator 文档创建者
     * @param timestamp 创建时间
     * @param description 档案描述
     * @param filename 档案名
     * @return 是否更新成功
     * @throws SQLException 数据库访问异常
     */
    public static boolean updateDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException {
    	
    	Connection connection = getConnection();
    	PreparedStatement preparedStatement = null;
    	int resultset = 0;
    	preparedStatement = connection.prepareStatement(SQLConstant.UPDATE_DOC);
    	preparedStatement.setObject(1, ID);
    	preparedStatement.setObject(2, creator);
    	preparedStatement.setObject(3, timestamp);
    	preparedStatement.setObject(4, description);
    	preparedStatement.setObject(5, filename);
    	resultset = preparedStatement.executeUpdate();
    	Common.free(connection, preparedStatement, null);
    	if(resultset != 0) {
    		return true;
    	}
    	return false;
    }

}