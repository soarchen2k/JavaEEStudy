package ca.monor.test;

import ca.monor.dao.UserDao;
import ca.monor.dao.impl.UserDaoImpl;
import ca.monor.domain.User;
import org.junit.Test;

import java.util.List;

public class userDaoTest {

    @Test
    public void testFindAll() {
        UserDao dao = new UserDaoImpl();
        List<User> users = dao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindUserBuUserNameAndPassword() {

        User user = new User();
        user.setPassword("111111");
        user.setUsername("dal");

        System.out.println(new UserDaoImpl().findUserBuUserNameAndPassword
                (user.getUsername(), user.getPassword()));
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setName("Lynn");
        user.setGender("F");
        user.setAge(30);
        user.setAddress("trt");
        user.setQq("12323456");
        user.setEmail("lynn@gmail.com");
        UserDao dao = new UserDaoImpl();
        for (int i = 0; i < 40; i++) {
            dao.add(user);
        }
    }

    @Test
    public void testDelete() {
        new UserDaoImpl().delete(13);
    }

    @Test
    public void testFindUserById() {
        System.out.println(new UserDaoImpl().findUserById(1));
    }

    @Test
    public void testUpdate() {
        User user = new UserDaoImpl().findUserById(78);
        user.setName("Marie");
        user.setAge(25);
        user.setAddress("mtl");
        user.setQq("7654321");
        user.setEmail("marie.lepent@gmail.com");
        new UserDaoImpl().update(user);
    }
}












