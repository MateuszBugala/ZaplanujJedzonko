package pl.coderslab.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        do wykorzystania później:
//        response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");
//        request.setCharacterEncoding("UTF-8");
//
//        request.getParameter("name");
//        request.getParameter("surname");
//        request.getParameter("email");
//        request.getParameter("password");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);

    }
}
