package org.gafur.web;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "GetGsonServlet", value = "/gson")
public class GetGsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        String helloWorld = jsonb.toJson("Hello world");

        response.setContentType("application/json");
        response.getWriter().append(helloWorld);
    }
}
