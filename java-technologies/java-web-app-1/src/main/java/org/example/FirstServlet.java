package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Getting parameter from request
        String name = request.getParameter("name");

        // Prints formatted representations of objects to a text-output stream.
        PrintWriter pr = response.getWriter();

        // Provides an output stream for sending binary data to the client.
        //ServletOutputStream os = response.getOutputStream();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        if (name != null && !name.trim().isEmpty()){
            pr.append("<strong>Hello, " + name + " !!!</strong>");
        }else{
            pr.append("<strong>Hello, world !!!</strong>");
        }

    }
}
