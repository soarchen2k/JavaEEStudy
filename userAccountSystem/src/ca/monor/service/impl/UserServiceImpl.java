package ca.monor.service.impl;

import ca.monor.dao.UserDao;
import ca.monor.dao.impl.UserDaoImpl;
import ca.monor.domain.PageBean;
import ca.monor.domain.User;
import ca.monor.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User login(String username, String password) {
        return dao.findUserBuUserNameAndPassword(username, password);
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(int id) {
        dao.delete(id);
    }

    @Override
    public User findUserById(int id) {
        return dao.findUserById(id);
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public PageBean<User> findUserByPage(String start, String pageRows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(start);
        int rows = Integer.parseInt(pageRows);

        if (currentPage <= 0) {
            currentPage = 1;
        }

        PageBean<User> pageBean = new PageBean<>();

        // 设置 PageBean 的参数
        pageBean.setCurrentPage(currentPage);
        pageBean.setRow(rows);
        int totalCount = dao.findTotalCount(condition);
        pageBean.setTotalCount(totalCount);
        int begin = (currentPage - 1) * rows;
        pageBean.setList(dao.findByPage(begin, rows, condition));
        int totalPages = totalCount % rows == 0 ? totalCount / rows : (totalCount / rows) + 1;
        pageBean.setTotalPages(totalPages);

        return pageBean;
    }
}
