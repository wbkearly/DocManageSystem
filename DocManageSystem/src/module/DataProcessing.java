package module;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class DataProcessing {

    private static boolean connectToDB = false; //是否连接到数据库

    /**
     * 数据库初始化
     */
    @SuppressWarnings("unused")
    public static void Init() {
    }

    @SuppressWarnings("WeakerAccess")
    static Hashtable<String, User> users;
    static Hashtable<String, Doc> docs;

    static {
        users = new Hashtable<>();
        users.put("jack", new Operator("jack", "123", "operator"));
        users.put("rose", new Browser("rose", "123", "browser"));
        users.put("kate", new Administrator("kate", "123", "administrator"));
        Init();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        docs = new Hashtable<>();
        docs.put("0001", new Doc("0001", "jack", timestamp, "module.Doc Source Java", "Doc.java"));
    }

    /**
     * 在Hashtable中查找是否存在姓名为name的用户
     *
     * @param name 用户姓名
     * @return 返回姓名为name的用户
     */
    @SuppressWarnings("WeakerAccess")
    public static User searchUser(String name) throws SQLException {
        /*if (!connectToDB) {
            throw new SQLException("Not Connected to Database");
        }
        double ranValue = Math.random();
        if (ranValue > 0.5) {
            throw new SQLException("Error in executing Query");
        }*/

        if (users.containsKey(name)) {
            return users.get(name);
        }
        return null;
    }

    /**
     * 登录时使用，根据用户名和密码查找用户
     *
     * @param name     用户姓名
     * @param password 用户密码
     * @return 如果找到了用户名为name, 密码为password的用户，则返回该用户，否则返回null
     */
    @SuppressWarnings("WeakerAccess")
    public static User search(String name, String password) throws SQLException {
        /*if (!connectToDB) {
            throw new SQLException("Not Connected to Database");
        }
        double ranValue = Math.random();
        if (ranValue > 0.5) {
            throw new SQLException("Error in executing Query");
        }*/

        if (users.containsKey(name)) {
            User temp = users.get(name);
            if ((temp.getPassword()).equals(password)) {
                return temp;
            }
        }
        return null;
    }

    /**
     * 在Hashtable中查找所有用户相关信息
     *
     * @return 返回Hashtable中存储的所有用户信息
     */
    @SuppressWarnings("WeakerAccess")
    public static Enumeration<User> getAllUser() throws SQLException {
        /*if (!connectToDB) {
            throw new SQLException("Not Connected to Database");
        }

        double ranValue = Math.random();
        if (ranValue > 0.5) {
            throw new SQLException("Error in executing Query");
        }*/

        return users.elements();
    }

    /**
     * 更新用户信息，修改用户时使用
     *
     * @param name     用户姓名
     * @param password 用户密码
     * @param role     用户身份
     * @return 返回是否更新成功
     */
    @SuppressWarnings("WeakerAccess")
    public static boolean update(String name, String password, String role) throws SQLException {
        /*if (!connectToDB)
            throw new SQLException("Not Connected to Database");

        double ranValue = Math.random();
        if (ranValue > 0.5)
            throw new SQLException("Error in executing Update");*/

        if (users.containsKey(name)) {
            return insertUser(name, password, role);
        } else
            return false;
    }

    /**
     * 向Hashtable中插入用户，在管理员新增用户时使用
     *
     * @param name     用户姓名
     * @param password 用户密码
     * @param role     用户身份
     * @return 是否插入成功
     */
    @SuppressWarnings("WeakerAccess")
    public static boolean insert(String name, String password, String role) throws SQLException {

       /* if (!connectToDB)
            throw new SQLException("Not Connected to Database");

        double ranValue = Math.random();
        if (ranValue > 0.5)
            throw new SQLException("Error in executing Insert");*/

        if (users.containsKey(name))
            return false;
        else {
            return insertUser(name, password, role);
        }
    }

    /**
     * 根据用户名信息来进行对用户信息的插入或更新
     *
     * @param name     用户姓名
     * @param password 用户密码
     * @param role     用户身份
     * @return 返回是否插入或更新成功
     */
    private static boolean insertUser(String name, String password, String role) {
        User user;
        if (role.equalsIgnoreCase("administrator")) {
            user = new Administrator(name, password, role);
        } else if (role.equalsIgnoreCase("operator")) {
            user = new Operator(name, password, role);
        } else {
            user = new Browser(name, password, role);
        }
        users.put(name, user);
        return true;
    }

    /**
     * 在Hashtable中删除用户姓名为name的用户信息
     *
     * @param name 用户姓名
     * @return 是否删除成功
     */
    @SuppressWarnings("WeakerAccess")
    public static boolean delete(String name) throws SQLException {
      /*  if (!connectToDB)
            throw new SQLException("Not Connected to Database");

        double ranValue = Math.random();
        if (ranValue > 0.5)
            throw new SQLException("Error in excecuting Delete");*/

        if (users.containsKey(name)) {
            users.remove(name);
            return true;
        } else {
            return false;
        }

    }

    /**
     * 从数据库断开连接
     */
    @SuppressWarnings("unused")
    public void disconnectFromDB() {
        if (connectToDB) {
            // close Statement and Connection
            try {
               /* if (Math.random() > 0.5)
                    throw new SQLException("Error in disconnecting DB");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();*/
            } finally {
                connectToDB = false;
            }
        }
    }

    public static Doc searchDoc(String ID) throws SQLException {
        if (docs.containsKey(ID)) {
            Doc temp = docs.get(ID);
            return temp;
        }
        return null;
    }

    public static Enumeration<Doc> getAllDocs() throws SQLException {
        return docs.elements();
    }

    public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException {
        Doc doc;

        if (docs.containsKey(ID))
            return false;
        else {
            doc = new Doc(ID, creator, timestamp, description, filename);
            docs.put(ID, doc);
            return true;
        }
    }

}
