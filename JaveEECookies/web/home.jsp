<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: soarchen
  Date: 2020-03-18
  Time: 9:25 下午
  To change this template use File | Settings | File Templates.

  把 servlet 中的内容引入jsp，并使用 html 代码来书写要输出的内容
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Previous Visit</title>
</head>
<body>
<%
    // 如果不是第一次登录
    // 获取上次的登录时间(保存在cookie中)
    Cookie[] cookies = request.getCookies();
    // 设置一个开关，控制是否是第一次访问网站
    boolean flag = false; //表示没有 cookie 的名字为 lastLoginTime
    String value = null;
    if (cookies != null && cookies.length > 0) {
        // 获取 cookie 的名称
        for (Cookie c : cookies) {
            String name = c.getName();
            // 一定用 last time 去 equals name
            if ("lastLoginTime".equals(name)) {
                // 把 flag 置为 true
                flag = true;
                String str_date = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
                response.addCookie(new Cookie("lastLoginTime", str_date));

                value = c.getValue();
//                response.getWriter().write
//                        ("<h1>Hello, welcome back! your last " +
//                                "login time is: " + value + "</h1>");
%>

<h1>
    Hello, welcome back! your last login time is:  <%=value%>
</h1>

<%
                break;
            }
        }
    }
    // 如果是第一次登录
    if (!flag || cookies == null || cookies.length == 0) {
        String str_date = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
        Cookie cookie = new Cookie("lastLoginTime", str_date);
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        %>

        <h1>Hello, this is your first visit!</h1>

<%
    }
%>
</body>
</html>
