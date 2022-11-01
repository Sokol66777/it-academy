package com.web.servlets;
import userImpl.UserDAOImpl;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.http.HttpSession;
import model.User;


@WebServlet (name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    public final UserDAO userDAO = new UserDAOImpl();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String name = request.getParameter("username");
        String password = request.getParameter("password");

        User user = null;

        try {
            user = userDAO.getByUsername(name);
        } catch (SQLException | PropertyVetoException e) {
            request.setAttribute("error",e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request,response);
        }


        if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("role", user.getRole());
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/welcome.jsp");
                rd.forward(request, response);
            } else {
                PrintWriter printWriter = response.getWriter();
                printWriter.write("Incorrect name or password");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.include(request, response);
                printWriter.close();
            }



    }
}
