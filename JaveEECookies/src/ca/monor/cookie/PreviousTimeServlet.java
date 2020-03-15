package ca.monor.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/PreviousTimeServlet")
public class PreviousTimeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 如果是第一次登录
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str_date = sdf.format(date);
        Cookie cookie = new Cookie("last time: ", str_date);
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        response.getWriter().write("<h1>Hello, this is your first visit!</h1>");

        // 如果不是第一次登录
        // 获取上次的登录时间(保存在cookie中)
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            // 获取 cookie 的名称
            for (Cookie c : cookies) {
                String name = c.getName();
                // 一定用 last time 去 equals name
                if ("last time".equals(name)) {
                    String value = cookie.getValue();
                    response.getWriter().write
                            ("<h1>Hello, welcome back! your last " +
                                    "login time is: </h1>" + value);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
