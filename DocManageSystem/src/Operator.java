import java.util.Scanner;

public class Operator extends User {

    private Scanner in = new Scanner(System.in);

    Operator(String name, String password, String role) {
        super(name, password, role);
    }

    private void uploadFile() {

        // TODO: 上传文件功能
        System.out.println("--------上传文件--------");
        System.out.println("请输入源文件名:");
        in.next();
        System.out.println("请输入档案号:");
        in.next();
        System.out.println("请输入档案描述:");
        in.next();
        System.out.println("上传文件......");
        System.out.println("上传成功!");
    }

    @Override
    public void showMenu() {

        String tip_menu = "****欢迎进入档案录入员菜单****";
        String tip_operation = "请选择操作：";
        String infos = "        1.上传文件\n"
                + "        2.下载文件\n"
                + "        3.文件列表\n"
                + "        4.修改密码\n"
                + "        5.退    出\n"
                + "*********************************\n";

        // TODO: 显示菜单功能
        while (true) {

            System.out.println(tip_menu);
            System.out.println(infos);
            System.out.println(tip_operation);

            switch (in.nextInt()) {
                case 1:
                    uploadFile();
                    break;
                case 2:
                    downloadFile();
                    break;
                case 3:
                    showFileList();
                    break;
                case 4:
                    changeSelfInfo();
                    break;
                case 5:
                    exitSystem();
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入错误");
                    break;
            }
        }
    }
}