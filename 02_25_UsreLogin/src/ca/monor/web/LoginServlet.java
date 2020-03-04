package ca.monor.web;

import ca.monor.dao.UserDao;
import ca.monor.domain.User;
import org.springframework.beans.BeanUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 获取请求的方式
 get
 post
 */

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

//        // 获取请求参数
//        Map<String, String[]> map = request.getParameterMap();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//
//        // 封装对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);

        // 调用 dao 层 api 实现登录
        UserDao dao = new UserDao();
        User logged = dao.login(loginUser);

        if (logged == null) {
            // 跳转：转发，重定向
            // 转发：用户只需要进行一次请求，服务器与前端只发送一次 response，转发是发送在服务器内部的
            // 重定向：用户发送请求，服务器返回一次 response 并发送302指令和重定向方向，用户收到302请求
            //       继续请求其他的 servlet，直到找到所需的信息

            // 服务器内部的转发只需要填写相对地址
            request.getRequestDispatcher("/failServlet").forward(request, response);
        } else {
            request.setAttribute("user", logged);
            request.getRequestDispatcher("/successServlet").forward(request, response);
        }
    }
}
