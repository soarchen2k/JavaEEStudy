package ca.monor.test;

import ca.monor.dao.UserDao;
import ca.monor.domain.User;
import org.junit.jupiter.api.Test;

public class TestUser {
    @Test
    public void testLogin() {
        User user = new User();
        user.setUsername("dal");
        user.setPASSWORD("123456");
        UserDao dao = new UserDao();
        User logged = dao.login(user);
        System.out.println(logged);
    }
}
