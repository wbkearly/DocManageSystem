public class Administrator extends User {

    public Administrator(String name, String password, String role) {
        super(name, password, role);
    }

    public boolean changeUserInfo() {

        // TODO: 修改所有用户信息功能
        return true;
    }

    public boolean delUser() {

        // TODO: 删除用户功能
        return true;
    }

    public boolean addUser() {

        // TODO: 新增用户功能
        return true;
    }

    public void listUser() {

        // TODO: 显示所有用户信息功能
    }

    @Override
    public void showMenu() {

        // TODO: 显示菜单功能
    }
}
