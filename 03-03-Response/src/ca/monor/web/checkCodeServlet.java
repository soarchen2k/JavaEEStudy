package ca.monor.web;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class checkCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 定义图片的尺寸
        int width = 100;
        int height = 50;

        // 1 创建图片（包含验证码）
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 2 美化图片
        // 背景颜色的填充
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        // 边框
        graphics.drawRect(0, 0, width - 1, height - 1);
        graphics.setColor(Color.RED);

        // 3 图片生成
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random random = new Random();
        for (int i = 1; i < 5; i++) {
            int index = random.nextInt(str.length());
            char ch = str.charAt(index);
            graphics.drawString(ch + "", width / 5 * i, 40);
        }

        // 4 图片输出到前端
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
