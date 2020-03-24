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
        user.setName("tony");
        user.setGender("M");
        user.setAge(29);
        user.setAddress("trt");
        user.setQq("123456");
        user.setEmail("tony@gmail.com");
        UserDao dao = new UserDaoImpl();
        dao.add(user);
    }

    @Test
    public void testDelete() {
        new UserDaoImpl().delete(13);
    }

    @Test
    public void testFindUserById() {
        System.out.println(new UserDaoImpl().findUserById(13));
    }
}
