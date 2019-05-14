import java.util.Enumeration;
import java.util.Scanner;

public class Administrator extends User {

    Scanner in=new Scanner(System.in);

    public Administrator(String name, String password, String role) {
        super(name, password, role);
    }

    public boolean changeUserInfo() {

        // TODO: 修改所有用户信息功能
        String name,password,role;
        System.out.println("修改用户");
        System.out.println("请输入用户名:");
        name=in.next();
        System.out.println("请输入口令：");
        password=in.next();
        System.out.println("请输入角色：");
        role=in.next();
        if(DataProcessing.update(name, password, role)) {
            User user = DataProcessing.searchUser(name);
            if(user.getName() == name && user.getRole() == role && user.getPassword() == password) {
                System.out.println("修改用户"+ name + "信息成功!");
                return true;
            }
        }
        System.out.println("修改失败!");
        return true;
    }

    public boolean delUser() {

        // TODO: 删除用户功能
        System.out.println("删除用户");
        System.out.println("请输入用户名：");
        String name = in.next();
        if(DataProcessing.delete(name) && DataProcessing.searchUser(name) == null) {
            System.out.println("删除用户" + name + "成功!");
        } else {
            System.out.println("删除失败!");
        }
        return true;
    }

    public boolean addUser() {

        // TODO: 新增用户功能
        String name, password, role;
        System.out.println("新增用户");
        System.out.println("请输入用户名:");
        name=in.next();
        System.out.println("请输入口令：");
        password=in.next();
        System.out.println("请输入角色：");
        role=in.next();
        if(DataProcessing.insert(name, password, role) && DataProcessing.searchUser(name) != null) {
            System.out.println("新增用户：role:" + role + " " + "name:"  + name + "成功!" );
        } else {
            System.out.println("新增用户失败!");
        }
        return true;
    }

    public void listUser() {

        // TODO: 显示所有用户信息功能
        Enumeration<User> e = DataProcessing.getAllUser();
        User user;
        System.out.println("用户名\t密码\t身份");
        while (e.hasMoreElements()) {
            user = e.nextElement();
            System.out.printf("%s\t%s\t%s%n", user.getName(), user.getName(), user.getRole());
        }

    }

    @Override
    public void showMenu() {

        // TODO: 显示菜单功能
    }
}
