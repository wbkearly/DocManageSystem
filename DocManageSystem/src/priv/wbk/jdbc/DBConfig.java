package priv.wbk.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {

    private String driver; //jdbc驱动信息
    private String url; //url
    private String userName; //数据库用户名
    private String password; //数据库用户密码

    /**
     * 构造函数， 读取jdbc.properties文件，初始化属性
     */
    public DBConfig() {
        super();
        InputStream is = null;
        try {
            is = new FileInputStream("D:\\Code\\Java\\OOP\\DocManageSystem\\JDBCProperties\\jdbc.properties");
            Properties p = new Properties();
            p.load(is);
            this.driver = p.getProperty("jdbc.driver");
            this.url = p.getProperty("jdbc.url");
            this.userName = p.getProperty("jdbc.userName");
            this.password = p.getProperty("jdbc.password");
        } catch (FileNotFoundException e) {
            System.out.println("jdbc.properties文件不存在!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件load失败!");
            e.printStackTrace();
        }
        try {
            if(is!= null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取驱动信息
     * @return jdbc驱动信息
     */
    public String getDriver() {
        return driver;
    }
    /**
     * 获取url
     * @return url
     */
    public String getUrl() {
        return url;
    }
    /**
     * 获取数据库用户登陆名
     * @return 数据库用户登陆名
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 获取数据库用户密码
     * @return 数据库用户密码
     */
    public String getPassword() {
        return password;
    }

}

