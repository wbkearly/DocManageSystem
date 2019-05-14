import java.util.Enumeration;
import java.util.Scanner;

public class Administrator extends User {

    String tip_menu = "****欢迎进入系统管理员菜单****";
    String tip_operation = "请选择操作：";
    String infos = "        1.修改用户\n"
            + "        2.删除用户\n"
            + "        3.增加用户\n"
            + "        4.列出用户\n"
            + "        5.下载文件\n"
            + "        6.文件列表\n"
            + "        7.修改密码\n"
            + "        8.退    出\n"
            + "*********************************\n";

    Scanner in = new Scanner(System.in);

    public Administrator(String name, String password, String role) {
        super(name, password, role);
    }

    public boolean changeUserInfo() {

        // TODO: 修改所有用户信息功能
        String name, password, role;
        System.out.println("--------修改用户--------");
        System.out.println("请输入用户名:");
        name = in.next();
        System.out.println("请输入口令：");
        password = in.next();
        System.out.println("请输入角色：");
        role = in.next();
        if (DataProcessing.searchUser(name) != null && DataProcessing.update(name, password, role)) {
            User user = DataProcessing.searchUser(name);
            if (user.getName() == name && user.getRole() == role && user.getPassword() == password) {
                System.out.println("修改用户" + name + "信息成功!");
                return true;
            }
        }
        System.out.println("修改失败!");
        return true;
    }

    public boolean delUser() {

        // TODO: 删除用户功能
        System.out.println("--------删除用户--------");
        System.out.println("请输入用户名：");
        String name = in.next();
        if (DataProcessing.delete(name) && DataProcessing.searchUser(name) == null) {
            System.out.println("删除用户" + name + "成功!");
        } else {
            System.out.println("删除失败!");
        }
        return true;
    }

    public boolean addUser() {

        // TODO: 新增用户功能
        String name, password, role;
        System.out.println("--------新增用户--------");
        System.out.println("请输入用户名:");
        name = in.next();
        System.out.println("请输入口令：");
        password = in.next();
        System.out.println("请输入角色：");
        role = in.next();
        if (DataProcessing.insert(name, password, role) && DataProcessing.searchUser(name) != null) {
            System.out.println("新增用户：role:" + role + " " + "name:" + name + "成功!");
        } else {
            System.out.println("新增用户失败!");
        }
        return true;
    }

    public void listUser() {

        // TODO: 显示所有用户信息功能
        Enumeration<User> e = DataProcessing.getAllUser();
        User user;
        if (e.hasMoreElements()) { //至少存在自己
            System.out.println("用户名\t密码\t身份");
            user = e.nextElement();
            System.out.printf("%s\t%s\t%s%n", user.getName(), user.getPassword(), user.getRole());
        }
        while (e.hasMoreElements()) {
            user = e.nextElement();
            System.out.printf("%s\t%s\t%s%n", user.getName(), user.getPassword(), user.getRole());
        }

    }

    @Override
    public void showMenu() {

        // TODO: 显示菜单功能
        while (true) {

            System.out.println(tip_menu);
            System.out.println(infos);
            System.out.println(tip_operation);

            switch (in.nextInt()) {
                case 1:
                    changeUserInfo();
                    break;
                case 2:
                    delUser();
                    break;
                case 3:
                    addUser();
                    break;
                case 4:
                    listUser();
                    break;
                case 5:
                    downloadFile();
                    break;
                case 6:
                    showFileList();
                    break;
                case 7:
                    changeSelfInfo();
                    break;
                case 8:
                    exitSystem();
                default:
                    System.out.println("输入错误");
                    break;
            }
        }
    }
}
