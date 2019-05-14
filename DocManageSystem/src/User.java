import java.sql.SQLException;
import java.util.Scanner;

public abstract class User {

    private String name;
    private String password;
    private String role;

    private Scanner in = new Scanner(System.in);

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    // 显示菜单
    public abstract void showMenu();

    // 显示文件列表
    void showFileList() {

        // TODO: 显示文件列表功能
        System.out.println("--------显示文件列表--------");
        System.out.println("文件列表...");
    }

    // 下载文件
    void downloadFile() {

        // TODO: 下载文件功能
        System.out.println("--------下载文件--------");
        System.out.println("请输入要下载的文件名:");
        String fileName = in.next();
        System.out.println("下载文件:" + fileName + "......");
        System.out.println("下载成功!");
    }

    // 修改个人信息
     void changeSelfInfo() {

        //  TODO: 修改密码功能
        System.out.println("--------修改(本人)密码--------");
        System.out.println("请输入新口令：");
        String password = in.next();
        try {
            if (DataProcessing.update(name, password, role)) {
                this.password = password;
                System.out.println("修改密码成功!");
            } else {
                System.out.println("修改密码失败!");
            }
        } catch (SQLException e) {
            System.out.println("修改密码失败!");
        }
    }

    // 退出系统
    void exitSystem() {

        // TODO：退出系统功能
        System.out.println("系统退出，谢谢使用!");
        System.exit(0);
    }

}
