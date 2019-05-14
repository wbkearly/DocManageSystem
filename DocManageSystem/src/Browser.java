import java.util.Scanner;

public class Browser extends User {

    String tip_menu = "****欢迎进入系统管理员菜单****";
    String tip_operation = "请选择操作：";
    String infos = "        1.下载文件\n"
            + "        2.文件列表\n"
            + "        3.修改密码\n"
            + "        4.退    出\n"
            + "*********************************\n";

    Scanner in = new Scanner(System.in);

    public Browser(String name, String password, String role) {
        super(name, password, role);
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
                    downloadFile();
                    break;
                case 2:
                    showFileList();
                    break;
                case 3:
                    changeSelfInfo();
                    break;
                case 4:
                    exitSystem();
                    break;
                default:
                    System.out.println("输入错误");
                    break;
            }
        }
    }
}