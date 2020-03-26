package ca.monor.service;

import ca.monor.domain.User;

import java.util.List;

public interface userService {

    public List<User> findAll();

    // 传入 username 和 password 只是为了前端取数据方便，一般应该传入封装好的 User
    // 大公司会传入 uservo 或 useravo 对象，在这个对象中只有 username 和 password
    // 便于进一步的精简代码和重构系统

    public User login(String username, String password);

    public void addUser(User user);

    public void deleteUser(int id);

    public User findUserById(int id);

    public void updateUser(User user);
}
