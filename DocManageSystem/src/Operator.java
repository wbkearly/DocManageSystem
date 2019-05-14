import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class Operator extends User {

    private Scanner in = new Scanner(System.in);

    Operator(String name, String password, String role) {
        super(name, password, role);
    }

    private void uploadFile() {

        // TODO: 上传文件功能
        System.out.println("--------上传文件--------");
        String sourceFileName;
        String ID;
        String description;
        System.out.println("请输入源文件名(完整路径):");
        sourceFileName = in.next();
        System.out.println("请输入档案号:");
        ID = in.next();
        System.out.println("请输入档案描述:");
        description = in.next();

        byte[] buffer = new byte[1024];

        // 获取当前文件
        File tempFile = new File(sourceFileName);
        // 获取当前文件名
        String fileName = tempFile.getName();
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(tempFile));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadPath + fileName));
            System.out.println("文件上传中...");
            while (true) {
                int byteRead = bufferedInputStream.read(buffer);
                if (byteRead == -1)
                    break;
                bufferedOutputStream.write(buffer, 0, byteRead);
            }
            bufferedInputStream.close();
            bufferedOutputStream.close();
            System.out.println("文件上传成功!");
            String creator = this.getName();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (!DataProcessing.insertDoc(ID, creator, timestamp, description, fileName)) {
                tempFile = new File(uploadPath + fileName);
                if(tempFile.delete()) {
                    System.out.println("文件删除成功!");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在或路径名错误!");
        } catch (SQLException e) {
            System.out.println("向数据库写入档案信息出错!");
        } catch (IOException e) {
            System.out.println("读写文件出错!");
        }

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