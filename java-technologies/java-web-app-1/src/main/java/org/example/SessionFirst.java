package org.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

public class SessionFirst extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String s = request.getParameter("uname");

        HttpSession session = request.getSession();
        session.setAttribute("uname",s);

        getServletContext().getRequestDispatcher("/session.jsp").forward(request, response);
    }

}
