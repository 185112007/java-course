package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Fourth", urlPatterns = {"/forth"})
public class FourthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bigData = "BIG DATA";
        request.setAttribute("key", bigData);
        getServletContext().getRequestDispatcher("/sample_data.jsp").forward(request, response);
    }
}
