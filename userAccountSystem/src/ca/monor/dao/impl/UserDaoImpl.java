package ca.monor.dao.impl;

import ca.monor.dao.UserDao;
import ca.monor.domain.User;
import ca.monor.util.JDBCUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        String sql = "INSERT INTO `user` VALUES(null, ?, ?, ?, ?, ?, ?, null, null);";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress()
                , user.getQq(), user.getEmail());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM `user` WHERE id=?;";
        template.update(sql, id);
    }

    @Override
    public User findUserById(int id) {
        String sql = "SELECT * FROM `user` WHERE id = ?;";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE `user` SET `name`=?, gender=?, age=?, address=?, qq=?, email=? WHERE id=?;";
        template.update(sql, user.getName(), user.getGender(), user.getAge()
                , user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "SELECT COUNT(*) FROM `user` WHERE 1=1 ";
        StringBuilder stringBuilder = new StringBuilder(sql);

        // 遍历 Map
        Set<String> keySet = condition.keySet();
        // 定义一个参数的集合
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {

            // 过滤分页信息
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }

            // 获取查询条件
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                // 有值
                stringBuilder.append(" && ").append(key).append(" like ? ");
                params.add("%" + value + "%");  // 此处是 ? 中的条件值，%是模糊查询所需的语法
            }
        }

        return template.queryForObject(stringBuilder.toString(),
                Integer.class, params.toArray()); // API 要求传可变参，此处传入数组
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "SELECT * FROM `user` WHERE 1=1 ";
        StringBuilder stringBuilder = new StringBuilder(sql);

        // 遍历 Map
        Set<String> keySet = condition.keySet();
        // 定义一个参数的集合
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {
            // 过滤分页信息
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }

            // 获取查询条件
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                // 有值
                stringBuilder.append(" && ").append(key).append(" like ? ");
                params.add("%" + value + "%");  // 此处是 ? 中的条件值，%是模糊查询所需的语法
            }
        }

        stringBuilder.append(" limit ?, ? ");
        params.add(start);
        params.add(rows);
        return template.query(stringBuilder.toString(),
                new BeanPropertyRowMapper<>(User.class), params.toArray());
    }
}
