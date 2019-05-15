package module;

import java.io.*;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

public abstract class User {

    private String name;
    private String password;
    private String role;
    String uploadPath = "D:\\OOP\\uploadFile\\";

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
        System.out.println("--------文件列表--------");
        try {
            Enumeration<Doc> e = DataProcessing.getAllDocs();
            Doc doc;
            if (e.hasMoreElements()) {
                System.out.println("ID\tcreator\ttimestamp\tdescription\tfilename");
                doc = e.nextElement();
                System.out.printf("%s\t%s\t%s\t%s\t%s\t%n", doc.getID(), doc.getCreator(), doc.getTimestamp(),
                        doc.getDescription(), doc.getFilename());
                while (e.hasMoreElements()) {
                    doc = e.nextElement();
                    System.out.printf("%s\t%s\t%s\t%s\t%s\t%n", doc.getID(), doc.getCreator(), doc.getTimestamp(),
                            doc.getDescription(), doc.getFilename());
                }
            } else {
                System.out.println("找不到任何档案信息!");
            }
        } catch (SQLException e) {
            System.out.println("从数据库获取档案信息失败!");
        }
    }

    // 下载文件
    void downloadFile() {

        // TODO: 下载文件功能
        System.out.println("--------文件下载--------");
        byte[] buffer = new byte[1024];
        String fileId;
        String fileName;
        String tempFileName;
        String targetFileName;
        System.out.println("请输入要下载的档案号:");
        fileId = in.next();
        try {
            Doc doc = DataProcessing.searchDoc(fileId);
            if (doc != null) {
                fileName = doc.getFilename();
                tempFileName = uploadPath + fileName;
                String downloadPath = "D:\\OOP\\downloadFile\\";
                targetFileName = downloadPath + fileName;
                File tempFile = new File(tempFileName);
                File targetFile = new File(targetFileName);
                FileInputStream fileInputStream;
                FileOutputStream fileOutputStream;
                fileInputStream = new FileInputStream(tempFile);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                fileOutputStream = new FileOutputStream(targetFile);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                System.out.println("文件下载中......");
                while (true) {
                    int byteRead = bufferedInputStream.read(buffer);
                    if (byteRead == -1)
                        break;
                    bufferedOutputStream.write(buffer, 0, byteRead);
                }
                bufferedInputStream.close();
                bufferedOutputStream.close();
                System.out.println("文件下载成功!");
            } else {
                System.out.println("未找到档案号为" + fileId + "的文件!");
            }
        } catch (SQLException e) {
            System.out.println("数据库查找文件失败!");
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在或路径名错误!");
        } catch (IOException e) {
            System.out.println("读写文件错误,文件下载失败!");
        }
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
