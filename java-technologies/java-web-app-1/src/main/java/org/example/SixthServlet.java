package org.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SixthServlet", value = "/sixth")
public class SixthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = "BIG_DATA";
        getServletContext().getRequestDispatcher("/sample_jstl.jsp").forward(request, response);
    }
}
