<%--
  Created by IntelliJ IDEA.
  User: soarchen
  Date: 2020-03-14
  Time: 11:12 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%
    // 这里定义的变量只能在这个尖括号之内使用
    int i = 10;
    // JSP 三个常用内置对象 request   response
    // out(字符输出流，相当于 response.getWriter())

    // 获取项目的虚拟路径
    out.print("Hello! JSP! Current path is : " + request.getContextPath() + " " + i + " " + a);
  %>

  <%! // 可以定义成员变量，这里定义的成员变量可以在整个页面中使用
    int a = 20;
  %>

  <%=
  "代码"
  %>


  </body>
</html>
