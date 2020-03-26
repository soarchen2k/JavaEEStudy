package ca.monor.dao.impl;

import ca.monor.dao.UserDao;
import ca.monor.domain.User;
import ca.monor.util.JDBCUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    // 拿到数据源
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        // 1. 编写 sql
        String sql = "SELECT * FROM `user`;";
        // template.query 方法可以返回一个列表
        return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User findUserBuUserNameAndPassword(String username, String password) {
        String sql = "SELECT * FROM `user` WHERE username=? && `password`=?;";

        // template.queryForObject 方法返回一个对象
        User user;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return user;
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO `user` VALUES(null, ?, ?, ?, ?, ?, ?, null, null)";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress()
                , user.getQq(), user.getEmail());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM `user` WHERE id=?";
        template.update(sql, id);
    }

    @Override
    public User findUserById(int id) {
        String sql = "SELECT * FROM `user` WHERE id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE `user` SET `name`=?, gender=?, age=?, address=?, qq=?, email=? WHERE id=?;";
        template.update(sql, user.getName(), user.getGender(), user.getAge()
                , user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }
}
