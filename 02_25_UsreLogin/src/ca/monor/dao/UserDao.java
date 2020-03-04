package ca.monor.dao;

import ca.monor.domain.User;
import ca.monor.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

// 用于和服务器进行交互
public class UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public User login(User user) {
        try {
            // 1. 编写 SQL 语句，? 表示占位符，会动态加载 template 的 @Nullable Object... args 参数
            // 必须一一对应
            String sql = "SELECT *FROM `USER` WHERE username=? && PASSWORD=?";
            // 2. 调用 query 工具
            return template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class),
                    user.getUsername(), user.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
