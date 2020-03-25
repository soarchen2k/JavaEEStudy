package ca.monor.test;

import ca.monor.dao.UserDao;
import ca.monor.domain.User;
import org.junit.Test;


public class TestUser {
    @Test
    public void testLogin() {
        User user = new User();
        user.setUsername("dal");
        user.setPassword("12345");
        User tony = new User();
        tony.setPassword("111111");
        tony.setUsername("tony");

        // 创建一个 UserDao ，用于和服务器交互
        UserDao dao = new UserDao();
        User logged1 = dao.login(user);
        User logged2 = dao.login(tony);
        System.out.println(logged1 + "logged");
        System.out.println(logged2 + "logged");
    }
}
