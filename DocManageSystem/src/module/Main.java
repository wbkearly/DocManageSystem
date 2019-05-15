package module;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String tip_system = "档案管理系统";
        String tip_menu = "请选择菜单：";
        String tip_exit = "系统退出，谢谢使用！";
        String infos = "****欢迎进入" + tip_system + "****" +
                "\n       1.登录" +
                "\n       2.退出" +
                "\n*******************************";
        System.out.println(infos);
        System.out.println(tip_menu);
        Scanner in = new Scanner(System.in);
        switch (in.nextInt()) {
            case 1:
                String name, password;
                System.out.println("请输入用户名:");
                name = in.next();
                System.out.println("请输入口令:");
                password = in.next();
                User user = null;
                try {
                    user = DataProcessing.search(name, password);
                } catch (SQLException e) {
                    System.out.println("登录失败");
                }
                if (user == null) {
                    System.out.println("用户名或口令错误\n");
                } else {
                    user.showMenu();
                }
                break;
            case 2:
                System.out.println(tip_exit);
                System.exit(0);
        }
    }
}
