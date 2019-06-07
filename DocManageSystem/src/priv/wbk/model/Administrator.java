package priv.wbk.model;

import priv.wbk.jdbc.DataProcessing;

import java.sql.SQLException;
import java.util.Scanner;

public class Administrator extends User {

    private Scanner in = new Scanner(System.in);

    public Administrator(String name, String password, String role) {
        super(name, password, role);
    }

    private void changeUserInfo() {

        // TODO: 修改所有用户信息功能
        String name, password, role;
        System.out.println("--------修改用户--------");
        System.out.println("请输入用户名:");
        name = in.next();
        System.out.println("请输入口令：");
        password = in.next();
        System.out.println("请输入角色：");
        role = in.next();
        try {
            if (DataProcessing.searchUser(name) != null && DataProcessing.updateUser(name, password, role)) {
                User user = DataProcessing.searchUser(name);
                if (user != null && user.getName().equals(name) && user.getRole().equals(role) && user.getPassword().equals(password)) {
                    System.out.println("修改用户" + name + "信息成功!");
                } else {
                    System.out.println("修改失败!");
                }
            } else {
                System.out.println("修改失败!");
            }
        } catch (SQLException e) {
            System.out.println("修改失败!");
        }
    }

    private void delUser() {

        // TODO: 删除用户功能
        System.out.println("--------删除用户--------");
        System.out.println("请输入用户名：");
        String name = in.next();
        try {
            if (DataProcessing.delete(name) && DataProcessing.searchUser(name) == null) {
                System.out.println("删除用户" + name + "成功!");
            } else {
                System.out.println("删除失败!");
            }
        } catch (SQLException e) {
            System.out.println("删除失败!");
        }
    }

    private void addUser() {

        // TODO: 新增用户功能
        String name, password, role;
        System.out.println("--------新增用户--------");
        System.out.println("请输入用户名:");
        name = in.next();
        System.out.println("请输入口令：");
        password = in.next();
        System.out.println("请输入角色：");
        role = in.next();
        try {
            if (DataProcessing.insertUser(name, password, role) && DataProcessing.searchUser(name) != null) {
                System.out.println("新增用户：role:" + role + " " + "name:" + name + "成功!");
            } else {
                System.out.println("新增用户失败!");
            }
        } catch (SQLException e) {
            System.out.println("新增用户失败!");
        }
    }

    private void listUser() {

        // TODO: 显示所有用户信息功能
    }

    @Override
    public void showMenu() {

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
                    System.exit(0);
                default:
                    System.out.println("输入错误");
                    break;
            }
        }
    }
}
