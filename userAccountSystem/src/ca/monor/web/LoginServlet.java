package ca.monor.web;

import ca.monor.domain.User;
import ca.monor.service.UserService;
import ca.monor.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 1. 先获取验证码
        String verifycodeUser = request.getParameter("verifycode");

        // 2. 验证获取的验证码的正确性
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");

        // 3. 为了不重复获取验证码，需要把当前验证码从域中删除掉，保证验证码的唯一化
        session.removeAttribute("CHECKCODE_SERVER");

        // 4. 验证校验码
        if (checkcode_server != null
                && checkcode_server.equalsIgnoreCase(verifycodeUser)) {
            // 验证码正确，开始验证用户名和密码
            User logged = new UserServiceImpl().login(username, password);

            if (logged != null) { // 登录成功
                // 把 user 对象放回域当中
                session.setAttribute("user", logged);
                // 跳转到首页
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {  // 登录失败
                request.setAttribute("login_msg", "Username or password wrong");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

        } else {

            // 验证码不正确，需要给用户返回一个错误信息
            request.setAttribute("login_msg", "Check Code Error!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
