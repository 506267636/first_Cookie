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

    // ��������ҳ��Cookies����
    private String LAST_ACCESS = "last_access";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // �õ���������ݹ�����cookie��Ϣ
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        Cookie lastCookies = getCookieByName(cookies, LAST_ACCESS);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        if (lastCookies == null) { // ��ʾ��һ�η���
            writer.println("<H1>��һ�η���</H1>");
            lastCookies = new Cookie(LAST_ACCESS, getCurrentDate());
            // ����ǰ��ϵͳʱ���װ��Cookie
        } else { // ��ʾ�ڶ��η���
            writer.println("<H1>����һ�εķ���ʱ��Ϊ" + lastCookies.getValue() + "</H1>");
        }

//        lastCookies.setMaxAge(60 * 60 * 24);  // ��cookie�־û�

        lastCookies = new Cookie(LAST_ACCESS, getCurrentDate());
        lastCookies.setMaxAge(0);        // ���Cookie
        lastCookies.setPath("/");       // ����·��

        response.addCookie(lastCookies); // ���Cookies  �������ͻ���д��Ϣ
    }

	// ��ȡ��ǰϵͳʱ����ַ���
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date());
    }

    // ��Cookie�����еõ�ָ�����Ƶ�Cookie
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