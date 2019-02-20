package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cookie extends HttpServlet{

    // 最后访问网页的Cookies名称
    private String LAST_ACCESS = "last_access";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 得到浏览器传递过来的cookie信息
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        Cookie lastCookies = getCookieByName(cookies, LAST_ACCESS);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        if (lastCookies == null) { // 表示第一次访问
            writer.println("<H1>第一次访问</H1>");
            lastCookies = new Cookie(LAST_ACCESS, getCurrentDate());
            // 将当前的系统时间封装成Cookie
        } else { // 表示第二次访问
            writer.println("<H1>您上一次的访问时间为" + lastCookies.getValue() + "</H1>");
        }

//        lastCookies.setMaxAge(60 * 60 * 24);  // 让cookie持久化

        lastCookies = new Cookie(LAST_ACCESS, getCurrentDate());
        lastCookies.setMaxAge(0);        // 清空Cookie
        lastCookies.setPath("/");       // 设置路径

        response.addCookie(lastCookies); // 添加Cookies  服务端向客户端写信息
    }

	// 获取当前系统时间的字符串
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date());
    }

    // 从Cookie数组中得到指定名称的Cookie
    private Cookie getCookieByName(Cookie[] cookies, String name) {
        if (cookies == null) {
            return null;
        }
        for (Cookie c : cookies) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }
}