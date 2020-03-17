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
        // 如果不是第一次登录
        // 获取上次的登录时间(保存在cookie中)
        Cookie[] cookies = request.getCookies();
        // 设置一个开关，控制是否是第一次访问网站
        boolean flag = false; //表示没有 cookie 的名字为 lastLoginTime
        if (cookies != null && cookies.length > 0) {
            // 获取 cookie 的名称
            for (Cookie c : cookies) {
                String name = c.getName();
                // 一定用 last time 去 equals name
                if ("lastLoginTime".equals(name)) {
                    // 把 flag 置为 true
                    flag = true;
                    String str_date = strDate();
                    response.addCookie(new Cookie("lastLoginTime", str_date));

                    String value = c.getValue();
                    response.getWriter().write
                            ("<h1>Hello, welcome back! your last " +
                                    "login time is: " + value + "</h1>");
                    break;
                }
            }
        }

        // 如果是第一次登录
        if (flag == false || cookies == null || cookies.length == 0) {
            String str_date = strDate();
            Cookie cookie = new Cookie("lastLoginTime", str_date);
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);
            response.getWriter().write("<h1>Hello, this is your first visit!</h1>");
        }
    }

    private String strDate() {
        // Date 对象建立一个时间日期对象
        // SimpleDateFormat 对象可以把获取到的日期时间，格式化为我们需要的格式向外输出
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        return sdf.format(date);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
