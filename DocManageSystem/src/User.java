public class User {

    private String name;
    private String password;
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String name, String password, String role){
        this.name = name;
        this.password = password;
        this.role = role;
    }

    // 显示菜单
    public void showMenu(){}

    // 显示文件列表
    public void showFileList(){

        // TODO: 显示文件列表功能
    }

    // 下载文件
    public boolean downloadFile(){

        // TODO: 下载文件功能
        return true;
    }

    // 修改个人信息
    public boolean changeSelfInfo(){

        // TODO: 修改密码功能
        return true;
    }

    // 退出系统
    public void exitSystem(){

        // TODO：退出系统功能
    }

}
