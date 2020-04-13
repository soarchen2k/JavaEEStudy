package ca.monor.dao;

import ca.monor.domain.User;
import java.util.List;
import java.util.Map;

/**
 * DAO 层，即数据库访问层、持久层，Data Access Object
 * 所有的 sql 语句都在这一层实现
 * 在接口中先定义所有需要实现的功能
 * 接口中定义的方法就是我们要实现的功能
 */

public interface UserDao {
    public List<User> findAll();

    // 传入 username 和 password 只是为了前端取数据方便，一般应该传入封装好的 User
    // 大公司会传入 uservo 或 useravo 对象，在这个对象中只有 username 和 password
    // 便于进一步的精简代码和重构系统

    public User findUserBuUserNameAndPassword(String username, String password);

    public void add(User user);

    public void delete(int id);

    public User findUserById(int id);

    public void update(User user);

    public int findTotalCount(Map<String, String[]> condition);

    public List<User> findByPage(int start, int rows, Map<String, String[]> condition);


}
