package org.gafur.web;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

public class FirstServlet extends HttpServlet {
//    @Override
//    public void init() throws ServletException {
//        System.err.println("init()");
//        super.init();
//    }
//
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.err.println("service()");
//        super.service(req, resp);
//    }
//
//    @Override
//    public void destroy() {
//        System.err.println("destroy()");
//        super.destroy();
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HttpServletRequest methods
        // 1. Getting parameter from request, return type is String
        String param1 = request.getParameter("name");

        // 2. Getting header
        String headerValue = request.getHeader("Header-Name");

        // 3. Setting character encoding
        request.setCharacterEncoding("UTF-8");

        // HttpServletResponse methods

        // 1. Setting content type
        response.setContentType("text/html");
        response.getWriter().append("Hello, world!!!");

        // 2. Getting writer: writing character text
//        PrintWriter printWriter = response.getWriter();

        // 3. Getting output stream: writing binary data
//        ServletOutputStream servletOutputStream = response.getOutputStream();
    }
}
