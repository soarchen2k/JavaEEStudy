package ca.monor.web;

import ca.monor.web.utils.DownloadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. 获取请求参数，文件名称
        String filename = request.getParameter("filename");

        // 2. 使用字节输入流，加载文件进入内存
        // 2.1 通过 ServletContext 找到文件所在的真实路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/img/" + filename);

        // 2.2 用字节流关联
        FileInputStream fileInputStream = new FileInputStream(realPath);

        // 3. 设置 response 响应头
        // 3.1 设置响应头类型：content-type
        String mimeType = servletContext.getMimeType(filename);
        response.setHeader("content-type", mimeType);

        // 解决文件名乱码问题
        // 1 获取 user=agent 请求头，也就是获取浏览器类型
        String agent = request.getHeader("user-agent");
        // 2 使用工具类方法编码文件名即可
        filename = DownloadUtils.getFileName(agent, filename);

        // 3.2 设置响应头打开方式：content-disposition
        response.setHeader("content-disposition", "attachment;filename=" + filename);

        // 4. 将输入流的数据写出到输出流中
        ServletOutputStream sos = response.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len = 0;

        // 流对象读取到 -1 时标志着已经读到了文件的末尾
        while ((len = fileInputStream.read(buff)) != -1) {
            sos.write(buff, 0, len);
        }
        fileInputStream.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
