package ca.monor.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/responseServlet1")
public class responseServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 传统写法
//        response.setStatus(302);
//        response.setHeader("location","/JavaEEResponse/responseServlet2");

        // 动态获取虚拟目录
        response.sendRedirect(request.getContextPath() + "/responseServlet2");

        // 转发到其他网址
//        response.sendRedirect("https://www.google.com/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
