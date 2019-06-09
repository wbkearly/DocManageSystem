package priv.wbk.jdbc;

public class SQLConstant {

	public static final String SEARCH_USER_BY_NAME = "select * from user_info where username = ?"; //根据用户名查找用户
    public static final String SEARCH_USER = "select * from user_info where username = ? and password = ?"; //根据用户名和密码查找用户
	public static final String SEARCH_ALL_USERS = "select * from user_info"; //查找所有用户
	public static final String UPDATE_USER = "update user_info set username = ?, password = ?, role = ? where username = ?"; //更新用户信息
    public static final String INSERT_USER = "insert into user_info(username, password, role) " //新增用户
    		+ " values(?, ?, ?)";
    public static final String DELETE_USER = "delete from user_info where username = ?"; //删除用户
    
    public static final String SEARCH_DOC_BY_ID = "select * from doc_info where id = ?"; //根据id查找文档信息
    public static final String SEARCH_ALL_DOCS = "select * from doc_info"; //查找所有文档	
    public static final String UPDATE_DOC = "update doc_info set id = ?, creator = ?, timestamp = ?, description = ?, filename = ? where id = ?"; //更新文档
    public static final String INSERT_DOC = "insert into doc_info(id, creator, timestamp, description, filename)" 
    	    + " values(?, ?, ?, ?, ?)";		 
    public static final String DELETE_DOC = "delete from doc_info where id = ?"; //根据id删除文档
 
}
