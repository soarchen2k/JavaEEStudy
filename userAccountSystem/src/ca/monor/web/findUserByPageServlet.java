package ca.monor.web;

import ca.monor.domain.PageBean;
import ca.monor.domain.User;
import ca.monor.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class findUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. 获取参数
        // 1.1 获取页面参数
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");

        if (currentPage == null || "".equals(currentPage)) { // 尽量写上判空条件
            currentPage = "1";
        }

        if (rows == null || "".equals(rows)) {
            rows = "5";
        }

        Map<String, String[]> conditions = request.getParameterMap();
        // 2. 向后台请求数据
        PageBean<User> pageBean = new UserServiceImpl().findUserByPage(currentPage, rows, conditions);

        // 3. 将装载好的数据传入域中，供前端调用
        request.setAttribute("pb", pageBean);
        request.setAttribute("condition", conditions);
        request.setAttribute("currentPage", currentPage);

        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
