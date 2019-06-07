package priv.wbk.jdbc;

import priv.wbk.model.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class DataProcessing {

    private  static DBConfig dbConfig = null;
    static {
        dbConfig = new DBConfig();
        try {
            Class.forName(dbConfig.getDriver()); //加载驱动
        } catch(ClassNotFoundException e) {
            System.out.println("没有找到jdbc驱动类!");
        }
    }

    /**
     * 在Hashtable中查找是否存在姓名为name的用户
     *
     * @param name 用户姓名
     * @return 返回姓名为name的用户
     */
    public static User searchUser(String name) throws SQLException {
        return new Administrator("jack", "123", "Administrator");
    }

    /**
     * 登录时使用，根据用户名和密码查找用户
     *
     * @param name     用户姓名
     * @param password 用户密码
     * @return 如果找到了用户名为name, 密码为password的用户，则返回该用户，否则返回null
     */
    public static User searchUser(String name, String password) throws SQLException {
        return null;
    }

    /**
     * 在Hashtable中查找所有用户相关信息
     *
     * @return 返回Hashtable中存储的所有用户信息
     */
    public static Vector<User> getAllUser() throws SQLException {
    	Vector<User> users = new Vector<>();
    	User user = new Administrator("jack", "123", "Administrator");
    	User user2 = new Operator("peter", "123", "Operator");
    	
    	users.add(user);
    	users.add(user2);
        return users;
    }

    /**
     * 更新用户信息，修改用户时使用
     *
     * @param name     用户姓名
     * @param password 用户密码
     * @param role     用户身份
     * @return 返回是否更新成功
     */
    public static boolean updateUser(String name, String password, String role) throws SQLException {
        return false;
    }

    /**
     * 根据用户名信息来进行对用户信息的插入或更新
     *
     * @param name     用户姓名
     * @param password 用户密码
     * @param role     用户身份
     * @return 返回是否插入或更新成功
     */
    public static boolean insertUser(String name, String password, String role) {
        return true;
    }

    /**
     * 在Hashtable中删除用户姓名为name的用户信息
     *
     * @param name 用户姓名
     * @return 是否删除成功
     */
    public static boolean delete(String name) throws SQLException {
        return true;
    }

    /**
     * 从数据库断开连接
     */
    public void disconnectFromDB() {
    }

    public static Doc searchDoc(String ID) throws SQLException {
        return null;
    }

    public static Vector<Doc> getAllDocs() throws SQLException {
    	Vector<Doc> docs = new Vector<>();
    	Doc doc = new Doc("111", "jack", new Timestamp(System.currentTimeMillis()), "doc", "file");
    	docs.add(doc);
        return docs;
    }

    public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException {
        return true;
    }

}
