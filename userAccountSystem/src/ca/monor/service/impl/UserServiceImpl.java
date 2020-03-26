package ca.monor.service.impl;

import ca.monor.dao.UserDao;
import ca.monor.dao.impl.UserDaoImpl;
import ca.monor.domain.User;
import ca.monor.service.UserService;

import java.util.List;

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
}
